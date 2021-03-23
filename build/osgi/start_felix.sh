#!/bin/bash -

cd felix-framework-4.2.1

rm -rf luciad-cache
java \
  -Xmx512m \
  -Dsun.awt.noerasebackground=true \
  "-Dorg.osgi.framework.bootdelegation=sun.*,com.sun.*,javax.*,javax.swing.*,org.w3c.*,org.xml.*" \
  "-Dorg.osgi.framework.storage=luciad-cache" \
  "-Dfelix.auto.deploy.dir=../osgi" \
  -jar bin/felix.jar
