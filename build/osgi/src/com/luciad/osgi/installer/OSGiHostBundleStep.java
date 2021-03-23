package com.luciad.osgi.installer;

import static com.luciad.osgi.installer.IOSupport.copyStream;
import static com.luciad.osgi.installer.IOSupport.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This step creates the OSGi host bundle.
 *
 * This bundle does not contain classes itself. It is merely the host bundle for all fragment bundles
 * of LuciadLightspeed.
 *
 * The fragment bundles mentioned above are created by the {@link MainBundlesStep}
 */
public class OSGiHostBundleStep implements InstallerStep {
  @Override
  public void execute() throws Exception {
    createOSGiHostBundle();
  }

  private void createOSGiHostBundle() throws IOException {
    File osgiHostBundle = file("distrib", "osgi", "com.luciad.osgi.jar");
    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(osgiHostBundle));
    zipOutputStream.putNextEntry(new ZipEntry("META-INF/MANIFEST.MF"));
    copyStream(getClass().getResourceAsStream("host_manifest.txt"),
               zipOutputStream);
    zipOutputStream.closeEntry();
    zipOutputStream.close();
  }
}
