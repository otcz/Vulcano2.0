package com.luciad.osgi.installer;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * Utility class to use Ant's tasks.
 */
public class AntSupport {

  private AntSupport() {
  }

  public static <T extends Task> void runAntTask(T aTask, Configure<T> aConfiguration) {
    Project project = new Project();
    project.setBaseDir(new File(".").getAbsoluteFile());
    aTask.setProject(project);
    aConfiguration.configure(aTask);
    aTask.execute();
  }

  public static interface Configure<T extends Task> {
    public void configure(T aTask);
  }
}
