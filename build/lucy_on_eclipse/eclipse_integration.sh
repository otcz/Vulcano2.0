#!/bin/bash
echo Please read the comments in the eclipse_integration.xml file.

export ANT_HOME=../ant
export ANT_OPTS=-Xmx256m

../ant/bin/ant -buildfile eclipse_integration.xml

echo Press enter to continue ...; read