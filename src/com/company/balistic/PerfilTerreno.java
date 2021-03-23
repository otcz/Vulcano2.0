package com.company.balistic;

import com.company.back.EarthTerrainElevationAdapter;
import com.luciad.earth.tileset.ILcdEarthTileSet;
import com.luciad.earth.tileset.terrain.TLcdEarthTileSetElevationProvider;
import com.luciad.geodesy.TLcdEllipsoid;
import com.luciad.lucy.ILcyLucyEnv;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape2D.TLcdLonLatPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.view.ILcdView;
import com.luciad.view.lightspeed.ILspView;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


/**
 *1.  Instancie un nuevo objeto de la clase perfil terreno
 * 2. Set Proporcional x y proporcional Y (Son los valores proporcionales que convierten una distancia en pixeles)
 * 3. Set posicion (posicion entre el arma y el blanco)
 * 4. Set fLucyEnv (Vista del ambiente de Lucy)
 * 5. LLame el metodo calcularPerfilTerreno()
 * 6. obtenga getPolygon que contendra los puntos necesarios para la graficación
 */


public class PerfilTerreno {

    private static PerfilTerreno perfilTerreno;
    Dimension dimension=new Dimension();
    Posicion posicion = new Posicion();
    Polygon polygon=new Polygon();
    double proporcionalX;
    double proporcionalY;
   // public ILcyLucyEnv fLucyEnv;
    private static ILcdView vistaActiva;


    public PerfilTerreno() {
    }

    public void calcularPerfilTerreno(ILcdView vistaActiva) {
        setVistaActiva(vistaActiva);
//        setVistaActiva(getfLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView());
        double cambioPosicion = Math.floor(posicion.getDistancia() / 50);
        polygon=new Polygon();
        for (int i = 0; i <= 46; i++) {
            try {
                TLcdLonLatPoint punto = calculaPolares(posicion.getPuntoA(), posicion.getAzimut(), cambioPosicion * i);


                        polygon.addPoint((int) (cambioPosicion * i*getProporcionalX()), (int)(dimension.getHeight()-(calculaAltura(punto, (ILspView) getVistaActiva())*getProporcionalY())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        TLcdLonLatPoint punto = null;
        try {
            punto = calculaPolares(posicion.getPuntoA(), posicion.getAzimut(), posicion.getDistancia());//Agrega un punto en la ultima posicion (porque cambio posicion no es entero, faltan distancias por agregar
            polygon.addPoint((int) (posicion.getDistancia()*getProporcionalX()), (int)(calculaAltura(punto, (ILspView) getVistaActiva())*getProporcionalY()));
            polygon.addPoint((int) (posicion.getDistancia()*getProporcionalX()), (int) dimension.getHeight());
            polygon.addPoint (0, (int) dimension.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static TLcdLonLatPoint calculaPolares(TLcdLonLatHeightPoint punto, double azimut, double distancia) throws IOException {
        TLcdLonLatPoint nuevoPunto = new TLcdLonLatPoint();
        TLcdEllipsoid ellipsoid = new TLcdEllipsoid();
        ellipsoid.geodesicPointSFCT(punto, distancia, azimut, nuevoPunto);
        return nuevoPunto;
    }

    public double calculaAltura(ILcdPoint point, ILspView vista) {//Carrillo 04Abr2018
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("##.#", simbolos);
//        ILspView vista = (ILspView) getfLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
        ILcdEarthTileSet elevationTileSet = vista.getServices().getTerrainSupport().getElevationTileSet();
        TLcdEarthTileSetElevationProvider proveedorElevacion = new TLcdEarthTileSetElevationProvider(elevationTileSet);
        EarthTerrainElevationAdapter proveedorAltitud = new EarthTerrainElevationAdapter(proveedorElevacion);
        double altura = proveedorAltitud.retrieveAltitudeAt(point, new TLcdGeodeticReference());
        return Double.parseDouble(formateador.format(altura));
    }

    public static PerfilTerreno getSingletonInstance() {
        if (perfilTerreno == null) {
            perfilTerreno = new PerfilTerreno();
        } else {

        }
        return perfilTerreno;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public  ILcdView getVistaActiva() {
        return vistaActiva;
    }

    public void setVistaActiva(ILcdView vistaActiva) {
        PerfilTerreno.vistaActiva = vistaActiva;
    }

//    public ILcyLucyEnv getfLucyEnv() {
//        return fLucyEnv;
//    }
//
//    public void setfLucyEnv(ILcyLucyEnv fLucyEnv) {
//        this.fLucyEnv = fLucyEnv;
//    }

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

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

}
