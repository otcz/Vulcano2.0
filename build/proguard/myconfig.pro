#
#-keeppackagenames
#
## Don't print notes about Class.forName calls.
#-dontnote
#
## luciadx-datamodelbuilder
#-dontshrink
#-dontoptimize
#
## JAXB needs attributes at runtime
#-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,LocalVariable*Table,*Annotation*,Synthetic,EnclosingMethod

#-include luciadlightspeed.pro
-include lucy_beans.pro
-ignorewarnings

# Don't print notes about Class.forName calls.
-dontnote

# Class file optimization may occasionally cause problems, so we avoid it
# (ProGuard 3.0 and higher).
-dontoptimize

# Some applications don't work properly if package names are obfuscated;
# notably, OSGi applications.
-keeppackagenames

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your application doesn't use serialization.
# If your code contains serializable classes that have to be backward
# compatible, please refer to the manual.
-keepclassmembers class * implements java.io.Serializable {
  static final long serialVersionUID;
  private void writeObject(java.io.ObjectOutputStream);
  private void readObject(java.io.ObjectInputStream);
  java.lang.Object writeReplace();
  java.lang.Object readResolve();
}

# Don't touch our own code.
-keep class com.company.DataRepositorySampleMain {
  *;
}



# keep all classes that are not of LuciadLightspeed itself
# this is a more general rule than the samples rule above
# use this rule if you do not want to obfuscate your application classes at all
#-keep class !com.luciad.** {
#    *;
#}


# These settings allow dynamic loading of annotated services:
#-keep public class com.luciad.model.ILcdModelDecoder
#-keep public class * implements com.luciad.model.ILcdModelDecoder
#-keep public class com.luciad.view.gxy.ILcdGXYLayerFactory
#-keep public class * implements com.luciad.view.gxy.ILcdGXYLayerFactory
#-keep public class com.luciad.view.lightspeed.layer.ILspLayerFactory
#-keep public class * implements com.luciad.view.lightspeed.layer.ILspLayerFactory
#-keep public class com.luciad.util.measure.ILcdModelMeasureProviderFactory
#-keep public class * implements com.luciad.util.measure.ILcdModelMeasureProviderFactory
#

