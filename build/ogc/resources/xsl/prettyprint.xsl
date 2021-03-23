<?xml version="1.0" encoding="ISO-8859-1"?>

<!-- This is an XSL Stylesheet to transform OGC/Luciad-related web service XML formats to HTML. The transformation does not cover every detail available in XML;
instead, it focuses on transforming a few items to show the big picture. It is used by the OGC web services sample front-end to show
the responses in HTML for a few example requests. -->
<xsl:stylesheet version="1.0" xmlns:wms="http://www.opengis.net/wms"
                xmlns:wfs="http://www.opengis.net/wfs"
                xmlns:wfs2="http://www.opengis.net/wfs/2.0"
                xmlns:wcs="http://www.opengis.net/wcs"
                xmlns:csw2="http://www.opengis.net/cat/csw/2.0.2"
                xmlns:csw3="http://www.opengis.net/cat/csw/3.0"
                xmlns:dc="http://purl.org/dc/elements/1.1/"
                xmlns:ows="http://www.opengis.net/ows"
                xmlns:ows11="http://www.opengis.net/ows/1.1"
                xmlns:ows2="http://www.opengis.net/ows/2.0"
                xmlns:gml="http://www.opengis.net/gml"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html"/>

  <!-- Template for OGC's WFS 1.1.0 Feature Collection XML format -->
  <xsl:template match="wfs:FeatureCollection">
    <div>
      <h3>Feature collection</h3>
    </div>

    <div>
      <ul>
        <xsl:for-each select="gml:featureMembers/*">
          <li>
            <xsl:value-of select="name(.)"/> with properties:
            <xsl:copy-of select="."/>
          </li>
        </xsl:for-each>
      </ul>
    </div>
  </xsl:template>

  <!-- Template for OGC's WFS 1.1.0 GetCapabilities response XML format -->
  <xsl:template match="wfs:WFS_Capabilities">
    <div>
      <h3>Service metadata and request overview</h3>
    </div>

    <div>
      <table>
        <tr>
          <td>Title:</td>
          <td>
            <xsl:value-of select="ows:ServiceIdentification/ows:Title"/>
          </td>
        </tr>
        <tr>
          <td>Source:</td>
          <td>
            <xsl:element name="a">
              <xsl:attribute name="href">
                <xsl:value-of select="ows:ServiceProvider/ows:ProviderSite/@*[local-name()='href']"/>
              </xsl:attribute>
              <xsl:value-of select="ows:ServiceProvider/ows:ProviderSite/@*[local-name()='href']"/>
            </xsl:element>
          </td>
        </tr>
        <tr>
          <td>Abstract:</td>
          <td>
            <xsl:value-of select="ows:ServiceIdentification/ows:Abstract"/>
          </td>
        </tr>
        <tr>
          <td>Fees:</td>
          <td>
            <xsl:value-of select="ows:ServiceIdentification/ows:Fees"/>
          </td>
        </tr>
        <tr>
          <td>Supported requests:</td>
          <td>
            <ul>
              <xsl:for-each select="ows:OperationsMetadata/*">
                <li>
                  <xsl:value-of select="@name"/>
                </li>
              </xsl:for-each>
            </ul>
          </td>
        </tr>
      </table>
    </div>

    <div>
      <h3>Feature types</h3>
    </div>

    <ul>
      <xsl:for-each select="wfs:FeatureTypeList/wfs:FeatureType">
        <li>
          <b>
            <xsl:value-of select="wfs:Title"/>
          </b>
          <div>
            <xsl:if test="wfs:Name">
              Name:
              <xsl:value-of select="wfs:Name"/>
            </xsl:if>
          </div>
        </li>
      </xsl:for-each>
    </ul>
  </xsl:template>

  <!-- Template for OGC's WFS 2.0 Feature Collection XML format -->
  <xsl:template match="wfs2:FeatureCollection">
    <div>
      <h3>Feature collection</h3>
    </div>

    <div>
      <ul>
        <xsl:for-each select="wfs2:member/*">
          <li>
            <xsl:value-of select="name(.)"/>
          </li>
        </xsl:for-each>
      </ul>
    </div>
  </xsl:template>

  <!-- Template for OGC's WFS 2.0 GetCapabilities response XML format -->
  <xsl:template match="wfs2:WFS_Capabilities">
    <div>
      <h3>Service metadata and request overview</h3>
    </div>

    <div>
      <table>
        <tr>
          <td>Title:</td>
          <td>
            <xsl:value-of select="ows11:ServiceIdentification/ows11:Title"/>
          </td>
        </tr>
        <tr>
          <td>Abstract:</td>
          <td>
            <xsl:value-of select="ows11:ServiceIdentification/ows11:Abstract"/>
          </td>
        </tr>
        <tr>
          <td>Fees:</td>
          <td>
            <xsl:value-of select="ows11:ServiceIdentification/ows11:Fees"/>
          </td>
        </tr>
        <tr>
          <td>Supported requests:</td>
          <td>
            <ul>
              <xsl:for-each select="ows11:OperationsMetadata/*">
                <li>
                  <xsl:value-of select="@name"/>
                </li>
              </xsl:for-each>
            </ul>
          </td>
        </tr>
      </table>
    </div>

    <div>
      <h3>Feature types</h3>
    </div>

    <ul>
      <xsl:for-each select="wfs2:FeatureTypeList/wfs2:FeatureType">
        <li>
          <b>
            <xsl:value-of select="wfs2:Title"/>
          </b>
          <div>
            <xsl:if test="wfs2:Name">
              Name:
              <xsl:value-of select="wfs2:Name"/>
            </xsl:if>
          </div>
        </li>
      </xsl:for-each>
    </ul>
  </xsl:template>

  <!-- Template for OGC's WCS 1.0.0 DescribeCoverage response XML format -->
  <xsl:template match="wcs:CoverageDescription">
    <div>
      <h3>Coverage offering description</h3>
    </div>

    <div>
      <table>
        <tr>
          <td>Name:</td>
          <td>
            <xsl:value-of select="wcs:CoverageOffering/wcs:name"/>
          </td>
        </tr>
        <tr>
          <td>Description:</td>
          <td>
            <xsl:value-of select="wcs:CoverageOffering/wcs:description"/>
          </td>
        </tr>
        <tr>
          <td>Abstract:</td>
          <td>
            <xsl:value-of select="wcs:CoverageOffering/wcs:label"/>
          </td>
        </tr>
        <tr>
          <td>Envelope:</td>
          <td>
            <xsl:value-of select="wcs:CoverageOffering/wcs:lonLatEnvelope"/>
          </td>
        </tr>
        <tr>
          <td>Supported CRSs:</td>
          <td>
            <ul>
              <xsl:for-each select="wcs:CoverageOffering/wcs:supportedCRSs/*">
                <li>
                  <xsl:value-of select="text()"/>
                </li>
              </xsl:for-each>
            </ul>
          </td>
        </tr>
        <tr>
          <td>Supported formats:</td>
          <td>
            <ul>
              <xsl:for-each select="wcs:CoverageOffering/wcs:supportedFormats/*">
                <li>
                  <xsl:value-of select="text()"/>
                </li>
              </xsl:for-each>
            </ul>
          </td>
        </tr>
        <tr>
          <td>Supported interpolations:</td>
          <td>
            <ul>
              <xsl:for-each select="wcs:CoverageOffering/wcs:supportedInterpolations/*">
                <li>
                  <xsl:value-of select="text()"/>
                </li>
              </xsl:for-each>
            </ul>
          </td>
        </tr>
      </table>
    </div>
  </xsl:template>

  <!-- Template for OGC's WCS 1.0.0 GetCapabilities response XML format -->
  <xsl:template match="wcs:WCS_Capabilities">
    <div>
      <h3>Service metadata and request overview</h3>
    </div>

    <div>
      <table>
        <tr>
          <td>Description:</td>
          <td>
            <xsl:value-of select="wcs:Service/wcs:description"/>
          </td>
        </tr>
        <tr>
          <td>Abstract:</td>
          <td>
            <xsl:value-of select="wcs:Service/wcs:label"/>
          </td>
        </tr>
        <tr>
          <td>Fees:</td>
          <td>
            <xsl:value-of select="wcs:Service/wcs:fees"/>
          </td>
        </tr>
        <tr>
          <td>Access constraints:</td>
          <td>
            <xsl:value-of select="wcs:Service/wcs:accessConstraints"/>
          </td>
        </tr>
        <tr>
          <td>Supported requests:</td>
          <td>
            <ul>
              <xsl:for-each select="wcs:Capability/wcs:Request/*">
                <li>
                  <xsl:value-of select="name(.)"/>
                </li>
              </xsl:for-each>
            </ul>
          </td>
        </tr>
      </table>
    </div>

    <div>
      <h3>Coverages</h3>
    </div>

    <ul>
      <xsl:for-each select="wcs:ContentMetadata/wcs:CoverageOfferingBrief">
        <li>
          <b>
            <xsl:value-of select="wcs:label"/>
          </b>
          <div>
            <xsl:if test="wcs:name">
              Name:
              <xsl:value-of select="wcs:name"/>
            </xsl:if>
          </div>
          <div>
            <xsl:if test="wcs:description">
              Description:
              <xsl:value-of select="wcs:description"/>
            </xsl:if>
          </div>
        </li>
      </xsl:for-each>
    </ul>
  </xsl:template>

  <!-- Template for OGC's WMS 1.3.0 GetCapabilities response XML format -->
  <xsl:template match="wms:WMS_Capabilities">

    <div>
      <h3>Service metadata and request overview</h3>
    </div>

    <div>
      <table>
        <tr>
          <td>Title:</td>
          <td>
            <xsl:value-of select="wms:Service/wms:Title"/>
          </td>
        </tr>
        <tr>
          <td>Name:</td>
          <td>
            <xsl:value-of select="wms:Service/wms:Name"/>
            <xsl:if test="wms:Service/wms:MaxWidth">
              (max. display size: <xsl:value-of select="wms:Service/wms:MaxWidth"/>x<xsl:value-of
                select="wms:Service/wms:MaxHeight"/>)
            </xsl:if>
          </td>
        </tr>
        <tr>
          <td>Source:</td>
          <td>
            <xsl:element name="a">
              <xsl:attribute name="href">
                <xsl:value-of select="wms:Service/wms:OnlineResource/@*[local-name()='href']"/>
              </xsl:attribute>
              <xsl:value-of select="wms:Service/wms:OnlineResource/@*[local-name()='href']"/>
            </xsl:element>
          </td>
        </tr>
        <tr>
          <td>Abstract:</td>
          <td>
            <xsl:value-of select="wms:Service/wms:Abstract"/>
          </td>
        </tr>
        <tr>
          <td>Contact:</td>
          <td>
            <xsl:apply-templates select="wms:Service/wms:ContactInformation"/>
          </td>
        </tr>
        <tr>
          <td>Access constraints:</td>
          <td>
            <xsl:value-of select="wms:Service/wms:AccessConstraints"/>
          </td>
        </tr>
        <tr>
          <td>Fees:</td>
          <td>
            <xsl:value-of select="wms:Service/wms:Fees"/>
          </td>
        </tr>
        <tr>
          <td>Supported requests:</td>
          <td>
            <ul>
              <xsl:for-each select="wms:Capability/wms:Request/*">
                <li>
                  <xsl:value-of select="name(.)"/>
                </li>
              </xsl:for-each>
            </ul>
          </td>
        </tr>
      </table>
    </div>

    <div>
      <h3>Layers</h3>
    </div>

    Root:
    <xsl:apply-templates select="wms:Capability/wms:Layer"/>

  </xsl:template>

  <!-- Template for OGC's WMS 1.3.0 layer definitions inside the GetCapabilities response XML format -->
  <xsl:template match="wms:Layer">

    <b>
      <xsl:value-of select="wms:Title"/>
    </b>

    <div>
      <xsl:if test="wms:Name">
        Name:
        <xsl:value-of select="wms:Name"/>
      </xsl:if>
    </div>
    <div>
      <xsl:if test="wms:Abstract">
        Abstract:
        <xsl:value-of select="wms:Abstract"/>
      </xsl:if>
    </div>

    <ul>
      <xsl:for-each select="wms:Layer">
        <li>
          <xsl:apply-templates select="."/>
        </li>
      </xsl:for-each>
    </ul>

  </xsl:template>

  <!-- Template for OGC's CSW 2.0.2 GetCapabilities response XML format -->
  <xsl:template match="csw2:Capabilities">
    <div>
      <h3>Service metadata and request overview</h3>
    </div>

    <div>
      <table>
        <tr>
          <td>Title:</td>
          <td>
            <xsl:value-of select="ows:ServiceIdentification/ows:Title"/>
          </td>
        </tr>
        <tr>
          <td>Abstract:</td>
          <td>
            <xsl:value-of select="ows:ServiceIdentification/ows:Abstract"/>
          </td>
        </tr>
        <tr>
          <td>Supported requests:</td>
          <td>
            <ul>
              <xsl:for-each select="ows:OperationsMetadata/ows:Operation">
                <li>
                  <xsl:value-of select="@name"/>
                </li>
              </xsl:for-each>
            </ul>
          </td>
        </tr>
      </table>
    </div>
  </xsl:template>

  <!-- Template for OGC's CSW 3.0.0 GetCapabilities response XML format -->
  <xsl:template match="csw3:Capabilities">
    <div>
      <h3>Service metadata and request overview</h3>
    </div>

    <div>
      <table>
        <tr>
          <td>Title:</td>
          <td>
            <xsl:value-of select="ows2:ServiceIdentification/ows2:Title"/>
          </td>
        </tr>
        <tr>
          <td>Abstract:</td>
          <td>
            <xsl:value-of select="ows2:ServiceIdentification/ows2:Abstract"/>
          </td>
        </tr>
        <tr>
          <td>Supported requests:</td>
          <td>
            <ul>
              <xsl:for-each select="ows2:OperationsMetadata/ows2:Operation">
                <li>
                  <xsl:value-of select="@name"/>
                </li>
              </xsl:for-each>
            </ul>
          </td>
        </tr>
      </table>
    </div>
  </xsl:template>

  <!-- Template for OGC's CSW 3.0.0 GetRecord response XML format -->
  <xsl:template match="csw3:GetRecordsResponse">
    <div>
      <h3>GetRecords response</h3>
      <p>Matched
        <xsl:value-of select="csw3:SearchResults/@numberOfRecordsMatched"/> records.
      </p>
    </div>
    <div>
      <table>
        <xsl:for-each select="csw3:SearchResults/csw3:Record">
          <tr>
            <xsl:apply-templates select="."/>
          </tr>
        </xsl:for-each>
      </table>
    </div>
  </xsl:template>

  <!-- Template for OGC's CSW 3.0.0 record XML format -->
  <xsl:template match="csw3:SummaryRecord | csw3:Record | csw3:BriefRecord">
    <div>
      <h4>Record
        <xsl:value-of select="dc:identifier"/>
      </h4>
      <ul>
        <xsl:for-each select="./*">
          <li>
            <xsl:value-of select="local-name(.)"/>:
            <xsl:copy-of select="."/>
          </li>
        </xsl:for-each>
      </ul>
    </div>
  </xsl:template>

</xsl:stylesheet>