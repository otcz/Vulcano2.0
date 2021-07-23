/*
 *
 * Copyright (c) 1999-2015 Luciad All Rights Reserved.
 *
 * Luciad grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Luciad.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. LUCIAD AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL LUCIAD OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF LUCIAD HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 */
package com.company.front.globo;
///Creada por Carrillo 14Mar2018
//Actualizada Carrillo 21Mar2018

import com.luciad.gui.TLcdGUIIcon;
import com.luciad.gui.swing.TLcdSWIcon;
import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelListener;
import com.luciad.model.TLcdModelChangedEvent;
import com.luciad.model.TLcdVectorModel;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.ILcdPointList;
import com.luciad.shape.shape2D.*;
import com.luciad.symbology.milstd2525b.model.ILcdMS2525bCoded;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.text.TLcdLonLatPointFormat;
import com.luciad.util.TLcdLonLatCoord;
import com.luciad.util.TLcdLonLatFormatter;
import com.luciad.util.TLcdLonLatParser;
import com.luciad.view.lightspeed.layer.TLspLayerTreeNode;
import com.luciad.view.swing.ALcdBalloonDescriptor;
import com.luciad.view.swing.ILcdBalloonContentProvider;
import com.luciad.view.swing.TLcdModelElementBalloonDescriptor;
import javax.swing.*;
import java.awt.*;


public class mensaje implements ILcdBalloonContentProvider {

    public static TLspLayerTreeNode node;
    public double lat = 0, lon = 0;
    public JPanel pPanePrincipal;
    public JRadioButton rbAgregado;
    public JRadioButton rbSegregado;
    public JRadioButton rbFuerzaTarea;
    public JTextField texCampo2;
    public JTextField texLon;
    public JTextField texLat;
    public JTextField texCampo5;
    public JTextField texCampo6;
    public JLabel lbCampo1;
    public JLabel lbCampo7;
    public JLabel lbCampo3;
    public JLabel lbCampo4;
    public JLabel lbLat;
    public JLabel lbLon;
    public JLabel lbCampo5;
    public JLabel lbCampo6;
    String nombre = null;
    TLcdEditableMS2525bObject simbolo = null;
    ILcdModel model = null;
    ILcdPoint point = null;
    ILcdPointList pointList = null;

