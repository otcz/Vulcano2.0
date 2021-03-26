package com.company.back;

import cartografia.CrearCarpetasVulcano;
import com.company.control.BasicLayerFactory;
import com.luciad.earth.model.TLcdEarthRepositoryModelDecoder;
import com.luciad.model.ILcdModel;
import com.luciad.view.ILcdView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.layer.ILspLayer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;

public class JButtonAddCartografia extends JButton implements ActionListener {

    public JButtonAddCartografia() {
        setIcon(new ImageIcon((getClass().getResource("mapareset.png"))));
        addActionListener(this);
        setToolTipText("Puedes agregar cartografia predecargada (Capa Altura Colombia y Capa Mundo o World)");
        File myDocuments = new File(System.getProperty("user.home") + "/" + "Archivos Vulcano v2.0");
        if (!myDocuments.exists()){
           new CrearCarpetasVulcano();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            try {

                isPosibleGraficarCapaColombia(colombia( System.getProperty("user.home") + "/" + "Archivos Vulcano v2.0/cartografia/capa altura colombia"));
                isPosibleGraficarCapaMundo(capaMundo( System.getProperty("user.home") + "/" + "Archivos Vulcano v2.0/cartografia/world"));
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
