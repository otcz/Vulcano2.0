# Proguard configuration for OGC services.
# ProGuard is a free Java class file shrinker, optimizer, and obfuscator that
# can be downloaded from:
#     http://proguard.sourceforge.net/
#
# This configuration file requires Proguard 5.3, but it is advisable to
# download the latest version from the address above, since Proguard
# is continuously  improved.

-libraryjars <java.home>/lib/rt.jar
-libraryjars <java.home>/lib/jce.jar
-libraryjars <java.home>/lib/ext/sunjce_provider.jar

# Include LuciadLightspeed obfuscation script
-include luciadlightspeed.pro

# General settings
-dontnote
-dontoptimize
-ignorewarnings

# Keep command dispatcher
-keep class * extends com.luciad.wms.server.ALcdWMSCommandDispatcherFactory
-keep class * implements com.luciad.ogc.common.ILcdOGCCommandDispatcherFactory

-keep interface com.luciad.ogc.common.ILcdOGCCommandDispatcherFactory
-keep class com.luciad.wms.server.ALcdWMSCommandDispatcherFactory

# Keep javax.servlet.http.HttpServlet extensions
-keep class * extends javax.servlet.http.HttpServlet

# Keep javax.servlet.Filter extensions
-keep class * implements javax.servlet.Filter

# Keep the ILcdLoggerFactory implementation from the LuciadFusion Platform for bridging to SLF4J-API and logback
-keep class * implements com.luciad.util.logging.ILcdLoggerFactory
