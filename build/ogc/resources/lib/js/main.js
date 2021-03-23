// JavaScript file to support the AngularJS-based front-end for Luciad's web services.

// Define an angular module for Luciad's web services front-end
var luciadWebServicesFrontEnd = angular.module('luciadWebServicesFrontEnd', ['ngRoute', 'ngSanitize']);

// Define the proper routing for the front-end
luciadWebServicesFrontEnd.config(['$routeProvider',
                                  function($routeProvider) {
                                    $routeProvider.
                                        when('/overview', {
                                          templateUrl: 'overview.html',
                                          controller: 'OverviewController'
                                        }).
                                        when('/wms', {
                                          templateUrl: 'service.html',
                                          controller: 'WMSController'
                                        }).
                                        when('/wmts', {
                                          templateUrl: 'service.html',
                                          controller: 'WMTSController'
                                        }).
                                        when('/wfs', {
                                          templateUrl: 'service.html',
                                          controller: 'WFSController'
                                        }).
                                        when('/wcs', {
                                          templateUrl: 'service.html',
                                          controller: 'WCSController'
                                        }).
                                        when('/csw', {
                                          templateUrl: 'service.html',
                                          controller: 'CSWController'
                                        }).
                                        when('/lts', {
                                          templateUrl: 'service.html',
                                          controller: 'LTSController'
                                        }).
                                        when('/lfs', {
                                          templateUrl: 'service.html',
                                          controller: 'LFSController'
                                        }).
                                        otherwise({
                                          redirectTo: '/overview'
                                        });
                                  }]);

//Define the controller for each route
luciadWebServicesFrontEnd.controller('OverviewController', function($scope) {
  // no specific controlling action yet
});

luciadWebServicesFrontEnd.controller('WMSController', function($scope, $http, $location) {
  loadService($scope, $http, $location, 'service/wms.json');
});

luciadWebServicesFrontEnd.controller('WFSController', function($scope, $http, $location) {
  loadService($scope, $http, $location, 'service/wfs.json');
});

luciadWebServicesFrontEnd.controller('WCSController', function($scope, $http, $location) {
  loadService($scope, $http, $location, 'service/wcs.json');
});

luciadWebServicesFrontEnd.controller('CSWController', function($scope, $http, $location) {
  loadService($scope, $http, $location, 'service/csw.json');
});

// Loads a new web service page based on the given parameters
function loadService(scope, http, location, serviceFile) {
  var serviceFileLocation = location.protocol() + "://" + location.host() + ":" + location.port() + "/LuciadOGCServices/" + serviceFile;
  http.get(serviceFileLocation).success(function(data) {
    scope.title = data.title;
    scope.description = data.description.replace("{{servletLocation}}", location.protocol() + "://" + location.host() + ":" + location.port());
    scope.baseUrl = location.protocol() + "://" + location.host() + ":" + location.port() + data.baseQuery;
    scope.requests = data.requests;
  });

  scope.load = function(baseUrl, request) {
    if (request.query != null) {
      loadGetRequest(scope, http, baseUrl + request.query);
    }
    else {
      loadPostRequest(scope, http, baseUrl, request.post);
    }
  }
}

// Loads a POST request and shows the result
function loadPostRequest(scope, http, requestUrl, postFile) {
  var postXml = loadXMLFromFile(postFile);
  var postXmlAsString = new XMLSerializer().serializeToString(postXml);

  scope.requestContent = "<a href=\"" + postFile + "\">XML request</a>";

  var responseTypeValue;
  if (postXmlAsString.indexOf("image") > -1) {
    responseTypeValue = "arraybuffer";
  }
  else {
    responseTypeValue = "string";
  }

  http.post(requestUrl, postXmlAsString, {headers: {'Content-Type': 'text/xml'}, responseType: responseTypeValue}).
  success(function(data, status, headers, config) {
    var contentType = headers()['content-type'];
    if (contentType.indexOf("image") > -1) {
      var imageAsBase64 = base64ArrayBuffer(data);
      scope.responseContent = "<img src=\"data:image/png;base64, " + imageAsBase64 + "\"/>";
    }
    else {
      scope.responseContent = formatXml(data);
    }
  }).
  error(function(data, status, headers, config) {
    var dataAsString;
    if(responseTypeValue === "arraybuffer") {
      dataAsString = String.fromCharCode.apply(null, new Uint8Array(data));
    }
    else {
      dataAsString = data;
    }
    scope.responseContent = formatXml(dataAsString);
  });
}

// Loads a GET request and shows the result
function loadGetRequest(scope, http, requestUrl) {
  var requestContentAsString;
  if (requestUrl.length > 145) {
    requestContentAsString = requestUrl.substring(0, 145) + "<br>" + requestUrl.substring(145, requestUrl.length);
  }
  else {
    requestContentAsString = requestUrl;
  }

  scope.requestContent = "<a href=\"" + requestUrl + "\">" + requestContentAsString + "</a>";

  var responseTypeValue;
  if (requestUrl.indexOf("image") > -1) {
    responseTypeValue = "arraybuffer";
  }
  else {
    responseTypeValue = "string";
  }

  http.get(requestUrl, {responseType: responseTypeValue}).
  success(function(data, status, headers, config) {
    var contentType = headers()['content-type'];
    if (contentType.indexOf("image") > -1) {
      var imageAsBase64 = base64ArrayBuffer(data);
      scope.responseContent = "<img src=\"data:image/png;base64, " + imageAsBase64 + "\"/>";
    }
    else {
      scope.responseContent = formatXml(data);
    }
  }).
  error(function(data, status, headers, config) {
    var dataAsString;
    if(responseTypeValue === "arraybuffer") {
      dataAsString = String.fromCharCode.apply(null, new Uint8Array(data));
    }
    else {
      dataAsString = data;
    }
    scope.responseContent = formatXml(dataAsString);
  });
}