#a partir de aqui se agrego
#-keep class * extends com.luciad.lucy.addons.ALcyAddOn
-keep class com.luciad.model.ILcdModelReference
-keep class com.luciad.lucy.addons.decoders.TLcyDefaultDecodersAddOn
-keep class com.luciad.lucy.addons.map.TLcyMapAddOn
-keep class com.luciad.lucy.addons.workspace.TLcyWorkspaceAddon
-keep class com.luciad.lucy.addons.AlcyAddOn
-keep class com.luciad.lucy.addons.preferences.TLcyPersistentPreferencesAddOn
-keep class com.luciad.lucy.addons.lookandfeel.TLcyLookAndFeelAddOn
-keep class com.luciad.lucy.addons.debug.TLcyDebugAddOn
-keep class com.luciad.lucy.addons.networkconnection.TLcyNetworkConnectionAddOn
-keep class com.luciad.lucy.addons.stylerepository.lightspeed.TLcyLspStyleRepositoryAddOn
-keep class  com.luciad.lucy.addons.asynchronouspaint.TLcyAsynchronousPaintAddOn
-keep class  com.luciad.lucy.addons.oracle.locator.TLcyOracleLocatorDecoderAddOn
-keep class  com.luciad.lucy.addons.oracle.georaster.TLcyOracleGeoRasterDecoderAddOn
-keep class com.luciad.lucy.addons.jpip.TLcyJPIPDecoderAddOn
-keep class com.luciad.lucy.addons.decoders.TLcyJPEG2000DecoderAddOn
-keep class com.luciad.lucy.addons.decoders.TLcyPOLDecoderAddOn
-keep class  com.luciad.lucy.addons.kml22.TLcyKML22AddOn
-keep class  com.luciad.lucy.addons.kml22.realtime.TLcyKML22RealtimeAddOn
-keep class com.luciad.lucy.addons.kml22.lightspeed.TLcyLspKML22FormatAddOn
-keep class com.luciad.lucy.addons.decoders.TLcyGRIBDecoderAddOn
-keep class  com.luciad.lucy.addons.obj.lightspeed.TLcyLspOBJFormatAddOn
#-keep class  com.luciad.lucy.addons.*
-keep class  com.luciad.lucy.addons.lightspeedadapter.gxy.TLcyLspFallbackFormatAddOn
-keep class  com.luciad.lucy.addons.drawing.TLcyDrawingAddOn
-keep class  com.luciad.lucy.addons.drawing.lightspeed.TLcyLspDrawingAddOn
-keep class com.luciad.lucy.addons.milstd2525b.TLcyMS2525bAddOn
-keep class com.luciad.lucy.addons.milstd2525b.lightspeed.TLcyLspMS2525bAddOn
-keep class com.luciad.lucy.addons.app6a.TLcyAPP6AAddOn
-keep class com.luciad.lucy.addons.app6a.lightspeed.TLcyLspAPP6AAddOn
-keep class com.luciad.lucy.addons.pim.TLcyPIMTrackAddOn
-keep class  com.luciad.lucy.addons.wmsclient.TLcyWMSClientAddOn
-keep class com.luciad.lucy.addons.ogc.wmtsclient.TLcyWMTSClientAddOn
-keep class  com.luciad.lucy.addons.ogc.wmtsclient.lightspeed.TLcyLspWMTSClientAddOn
-keep class  com.luciad.lucy.addons.wmsclient.lightspeed.TLcyLspWMSClientAddOn
-keep class com.luciad.lucy.addons.earth.TLcyEarthAddOn
-keep class com.luciad.lucy.addons.earth.lightspeed.TLcyLspEarthFormatAddOn
-keep class  com.luciad.lucy.addons.arcsde.TLcyArcSDEDecoderAddOn
-keep class com.luciad.lucy.addons.shp.lightspeed.TLcyLspSHPFormatAddOn
-keep class com.company.ShowReadMeAddOn
-keep class com.luciad.lucy.addons.gml.lightspeed.TLcyLspGMLFormatAddOn
-keep class com.luciad.lucy.addons.oracle.locator.lightspeed.TLcyLspOracleLocatorFormatAddOn
-keep class com.luciad.lucy.addons.arcsde.lightspeed.raster.TLcyLspArcSDERasterFormatAddOn
-keep class com.luciad.lucy.addons.arcsde.lightspeed.vector.TLcyLspArcSDEVectorFormatAddOn
-keep class com.luciad.lucy.addons.mif.lightspeed.TLcyLspMIFFormatAddOn
-keep class com.luciad.lucy.addons.dted.lightspeed.TLcyLspDTEDFormatAddOn
-keep class com.luciad.lucy.addons.dmed.lightspeed.TLcyLspDMEDFormatAddOn
-keep class com.luciad.lucy.addons.rst.lightspeed.TLcyLspRSTFormatAddOn
-keep class com.luciad.lucy.addons.jpip.lightspeed.TLcyLspJPIPFormatAddOn
-keep class com.luciad.lucy.addons.jpeg2000.lightspeed.TLcyLspJPEG2000FormatAddOn
-keep class com.luciad.lucy.addons.oracle.georaster.lightspeed.TLcyLspOracleGeoRasterFormatAddOn
-keep class com.luciad.lucy.addons.jai.lightspeed.TLcyLspJAIFormatAddOn
-keep class com.luciad.lucy.addons.geotiff.lightspeed.TLcyLspGeoTIFFFormatAddOn
-keep class com.luciad.lucy.addons.cadrg.lightspeed.TLcyLspCADRGFormatAddOn
-keep class com.luciad.lucy.addons.geojson.TLcyGeoJsonAddOn
-keep class com.luciad.lucy.addons.geojson.lightspeed.TLcyLspGeoJsonFormatAddOn
-keep class com.luciad.lucy.addons.dem.lightspeed.TLcyLspDEMFormatAddOn
-keep class com.luciad.lucy.addons.etopo.lightspeed.TLcyLspETOPOFormatAddOn
-keep class com.luciad.lucy.addons.bil.lightspeed.TLcyLspBILFormatAddOn
-keep class com.luciad.lucy.addons.openflight.lightspeed.TLcyLspOpenFlightFormatAddOn
-keep class com.luciad.lucy.addons.grib.lightspeed.TLcyLspGRIBFormatAddOn
-keep class com.luciad.lucy.addons.grid.lightspeed.TLcyLspGridAddOn
-keep class com.luciad.lucy.addons.grid.mgrs.lightspeed.TLcyLspMGRSGridAddOn
-keep class com.luciad.lucy.addons.grid.georef.lightspeed.TLcyLspGeorefGridAddOn
-keep class com.luciad.lucy.addons.arcinfoasciigrid.TLcyArcInfoASCIIGridFormatAddOn
-keep class com.luciad.lucy.addons.arcinfoasciigrid.lightspeed.TLcyLspArcInfoASCIIGridFormatAddOn
-keep class com.luciad.lucy.addons.modelreference.TLcyModelReferenceAddOn
-keep class com.luciad.lucy.addons.tea.contour.TLcyContourAddOn
-keep class com.luciad.lucy.addons.tea.extremepoint.TLcyExtremePointAddOn
-keep class com.luciad.lucy.addons.tea.visibility.TLcyVisibilityAddOn
-keep class com.luciad.lucy.addons.tea.loscoverage.TLcyLOSCoverageAddOn
-keep class com.luciad.lucy.addons.tea.hypsometry.TLcyHypsometryAddon
-keep class com.luciad.lucy.addons.tea.viewshed.TLcyViewshedAddOn
-keep class com.luciad.lucy.addons.drawing.tea.TLcyDrawingTEAAddOn
-keep class com.luciad.lucy.addons.map.TLcyGridAddOn
-keep class com.luciad.lucy.addons.wmsclient.earth.TLcyWMSEarthClientAddOn
-keep class com.luciad.lucy.addons.ogc.wfsclient.TLcyWFSClientAddOn
-keep class com.luciad.lucy.addons.ogc.wcsclient.TLcyWCSClientAddOn
-keep class com.luciad.lucy.addons.lspmap.TLcyLspMapAddOn
-keep class com.luciad.lucy.addons.map.TLcyMapOverviewAddOn
-keep class com.luciad.lucy.addons.map.lightspeed.TLcyLspMapOverviewAddOn
-keep class com.luciad.lucy.addons.dimensionalfilter.TLcyDimensionalFilterAddOn
-keep class com.luciad.lucy.addons.about.TLcyAboutBoxAddon
-keep class com.luciad.lucy.addons.help.TLcyHelpAddOn
-keep class com.luciad.lucy.addons.hyperlink.TLcyHyperlinkAddOn
-keep class com.luciad.lucy.addons.magneticnorth.TLcyMagneticNorthAddOn
-keep class com.luciad.lucy.addons.layercontrol.TLcyLayerControlAddOn
-keep class com.company.DataRepositoryAddOn
-keep class com.luciad.lucy.addons.map.TLcyMapEditUnitAddOn
-keep class com.luciad.lucy.addons.comparison.TLcyObjectPropertiesComparisonAddOn
-keep class com.luciad.lucy.addons.selectioneditor.TLcySelectionEditorAddOn
-keep class com.luciad.lucy.addons.gridcoordinate.TLcyGridCoordinateAddOn
-keep class com.luciad.lucy.addons.formatbar.TLcyFormatBarAddOn
-keep class com.luciad.lucy.addons.modelcustomizer.TLcyModelCustomizerAddOn
-keep class com.luciad.lucy.addons.tote.TLcyToteAddOn
-keep class com.luciad.lucy.addons.tote.lightspeed.TLcyLspToteAddOn
-keep class com.luciad.lucy.addons.treetable.TLcyTreeTableViewAddOn
-keep class com.luciad.lucy.addons.previewer.lightspeed.TLcyLspPreviewAddOn
-keep class com.luciad.lucy.addons.previewer.TLcyPreviewAddOn
-keep class com.luciad.lucy.addons.vertical.TLcyVerticalViewAddOn
-keep class com.luciad.lucy.addons.gridcoordinate.lightspeed.TLcyLspGridCoordinateAddOn
-keep class com.luciad.lucy.addons.print.lightspeed.TLcyLspPrintAddOn
-keep class com.luciad.lucy.addons.undo.TLcyUndoAddOn
-keep class com.luciad.lucy.addons.datatransfer.TLcyCopyPasteAddOn
-keep class com.luciad.lucy.addons.genericmap.TLcyGenericMapAddOn
-keep class com.luciad.lucy.addons.connection.TLcyConnectionAddOn
-keep class com.luciad.lucy.addons.layercustomizer.lightspeed.TLcyLspLayerCustomizerAddOn
-keep class com.luciad.lucy.addons.cameralinking.lightspeed.TLcyLspCameraLinkAddOn
-keep class com.luciad.lucy.addons.recentfiles.TLcyRecentFilesAddOn
-keep class com.luciad.lucy.addons.map.TLcyMapRadarViewAddOn
-keep class com.luciad.lucy.addons.map.TLcyGotoAreaOfInterestAddOn
-keep class com.luciad.lucy.addons.tea.controller.TLcyTEAControllerAddOn
-keep class com.luciad.lucy.addons.print.TLcyPrintAddOn



# These are necessary to prevent obfuscation of DataFlavor representation classes.
-keep class com.luciad.lucy.map.ILcyLayerSubsetList

# Keep the various factories that can be configured in configuration files
-keep public class * extends com.luciad.lucy.map.ALcyMapComponentFactory
-keep public class * implements com.luciad.lucy.map.ILcyMapLayerControlFactory
-keep public class * extends com.luciad.lucy.addons.previewer.view.ALcyPreviewAddOnCustomizerFactory
-keep public class * extends com.luciad.lucy.addons.print.ALcyPrintPreviewFactory
-keep public class * extends com.luciad.lucy.addons.print.ALcyPrintableComponentFactory
-keep public class * extends com.luciad.lucy.addons.vertical.ALcyVerticalViewComponentFactory
-keep public class * extends com.luciad.lucy.gui.ALcyGUIFactory


