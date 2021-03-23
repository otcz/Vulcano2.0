package com.luciad.osgi.installer;

import static com.luciad.osgi.installer.IOSupport.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility class to invoke the BND tool.
 */
public class BndSupport {

  private BndSupport() {
  }

  public static void runBnd(String... aArguments) throws Exception {

    ArrayList<String> command = new ArrayList<String>(aArguments.length + 3);
    command.add(System.getProperty("java.home") + "/bin/java");
    command.add("-jar");
    command.add(file("build", "osgi", "lib", "bnd.jar").getAbsolutePath());
    command.addAll(Arrays.asList(aArguments));
    ProcessBuilder builder = new ProcessBuilder(command);
    Process process = builder.start();
    consumeInputStream(process.getInputStream(), System.out);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(bos);
    consumeInputStream(process.getErrorStream(), printStream);
    process.waitFor();
    printStream.close();

    int exitValue = process.exitValue();
    if (exitValue != 0) {
      throw new RuntimeException("BND failed!:\n" + bos.toString());
    }
  }

  private static void consumeInputStream(final InputStream aInputStream, final PrintStream aWriter) {
    new Thread() {
      @Override
      public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(aInputStream));
        try {
          String line;
          while ((line = reader.readLine()) != null) {
            aWriter.println(line);
          }
        } catch (IOException e) {
          throw new RuntimeException(e); //This shouldn't happen
        }
      }
    }.start();
  }
}
