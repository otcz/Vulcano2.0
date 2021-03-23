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

# keep all classes that have a main method, so that the samples can be started
-keepclasseswithmembers public class samples.** {
  public static void main(java.lang.String[]);
}

# keep all sample classes
-keep class samples.** {
    *;
}

# keep all classes that are not of LuciadLightspeed itself
# this is a more general rule than the samples rule above
# use this rule if you do not want to obfuscate your application classes at all
#-keep class !com.luciad.** {
#    *;
#}

# These settings allow dynamic loading of annotated services:
-keep public class com.luciad.model.ILcdModelDecoder
-keep public class * implements com.luciad.model.ILcdModelDecoder
-keep public class com.luciad.view.gxy.ILcdGXYLayerFactory
-keep public class * implements com.luciad.view.gxy.ILcdGXYLayerFactory
-keep public class com.luciad.view.lightspeed.layer.ILspLayerFactory
-keep public class * implements com.luciad.view.lightspeed.layer.ILspLayerFactory
-keep public class com.luciad.util.measure.ILcdModelMeasureProviderFactory
-keep public class * implements com.luciad.util.measure.ILcdModelMeasureProviderFactory
