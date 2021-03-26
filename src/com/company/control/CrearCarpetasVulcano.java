package com.company.control;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;

public class CrearCarpetasVulcano {
    ArrayList<String> nombres = new ArrayList<>();

    public CrearCarpetasVulcano() {
        nombres.add("Cartografia");
        nombres.add("Dispositivos");
        nombres.add("Espacios de trabajo");
        nombres.add("Capas");
        nombres.add("Otros recursos");
        File myDocuments = new File(System.getProperty("user.home") + "/" + nombres.get(0));
        if (!myDocuments.exists()) {
            myDocuments.mkdir();
        } else {
            crearSubCarpetas();
        }


    }

    public void crearSubCarpetas() {
        for (int i = 0; i < nombres.size(); i++) {
            if (!(new File(System.getProperty("user.home") + "/Archivos Vulcano/" + nombres.get(i)).exists())) {
                new File(System.getProperty("user.home") + "/Archivos Vulcano/" + nombres.get(i)).mkdir();
            }
        }
    }


    public static void main(String[] args) {
        new CrearCarpetasVulcano();
    }
}
