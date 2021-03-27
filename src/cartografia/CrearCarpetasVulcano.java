package cartografia;

import java.io.*;
import java.util.ArrayList;

public class CrearCarpetasVulcano {
    ArrayList<String> nombres = new ArrayList<>();
    ArrayList<File> carpetas = new ArrayList<>();

    public CrearCarpetasVulcano() {
        nombres.add("Cartografia");
        nombres.add("Dispositivos");
        nombres.add("Espacios de trabajo");
        nombres.add("Capas");
        nombres.add("Otros recursos");
        File myDocuments = new File(System.getProperty("user.home") + "/" + "Archivos Vulcano v2.0");
        if (!myDocuments.exists()) {
            myDocuments.mkdir();
            crearSubCarpetas();
        } else {
            crearSubCarpetas();
        }

        File capaalturacolombia = new File(getClass().getResource("capaalturacolombia").getPath());
        File world = new File(getClass().getResource("Data/Shp/World").getPath());
      //  File word = new File(getClass().getResource("Formato.xlsx").getPath());


        copiarDirectorio(capaalturacolombia.getPath(), carpetas.get(0).getPath()+"/capa altura colombia");
        copiarDirectorio(world.getPath(), carpetas.get(0).getPath()+"/world");
      //  copiarDirectorio(word.getPath(), carpetas.get(1).getPath());

    }

    public void crearSubCarpetas() {
        for (int i = 0; i < nombres.size(); i++) {
            if (!(new File(System.getProperty("user.home") + "/Archivos Vulcano v2.0/" + nombres.get(i)).exists())) {
                File file = new File(System.getProperty("user.home") + "/Archivos Vulcano v2.0/" + nombres.get(i));
                file.mkdir();
                carpetas.add(file);

            }
        }
        File Colombia = new File(System.getProperty("user.home") + "/Archivos Vulcano v2.0/" + nombres.get(0) + "/Capa altura Colombia");
        File World = new File(System.getProperty("user.home") + "/Archivos Vulcano v2.0/" + nombres.get(0) + "/World");
        Colombia.mkdir();
        World.mkdir();
    }

    private void copiarArchivo(String sOrigen, String sDestino) {
        try {
            File origen = new File(sOrigen);
            File destino = new File(sDestino);
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copiarDirectorio(String origen, String destino) {
        comprobarCrearDirectorio(destino);
        File directorio = new File(origen);
        File f;
        if (directorio.isDirectory()) {
            comprobarCrearDirectorio(destino);
            String[] files = directorio.list();
            if (files.length > 0) {
                for (String archivo : files) {
                    f = new File(origen + File.separator + archivo);
                    if (f.isDirectory()) {
                        comprobarCrearDirectorio(destino + File.separator + archivo + File.separator);
                        copiarDirectorio(origen + File.separator + archivo + File.separator, destino + File.separator + archivo + File.separator);
                    } else { //Es un archivo
                        copiarArchivo(origen + File.separator + archivo, destino + File.separator + archivo);
                    }
                }
            }
        }
    }

    private void comprobarCrearDirectorio(String ruta) {
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }


}
