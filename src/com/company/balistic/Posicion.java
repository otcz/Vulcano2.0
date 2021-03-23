package com.company.balistic;


import com.company.control.DatosTiro;
import com.luciad.geodesy.TLcdEllipsoid;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;

/**
 * Created by USUARIO on 14/07/2018.
 * Al crear un objeto con el contructor, automaticamente calcula: distancia, azimut e intervalo.
 * Si solamente se crea con el constructor sin argumento, debe setearse manualmente
 * llamar los getDistancia, getAzimut y getIntervalo;
 */
public class Posicion extends Tools {

    private TLcdLonLatHeightPoint puntoA;

    private TLcdLonLatHeightPoint puntoB;

    private TLcdEllipsoid ellipsoid = new TLcdEllipsoid();

    private int distancia;
    private int azimut;
    private int intervalo;


    public Posicion() {

    }

    public Posicion(TLcdLonLatHeightPoint puntoA, TLcdLonLatHeightPoint puntoB) {
        this.puntoA = puntoA;
        this.puntoB = puntoB;
        setDistancia();
        setAzimut();
        setIntervalo();
    }

    public void setDistancia() {
        this.distancia = (int) ellipsoid.rhumblineDistance(puntoA, puntoB);
    }

    public void setAzimut() {
        this.azimut = (int) (ellipsoid.forwardAzimuth2D(puntoA, puntoB) * 1000);
    }

    public void setIntervalo() {
        this.intervalo = (int) (puntoB.getZ() - puntoA.getZ());
    }

    public double getDistancia() {
        return distancia;
    }

    public double getAzimut() {
        return azimut;
    }

    public double getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(double intervalo) {
        this.intervalo = (int) intervalo;
    }

    public TLcdLonLatHeightPoint getPuntoA() {
        return puntoA;
    }

    public void setPuntoA(TLcdLonLatHeightPoint puntoA) {
        this.puntoA = puntoA;
    }

    public TLcdLonLatHeightPoint getPuntoB() {
        return puntoB;
    }

    public void setPuntoB(TLcdLonLatHeightPoint puntoB) {
        this.puntoB = puntoB;
    }

}
