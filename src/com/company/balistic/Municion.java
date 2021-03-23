package com.company.balistic;

/**
 * Created by USUARIO on 30/07/2018.
 */
public interface Municion {


    void setCarga(int carga);

    int getCarga();

    void setRasante(boolean isRasante);
    boolean isRasante();

    int getMuzzleVelocity();

    void setCuadros(int cuadros);

    int getCuadros();

    int getCuadrosStandard();
    double getTemperaturaPropelenteStandard();

    String getTipoMunicion();
    double getWeitgh();
    double getC(double elevation);

    double getDeltaCVelInicial(double elevation, double cambioVel, int carga);
    double getDeltaCVientoLongitudinal(double elevation, double vientoLong, int carga);
    double getDeltaCTemperatura(double elevation, double porcTemp, int carga);
    double getDeltaCDensidad(double elevation, double porcDens, int carga);
    double getDeltaCCuadros(double elevation, double inc_O_Dec, int carga);

    int seleccionCargaAutomatica(double distancia, double intervalo);
    boolean isPossible(double distancia, double intervalo, int carga);

    int getTA_LineaMeteorologica(double elevacion, int carga);

    double getTBDistanciaPorComplementario(double distancia, double intervalo, int carga);

    double getTC_ComponenteVientoCruzado(double azimuth);

    double getTC_ComponenteVientoCabeza(double azimuth);

    double getTD_CorreccionTemperatura(double deltaH_Bat_DMP);

    double getTD_CorreccionDensidad(double deltaH_Bat_DMP);

    double getTE_Propellanttemperature(double temperature, int carga);


    double getTFi1_DistanciaApuntada(double elevacion, int carga);

    double getTFi2_Elevacion(double distancia, int carga);

    double getTFi4_HeighExplosure10m(double distancia, int carga);

    double getTFi5_ChangeRangeFor1Mil(double distancia, int carga);

    double getTFi6_Fork(double distancia, int carga);

    double getTFi7_TiempoVuelo(double distancia, int carga);

    double getTFi8_Drift(double tiempoVuelo, int carga);

    double getTFi9_CrossWind(double distancia, int carga);

    double getTFii10_muzzleVelDec(double distancia, int carga);

    double getTFii11_muzzleVelInc(double distancia, int carga);

    double getTFii12_VientoLongitudinal1KNCabeza(double distancia, int carga);

    double getTFii13_VientoLongitudinal1KNCola(double distancia, int carga);


    double getFii14_TempAireDec(double distancia, int carga);

    double getFii15_TempAireInc(double distancia, int carga);


    double getFii16_DensAireDec(double distancia, int carga);

    double getFii17_DensAireInc(double distancia, int carga);

    double getFii18_BoxWeitghDec(double distancia, int carga);

    double getFii19_BoxWeitghInc(double distancia, int carga);

    double getGOrdenadaMax_(double elevacion, int carga);

    double getTG_ErrorProb_Alcance(double tiempoV, int carga);

    double getTG_ErrorProb_Azimut(double tiempoV, int carga);

    double getTG_AnguloComp_Menos(double distancia, int carga);

    double getTG_AnguloComp_Mas(double distancia, int carga);

    double getH_DistanciaPorRotacionTierra(double distancia, double azimut, double latitud, int carga);

}
