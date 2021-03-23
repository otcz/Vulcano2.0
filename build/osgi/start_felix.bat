:: Disable command echoing
@echo off

:: Enable delayed variable expansion.  This is required to assign variables in for loops.
SETLOCAL ENABLEDELAYEDEXPANSION

cd felix-framework-4.2.1

rmdir /Q /S luciad-cache
java -Xmx512m -Dsun.awt.noerasebackground=true "-Dorg.osgi.framework.bootdelegation=sun.*,com.sun.*,javax.*,javax.swing.*,org.w3c.*,org.xml.*" "-Dorg.osgi.framework.storage=luciad-cache" "-Dfelix.auto.deploy.dir=../osgi" -jar bin/felix.jar
