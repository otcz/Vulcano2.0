package com.company.balistic;

/**
 * Por defecto inicia configurando:
 * Municion ER50 BB
 * Carga 1
 * Tiro rasante
 * Unidades : metros y milesimas
 */
public class Configuracion {


    private static Configuracion configuracion;
    public Municion municionRas;
    public Municion municionGA;
    public double declinacionMagnetica = 100;
    public int carga=2;
    public int cuadros=2;
    public double tempProp=20.0;
    public TipoMunicion tipoMunicion;
    public TipoCalculo tipoCalculo;




    public enum TipoMunicion {ER50, M1}
    public enum TipoCalculo {NULL,COORDENADAS,POLARES,PUNTERIA}

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        //Verifica si La Municion ER/50 esta en el rango de carga correcto
        if (getTipoMunicion()== TipoMunicion.ER50&&carga>=1&&carga<=2){
            this.municionRas.setCarga(carga);
            this.municionGA.setCarga(carga);
            this.carga = carga;
        }//SI esta fuera de Rango muy alto, la setea al maximo (2)
        else if (getTipoMunicion()== TipoMunicion.ER50&&carga>2){
            this.municionRas.setCarga(2);
            this.municionGA.setCarga(2);
            System.out.println("Configuracion.setCarga");
            System.out.println("Carga demasiado alta");
        }//Si está fuera de rango muy bajo, se setea al minimo (1)
        else if (getTipoMunicion()== TipoMunicion.ER50&&carga<1){
            this.municionRas.setCarga(1);
            this.municionGA.setCarga(1);
            System.out.println("Configuracion.setCarga");
            System.out.println("Carga demasiado Baja");
        }
        //Verifica si La Municion M1 esta en el rango de carga correcto

         if (getTipoMunicion()== TipoMunicion.M1&&carga>=1&&carga<=7){
            this.municionRas.setCarga(carga);
            this.municionGA.setCarga(carga);
            this.carga = carga;
        }
         else if (getTipoMunicion()== TipoMunicion.M1&&carga>7){
             this.municionRas.setCarga(7);
             this.municionGA.setCarga(7);
             System.out.println("Configuracion.setCarga");
             System.out.println("Carga demasiado alta, setada en 7");
         }//Si está fuera de rango muy bajo, se setea al minimo (1)
         else if (getTipoMunicion()== TipoMunicion.M1&&carga<1){
             this.municionRas.setCarga(1);
             this.municionGA.setCarga(1);
             System.out.println("Configuracion.setCarga");
             System.out.println("Carga demasiado Baja Seteada en 1");
         }
    }

    public int getCuadros() {
        return cuadros;
    }

    public void setCuadros(int cuadros) {
        this.municionRas.setCuadros(cuadros);
        this.cuadros = cuadros;
    }

    public double getTempProp() {
        return tempProp;
    }

    public void setTempProp(double tempProp) {
        this.tempProp = tempProp;
    }


    public TipoMunicion getTipoMunicion() {
        return tipoMunicion;
    }

    public void setTipoMunicion(TipoMunicion tipoMunicion) {
        if (tipoMunicion == TipoMunicion.ER50) {
            municionRas = new MunicionER50();
            municionGA = new MunicionER50();
        } else if (tipoMunicion == TipoMunicion.M1) {
            municionRas = new AmmunitionM1();
            municionGA = new AmmunitionM1();
        }
        municionRas.setRasante(true);
        municionGA.setRasante(false);
        this.tipoMunicion = tipoMunicion;
    }


    public TipoCalculo getTipoCalculo() {
        return tipoCalculo;
    }

    public void setTipoCalculo(TipoCalculo tipoCalculo) {
        this.tipoCalculo = tipoCalculo;
    }

    public Configuracion() {
        setTipoMunicion(TipoMunicion.ER50);
        setCarga(1);
        setCuadros(2);
        setTipoCalculo(TipoCalculo.NULL);
    }


    public static Configuracion getSingletonInstance() {
        if (configuracion == null) {
            configuracion = new Configuracion();
        } else {

        }
        return configuracion;
    }

}
