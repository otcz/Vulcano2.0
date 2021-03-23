package com.luciad.osgi.installer;

import static com.luciad.osgi.installer.AntSupport.runAntTask;
import static com.luciad.osgi.installer.IOSupport.file;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;

/**
 * This step provides the Apache Felix OSGi runtime.
 *
 * This step is optional, but allows you to easily get the sample running by running the start_felix script.
 */
public class ProvideFelixStep implements InstallerStep {

  @Override
  public void execute() throws Exception {
    provideFelixFramework();
  }

  private void provideFelixFramework() throws IOException {
    final File felixDirectory = file("distrib");
    IOSupport.mkdirs(felixDirectory);

    runAntTask(new Copy(), new AntSupport.Configure<Copy>() {
      @Override
      public void configure(Copy copy) {
        copy.setTodir(felixDirectory);
        FileSet felix = new FileSet();
        felix.setDir(file("build", "osgi"));
        felix.setIncludes("felix-framework*/**,start_felix.*");
        copy.addFileset(felix);
      }
    });
  }
}
