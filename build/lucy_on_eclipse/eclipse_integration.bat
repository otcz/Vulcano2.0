@echo off
echo Please read the comments in the eclipse_integration.xml file.

set ANT_HOME=../ant
set ANT_OPTS=-Xmx256m

..\ant\bin\ant -buildfile eclipse_integration.xml

pause