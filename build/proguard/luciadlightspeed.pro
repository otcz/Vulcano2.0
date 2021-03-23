#
# This ProGuard configuration file illustrates how to obfuscate LuciadLightspeed as
# part of an application. It should be included in application-specific
# configuration files like decoder.pro.
#
# For more compact end results, you can comment out sections that you don't
# require. Sections refering to unavailable options will not affect the
# obfuscation process.

# Don't warn about optional packages that might not be present at all.
-dontwarn com.luciad.format.aixm.**
-dontwarn com.luciad.format.aixm5.**
-dontwarn com.luciad.format.arinc.**
-dontwarn com.luciad.format.asdi.**
-dontwarn com.luciad.format.asterix.**
-dontwarn com.luciad.format.dafif.**
-dontwarn com.luciad.format.db2.**
-dontwarn com.luciad.format.dgn.**
-dontwarn com.luciad.format.dwg.**
-dontwarn com.luciad.format.informix.**
-dontwarn com.luciad.format.object3d.**
-dontwarn com.luciad.format.oracle.**
-dontwarn com.luciad.format.postgresql.**
-dontwarn com.luciad.format.raster.terrain.**
-dontwarn com.luciad.format.raster.*DIMAP*
-dontwarn com.luciad.format.raster.*ECW*
-dontwarn com.luciad.format.raster.*GeoSPOT*
-dontwarn com.luciad.format.raster.*JPEG2000*
-dontwarn com.luciad.format.raster.*MrSID*
-dontwarn com.luciad.format.raster.*NITF*
-dontwarn com.luciad.format.raster.*SwissDHM*
-dontwarn com.luciad.format.raster.*USRP*
-dontwarn com.luciad.format.s52.**
-dontwarn com.luciad.format.s57.**
-dontwarn com.luciad.format.s63.**
-dontwarn com.luciad.format.vpf.**
-dontwarn com.luciad.format.kml22.view.opengl.**
-dontwarn com.luciad.internal.**
-dontwarn com.luciad.ogc.wcs.**
-dontwarn com.luciad.ogc.wfs.**
-dontwarn com.luciad.ogc.ows.**
-dontwarn com.luciad.realtime.**
-dontwarn com.luciad.shape.shape3D.TLcdEditablePlaneSet
-dontwarn com.luciad.symbology.**
-dontwarn com.luciad.tea.**
-dontwarn com.luciad.wms.**
-dontwarn com.luciad.view.opengl.**
-dontwarn com.luciad.lucy.addons.**
-dontwarn com.bentley.**
-dontwarn samples.lucy.**


# Make sure all library classes are loaded for analysis.
-dontskipnonpubliclibraryclasses


# Always preserve all native method names and the names of their classes.
-keepclasseswithmembernames class * {
    native <methods>;
}

# Always preserve the special methods required in enumeration classes.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

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

# If you have external files that refer to model reference classes and related
# classes by name (for instance, .ref property files or .rst property files),
# you'll have to preserve those classes with their original names.
-keep class * implements com.luciad.model.ILcdModelReference
-keep class * extends    com.luciad.reference.TLcdGridReference
-keep class * implements com.luciad.projection.ILcdProjection
-keep class * implements com.luciad.geodesy.ILcdGeodeticDatumFactory
-keep class * implements com.luciad.transformation.ILcdModelXYWorldTransformation

# If you plan to add new ILcdModelReference implementations after having
# obfuscated your application, you'll have to preserve the interface with its
# original name. The same goes for the other interfaces.
#-keep interface com.luciad.model.ILcdModelReference
#...

# If you have external files that refer to image tile decoder classes and
# related classes by name (typically .rst property files), you'll have to
# preserve those classes.
-keep class * implements com.luciad.format.raster.ILcdColorModelFactory
-keep class * implements com.luciad.format.raster.ILcdTileDecoder

# If you have external files that refer to model decoder classes by name
# (typically .mtm property files), you'll have to preserve those classes.
# -keep class * implements com.luciad.model.ILcdModelDecoder
# You can also keep specific instances of ILcdModelDecoder once you know
# which implementations are referred to by mtm files
-keep class com.luciad.format.shp.TLcdSHPModelDecoder

# If you have external files that refer to model tile provider classes by name
# (typically .mlm property files), you'll have to preserve those classes.
-keep class * implements com.luciad.model.ILcdTileProvider

# If you have external files that refer to model tile decoder classes by name
# (typically .mtm property files), you'll have to preserve those classes.
-keep class * implements com.luciad.model.ILcdTileDecoder

# If you have external files that refer to database factory classes by name
# (very uncommon), you'll have to preserve those classes.
-keep,allowobfuscation class * implements com.luciad.format.database.ILcdDatabaseFactory

# If you are processing your own implementations of SQL drivers (very uncommon),
# you'll have to preserve those classes.
-keep class * implements java.sql.Driver

# We'll mention the clone method, because some classes are accessing it
# dynamically. All implementations would be kept anyway, because they override
# Object#clone.
-keepclassmembers class * {
  void clone();
}

