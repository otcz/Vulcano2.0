package com.company.balistic;

import java.awt.*;
import java.util.ArrayList;

/**
 * Clase que tiene dos propiedades:
 * <p>
 * Los valores de masa, y velocidad en boca, los toma directamente de la configuración de la munición
 * <p>
 * para su uso:
 * 1. Cree la instancia del objeto.
 * 2.Llame la funcion calculoTrayectoria(double elevacion, double intervalo)
 * 3. Utilice los valores(X ó Y) para graficar la trayectoria
 * 4. Son Valores en metros, utilice un factor para convertir a pixeles
 */
public class Trayectoria extends Tools {


    private Boletin boletin = Boletin.getSingletonInstance();
    private Configuracion configuracion = Configuracion.getSingletonInstance();

    public ArrayList<Double> X_Trayectoria_m = new ArrayList<Double>();
    public ArrayList<Double> Y_Trayectoria_m = new ArrayList<Double>();
    public int[] X_TrayectoriaPx = new int[50];
    public int[] Y_TrayectoriaPx = new int[50];
    double proporcionalX =0;
    double proporcionalY=0;


    private int carga = configuracion.getCarga();
    private double tiempoVuelo = 0;
    private double ordenadaMaxima = 0;
    private boolean isPosibleDesdePosicion;
    private boolean isPosibleDesdeActitudRas;
    private boolean isPosibleDesdeActitudGA;
    public Posicion posicion;
    public Dimension dimension = new Dimension();


    public Trayectoria(Posicion posicion) {
        this.posicion = posicion;
//        posicion.setDistancia(11000);
//        posicion.setIntervalo(-100);

        //calculaDistancia(0, 74);
        System.out.println("calculaDistancia(intervalo y angulo) = " + calculaDistancia(403));
        System.out.println("calculaAnguloMils() = " + calculaAnguloMils());
//        System.out.println("configuracion.getTipoMunicion() = " + configuracion.getTipoMunicion());
//        System.out.println("configuracion.getCarga() = " + configuracion.getCarga());
//        System.out.println("calculaAnguloMils GA() = " + calculaAnguloMilsGA(posicion));
        System.out.println("tiempoVuelo = " + tiempoVuelo);
        System.out.println("isPosibleDesdePosicion = " + isPosibleDesdePosicion);
        System.out.println("isPosibleDesdeActitudRas = " + isPosibleDesdeActitudRas);
        System.out.println("isPosibleDesdeActitudGA = " + isPosibleDesdeActitudGA);
        System.out.println("ordenadaMaxima = " + ordenadaMaxima);
    }

    public double calculaDistancia(double elevacionMilesimas) {
        /**Metodo que calcula una distancia a partir de un intervalo y un angulo en milesimas
         * esta basado en la configuracion global de municion
         * si es imposible alcanzar el intervalo con esa elevación, puede seterar un booleano, mirar (if t<tmax+0.5)
         */
        double c = calculaC(elevacionMilesimas);

        double x = 0;
        double y = 0;
        double t = 0;
        double g = 9.8;
        double m = configuracion.municionRas.getWeitgh();
        double muzzleVelocity = configuracion.municionRas.getMuzzleVelocity();

        double elevationDegrees = convierteMilesimas_a_Grados(elevacionMilesimas);
        double Vx = muzzleVelocity * Math.cos(Math.toRadians(elevationDegrees));
        double Vy = muzzleVelocity * Math.sin(Math.toRadians(elevationDegrees));

        double tmax = m / c * Math.log(1 + ((c * Vy) / (m * g)));
        ordenadaMaxima = -((Math.pow(m, 2) * g) / Math.pow(c, 2)) * Math.log(1 + (c * Vy) / (m * g)) + ((m * Vy) / (c));

        Y_Trayectoria_m.clear();
        X_Trayectoria_m.clear();
        int i = 0;
        while (y >= posicion.getIntervalo() || t < tmax) {
            y = 0 - (m * g / c) * t + (m / c) * (Vy + (m * g / c)) * (1 - Math.exp(-c * t / m));
            x = (m / c) * Vx * (1 - Math.exp(-c * (t) / m));
            X_Trayectoria_m.add(x);
            Y_Trayectoria_m.add(y);
            t = t + 0.1;
            i++;
            setPosibleDesdePosicion(true);
            if (t > 120) {////Aqui se identifica que el alcance no es posible
                return x = 0;

            }
        }
        setTiempoVuelo(t);
        if (t <= tmax + 0.5) {//El tiempo solamente corre hasta la altura maxima?
            x = 0;
        }
        if (x == 0) setPosibleDesdePosicion(false);
        return x;

    }

