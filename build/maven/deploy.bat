@echo off

set ANT_HOME=%~dp0\..\ant

"%ANT_HOME%\bin\ant.bat" -nouserlib -f deploy.xml %*
