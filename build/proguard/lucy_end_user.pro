# This file describes how to obfuscate Lucy as an end user application.

# Note that the injars, outjar and library jars are configured dynamically through
# the 'deploy_lucy.xml' ant script.

-include lucy_beans.pro

-dontoptimize
-dontnote

# We have to ignore warnings because if not all Additional and/or Industry Specific Components are available
# (3D, realtime, AIS, ...), some Lucy classes will still refer to those classes
# of those Components, and warnings will be generated. This is however
# no harm, because if the Components are not available, the Lucy classes
# that cause the warnings will never be used.
-ignorewarnings

# Keep all main classes.
# Note that the Lucy batch files (or whatever mechanism is used to start the application)
# need to be adapted if a main class other than the default TLcyMain is used.
# Sample code is by default excluded from this build, so e.g. sample front-end
# TabbedPanesFrontendMain won't be available. See deploy_lucy.xml to know where to put
# additional source code.
-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}


# Keep the addons because they are loaded dynamically from an xml file. It can
# be usefull to explicitly list the addons that are actually used instead of
# keeping all addons. This reduces the size of the resulting jar file because
# Proguard automatically shrinks it by removing unused code.
-keep class * extends com.luciad.lucy.addons.ALcyAddOn

#-keep class com.luciad.lucy.addons.decoders.TLcyDefaultDecodersAddOn
#-keep class com.luciad.lucy.addons.map.TLcyMapAddOn
#-keep class com.luciad.lucy.addons.workspace.TLcyWorkspaceAddon
# ...

# On behalf of the oracle format
-keep class com.luciad.model.ILcdModelReference

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

# Some classes are instantiated solely using reflection. This means the constructors
# of the classes must be kept.
-keepclassmembers class * { public protected <init>(...); }
