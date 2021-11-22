package administrarMunicion.D_nuevoTipoMunicion.control;

import administrarMunicion.D_nuevoTipoMunicion.modelo.TipoMuni;

import java.util.ArrayList;

public class AlmacenTipoMuni {
    public static ArrayList<TipoMuni> arrayList = new ArrayList();

    public static boolean addTipoMuni(TipoMuni tipoMuni) {
        return arrayList.add(tipoMuni);
    }

    public static ArrayList<TipoMuni> getArrayList() {
        return arrayList;
    }

    public static void setArrayList(ArrayList<TipoMuni> arrayList) {
        AlmacenTipoMuni.arrayList = arrayList;
    }
}
