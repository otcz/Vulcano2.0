package com.company.balistic;

import java.io.File;

public abstract class Tools {

    public double interpola(double[] x, double[] y, double valor) {
        double m = 0;
        double yo = 0;
        double resultado = 0;
        for (int i = 0; i < x.length - 1; i++) {

            if (x[i] >= valor && valor <= x[i + 1]) {
                m = (y[i + 1] - y[i]) / (x[i + 1] - x[i]);
                yo = y[i];
                return resultado = yo + m * (valor - x[i]);
            }

        }
        return resultado;
    }

    public double restringeValores(double valor, double valorMaximo, double valorMinimo){
        /**Funcion que reinicia un valor ciclico, por ejemplo si azimut == 6500, lo cambia a 100*/
        if (valor>valorMaximo)valor=valor-valorMaximo;
        else if (valor<valorMinimo)valor=valor+valorMaximo;
        return valor;
    }
    public double convierteGrados_a_Milesimas(double valorGrados) {
        valorGrados = valorGrados * 6400 / 360;
        return valorGrados;
    }
    public double convierteMetros_a_Km(double valorMetros) {
        return valorMetros/1000;
    }
    public double convierteKm_a_Metros(double valorKm) {
                   return valorKm*1000;
    }
    public double convierteMilesimas_a_Grados(double valorMilesimas) {
        valorMilesimas = valorMilesimas * 360 / 6400;
        return valorMilesimas;
    }
    public double potencia(double numero, int exponente){
        return Math.pow(numero,exponente);

    }
    public boolean isRank(double valor, double valorMaximo, double valorMinimo){
        /**Funcion que debuelve true si un valor esta dentro de un rango*/
        if (valor >=valorMinimo && valor <= valorMaximo) return true;
        else return false;
    }
    public double calculaAnguloCatetosMilesimas(double catetoOpuesto, double catetoAdyacente){
        return convierteGrados_a_Milesimas(Math.toDegrees(Math.atan(catetoOpuesto/catetoAdyacente)));
    }

    public boolean sonValoresIguales(double valor1, double valor2){
     if (valor1==valor2)return true;
     else return false;
    }

    public double limitaDecimales(double valor){
        return (double)Math.round(valor * 100d) / 100d;
    }

}
