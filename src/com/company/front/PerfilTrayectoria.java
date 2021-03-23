package com.company.front;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PerfilTrayectoria extends JPanel {
    private static PerfilTrayectoria perfilTrayectoria;
    public ArrayList<Integer> valoresX_Terreno = new ArrayList<>();//Hasta 1481
    public ArrayList<Integer> valoresY_Terreno = new ArrayList<>();//Hasta 197
    public int[] valoresX_Trayectoria = new int[50];//Hasta 1481
    public int[] valoresY_Trayectoria = new int[50];//Hasta 197
    Polygon poligono2;
    double proporcionalX;
    double proporcionalY;



    public void paintComponent(Graphics g) {
        super.paintComponent(g); // llama al método paintComponent de la superclase
// dibuja polígono relleno con objeto Polygon
        g.setColor(new Color(110,154,100));
        g.fillPolygon( poligono2);
        g.setColor(Color.red);
        g.drawPolyline(valoresX_Trayectoria, valoresY_Trayectoria, 50);
        System.out.println("PerfilTrayectoria.paintComponent");
//        repaint();
    } // fin del método paintComponent

    public static int[] convertDoubles(List<Double> doubles) {
        int[] ret = new int[doubles.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = doubles.get(i).intValue();
        }
        return ret;
    }

    public static PerfilTrayectoria getSingletonInstance() {
        if (perfilTrayectoria == null) {
            perfilTrayectoria = new PerfilTrayectoria();
        } else {

        }
        return perfilTrayectoria;
    }

    public Polygon getPoligono2() {
        return poligono2;
    }

    public void setPoligono2(Polygon poligono2) {
        this.poligono2 = poligono2;
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

} // fin de la clase PoligonosJPanel


