package com.company;

import com.company.back.EarthTerrainElevationAdapter;
import com.company.back.LineaVista;
import com.company.balistic.PerfilTerreno;
import com.company.balistic.Posicion;
import com.company.control.DatosTiro;
import com.company.front.PerfilTrayectoria;
import com.company.front.ToolBar;
import com.luciad.earth.tileset.ILcdEarthTileSet;
import com.luciad.earth.tileset.terrain.TLcdEarthTileSetElevationProvider;
import com.luciad.lucy.ILcyLucyEnv;
import com.luciad.lucy.addons.ALcyPreferencesAddOn;
import com.luciad.lucy.gui.ALcyApplicationPaneTool;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape2D.TLcdLonLatPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.view.lightspeed.ILspView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.awt.BorderLayout.*;

public class ShowReadMeAddOn extends ALcyPreferencesAddOn {
    Posicion posicion;
    private static ShowReadMeAddOn showReadMeAddOn;
    private ALcyApplicationPaneTool fApplicationPaneTool;
    public JPanel content = new JPanel(new BorderLayout());
    DatosTiro datosTiro=DatosTiro.getSingletonInstance();


    public ShowReadMeAddOn() {
        super("samples.lucy.showreadme.", "ShowReadMeAddOn.");
    }

    @Override
    public void plugInto(ILcyLucyEnv aLucyEnv) {
        super.plugInto(aLucyEnv);

        fApplicationPaneTool = new MyApplicationPaneTool();
        fApplicationPaneTool.plugInto(aLucyEnv);
        ToolBar toolBar = new ToolBar(aLucyEnv);
        toolBar.thread.start();

    }

    @Override
    public void unplugFrom(ILcyLucyEnv aLucyEnv) {
        super.unplugFrom(aLucyEnv);
        fApplicationPaneTool.unplugFrom(aLucyEnv);
        fApplicationPaneTool = null;
    }

    private String getTextFileName() {
        return getPreferences().getString("ShowReadMeAddOn.ReadMeFile", null);
    }

    public class MyApplicationPaneTool extends ALcyApplicationPaneTool {

        public MyApplicationPaneTool() {
            super(ShowReadMeAddOn.this.getPreferences(),
                    ShowReadMeAddOn.this.getLongPrefix(),
                    ShowReadMeAddOn.this.getShortPrefix());
        }

        @Override
        protected Component createContent() {

            final boolean[] salir = {false};

            content.setLayout(new BorderLayout());
            content.setLayout(null);
            content.setBackground(Color.getColor("#727272"));
            content.setSize(40,40);
          //  content.add(new JButton("BorderLayout.SOUTH"), BorderLayout.EAST);


            JButton botonVista = new JButton(new ImageIcon("src\\images\\eye.png"));
            botonVista.setBounds(60,0,52,30);
            content.add(botonVista);


            JButton botonTrayectoria = new JButton(new ImageIcon("src\\images\\angulo.png"));
           // boton.setBounds(35,35,52,52);
            botonTrayectoria.setSize(52,30);
            content.add(botonTrayectoria);

            botonTrayectoria.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    ILspView vista = (ILspView) getLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
                    PerfilTrayectoria perfilTrayectoria = PerfilTrayectoria.getSingletonInstance();
                    Container cp = content.getParent();


                    PerfilTerreno perfilTerreno = PerfilTerreno.getSingletonInstance();
                    perfilTerreno.setProporcionalX(perfilTrayectoria.getProporcionalX());
                    perfilTerreno.setProporcionalY(perfilTrayectoria.getProporcionalY());
                    perfilTerreno.setDimension(cp.getSize());
                    DatosTiro datosTiro=DatosTiro.getSingletonInstance();
                   // perfilTerreno.setPosicion(datosTiro.getPosicion());
                    perfilTerreno.calcularPerfilTerreno(vista);
                    perfilTrayectoria.setPoligono2(perfilTerreno.getPolygon());
                    perfilTrayectoria.paintComponent(content.getGraphics());
                    cp.add(perfilTrayectoria);
                    TLcdLonLatPoint punto = new TLcdLonLatPoint(-73, 4);

                    System.out.println("Altura: " + calculaAltura(punto));
                    cp.setVisible(true);
                    Dimension dimension = cp.getSize();


                }
            });

            botonVista.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    ILspView vista = (ILspView) getLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
                    LineaVista lineaVista=new LineaVista();
                    TLcdLonLatHeightPoint OA=new TLcdLonLatHeightPoint(DatosTiro.getSingletonInstance().observador);
                    double altura=calculaAltura(OA);
                    OA.move3D(OA.getLon(),OA.getLat(),altura);
                    lineaVista.graficarLOS(vista,OA,6000);
                }
            });


            return content;

        }

        public double calculaAltura(ILcdPoint point) {//Carrillo 04Abr2018
//            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
//            simbolos.setDecimalSeparator('.');
//            DecimalFormat formateador = new DecimalFormat("##.#", simbolos);
            ILspView vista = (ILspView) getLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
            ILcdEarthTileSet elevationTileSet = vista.getServices().getTerrainSupport().getElevationTileSet();
            TLcdEarthTileSetElevationProvider proveedorElevacion = new TLcdEarthTileSetElevationProvider(elevationTileSet);
            EarthTerrainElevationAdapter proveedorAltitud = new EarthTerrainElevationAdapter(proveedorElevacion);

            double altura = proveedorAltitud.retrieveAltitudeAt(point, new TLcdGeodeticReference());
            return altura;
//            return Double.parseDouble(formateador.format(altura));
        }

    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public static ShowReadMeAddOn getSingletonInstance() {
        if (showReadMeAddOn == null) {
            showReadMeAddOn = new ShowReadMeAddOn();
        } else {

        }
        return showReadMeAddOn;
    }


}