    public double calculaAnguloMils() {
        /**Metodo que calcula la elevación aumentando cada 0.1 mils hasta llegar a la distancia e intervalo deseado
         *Solamente calcula para distancia rasante
         */
        double distancia = posicion.getDistancia();
        double elevacion = 0;
        for (int elev1 = -100; elev1 < 1200; elev1 = elev1 + 100) {
            if (calculaDistancia(elev1) < distancia && distancia < calculaDistancia(elev1 + 100)) {
                for (int elev2 = 0; elev2 < 100; elev2 = elev2 + 20) {
                    if (calculaDistancia(elev1 + elev2) < distancia && distancia < calculaDistancia(elev1 + elev2 + 20)) {
                        for (int elev3 = 0; elev3 < 20; elev3 = elev3 + 5) {
                            if (calculaDistancia(elev1 + elev2 + elev3) < distancia && distancia < calculaDistancia(elev1 + elev2 + elev3 + 5)) {
                                for (int elev4 = 0; elev4 < 5; elev4++) {
                                    if (calculaDistancia(elev1 + elev2 + elev3 + elev4) < distancia && distancia < calculaDistancia(elev1 + elev2 + elev3 + elev4 + 1)) {
                                        for (double elev5 = 0; elev5 < 1; elev5 = elev5 + 0.1) {
                                            if (calculaDistancia(elev1 + elev2 + elev3 + elev4 + elev5) < distancia && distancia < calculaDistancia(elev1 + elev2 + elev3 + elev4 + elev5 + 0.1)) {
                                                setPosibleDesdeActitudRas(true);
                                                return elevacion = elev1 + elev2 + elev3 + elev4 + elev5;

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (elevacion == 0) setPosibleDesdeActitudRas(false);
        return elevacion;

    }

    public double calculaAnguloMilsGA(Posicion posicion) {
        /**Metodo que calcula la elevación aumentando cada 0.1 mils hasta llegar a la distancia e intervalo deseado
         *
         */
        double distancia = posicion.getDistancia();
        double elevacion = 800;
        for (int elev1 = 800; elev1 < 1300; elev1 = elev1 + 100) {
            if (calculaDistancia(elev1) > distancia && distancia > calculaDistancia(elev1 + 100)) {
                for (int elev2 = 0; elev2 < 100; elev2 = elev2 + 20) {
                    if (calculaDistancia(elev1 + elev2) > distancia && distancia > calculaDistancia(elev1 + elev2 + 20)) {
                        for (int elev3 = 0; elev3 < 20; elev3 = elev3 + 5) {
                            if (calculaDistancia(elev1 + elev2 + elev3) > distancia && distancia > calculaDistancia(elev1 + elev2 + elev3 + 5)) {
                                for (int elev4 = 0; elev4 < 5; elev4++) {
                                    if (calculaDistancia(elev1 + elev2 + elev3 + elev4) > distancia && distancia > calculaDistancia(elev1 + elev2 + elev3 + elev4 + 1)) {
                                        for (double elev5 = 0; elev5 < 1; elev5 = elev5 + 0.1) {
                                            if (calculaDistancia(elev1 + elev2 + elev3 + elev4 + elev5) > distancia && distancia > calculaDistancia(elev1 + elev2 + elev3 + elev4 + elev5 + 0.1)) {
                                                elevacion = elev1 + elev2 + elev3 + elev4 + elev5;
                                                setPosibleDesdeActitudRas(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (elevacion == 800) setPosibleDesdeActitudGA(false);
        return elevacion;

    }

    public double getDeriva() {
        return configuracion.municionRas.getTFi8_Drift(getTiempoVuelo(), carga);
    }

    public static void main(String[] args) {
        Posicion posicion2 = new Posicion();
        Trayectoria nuevo = new Trayectoria(posicion2);
    }

    private double calculaC(double elevacionMilesimas) {
        /**Metodo privado para calcular el factor C*/
        double c = 0;
        if (boletin.aplicaBoletin == Boletin.AplicaBoletin.SI_APLICA) {

            int lineaMeteorologica = configuracion.municionRas.getTA_LineaMeteorologica(elevacionMilesimas, carga);
            double velocidadViento = boletin.getVelocidadVientoNudos(lineaMeteorologica);
            double azimutViento = boletin.getAzimutViento(lineaMeteorologica);
            double velLongViento = getVelocidadVientoLongitudinal(azimutViento, posicion.getAzimut(), velocidadViento);
            double porcTemp = boletin.getPorcentajeTemperaturaBalistica(lineaMeteorologica) - 100;
            double porcDens = boletin.getPorcentajeDensidadBalistica(lineaMeteorologica) - 100;
            double inc_O_Dec_cuad = configuracion.getCuadros() - configuracion.municionRas.getCuadrosStandard();
            double cambioVel = configuracion.municionRas.getTE_Propellanttemperature(configuracion.getTempProp(), configuracion.getCarga());

            double corrViento = configuracion.municionRas.getDeltaCVientoLongitudinal(elevacionMilesimas, velLongViento, carga);
            double corrVelIni = configuracion.municionRas.getDeltaCVelInicial(elevacionMilesimas, cambioVel, carga);
            double corrTemp = configuracion.municionRas.getDeltaCTemperatura(elevacionMilesimas, porcTemp, carga);
            double corrDens = configuracion.municionRas.getDeltaCDensidad(elevacionMilesimas, porcDens, carga);
            double corrCuadr = configuracion.municionRas.getDeltaCCuadros(elevacionMilesimas, inc_O_Dec_cuad, carga);
            c = configuracion.municionRas.getC(elevacionMilesimas)
                    - corrViento
                    - corrVelIni
                    - corrTemp
                    - corrDens
                    - corrCuadr;
        } else if (boletin.aplicaBoletin == Boletin.AplicaBoletin.NO_APLICA) {

            c = configuracion.municionRas.getC(elevacionMilesimas);

        }

        return c;
    }

    double getVelocidadVientoLongitudinal(double azimutviento, double azimutTiro, double VelocidadViento) {
        /**Descompone el azimut del viento y retorna el viento longitudinal en nudos*/
        double angulo = azimutviento - azimutTiro;
        double anguloGrados = convierteMilesimas_a_Grados(angulo);
        double componenteLongitudinal = Math.cos(Math.toRadians(anguloGrados)) * VelocidadViento;
        return -componenteLongitudinal;//Lo coloco menos (-), porque si es de cola debe acortarse distancia en C
    }

    public void setPuntosGraficaPX() {
        int deltaXtrayectoria = (int) Math.floor(X_Trayectoria_m.size() / 50);//cantidad de espacios entre posiciones del vector para sacar solo 50 puntos

        proporcionalX =dimension.width/posicion.getDistancia();//Al multiplicar una distancia en m por este proporcional entrega la medida en Px
        proporcionalY =dimension.height/getOrdenadaMaxima();//Al multiplicar una distancia en m por este proporcional entrega la medida en Px

        double [] X_Vector = new double [50];//Vector para almacenar solo 50 puntos de la trayectoria
        double [] Y_Vector= new double [50];//Vector para almacenar solo 50 puntos de la trayectoria

        for (int i = 0; i <= 48; i++) {
           X_Vector[i]=X_Trayectoria_m.get(i*deltaXtrayectoria);//Parametrización con solo 50 puntos de la trayectoria
           Y_Vector[i]=Y_Trayectoria_m.get(i*deltaXtrayectoria);//Parametrización con solo 50 puntos de la trayectoria
        }
        X_Vector[49]=X_Trayectoria_m.get(X_Trayectoria_m.size()-1);//Agregar la ultima posición del vector X a la trayectoria
        Y_Vector[49]=Y_Trayectoria_m.get(Y_Trayectoria_m.size()-1);//Agregar la ultima posición del vector y a la trayectoria
        for (int i=0;i<=49;i++){
            X_TrayectoriaPx[i]= (int) (X_Vector[i]*proporcionalX);//la posicion por el proporcional para convertir distancia en pixeles
            Y_TrayectoriaPx[i]=dimension.height-(int)(Y_Vector[i]*proporcionalY);//la posicion por el proporcional para convertir distancia en pixeles
        }

        System.out.println("Trayectoria.setPuntosGraficaPX");
    }

    public double getTiempoVuelo() {
        return tiempoVuelo;
    }

    public void setTiempoVuelo(double tiempoVuelo) {
        this.tiempoVuelo = tiempoVuelo;
    }

    public double getOrdenadaMaxima() {
        return ordenadaMaxima;
    }

    public void setOrdenadaMaxima(double ordenadaMaxima) {
        this.ordenadaMaxima = ordenadaMaxima;
    }

    public boolean isPosibleDesdePosicion() {
        return isPosibleDesdePosicion;
    }

    public void setPosibleDesdePosicion(boolean posibleDesdePosicion) {
        isPosibleDesdePosicion = posibleDesdePosicion;
    }

    public boolean isPosibleDesdeActitudRas() {
        return isPosibleDesdeActitudRas;
    }

    public void setPosibleDesdeActitudRas(boolean posibleDesdeActitudRas) {
        isPosibleDesdeActitudRas = posibleDesdeActitudRas;
    }

    public boolean isPosibleDesdeActitudGA() {
        return isPosibleDesdeActitudGA;
    }

    public void setPosibleDesdeActitudGA(boolean posibleDesdeActitudGA) {
        isPosibleDesdeActitudGA = posibleDesdeActitudGA;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public double getProporcionalX() {
        return proporcionalX;
    }

    public void setProporcionalX(double proporcionalX) {
        this.proporcionalX = proporcionalX;
    }

    public double getProporcionalY() {
        return proporcionalY;
    }

    public void setProporcionalY(double proporcionalY) {
        this.proporcionalY = proporcionalY;
    }

}
