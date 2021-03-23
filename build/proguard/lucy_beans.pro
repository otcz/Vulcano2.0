#
# This ProGuard configuration file illustrates how to process applications
# that use Lucy/LuciadLightspeed beans. It is typically included as part of a main
# configuration.
#

# Keep all BeanInfo and PropertyEditor classes, they are referenced by name.
# See also comment in java.beans.PropertyEditorManager.
-keep class * implements java.beans.BeanInfo
-keep class * implements java.beans.PropertyEditor

# Keep the projection property classes of grid references. Used by Map|Projection|Edit current
-keepclassmembers class * implements com.luciad.geodesy.ILcdGeodeticDatum { public *; }
-keepclassmembers class * implements com.luciad.geodesy.ILcdEllipsoid { public *; }
-keepclassmembers class * implements com.luciad.projection.ILcdProjection { public *; }




# Keep what the TLcyG4JAddOn needs
-keepnames class com.luciad.lucy.addons.g4j.TLcySkybox
-keepnames public class * implements com.luciad.view.opengl.painter.ILcdGLPainter {
  public *;
}
-keepnames class com.luciad.view.opengl.TLcdGLFog {
  public *;
}
-keepnames class com.luciad.view.opengl.TLcdGLCamera {
  public *;
}


# Keep classes that register TLcdGenericPropertyEditor as their editor:
# either by means of PropertyEditorManager.registerEditor or by
# PropertyDescriptor.setPropertyEditorClass.

# Classes that are given to TLcdBeanEditAction