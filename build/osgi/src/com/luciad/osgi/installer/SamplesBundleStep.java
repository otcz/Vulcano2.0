package com.luciad.osgi.installer;

import static com.luciad.osgi.installer.AntSupport.runAntTask;
import static com.luciad.osgi.installer.BndSupport.runBnd;
import static com.luciad.osgi.installer.IOSupport.file;

import java.io.File;
import java.util.Arrays;

import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * This step creates a fragment bundle for the LuciadLightspeed sample.
 *
 * This step is optional.
 */
public class SamplesBundleStep implements InstallerStep {

  @Override
  public void execute() throws Exception {
    makeSampleBundles();
    makeSampleResourcesBundle();
  }

  private void makeSampleBundles() throws Exception {
    File samplesLibFolder = file("samples", "lib");
    File[] sampleJars = samplesLibFolder.listFiles();
    Arrays.sort(sampleJars, new FileNameComparator());
    for (File sampleJar : sampleJars) {
      System.out.println("Processing " + sampleJar.getName());
      String sampleBundleName = deriveBundleName(sampleJar);
      String manifest = sampleBundleName + ".bnd";
      File manifestFile = file(manifest);
      BndFile sampleBndFile = BndFile.fromStream(getClass().getResourceAsStream("samples_template.bnd"));
      sampleBndFile.addEntry("Bundle-SymbolicName", sampleBundleName);
      sampleBndFile.addEntry("Include-Resource", sampleJar);
      sampleBndFile.writeTo(manifestFile);

      runBnd("buildx", "--classpath", sampleJar.getAbsolutePath(), "--output", "distrib/osgi/" + sampleBundleName + ".jar", manifest);
      if (!manifestFile.delete()) {
        System.out.println("Could not delete Bnd configuration file '" + manifestFile.getAbsolutePath() + "'");
      }
    }
  }

  private String deriveBundleName(File sampleJar) {
    String sampleBaseName = sampleJar.getName().replace("lcd_", "").replace("_samples.jar", "");
    return "com.luciad.samples." + sampleBaseName;
  }

  private void makeSampleResourcesBundle() throws Exception {
    String bundleName = "com.luciad.samples.resources";
    final File samplesBundle = file("samples", bundleName + ".jar");

    if (samplesBundle.exists()) {
      IOSupport.deleteFile(samplesBundle);
    }
    runAntTask(new Zip(), new AntSupport.Configure<Zip>() {
      @Override
      public void configure(Zip zip) {
        zip.setDestFile(samplesBundle);

        FileSet resourcesSet = new FileSet();
        resourcesSet.setDir(file("samples", "resources"));
        resourcesSet.setExcludes("Data/**");
        zip.addFileset(resourcesSet);

        FileSet minimalDataFiles = new FileSet();
        minimalDataFiles.setDir(file("samples", "resources"));
        minimalDataFiles.setIncludes("Data/Shp/World/**,Data/Gml31/**,Data/GeoTIFF/BlueMarble/**");
        zip.addFileset(minimalDataFiles);
      }
    });

    String manifest = bundleName + ".bnd";
    File manifestFile = file(manifest);

    BndFile bndFile = BndFile.fromStream(getClass().getResource("resource_template.bnd").openStream());
    bndFile.addEntry("Bundle-SymbolicName", bundleName);
    bndFile.addEntry("Include-Resource", samplesBundle);
    bndFile.writeTo(manifestFile);

    runBnd("buildx", "--classpath", samplesBundle.getAbsolutePath(), "--output", "distrib/osgi/" + bundleName + ".jar", manifest);
    if (!manifestFile.delete()) {
      System.out.println("Could not delete Bnd configuration file '" + manifestFile.getAbsolutePath() + "'");
    }
  }

}
