package com.company.balistic;


/**
 * Created by USUARIO on 01/08/2018.
 */
public class Boletin {

    public enum AplicaBoletin{SI_APLICA,NO_APLICA};
    public AplicaBoletin aplicaBoletin;
    private static Boletin boletin;
    boolean aplica=false;
    double azimutViento;
    double velocidadVientoNudos;
    double porcentajeTemperaturaBalistica;
    double porcentajeDensidadBalistica;

    public double getAzimutViento(int lineaMet) {
        return azimutViento;
    }

    public void setAzimutViento(double azimutViento) {
        this.azimutViento = azimutViento;
    }

    public double getVelocidadVientoNudos(int lineaMet) {
        return velocidadVientoNudos;
    }

    public void setVelocidadVientoNudos(double velocidadVientoNudos) {
        this.velocidadVientoNudos = velocidadVientoNudos;
    }

    public double getPorcentajeTemperaturaBalistica(int lineaMet) {
        return porcentajeTemperaturaBalistica;
    }

    public void setPorcentajeTemperaturaBalistica(double porcentajeTemperaturaBalistica) {
        this.porcentajeTemperaturaBalistica = porcentajeTemperaturaBalistica;
    }

    public double getPorcentajeDensidadBalistica(int lineaMet) {
        return porcentajeDensidadBalistica;
    }

    public void setPorcentajeDensidadBalistica(double porcentajeDensidadBalistica) {
        this.porcentajeDensidadBalistica = porcentajeDensidadBalistica;
    }

    public Boletin() {
        aplicaBoletin= AplicaBoletin.NO_APLICA;

    }
    public static Boletin getSingletonInstance(){
        if (boletin == null) {
            boletin = new Boletin();
        }
        else {
        }
        return boletin;
    }


}
