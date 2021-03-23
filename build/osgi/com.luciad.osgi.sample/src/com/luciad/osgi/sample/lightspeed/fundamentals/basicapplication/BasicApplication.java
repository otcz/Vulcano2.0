package com.luciad.osgi.sample.lightspeed.fundamentals.basicapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ToolTipManager;

import com.luciad.format.gml31.xml.TLcdGML31ModelDecoder;
import com.luciad.geodesy.TLcdGeodeticDatum;
import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelDecoder;
import com.luciad.projection.TLcdEquidistantCylindrical;
import com.luciad.reference.TLcdGridReference;
import com.luciad.util.TLcdNoBoundsException;
import com.luciad.util.TLcdOutOfBoundsException;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspAWTView;
import com.luciad.view.lightspeed.TLspViewBuilder;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.painter.grid.TLspLonLatGridLayerBuilder;
import com.luciad.view.lightspeed.util.TLspViewNavigationUtil;
import com.luciad.view.lightspeed.util.TLspViewTransformationUtil;
import com.luciad.view.swing.TLcdLayerTree;

/**
 * The sample demonstrates how to create and set up a 2D/3D view with some background data.
 */
public class BasicApplication {

  static {
    // Set-up Swing to support heavy weight components, such as the TLspAWTView
    JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
  }

  // The application frame
  private JFrame fFrame;

  /**
   * Instantiates an TLspAWTView that can be added to our GUI.
   *
   * @return The created view.
   */
  private TLspAWTView createView() {
    TLspAWTView view = TLspViewBuilder.newBuilder().buildAWTView();

    view.setLayerFactory(new BasicLayerFactory());

    return view;
  }

  /**
   * Creates and adds the layers that will be visible in the view.
   *
   * @param aView The view.
   *
   * @throws IOException In case of I/O failure.
   */
  protected void initLayers(ILspView aView) throws IOException {
    // TLcdGML31ModelDecoder can decode GML 3.1 files
    ILcdModelDecoder decoder = new TLcdGML31ModelDecoder();
    ILcdModel shpModel = decoder.decode("Data/Gml31/world.gml31");
    Collection<ILspLayer> shpLayer = aView.addLayersFor(shpModel);

    fitViewExtents(aView, shpLayer);

    // Create and add the grid layer
    aView.addLayer(TLspLonLatGridLayerBuilder.newBuilder().build());
  }

  protected void fitViewExtents(ILspView aView, Collection<ILspLayer> aLayers) {
    try {
      // Fit the view to the relevant layers.
      new TLspViewNavigationUtil(aView).fit(aLayers);
    } catch (TLcdOutOfBoundsException e) {
      JOptionPane.showMessageDialog(fFrame,
                                    "Could not fit on layer, layer is outside the valid bounds");
    } catch (TLcdNoBoundsException e) {
      JOptionPane.showMessageDialog(fFrame,
                                    "Could not fit on destination, no valid bounds found");
    }
  }

  /**
   * Opens a JFrame containing our view, tool bar and layer control.
   *
   * @param aView The view.
   */
  private void initGUI(TLspAWTView aView) {
    fFrame = new JFrame("Luciad Lightspeed Fundamentals");
    fFrame.getContentPane().setLayout(new BorderLayout());
    fFrame.getContentPane().add(aView.getHostComponent(), BorderLayout.CENTER);
    fFrame.add(createToolBar(aView), BorderLayout.NORTH);
    fFrame.add(new JScrollPane(new TLcdLayerTree(aView)), BorderLayout.EAST);
    fFrame.setSize(800, 600);
    fFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fFrame.setVisible(true);
  }

  protected JToolBar createToolBar(final ILspView aView) {
    // Create and add toolbar to frame
    JToolBar toolBar = new JToolBar();

    // Create a button group for the radio buttons
    ButtonGroup group = new ButtonGroup();

    // Create a button to switch to 2D
    JRadioButton b2d = new JRadioButton("2D", true);
    b2d.setAction(new AbstractAction("2D") {
      @Override
      public void actionPerformed(ActionEvent e) {
        TLspViewTransformationUtil.setup2DView(
            aView,
            new TLcdGridReference(new TLcdGeodeticDatum(),
                                  new TLcdEquidistantCylindrical()),
            true);
      }
    });
    b2d.setToolTipText("Switch the view to 2D");
    group.add(b2d);

    // Create a button to switch to 3D
    JRadioButton b3d = new JRadioButton("3D", false);
    b3d.setAction(new AbstractAction("3D") {
      @Override
      public void actionPerformed(ActionEvent e) {
        TLspViewTransformationUtil.setup3DView(aView, true);
      }
    });
    b3d.setToolTipText("Switch the view to 3D");
    group.add(b3d);

    // Add the two buttons to the toolbar
    toolBar.add(b2d);
    toolBar.add(b3d);

    return toolBar;
  }

  /**
   * Entry point for starting the application.
   */
  public void start() {
    Runnable runnable = new Runnable() {
      public void run() {
        try {
          TLspAWTView view = createView();
          initGUI(view);
          initLayers(view);
        } catch (IOException e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "Starting the sample failed:\n\t" + e.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    };
    EventQueue.invokeLater(runnable);

  }

  public void stop() {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        fFrame.dispose();
        fFrame = null;
      }
    });
  }
}
