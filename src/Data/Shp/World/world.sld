<?xml version='1.0' encoding='UTF-8'?>
<FeatureTypeStyle xmlns="http://www.opengis.net/se" xmlns:se="http://www.opengis.net/se" xmlns:ogc="http://www.opengis.net/ogc" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:gml="http://www.opengis.net/gml" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.opengis.net/ogc http://schemas.opengis.net/filter/1.1.0/filter.xsd http://www.w3.org/2001/XMLSchema http://www.w3.org/2001/XMLSchema.xsd http://www.opengis.net/se http://schemas.opengis.net/se/1.1.0/FeatureStyle.xsd http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://www.w3.org/1999/xlink http://www.w3.org/1999/xlink.xsd " version="1.1.0">
  <Name>World</Name>
  <Description>
    <Title>World</Title>
    <Abstract>Considers any object as an area and fills it with grey and a white outline of width 1.</Abstract>
  </Description>
  <Rule>
    <Name>body</Name>
    <Description>
      <Title>body rule</Title>
      <Abstract>rule to render the bodies</Abstract>
    </Description>
    <PolygonSymbolizer>
      <Fill>
        <SvgParameter name="fill">#53525C</SvgParameter>
        <SvgParameter name="fill-opacity">0.4</SvgParameter>
      </Fill>
      <Stroke>
        <SvgParameter name="stroke">#000000</SvgParameter>
        <SvgParameter name="stroke-width">1.5</SvgParameter>
        <SvgParameter name="stroke-opacity">0.4</SvgParameter>
      </Stroke>
    </PolygonSymbolizer>
  </Rule>
  <Rule>
    <Name>labels</Name>
    <Description>
	  <Title>label rule</Title>
      <Abstract>Labels the country with their name</Abstract>
    </Description>
    <TextSymbolizer>
      <Label>
        <ogc:PropertyName>COUNTRY</ogc:PropertyName>
      </Label>
      <Font>
        <SvgParameter name="font-family">Dialog</SvgParameter>
        <SvgParameter name="font-weight">bold</SvgParameter>
        <SvgParameter name="font-size">12</SvgParameter>
      </Font>
      <Halo>
        <Radius>1</Radius>
        <Fill>
          <SvgParameter name="fill">#000000</SvgParameter>
        </Fill>
      </Halo>
      <Fill>
        <SvgParameter name="fill">#ffffff</SvgParameter>
      </Fill>
    </TextSymbolizer>
  </Rule>
</FeatureTypeStyle>