// Properly formats XML using a predefined stylesheet
function formatXml(data) {
  var xml = loadXMLFromString(data);

  // code for IE
  if (window.ActiveXObject || "ActiveXObject" in window) {
    var xslTemplate = new ActiveXObject("Msxml2.XSLTemplate");
    var xslDoc = new ActiveXObject("Msxml2.FreeThreadedDOMDocument.3.0");
    xslDoc.async = false;
    xslDoc.load("xsl/prettyprint.xsl");

    if (xslDoc.parseError.errorCode == 0) {
      xslTemplate.stylesheet = xslDoc;
      var xsltProcessor = xslTemplate.createProcessor();
      xsltProcessor.input = xml;
      xsltProcessor.transform();
      return xsltProcessor.output;
    }
  }
  // code for Chrome, Firefox, Opera, etc.
  else if (document.implementation && document.implementation.createDocument) {
    var xsl = loadXMLFromFile("xsl/prettyprint.xsl");
    var xsltProcessor = new XSLTProcessor();
    xsltProcessor.importStylesheet(xsl);

    var ownerDocument = document.implementation.createDocument("", "", null);
    var resultDocument = xsltProcessor.transformToFragment(xml, ownerDocument);

    return new XMLSerializer().serializeToString(resultDocument);
  }
}

// Loads XML data contained in a string
function loadXMLFromString(string) {
  if (window.DOMParser) {
    var parser = new window.DOMParser();
    var xmlDoc = parser.parseFromString(string, "text/xml");
    return xmlDoc;
  }
  else if (typeof window.ActiveXObject != "undefined" && new window.ActiveXObject("Microsoft.XMLDOM")) {
    var xmlDoc = new window.ActiveXObject("Microsoft.XMLDOM");
    xmlDoc.async = "false";
    xmlDoc.loadXML(string);
    return xmlDoc;
  }
  else {
    return null;
  }
}

// Loads XML data from a file with the given filename
function loadXMLFromFile(filename) {
  var xhttp = getXMLHttpRequest();
  if (xhttp != null) {
    xhttp.open("GET", filename, false);
    xhttp.send();
    return xhttp.responseXML;
  }
  else {
    window.console.log("AJAX (XMLHTTP) not supported.");
  }
}

// Gets an XML HttpRequest
function getXMLHttpRequest() {
  if (window.XMLHttpRequest) {
    return new window.XMLHttpRequest;
  }
  else {
    try {
      return new ActiveXObject("MSXML2.XMLHTTP.3.0");
    }
    catch (ex) {
      return null;
    }
  }
}

// Encodes a binary array buffer as Base64
function base64ArrayBuffer(arrayBuffer) {
  var base64 = ''
  var encodings = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'

  var bytes = new Uint8Array(arrayBuffer)
  var byteLength = bytes.byteLength
  var byteRemainder = byteLength % 3
  var mainLength = byteLength - byteRemainder

  var a, b, c, d
  var chunk

  // Main loop deals with bytes in chunks of 3
  for (var i = 0; i < mainLength; i = i + 3) {
    // Combine the three bytes into a single integer
    chunk = (bytes[i] << 16) | (bytes[i + 1] << 8) | bytes[i + 2]

    // Use bitmasks to extract 6-bit segments from the triplet
    a = (chunk & 16515072) >> 18 // 16515072 = (2^6 - 1) << 18
    b = (chunk & 258048) >> 12 // 258048   = (2^6 - 1) << 12
    c = (chunk & 4032) >> 6 // 4032     = (2^6 - 1) << 6
    d = chunk & 63               // 63       = 2^6 - 1

    // Convert the raw binary segments to the appropriate ASCII encoding
    base64 += encodings[a] + encodings[b] + encodings[c] + encodings[d]
  }

  // Deal with the remaining bytes and padding
  if (byteRemainder == 1) {
    chunk = bytes[mainLength]

    a = (chunk & 252) >> 2 // 252 = (2^6 - 1) << 2

    // Set the 4 least significant bits to zero
    b = (chunk & 3) << 4 // 3   = 2^2 - 1

    base64 += encodings[a] + encodings[b] + '=='
  } else if (byteRemainder == 2) {
    chunk = (bytes[mainLength] << 8) | bytes[mainLength + 1]

    a = (chunk & 64512) >> 10 // 64512 = (2^6 - 1) << 10
    b = (chunk & 1008) >> 4 // 1008  = (2^6 - 1) << 4

    // Set the 2 least significant bits to zero
    c = (chunk & 15) << 2 // 15    = 2^4 - 1

    base64 += encodings[a] + encodings[b] + encodings[c] + '='
  }

  return base64
}