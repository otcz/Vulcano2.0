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

    private static final String RUTA_CAPA_COLOMBIA = "D:\\OTCZ\\Developer\\vulcano\\src\\cartografia\\capaalturacolombia";
    private static final String RUTA_CAPA_MUNDO = System.getProperty("user.home") + "/Archivos Vulcano v2.0/cartografia/world";

    private static final String LABEL_COLOMBIA = "Capa Colombia alturas";
    private static final String LABEL_MUNDO = "Capa mundo";

    public JButtonAddCartografia() {
        setIcon(new ImageIcon(getClass().getResource("mapareset.png")));
        addActionListener(this);
        setToolTipText("Puedes agregar cartografía precargada (Capa Altura Colombia y Capa Mundo)");

        File myDocuments = new File(System.getProperty("user.home") + "/Archivos Vulcano v2.0");
        if (!myDocuments.exists()) {
            new CrearCarpetasVulcano();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != this) return;

        ILspView vista3D = obtenerVista3D();
        if (vista3D == null) return;

        try {
            agregarCapaSiNoExiste(vista3D, RUTA_CAPA_COLOMBIA, LABEL_COLOMBIA);
            agregarCapaSiNoExiste(vista3D, RUTA_CAPA_MUNDO, LABEL_MUNDO);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private ILspView obtenerVista3D() {
        ILcdView vista = getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
        return (vista instanceof ILspView) ? (ILspView) vista : null;
    }

    private void agregarCapaSiNoExiste(ILspView vista3D, String ruta, String label) throws IOException {
        if (yaExisteCapa(vista3D, label)) return;

        ILspLayer capa = crearCapaDesdeRuta(ruta, label);
        if (capa != null) {
            vista3D.addLayer(capa);
            if (label.equals(LABEL_COLOMBIA)) {
                vista3D.moveLayerAt(vista3D.layerCount() - 1, capa); // mover al fondo
            }
        }
    }

    private boolean yaExisteCapa(ILspView vista3D, String label) {
        for (int i = 0; i < vista3D.layerCount(); i++) {
            String capaLabel = vista3D.getLayer(i).getLabel();
            if (capaLabel.equalsIgnoreCase(label) || (label.equals(LABEL_MUNDO) && capaLabel.equalsIgnoreCase("World"))) {
                return true;
            }
        }
        return false;
    }

    private ILspLayer crearCapaDesdeRuta(String ruta, String label) throws IOException {
        TLcdEarthRepositoryModelDecoder decoder = new TLcdEarthRepositoryModelDecoder();
        ILcdModel model = decoder.decode(ruta);
        BasicLayerFactory factory = new BasicLayerFactory();
        ILspLayer layer = factory.createLayer(model);
        layer.setLabel(label);
        return layer;
    }
}
