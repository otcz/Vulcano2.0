:: Disable command echoing
@echo off

:: Enable delayed variable expansion.  This is required to assign variables in for loops.
SETLOCAL ENABLEDELAYEDEXPANSION

:: change the working directory to the installer directory
cd %~dp0

set INSTALLER_DIR=%CD%

cd ..

set ANT_HOME=%CD%\ant
set ANT_LIBRARY=%ANT_HOME%\lib\ant.jar

cd ..

java -cp "%ANT_LIBRARY%;%INSTALLER_DIR%\target\installer.jar" com.luciad.osgi.installer.OsgiInstaller

build\ant\bin\ant -f build\osgi\com.luciad.osgi.sample\build.xml

echo "All OSGi bundles have now been created in the distrib\osgi folder. You can start an OSGi based sample by running the start_felix.bat script in the distrib folder, or you can read more about the OSGi integration in LuciadLightspeed's developer's guide"
