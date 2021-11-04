package com.company.back;
import com.luciad.shape.ILcdPoint;
import com.luciad.symbology.milstd2525b.model.ELcdMS2525Standard;
import com.luciad.symbology.milstd2525b.model.ILcdMS2525bCoded;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;

import java.util.ArrayList;

public class SimboloMilStd2525b extends TLcdEditableMS2525bObject {

   private ArrayList ListMunicion = new ArrayList<Municion>();
   private String ubicacion, mantenimiento;

    public SimboloMilStd2525b() {
    }

    public SimboloMilStd2525b(ELcdMS2525Standard aStandard) {
        super(aStandard);
    }

    public SimboloMilStd2525b(String aMS2525bCode) {
        super(aMS2525bCode);
    }

    public SimboloMilStd2525b(String aMS2525bCode, ELcdMS2525Standard aStandard) {
        super(aMS2525bCode, aStandard);
    }

    public SimboloMilStd2525b(double aLon, double aLat) {
        super(aLon, aLat);
    }

    public SimboloMilStd2525b(ILcdPoint aPoint) {
        super(aPoint);
    }

    public SimboloMilStd2525b(double aLon, double aLat, double aHeight) {
        super(aLon, aLat, aHeight);
    }

    public SimboloMilStd2525b(ILcdMS2525bCoded aMS2525bCoded) {
        super(aMS2525bCoded);
    }

    public SimboloMilStd2525b(String aMS2525bCode, ILcdPoint[] aPoints) {
        super(aMS2525bCode, aPoints);
    }


    public ArrayList getListMunicion() {
        return ListMunicion;
    }

    public void setListMunicion(ArrayList listMunicion) {
        ListMunicion = listMunicion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(String mantenimiento) {
        this.mantenimiento = mantenimiento;
    }
}
