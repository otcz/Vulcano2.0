<?xml version='1.0' encoding='UTF-8'?>
<FeatureTypeStyle
    xmlns="http://www.opengis.net/se" xmlns:ogc="http://www.opengis.net/ogc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.opengis.net/ogc http://schemas.opengis.net/filter/1.1.0/filter.xsd http://www.opengis.net/se http://schemas.opengis.net/se/1.1.0/FeatureStyle.xsd"
    version="1.1.0">
  <Name>Countries</Name>
  <Description>
    <Title>Countries</Title>
    <Abstract>Considers any object as an area with a dark outline, filled with grey.</Abstract>
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
      <Abstract>Labels the country with their name, when scale is 1:25,000,000 or more zoomed in.</Abstract>
    </Description>
    <MaxScaleDenominator>2.5E7</MaxScaleDenominator>
    <TextSymbolizer>
      <Label>
        <ogc:PropertyName>admin</ogc:PropertyName>
      </Label>
      <Font>
        <SvgParameter name="font-family">Lucida Sans</SvgParameter>
        <SvgParameter name="font-weight">normal</SvgParameter>
        <SvgParameter name="font-size">12</SvgParameter>
      </Font>
      <Halo>
        <Radius>1</Radius>
        <Fill>
          <SvgParameter name="fill">#000000</SvgParameter>
        </Fill>
      </Halo>
      <Fill>
        <SvgParameter name="fill">#eeeeee</SvgParameter>
      </Fill>
    </TextSymbolizer>
  </Rule>
</FeatureTypeStyle>