package com.luciad.osgi.installer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.Project;

/**
 * This installer script creates all necessary OSGi bundles to run LuciadLightspeed in an OSGi environment.
 *
 * @see #createOsgiBundles()
 */
public class OsgiInstaller {
  private final Map<String, List<String>> fNativePlatformMappings;
  private final Map<String, String> fNativeJogampMappings;
  private Project fAntProject = new Project();

  public OsgiInstaller() {
    fAntProject.setBaseDir(new File(".").getAbsoluteFile());

    try {
      // see https://www.osgi.org/developer/specifications/reference/
      fNativePlatformMappings = insertAllWindowsVersions(readMappingConfiguration("platform_mapping.txt"));
      fNativeJogampMappings = readMappingConfiguration("jogamp_mapping.txt");
    } catch (IOException e) {
      throw new RuntimeException(e); //this shouldn't happen, this is an internal resource format
    }
  }

  private static Map<String, String> readMappingConfiguration(String aMappingFile) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(OsgiInstaller.class.getResource(aMappingFile).openStream(), "UTF-8"));
    Map<String, String> mappings = new HashMap<String, String>();
    String line;
    while ((line = reader.readLine()) != null) {
      String[] split = line.split(" ", 2);
      mappings.put(split[0], split[1]);
    }
    return Collections.unmodifiableMap(mappings);
  }

  /**
   * There is no general way to specify 'all windows' versions, so we need to create
   * platform mappings for all known Windows versions here.
   * 
   * @see https://www.osgi.org/developer/specifications/reference/#Referenceprocessor
   */
  private static Map<String, List<String>> insertAllWindowsVersions(Map<String, String> aMappingBeforeReplacement) throws IOException {
    List<String> allWindowsVersions = readWindowsVersions();
    Map<String, List<String>> mappingForAllWindowsVersions = new HashMap<>();
    for(Map.Entry<String, String> entry : aMappingBeforeReplacement.entrySet()) {
      String key = entry.getKey();
      String originalValue = entry.getValue();
      if (originalValue.contains("%WINDOWS%")) {
        List<String> valueForAllWindowsVersions = new ArrayList<>(allWindowsVersions.size());
        for (String windowsVersion : allWindowsVersions) {
          valueForAllWindowsVersions.add(originalValue.replace("%WINDOWS%", windowsVersion));
        }
        mappingForAllWindowsVersions.put(key, valueForAllWindowsVersions);
      } else {
        mappingForAllWindowsVersions.put(key, Collections.singletonList(originalValue));
      }
    }
    return mappingForAllWindowsVersions;
  }

  private static List<String> readWindowsVersions() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(OsgiInstaller.class.getResource("windows_versions.txt").openStream(), "UTF-8"));
    List<String> windowsVersions = new ArrayList<>();
    String line;
    while ((line = reader.readLine()) != null) {
      if (line.trim().isEmpty()) {
        continue;
      }
      windowsVersions.add(line.trim());
    }
    return Collections.unmodifiableList(windowsVersions);
  }

  private void createOsgiBundles() throws Exception {
    InstallerStep[] steps = {
        new LicenseBundlesStep(),
        new MainBundlesStep(),
        new OSGiHostBundleStep(),
        new SamplesBundleStep(),
        new ThirdPartyBundleStep(fNativePlatformMappings, fNativeJogampMappings),
        new ProvideFelixStep(),
    };
    for (InstallerStep step : steps) {
      step.execute();
    }
  }

  public static void main(String[] args) throws Exception {
    OsgiInstaller installer = new OsgiInstaller();
    installer.createOsgiBundles();
  }
}
