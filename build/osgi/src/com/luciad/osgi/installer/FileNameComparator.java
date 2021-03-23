package com.luciad.osgi.installer;

import java.io.File;
import java.util.Comparator;

/**
 * Compares the names of the two given files.
 */
class FileNameComparator implements Comparator<File> {
  @Override
  public int compare(File o1, File o2) {
    return o1.getName().compareTo(o2.getName());
  }
}
