@echo off
echo Please read the comments in the deploy_ogc_service.xml file.

set ANT_HOME=../ant
set ANT_OPTS=-Xmx1024m

%ANT_HOME%\bin\ant -buildfile deploy_ogc_service.xml %1