    final TLcdLonLatPointFormat format = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2);

    public static TLspLayerTreeNode getNode() {
        return node;
    }

    public static void setNode(TLspLayerTreeNode node) {
        mensaje.node = node;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public boolean canGetContent(ALcdBalloonDescriptor aBalloonDescriptor) {
        return true;
    }

    @Override
    public JComponent getContent(ALcdBalloonDescriptor aBalloonDescriptor) {
        final TLcdModelElementBalloonDescriptor balloonDescriptor = (TLcdModelElementBalloonDescriptor) aBalloonDescriptor;
        final JPanel pCentro;
        final JPanel pCentro1;
        final JPanel pCentro2;
        final JPanel pCentro3;
        final JPanel pNorte;
        final JPanel pSur;
        final String etiquetas[] = {"Misi\u00f3n", "Efectivos", "Municici\u00f3n", "Serial"};
        simbolo = (TLcdEditableMS2525bObject) balloonDescriptor.getObject();
        point = simbolo.getPoint(0);
        pointList = simbolo.getPointList();
        model = balloonDescriptor.getModel();
        nombre = simbolo.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation);

        pPanePrincipal = new JPanel();
        pNorte = new JPanel();
        pCentro = new JPanel();
        pCentro1 = new JPanel();
        pCentro2 = new JPanel();
        pCentro3 = new JPanel();
        pSur = new JPanel();
        lbLon = new JLabel();
        lbLat = new JLabel();
        lbCampo5 = new JLabel();
        lbCampo6 = new JLabel();
        lbCampo7 = new JLabel();
        lbCampo1 = new JLabel("", new TLcdSWIcon(new TLcdGUIIcon(TLcdGUIIcon.GLOBE16)), SwingConstants.LEFT);
        texCampo2 = new JTextField();
        lbCampo3 = new JLabel();
        lbCampo4 = new JLabel();
        texLon = new JTextField();
        texLat = new JTextField();
        texCampo5 = new JTextField();
        texCampo6 = new JTextField();
        rbSegregado = new JRadioButton();
        rbAgregado = new JRadioButton();
        rbFuerzaTarea = new JRadioButton();
        pPanePrincipal.setLayout(new BorderLayout());
        lbCampo1.setText("");
        texCampo2.setText("");
        lbCampo3.setText("");
        lbCampo4.setText("");
        texLon.setText("");
        texLat.setText("");
        texCampo5.setText("");
        texCampo6.setText("");
        lbCampo5.setText("Misi\u00f3n");
        lbCampo6.setText("  Efectivos ");


        lbLat.setText("Latitud");
        lbLon.setText("  Longitud ");
        rbSegregado.setText("Segregado   ");
        rbAgregado.setText("Agregado");
        rbFuerzaTarea.setText("F.tarea");
        texCampo2.setPreferredSize(new Dimension(130, 30));
        texLon.setPreferredSize(new Dimension(130, 30));
        texLat.setPreferredSize(new Dimension(130, 30));
        texCampo5.setPreferredSize(new Dimension(130, 30));
        texCampo6.setPreferredSize(new Dimension(130, 30));
        lbLon.setPreferredSize(new Dimension(62, 30));
        lbLat.setPreferredSize(new Dimension(45, 30));
        lbCampo5.setPreferredSize(new Dimension(45, 30));
        lbCampo6.setPreferredSize(new Dimension(62, 30));
        lbCampo7.setPreferredSize(new Dimension(62, 30));

        texLat.setMinimumSize(new Dimension(90, 30));
        texLon.setMinimumSize(new Dimension(90, 30));
        texCampo5.setMinimumSize(new Dimension(90, 30));
        texCampo6.setMinimumSize(new Dimension(90, 30));

        lbLon.setMinimumSize(new Dimension(62, 30));
        lbLat.setMinimumSize(new Dimension(45, 30));
        lbCampo5.setMinimumSize(new Dimension(45, 30));
        lbCampo6.setMinimumSize(new Dimension(62, 30));
        lbCampo7.setMinimumSize(new Dimension(110, 30));
        rbFuerzaTarea.setMinimumSize(new Dimension(80, 30));
        rbAgregado.setMinimumSize(new Dimension(80, 30));
        rbSegregado.setMinimumSize(new Dimension(80, 30));


        pCentro.setLayout(new GridLayout(3, 0));
        pCentro1.setLayout(new BoxLayout(pCentro1, BoxLayout.LINE_AXIS));
        pCentro1.add(lbCampo4);
        pCentro.add(pCentro1);
        pCentro2.setLayout(new BoxLayout(pCentro2, BoxLayout.LINE_AXIS));
        pCentro2.add(lbLat);
        pCentro2.add(texLat);
        pCentro2.add(lbLon);
        pCentro2.add(texLon);
        pCentro.add(pCentro2);
        pCentro3.setLayout(new BoxLayout(pCentro3, BoxLayout.LINE_AXIS));
        pCentro3.add(lbCampo5);
        pCentro3.add(texCampo5);
        pCentro3.add(lbCampo6);
        pCentro3.add(texCampo6);
        pCentro.add(pCentro3);
        pPanePrincipal.add(pCentro, BorderLayout.CENTER);
        pNorte.setLayout(new BoxLayout(pNorte, BoxLayout.LINE_AXIS));
        pNorte.add(lbCampo1);
        pNorte.add(texCampo2);
        pNorte.add(lbCampo3);
        pPanePrincipal.add(pNorte, BorderLayout.PAGE_START);
        pSur.setLayout(new BoxLayout(pSur, BoxLayout.LINE_AXIS));
        pSur.add(rbAgregado);
        pSur.add(rbSegregado);
        pSur.add(rbFuerzaTarea);
        pSur.add(lbCampo7);
        pPanePrincipal.add(pSur, BorderLayout.PAGE_END);


        return pPanePrincipal;

    }




}
