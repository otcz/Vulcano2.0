#!/bin/bash

installer_dir="$PWD"
if ! [[ -f "$PWD/target/installer.jar" ]] ; then
  echo "You should run this script from the build/osgi folder"
  exit 1
fi

cd ..

ant_lib="$PWD/ant/lib/ant.jar"

cd ..

java -cp "$ant_lib:$installer_dir/target/installer.jar" com.luciad.osgi.installer.OsgiInstaller

sh build/ant/bin/ant -f build/osgi/com.luciad.osgi.sample/build.xml

echo "All OSGi bundles have now been created in the distrib/osgi folder. You can start an OSGi based sample by running the start_felix.bat script in the distrib folder, or you can read more about the OSGi integration in LuciadLightspeed's developer's guide"
