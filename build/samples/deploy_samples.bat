@echo off
echo Please read the comments in the deploy_samples.xml file.

set ANT_HOME=../ant
set ANT_OPTS=-Xmx1024m

..\ant\bin\ant -buildfile deploy_samples.xml %1 %2 %3 %4 %5 %6 %7 %8 %9