# We'll mention an internal AWT field, because some classes are accessing it
# dynamically. It would be kept anyway, because it is part of a library jar.
-keep class sun.awt.windows.WComponentPeer {
  long hwnd;
}

# Swing ComponentUI implementations are also instantiated by name.
-keep class * extends javax.swing.plaf.ComponentUI {
    public static javax.swing.plaf.ComponentUI createUI(javax.swing.JComponent);
}

# The range slider is a LuciadLightspeed component that should be preserved to
# find the UI classes for it.
-keep class com.luciad.gui.swing.TLcdRangeSlider

# The 3D package may require the following packages to remain available.
-keep class * extends com.luciad.view.opengl.binding.ALcdGLBinding
-keep class com.luciad.util.TLcdLog {
    public protected *;
}

# The MIL-STD 2525b symbology requires the following painter classes to remain
# available, since they are instantiated by name.
-keep class com.luciad.internal.symbology.app6a_ms2525b.view.gxy.painter.* implements com.luciad.view.gxy.ILcdGXYPainter
-keep class com.luciad.internal.symbology.app6a_ms2525b.view.gxy.painter.* implements com.luciad.view.gxy.ILcdGXYLabelPainter
-keep class com.luciad.internal.symbology.app6a_ms2525b.view.canvas.ILcdSymbologyPainter
-keep class com.luciad.internal.symbology.app6a_ms2525b.view.canvas.painter.* implements com.luciad.internal.symbology.app6a_ms2525b.view.canvas.ILcdSymbologyPainter {
  <init>(...);
}

# Keep constructors of all package private GML3 element readers and adapters.
# These classes are all instantiated using reflection.
-keepclassmembers class com.luciad.format.gml3.reader.**  {
  <init>(com.luciad.format.xml.schema.ILcdXMLTypedElementReaderProvider,
         com.luciad.format.xml.ILcdXMLObjectFactoryProvider,
         com.luciad.format.xml.schema.ILcdXMLSchemaProvider);
}
-keepclassmembers class com.luciad.format.gml3.adapter.**  {
  <init>(com.luciad.format.xml.schema.ILcdXMLTypedElementAdapterProvider,
         com.luciad.format.xml.ILcdXMLElementNameProvider,
         com.luciad.format.xml.schema.ILcdXMLSchemaProvider);
}

# The XML framework reflectively instantiates data object classes, so keep
# all constructors of those classes
-keepclassmembers class * implements com.luciad.datamodel.ILcdDataObject {
  <init>(...);
}

# The AIS package requires the following classes to remain available.
-keep public class * extends com.luciad.ais.model.ALcdAISObjectFactory
-keep public class com.luciad.ais.symbology.*
-keep public class com.luciad.ais.symbology.icao.*

-keep public interface com.luciad.format.dafif.gui.ILcdDAFIFLoaderViewAdapter {
    public protected *;
}

-keep public interface com.luciad.format.dafif.gui.ILcdDAFIFLoaderViewAdapterFactory {
    public protected *;
}

-keep public class com.luciad.format.dafif.gui.TLcdDAFIFLoader {
    public protected *;
}

-keep public class * implements com.luciad.format.dafif.decoder.ILcdDAFIFDecoder {
    <init>(com.luciad.ais.model.ALcdAISObjectFactory, java.util.Properties);
}
-keep public interface          com.luciad.format.dafif.decoder.ILcdDAFIFDecoder {
    public protected *;
}

-keep public class * implements com.luciad.format.arinc.decoder.ILcdARINCDecoder
-keep public interface          com.luciad.format.arinc.decoder.ILcdARINCDecoder {
    public protected *;
}

-keep public class * implements com.luciad.format.arinc.decoder.ILcdARINCHandler
-keep public interface          com.luciad.format.arinc.decoder.ILcdARINCHandler {
    public protected *;
}

-keep public class com.luciad.format.dafift.decoder.TLcdDAFIFT*Decoder {
    <init>(com.luciad.ais.model.ALcdAISObjectFactory);
}

-keep public class * extends java.util.logging.Handler {
    public protected *;
}

-keep public class * extends java.util.logging.Formatter {
    public protected *;
}

# Keep all members that are designated as native callbacks.
-keep class com.luciad.internal.util.LinNativeCallback { <methods>; }

-keepclasseswithmembers class ** {
  @com.luciad.internal.util.LinNativeCallback <fields>;
}
-keepclasseswithmembers class ** {
  @com.luciad.internal.util.LinNativeCallback <methods>;
}

-keep public class com.luciad.internal.imaging.engine.ALinImagingEngineFactory
-keep public class * extends com.luciad.internal.imaging.engine.ALinImagingEngineFactory

-keep public interface com.luciad.internal.imaging.image.adapter.ILinImageAdapterFactory
-keep public class * implements com.luciad.internal.imaging.image.adapter.ILinImageAdapterFactory
