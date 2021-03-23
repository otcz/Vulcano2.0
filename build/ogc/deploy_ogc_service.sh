#!/bin/bash
echo Please read the comments in the deploy_ogc_service.xml file.

export ANT_HOME=../ant
export ANT_OPTS=-Xmx1024m

$ANT_HOME/bin/ant -buildfile deploy_ogc_service.xml "$@"
