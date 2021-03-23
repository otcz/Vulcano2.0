package com.luciad.osgi.sample;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.luciad.osgi.sample.lightspeed.fundamentals.basicapplication.BasicApplication;

public class Activator implements BundleActivator {

  private BasicApplication fApplication;

  public void start(BundleContext bundleContext) throws Exception {
    System.setProperty("jogamp.gluegen.UseTempJarCache", "false");
    fApplication = new BasicApplication();
    fApplication.start();
  }

  public void stop(BundleContext bundleContext) throws Exception {
    fApplication.stop();
    fApplication = null;
  }
}
