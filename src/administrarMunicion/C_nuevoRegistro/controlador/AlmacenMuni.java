package administrarMunicion.C_nuevoRegistro.controlador;

import administrarMunicion.C_nuevoRegistro.modelo.Municion;

import java.util.ArrayList;

public class AlmacenMuni {
    public static ArrayList<Municion> arrayList = new ArrayList();

    public static boolean addMuni(Municion municion) {
        return arrayList.add(municion);
    }

    public static ArrayList<Municion> getArrayList() {
        return arrayList;
    }

    public static void setArrayList(ArrayList<Municion> arrayList) {
        AlmacenMuni.arrayList = arrayList;
    }
}
