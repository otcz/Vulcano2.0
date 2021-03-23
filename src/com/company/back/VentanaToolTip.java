package com.company.back;

import com.luciad.model.TLcdVectorModel;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.symbology.app6a.model.ILcdAPP6ACoded;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.ILcdMS2525bCoded;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.text.TLcdLonLatPointFormat;
import com.luciad.util.TLcdOutOfBoundsException;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspAWTView;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.util.TLspViewNavigationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;

public class VentanaToolTip extends JDialog implements MouseListener, ActionListener, FocusListener {
    public JPanel panel;
    public JLabel lbTextoToolTip, lbCoordenadas;
    public JButton btnIra;
    public JComboBox cbMostrarToolTip;
    public ILspLayer layer;
    public ILcdPoint point = new TLcdLonLatHeightPoint(0, 0, 0);
    public boolean estadoMouseCB, entroJD;
    public Timer timer;
    public int delay = 10;

    public VentanaToolTip(JComboBox cbMostrarToolTip) {
        this.layer = layer;
        this.cbMostrarToolTip = cbMostrarToolTip;
        setUndecorated(true);
        setAlwaysOnTop(true);
        this.setOpacity(0.8f);
        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        lbTextoToolTip = new JLabel();
        lbCoordenadas = new JLabel();
        btnIra = new JButton();

        panel.setBackground(new Color(153, 153, 153));
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        lbTextoToolTip.setText("     ");
        panel.add(lbTextoToolTip);

        lbCoordenadas.setText(new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2).format(this.point) + "   ");
        panel.add(lbCoordenadas);

        btnIra.setIcon(new ImageIcon((getClass().getResource("mapaInicio.png"))));
        panel.add(btnIra);
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        cbMostrarToolTip.addMouseListener(this);
        btnIra.addMouseListener(this);
        addMouseListener(this);
        btnIra.addActionListener(this);
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                timer.stop();
            }
        });
        cbMostrarToolTip.addFocusListener(this);


    }

    public Object getObjetoSistema(String nombre) {
        int c1 = 0, c2 = 0;
        Object object = "null";
        Enumeration enumeration;
        TLcdVectorModel vectorModel = null;
        TLcdEditableMS2525bObject editableMS2525bObject = null;
        TLcdEditableAPP6AObject editableAPP6AObject;
        if (nombre != null && !nombre.isEmpty()) {
            if (getiLcyLucyEnv().getMainMenuBar().getComponent().isValid()) {
                if (getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof TLspAWTView) {
                    ILspView vista = (ILspView) getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
                    for (int i = 0; i < vista.layerCount(); i++) {
                        enumeration = vista.getLayer(i).getModel().elements();
                        c1 = 0;
                        while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                            vectorModel = (TLcdVectorModel) vista.getLayer(i).getModel();
                            editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c1);
                            if (editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation).equals(nombre)) {
                                object = editableMS2525bObject;
                            }
                            c1++;
                        }
                        enumeration = vista.getLayer(i).getModel().elements();
                        c2 = 0;
                        while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                            vectorModel = (TLcdVectorModel) vista.getLayer(i).getModel();
                            editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c2);
                            if (editableAPP6AObject.getTextModifierValue(ILcdAPP6ACoded.sUniqueDesignation).equals(nombre)) {
                                object = editableAPP6AObject;
                            }
                            c2++;
                        }
                    }
                } else if (getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof ILcdGXYView) {
                    ILcdGXYView vista = (ILcdGXYView) getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
                    for (int i = 0; i < vista.layerCount(); i++) {
                        enumeration = vista.getLayer(i).getModel().elements();
                        c1 = 0;
                        while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                            vectorModel = (TLcdVectorModel) vista.getLayer(i).getModel();
                            editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c1);
                            if (editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation).equals(nombre)) {
                                object = editableMS2525bObject;
                            }
                            c1++;
                        }
                        enumeration = vista.getLayer(i).getModel().elements();
                        c2 = 0;
                        while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                            vectorModel = (TLcdVectorModel) vista.getLayer(i).getModel();
                            editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c2);
                            if (editableAPP6AObject.getTextModifierValue(ILcdAPP6ACoded.sUniqueDesignation).equals(nombre)) {
                                object = editableAPP6AObject;
                            }
                            c2++;
                        }
                    }
                }

            }


        }

        return object;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(cbMostrarToolTip)) {
            if (this.isVisible() == false && cbMostrarToolTip.getSelectedIndex() >= 0) {
                System.out.println("isVisible y getSelectedIndex()>=0");
                Object object = getObjetoSistema(cbMostrarToolTip.getSelectedItem().toString());
                TLcdEditableAPP6AObject editableAPP6AObject;
                TLcdEditableMS2525bObject editableMS2525bObject;
                if (object instanceof TLcdEditableMS2525bObject) {
                    System.out.println("TLcdEditableMS2525bObject");
                    editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                    System.out.println(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
                    lbCoordenadas.setText(new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2).format(editableMS2525bObject.getPoint(0)) + "     ");
                    point = editableMS2525bObject.getPoint(0);
                } else if (object instanceof TLcdEditableAPP6AObject) {
                    editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                    System.out.println(editableAPP6AObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));

                    lbCoordenadas.setText(new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2).format(editableAPP6AObject.getPoint(0)) + "     ");
                    point = editableAPP6AObject.getPoint(0);
                }
                lbTextoToolTip.setText(cbMostrarToolTip.getSelectedItem().toString() + "     ");

                Point location = new Point();
                location.setLocation(e.getXOnScreen(), (e.getYOnScreen()));
                setLocation(location);
                this.setVisible(true);
                System.out.println("this.setVisible(true);");
                pack();
            } else {
                timer.setDelay(delay);
                timer.stop();
                System.out.println("ELSE");
            }

        } else if (e.getSource().equals(this)) {
            timer.setDelay(delay);
            timer.stop();
        } else if (e.getSource().equals(btnIra)) {
            timer.setDelay(delay);
            timer.stop();
        }


    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(cbMostrarToolTip)) {
            if (isVisible()) {
                timer.setDelay(delay);
                timer.start();
            }

        } else if (e.getSource().equals(this)) {
            timer.setDelay(delay);
            timer.start();

        } else if (e.getSource().equals(btnIra)) {
            timer.setDelay(delay);
            timer.start();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnIra)) {
            if (getiLcyLucyEnv().getMainMenuBar().getComponent().isValid()) {
                if (getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof TLspAWTView) {
                    ILspView vista = (ILspView) getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
                    TLspViewNavigationUtil navigationUtil = new TLspViewNavigationUtil(vista);
                    try {
                        navigationUtil.fit(point.getBounds(), new TLcdGeodeticReference());
                    } catch (TLcdOutOfBoundsException ex) {
                        ex.printStackTrace();
                    }
                } else if (getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof ILcdGXYView) {
                    ILcdGXYView vista = (ILcdGXYView) getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();

                }

            }

        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource().equals(cbMostrarToolTip)) {
            dispose();
        }
    }
}
