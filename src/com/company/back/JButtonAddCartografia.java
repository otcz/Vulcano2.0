package com.company.back;

import com.company.control.BasicLayerFactory;
import com.luciad.earth.model.TLcdEarthRepositoryModelDecoder;
import com.luciad.model.ILcdModel;
import com.luciad.model.TLcdModelDescriptor;
import com.luciad.model.TLcdVectorModel;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.util.TLcdNoBoundsException;
import com.luciad.util.TLcdOutOfBoundsException;
import com.luciad.view.ILcdLayer;
import com.luciad.view.ILcdView;
import com.luciad.view.gxy.ILcdGXYLayer;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.gxy.TLcdGXYLayer;
import com.luciad.view.gxy.TLcdGXYViewFitAction;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspLayer;
import com.luciad.view.lightspeed.util.TLspViewNavigationUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;

public class JButtonAddCartografia extends JButton implements ActionListener {

    public JButtonAddCartografia() {
        setIcon(new ImageIcon((getClass().getResource("mapareset.png"))));
        addActionListener(this);
        setToolTipText("Puedes agregar cartografia predecargada");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            try {
                isPosibleGraficarCapaColombia(colombia("src/capa altura colombia"));
                isPosibleGraficarCapaMundo(capaMundo("src/Data/Earth/World"));
            }
            catch (Exception n)
            {

            }


        }

    }

    public ILspLayer colombia(String ruta) {
        TLcdEarthRepositoryModelDecoder decoder = new TLcdEarthRepositoryModelDecoder();
        ILspLayer layer = null;
        try {
            ILcdModel model = decoder.decode(ruta);
            BasicLayerFactory factory = new BasicLayerFactory();
            layer = factory.createLayer(model);
            layer.setLabel("Capa Colombia alturas");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return layer;

    }

    public ILspLayer capaMundo(String ruta) {

        TLcdEarthRepositoryModelDecoder decoder = new TLcdEarthRepositoryModelDecoder();
        ILspLayer layer = null;

        try {
            ILcdModel model = decoder.decode(ruta);

            BasicLayerFactory factory = new BasicLayerFactory();
            layer = factory.createLayer(model);
            layer.setLabel("Capa mundo");


        } catch (IOException e) {
            e.printStackTrace();
        }
        return layer;

    }

    private boolean isPosibleGraficarCapaMundo(ILspLayer layer) {
        ILcdView vista = getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
        ILspView vista3D = null;
        if (vista instanceof ILspView) {
            vista3D = (ILspView) vista;
            for (int i = 0; i < vista3D.layerCount(); i++) {
                if (vista3D.getLayer(i).getLabel().equals("Capa mundo") | vista3D.getLayer(i).getLabel().equals("World")) {
                    return false;
                }
            }

        }
        vista3D.addLayer(layer);
        return true;
    }

    private boolean isPosibleGraficarCapaColombia(ILspLayer layer) {
        ILcdView vista = getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
        ILspView vista3D = null;
        if (vista instanceof ILspView) {
            vista3D = (ILspView) vista;
            for (int i = 0; i < vista3D.layerCount(); i++) {
                if (vista3D.getLayer(i).getLabel().equals("Capa Colombia alturas")) {
                    return false;
                }
            }

        }
        vista3D.addLayer(layer);
        vista3D.moveLayerAt(vista3D.layerCount()-1,layer);
        return true;
    }


}
