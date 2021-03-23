package com.luciad.osgi.installer;

import static com.luciad.osgi.installer.IOSupport.copyStream;
import static com.luciad.osgi.installer.IOSupport.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;

/**
 * This step creates an OSGi fragment containing the LuciadLightspeed license.
 *
 * First it tries to find the licenses in the most common places. If it can't find a license there,
 * it shows a file chooser asking the user to look for it.
 */
public class LicenseBundlesStep implements InstallerStep {
  private File fLicenseFile;

  @Override
  public void execute() throws Exception {
    makeLicenseBundle();
  }

  private void findLicenseFile() {
    boolean licenseFound = checkLicenseFile("licenses", "development.jar") ||
                           checkLicenseFile("licenses", "development_license.jar") ||
                           checkLicenseFile("licenses", "development_license.txt") ||
                           checkLicenseFile("development_license.txt") ||
                           checkLicenseFile("development.jar") ||
                           checkLicenseFile("lib", "development.jar");

    if (!licenseFound) {
      System.out.println("Could not find development license. Please copy the development.jar file to the licenses folder");
      JFileChooser fileChooser = new JFileChooser(file("."));
      fileChooser.setDialogTitle("Select license file");
      fileChooser.showOpenDialog(null);
      File selectedFile = fileChooser.getSelectedFile();
      if (selectedFile != null) {
        fLicenseFile = selectedFile;
      }
    }
  }

  private boolean checkLicenseFile(String... aNameComponents) {
    File licenseFile = file(aNameComponents);
    if (licenseFile.exists()) {
      fLicenseFile = licenseFile;
      return true;
    }
    return false;
  }

  private void makeLicenseBundle() throws IOException {
    findLicenseFile();
    if (fLicenseFile != null) {
      String licenseBundleName = "com.luciad.license";
      File licenseBundle = file("distrib", "osgi", licenseBundleName + ".jar");
      IOSupport.mkdirs(licenseBundle.getParentFile());
      ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(licenseBundle));

      //For some OSGi containers it is important that the JAR specification is followed to the letter:
      //the META-INF/ and META-INF/MANIFEST.MF *must* be the first two zip entries in the jar file.
      zipOutputStream.putNextEntry(new ZipEntry("META-INF/"));
      zipOutputStream.closeEntry();
      zipOutputStream.putNextEntry(new ZipEntry("META-INF/MANIFEST.MF"));
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(zipOutputStream, "UTF-8"));
      writer.println("Manifest-Version: 1.0");
      writer.println("Bundle-ManifestVersion: 2");
      writer.println("Bundle-SymbolicName: " + licenseBundleName);
      writer.println("Bundle-Version: 2017.1.08");
      writer.println("Fragment-Host: com.luciad.osgi");
      writer.flush();
      zipOutputStream.closeEntry();

      zipOutputStream.putNextEntry(new ZipEntry("development_license.txt"));

      if (fLicenseFile.getName().endsWith(".jar")) {
        ZipFile zipFile = new ZipFile(fLicenseFile);
        ZipEntry entry = zipFile.getEntry("development_license.txt");
        InputStream inputStream = zipFile.getInputStream(entry);
        copyStream(inputStream, zipOutputStream);
        inputStream.close();
        zipFile.close();
      } else {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fLicenseFile));
        copyStream(bufferedInputStream, zipOutputStream);
        bufferedInputStream.close();
      }
      zipOutputStream.closeEntry();
      zipOutputStream.close();

    }
  }
}
