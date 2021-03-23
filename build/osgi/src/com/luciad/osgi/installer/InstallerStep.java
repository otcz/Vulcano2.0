package com.luciad.osgi.installer;

/**
 * Interface representing a single step in the installation process.
 */
public interface InstallerStep {

  public void execute() throws Exception;
}
