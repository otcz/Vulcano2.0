package com.luciad.osgi.installer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility class providing convenient methods related to I/O.
 */
public class IOSupport {

  private IOSupport() {
  }

  public static void copyStream(InputStream aInputStream, OutputStream aOutputStream) throws IOException {
    try {
      for (int readByte = aInputStream.read(); readByte != -1; readByte = aInputStream.read()) {
        aOutputStream.write(readByte);
      }
    } finally {
      aInputStream.close();
    }
  }

  public static File file(String... pathComponents) {
    File result = null;
    for (String component : pathComponents) {
      result = result == null ? new File(component) : new File(result, component);
    }
    return result;
  }

  public static void mkdirs(File aDirectoryToCreate) throws IOException {
    boolean success = aDirectoryToCreate.mkdirs();
    if (!success && !(aDirectoryToCreate.exists() && aDirectoryToCreate.isDirectory())) {
      throw new IOException("Could not create directory " + aDirectoryToCreate);
    }
  }

  public static void deleteFile(File aFileToDelete) throws IOException {
    boolean success = aFileToDelete.delete();
    if (!success) {
      throw new IOException("Could not delete file " + aFileToDelete);
    }
  }

  public static File[] listFiles(File aDirectoryToList) throws IOException {
    File[] thirdPartyJars = aDirectoryToList.listFiles();
    if (thirdPartyJars == null) {
      throw new IOException("Could not list files in directory " + aDirectoryToList);
    }
    return thirdPartyJars;
  }
}
