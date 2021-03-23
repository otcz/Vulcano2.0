package com.luciad.osgi.installer;

import static com.luciad.osgi.installer.BndSupport.runBnd;
import static com.luciad.osgi.installer.IOSupport.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * This steps converts the plain LuciadLightspeed jars into OSGi bundles.
 *
 * For each code jar of LuciadLightspeed, it
 * - creates the necessary OSGi headers for the MANIFEST.MF file
 * - includes the resources of the corresponding resources jar if it exists
 * - includes the corresponding native libraries, if any
 *
 * The created OSGi bundles are all fragments. This means all classes of LuciadLightspeed are in the same
 * classloader.
 *
 * The host bundle for these fragments is created in the {@link OSGiHostBundleStep}.
 */
public class MainBundlesStep implements InstallerStep {
  @Override
  public void execute() throws Exception {
    makeLuciadBundles();
    makeResourceBundles();
  }

  private void makeLuciadBundles() throws Exception {

    File bundlesDir = file("distrib", "osgi");
    IOSupport.mkdirs(bundlesDir);

    File[] luciadJars = file("lib").listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".jar") &&
               (name.startsWith("luciad") || name.startsWith("lcd_") || name.startsWith("lightspeed_")) &&
               !name.endsWith("resources.jar");
      }
    });

    Arrays.sort(luciadJars, new FileNameComparator());
    processJar(luciadJars, "template.bnd");
  }

  private void makeResourceBundles() throws Exception {
    File[] resourceJars = file("lib").listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".jar") &&
               (name.startsWith("lcd_geoid") || name.startsWith("lcd_epsg"));
      }
    });

    processJar(resourceJars, "resource_template.bnd");
  }

  private void processJar(File[] aLuciadJars, String aTemplateName) throws Exception {
    for (File luciadJar : aLuciadJars) {
      System.out.println("Creating metadata for " + luciadJar.getName());
      String bundleName = jarToBundleName(luciadJar);
      String manifest = bundleName + ".bnd";

      BndFile bndFile = BndFile.fromStream(getClass().getResource(aTemplateName).openStream());

      bndFile.addEntry("Bundle-SymbolicName", bundleName);

      bndFile.addEntry("Include-Resource", luciadJar);

      if ("com.luciad.lucy".equals(bundleName)) {
        File resourcesDir = file("resources");
        if (resourcesDir.exists() && resourcesDir.isDirectory()) {
          bndFile.addEntry("Include-Resource", resourcesDir);
        }
        File configDir = file("config");
        if (configDir.exists() && configDir.isDirectory()) {
          bndFile.addEntry("Include-Resource", configDir);
        }
      }

      File manifestFile = file(manifest);
      bndFile.writeTo(manifestFile);

      runBnd("buildx", "--classpath", luciadJar.getAbsolutePath(), "--output", "distrib/osgi/" + bundleName + ".jar", manifest);
      if (!manifestFile.delete()) {
        System.out.println("Could not delete Bnd configuration file '" + manifestFile.getAbsolutePath() + "'");
      }
    }
  }

  private static String jarToBundleName(File aJar) {
    String bundleName = aJar.getName().replaceAll("^lcd_", "").replaceAll("\\.jar$", "");
    return "com.luciad." + bundleName;
  }
}
