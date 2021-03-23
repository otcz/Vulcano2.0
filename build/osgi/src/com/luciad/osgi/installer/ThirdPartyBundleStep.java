package com.luciad.osgi.installer;

import static com.luciad.osgi.installer.AntSupport.runAntTask;
import static com.luciad.osgi.installer.IOSupport.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.util.FlatFileNameMapper;

/**
 * This step creates a single thirdparty bundle containing all third-party libraries required by
 * LuciadLightspeed.
 */
public class ThirdPartyBundleStep implements InstallerStep {

  private final Map<String, List<String>> fNativePlatformMappings;
  private final Map<String, String> fNativeJogampMappings;

  public ThirdPartyBundleStep(Map<String, List<String>> aNativePlatformMappings, Map<String, String> aNativeJogampMappings) {
    fNativePlatformMappings = aNativePlatformMappings;
    fNativeJogampMappings = aNativeJogampMappings;
  }

  @Override
  public void execute() throws Exception {
    makeThirdPartyBundle();
  }

  private void makeThirdPartyBundle() throws Exception {
    runAntTask(new Delete(), new AntSupport.Configure<Delete>() {
      @Override
      public void configure(Delete delete) {
        delete.setDir(file("build", "osgi", "thirdparty"));
      }
    });

    runAntTask(new Copy(), new AntSupport.Configure<Copy>() {
      @Override
      public void configure(Copy copy) {
        copy.setTodir(file("build", "osgi", "thirdparty"));
        FileSet fileSet = new FileSet();
        fileSet.setDir(file("lib"));
        fileSet.setIncludes("*.jar");
        fileSet.setExcludes("lcd*.jar,luciad*.jar");
        copy.addFileset(fileSet);
      }
    });

    for (final String platformDir : fNativePlatformMappings.keySet()) {
      final String suffix = "-natives-" + fNativeJogampMappings.get(platformDir) + ".jar";
      runAntTask(new Expand(), new AntSupport.Configure<Expand>() {
        @Override
        public void configure(Expand expand) {
          FileSet jars = new FileSet();
          // Expand doesn't pass the project to the fileset, we have to do it ourselves
          jars.setProject(expand.getProject());
          jars.setDir(file("lib"));
          jars.appendIncludes(new String[]{"jocl-*" + suffix,
                                           "jogl-all-*" + suffix,
                                           "gluegen-rt-*" + suffix});
          PatternSet pattern = new PatternSet();
          pattern.setIncludes("natives/**");

          expand.setDest(file("build", "osgi", "thirdparty", "native", platformDir));
          expand.addFileset(jars);
          expand.addPatternset(pattern);
          expand.add(new FlatFileNameMapper());
        }
      });
    }

    Set<String> packagesToExport = new HashSet<>();
    StringBuilder classPathBuilder = new StringBuilder();
    File thirdPartyDir = file("build", "osgi", "thirdparty");
    File[] thirdPartyJars = IOSupport.listFiles(thirdPartyDir);
    for (File thirdPartyJar : thirdPartyJars) {
      if (!thirdPartyJar.isFile()) {
        continue;
      }
      classPathBuilder.append(" ").append(thirdPartyJar.getName()).append(",\n");
      ZipFile zipFile = new ZipFile(thirdPartyJar);
      for (Enumeration<? extends ZipEntry> entries = zipFile.entries(); entries.hasMoreElements(); ) {
        ZipEntry entry = entries.nextElement();
        String classEntry = entry.getName();
        if (classEntry.endsWith(".class")) {
          String packageName = classEntry.replaceFirst("/[^/]+\\.class$", "").replace("/", ".");
          packagesToExport.add(packageName);
        }
      }
    }
    classPathBuilder.setLength(classPathBuilder.length() - 2); //delete the trailing comma and newline
    // LCD-9458: export native libraries
    packagesToExport.add("native.linux");
    packagesToExport.add("native.linux64");
    packagesToExport.add("native.win32");
    packagesToExport.add("native.win64");
    packagesToExport.add("native.macos_64");

    StringBuilder packages = new StringBuilder();
    for (String packageToExport : sort(packagesToExport)) {
      packages.append(" ").append(packageToExport).append(",\n");
    }
    packages.setLength(packages.length() - 2); //delete the trailing comma and newline

    StringBuilder nativeCodeBuilder = new StringBuilder();
    for (Map.Entry<String, List<String>> entry : fNativePlatformMappings.entrySet()) {
      String platformDir = entry.getKey();
      List<String> osgiQualifiers = entry.getValue();
      for (String osgiQualifier : osgiQualifiers) {
        File[] nativeLibs = IOSupport.listFiles(file("build", "osgi", "thirdparty", "native", platformDir));
        for (File nativeLib : nativeLibs) {
          nativeCodeBuilder.append(" native/").append(platformDir).append("/").append(nativeLib.getName()).append(";\n");
        }
        nativeCodeBuilder.append(" ").append(osgiQualifier).append(",\n");
      }
    }
    nativeCodeBuilder.setLength(nativeCodeBuilder.length() - 2); //strip the trailing comma and newline

    final File manifest = file("build", "osgi", "thirdparty", "META-INF", "MANIFEST.MF");
    IOSupport.mkdirs(manifest.getParentFile());
    PrintStream manifestWriter = new PrintStream(new FileOutputStream(manifest), true, "UTF-8");
    manifestWriter.println("Manifest-Version: 1.0");
    manifestWriter.println("Bundle-ManifestVersion: 2");
    manifestWriter.println("Bundle-SymbolicName: thirdparty");
    manifestWriter.println("Bundle-Version: 2017.1.08");
    manifestWriter.println("Bundle-ClassPath:" + classPathBuilder.toString());
    manifestWriter.println("Export-Package: " + packages.toString());
    manifestWriter.println("Bundle-NativeCode:" + nativeCodeBuilder.toString());

    runAntTask(new Jar(), new AntSupport.Configure<Jar>() {
      @Override
      public void configure(Jar jar) {
        jar.setDestFile(file("distrib", "osgi", "thirdparty.jar"));
        jar.setManifest(manifest);
        FileSet set = new FileSet();
        set.setDir(file("build", "osgi", "thirdparty"));
        jar.addFileset(set);
      }
    });
  }

  private Collection<String> sort(Collection<String> aSet) {
    ArrayList<String> list = new ArrayList<String>(aSet);
    Collections.sort(list);
    return list;
  }
}
