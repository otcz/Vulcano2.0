@echo off
echo Please read the comments in the obfuscate_project.xml file.

set ANT_HOME=../ant
set ANT_OPTS=-Xmx1024m
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181\

..\ant\bin\ant -buildfile obfuscate_project2.xml %1 %2 %3 %4 %5 %6 %7 %8 %9
