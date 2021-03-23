package com.luciad.osgi.installer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstraction of the configuration file for the BND-tool.
 *
 * @see <a href="http://www.aqute.biz/Bnd/Format">Bnd Format</a>
 */
public class BndFile {

  private Map<String, String> fEntries = new HashMap<String, String>();

  public BndFile() {
  }

  public static BndFile fromStream(InputStream aInputStream) throws IOException {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(aInputStream, "UTF-8"));
      String line;
      BndFile bndFile = new BndFile();
      while ((line = reader.readLine()) != null) {
        String[] split = line.split(":", 2);
        if (split.length < 2) {
          throw new IllegalArgumentException("The given content is not a valid in a bnd file: " + line);
        }
        bndFile.addEntry(split[0], split[1]);
      }
      return bndFile;
    } finally {
      aInputStream.close();
    }
  }

  public void writeTo(File aDestinationFile) throws IOException {
    FileOutputStream outputStream = new FileOutputStream(aDestinationFile);
    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
    for (Map.Entry<String, String> stringStringEntry : fEntries.entrySet()) {
      printWriter.append(stringStringEntry.getKey()).append(": ").append(stringStringEntry.getValue()).append("\n");
    }
    printWriter.close();
  }

  public void addEntry(String aKey, String aValue) {
    String value = fEntries.get(aKey);
    if (value == null || value.isEmpty()) {
      value = aValue;
    } else {
      value = value + "," + aValue;
    }
    fEntries.put(aKey, value);
  }

  public void addEntry(String aKey, File aFile) {
    //backslashes (in windows paths) should be  escaped in BND configuration files
    String toInclude = aFile.getAbsolutePath().replace("\\", "\\\\");
    addEntry(aKey, "@" + toInclude);

  }

  public void addNativeLibs(List<String> aOSGIQualifiers, List<String> aLibNames) {
    StringBuilder stringJoin = new StringBuilder();
    for (String aOSGIQualifier : aOSGIQualifiers) {
      for (String libName : aLibNames) {
        stringJoin.append(libName).append(";");
      }
      stringJoin.append(aOSGIQualifier).append(",");
    }
    stringJoin.setLength(stringJoin.length() - 1); //remove the trailing comma

    addEntry("Bundle-NativeCode", stringJoin.toString());
  }
}
