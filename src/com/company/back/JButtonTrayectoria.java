package com.company.back;

import com.company.balistic.PerfilTerreno;
import com.company.balistic.Posicion;
import com.company.balistic.Trayectoria;
import com.company.front.DatosTirofrm;
import com.company.front.PerfilTrayectoria;
import com.luciad.earth.tileset.ILcdEarthTileSet;
import com.luciad.earth.tileset.terrain.TLcdEarthTileSetElevationProvider;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.text.TLcdLonLatPointFormat;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspAWTView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import static com.company.ShowReadMeAddOn.getContent;
import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;
import static com.company.front.ToolBar.getVista;

public class JButtonTrayectoria extends JButton implements ActionListener {
    DatosTirofrm datosTirofrm = null;

    public JButtonTrayectoria() {
        super();
        setIcon(new ImageIcon(getClass().getResource("canonTrayectoria.png")));
        addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            trayectoria();
        }
    }

    public void trayectoria() {
        if (getVista() instanceof ILspView) {
            Container cp = getContent().getParent();
            PerfilTrayectoria perfilTrayectoria =new PerfilTrayectoria();
            PerfilTerreno perfilTerreno = new PerfilTerreno(new Posicion(getPosicionSistemaSeleccionado(), getPosicionBlancoSeleccionado()));
            Trayectoria trayectoria = new Trayectoria(new Posicion(getPosicionSistemaSeleccionado(), getPosicionBlancoSeleccionado()));
            Dimension dimension = new Dimension(1200, 180);

            trayectoria.setDimension(dimension);
            trayectoria.setPuntosGraficaPX();
            perfilTrayectoria.setProporcionalX(trayectoria.getProporcionalX());
            perfilTrayectoria.setProporcionalY(trayectoria.getProporcionalY());
            perfilTrayectoria.valoresX_Trayectoria = trayectoria.X_TrayectoriaPx;
            perfilTrayectoria.valoresY_Trayectoria = trayectoria.Y_TrayectoriaPx;
            perfilTerreno.setPosicion(new Posicion(getPosicionSistemaSeleccionado(), getPosicionBlancoSeleccionado()));
            perfilTerreno.setProporcionalX(perfilTrayectoria.getProporcionalX());
            perfilTerreno.setProporcionalY(perfilTrayectoria.getProporcionalY());
            perfilTerreno.setDimension(cp.getSize());
            perfilTerreno.calcularPerfilTerreno(getVista());
            perfilTrayectoria.setPoligono2(perfilTerreno.getPolygon());
            perfilTrayectoria.paintComponent(getContent().getGraphics());
            cp.add(perfilTrayectoria);
            cp.setVisible(false);
        }

    }

    public TLcdLonLatHeightPoint getPosicionSistemaSeleccionado() {
        ILcdPoint point = null;
        try {

            point = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2).parseObject(getDatosTirofrm().texResultadoCoordenadasC.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new TLcdLonLatHeightPoint(point.getX(), point.getY(), calculaAltura(point));
    }

    public TLcdLonLatHeightPoint getPosicionBlancoSeleccionado() {
        ILcdPoint point = null;
        try {
            point = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2).parseObject(getDatosTirofrm().texResultadoCoordenadasB.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new TLcdLonLatHeightPoint(point.getX(), point.getY(), calculaAltura(point));
    }

    public double calculaAltura(ILcdPoint point) {
        ILcdEarthTileSet elevationTileSet = null;
        if (getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof TLspAWTView) {
            ILspView vista = (ILspView) getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
            elevationTileSet = vista.getServices().getTerrainSupport().getElevationTileSet();
            TLcdEarthTileSetElevationProvider proveedorElevacion = new TLcdEarthTileSetElevationProvider(elevationTileSet);
            EarthTerrainElevationAdapter proveedorAltitud = new EarthTerrainElevationAdapter(proveedorElevacion);
            return proveedorAltitud.retrieveAltitudeAt(point, new TLcdGeodeticReference());
        } else if (getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof ILcdGXYView) {
            ILcdGXYView vista = (ILcdGXYView) getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
            return 00;
        }

        return 00;
    }

    public DatosTirofrm getDatosTirofrm() {
        return datosTirofrm;
    }

    public void setDatosTirofrm(DatosTirofrm datosTirofrm) {
        this.datosTirofrm = datosTirofrm;
    }


}
