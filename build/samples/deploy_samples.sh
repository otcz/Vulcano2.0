#!/bin/bash
echo Please read the comments in the deploy_samples.xml file.

export ANT_HOME=../ant
export ANT_OPTS=-Xmx1024m

../ant/bin/ant -buildfile deploy_samples.xml "$@"
