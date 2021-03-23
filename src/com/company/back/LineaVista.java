package com.company.back;

import com.luciad.earth.tileset.ILcdEarthTileSet;
import com.luciad.earth.tileset.terrain.TLcdEarthTileSetElevationProvider;
import com.luciad.model.ILcdModel;
import com.luciad.model.TLcdVectorModel;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.tea.ILcdLOSCoverageMatrix;
import com.luciad.tea.TLcdCoverageAltitudeMode;
import com.luciad.tea.lightspeed.los.TLspLOSCalculator;
import com.luciad.tea.lightspeed.los.TLspLOSProperties;
import com.luciad.tea.lightspeed.los.view.TLspLOSCoveragePainter;
import com.luciad.tea.lightspeed.los.view.TLspLOSCoverageStyle;
import com.luciad.util.TLcdLonLatParser;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspAWTView;
import com.luciad.view.lightspeed.layer.ILspInteractivePaintableLayer;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspPaintState;
import com.luciad.view.lightspeed.layer.shape.TLspShapeLayerBuilder;
import com.luciad.view.lightspeed.style.ILspWorldElevationStyle;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;

public class LineaVista {
    ILspInteractivePaintableLayer capaLOS = null;
    ILspView aView = null;
    TLcdLonLatHeightPoint point = new TLcdLonLatHeightPoint(0, 0, 0);
    String nombreCapa = "LOS";

    double dbRadio;

    public LineaVista(ILspView aView, TLcdLonLatHeightPoint point, double dbRadio) {
        setaView(aView);
        setPoint(point);
        setDbRadio(dbRadio);

    }

    public ILspInteractivePaintableLayer crearLineaVista() {
        TLspLOSProperties propiedadesLOS = new TLspLOSProperties();
        propiedadesLOS.setCenterPoint(getPoint());
        propiedadesLOS.setRadius(getDbRadio());
        ILcdEarthTileSet elevationTileSet = getaView().getServices().getTerrainSupport().getElevationTileSet();
        TLcdEarthTileSetElevationProvider proveedorElevacion = new TLcdEarthTileSetElevationProvider(elevationTileSet);
        EarthTerrainElevationAdapter proveedorAltitud = new EarthTerrainElevationAdapter(proveedorElevacion);
        TLspLOSCalculator calculador = new TLspLOSCalculator();
        calculador.setCoverageAltitudeMode(TLcdCoverageAltitudeMode.ABOVE_GROUND_LEVEL);
        ILcdLOSCoverageMatrix matriz = calculador.calculateLOS(propiedadesLOS, proveedorAltitud, new TLcdGeodeticReference());

        TLcdVectorModel modelo = new TLcdVectorModel();
        modelo.setModelReference(new TLcdGeodeticReference());
        modelo.addElement(matriz, ILcdModel.FIRE_NOW);

        capaLOS = TLspShapeLayerBuilder.newBuilder()
                .label(getNombreCapa())
                .bodyEditable(true)
                .model(modelo)
                .bodyPainter(new TLspLOSCoveragePainter())
                .layerType(ILspLayer.LayerType.EDITABLE)
                .bodyStyles(TLspPaintState.REGULAR, TLspLOSCoverageStyle.newBuilder().elevationMode(ILspWorldElevationStyle.ElevationMode.ON_TERRAIN)
                        .build())
                .build();
        TLcdVectorModel modelocentro = new TLcdVectorModel();
        modelocentro.setModelReference(new TLcdGeodeticReference());
        return capaLOS;
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

    public void graficarLineaVista() {
        if (getCapaLOS() == null) {
            crearLineaVista();
            getaView().addLayer(getCapaLOS());
        } else {
            getaView().addLayer(getCapaLOS());
        }

    }

    public String getNombreCapa() {
        return nombreCapa;
    }

    public void setNombreCapa(String nombreCapa) {
        this.nombreCapa = nombreCapa;
    }

    public ILspInteractivePaintableLayer getCapaLOS() {
        return capaLOS;
    }

    public void setCapaLOS(ILspInteractivePaintableLayer capaLOS) {
        this.capaLOS = capaLOS;
    }

    public ILspView getaView() {
        return aView;
    }

    public void setaView(ILspView aView) {
        this.aView = aView;
    }

    public TLcdLonLatHeightPoint getPoint() {
        return point;
    }

    public void setPoint(TLcdLonLatHeightPoint point) {
        this.point = new TLcdLonLatHeightPoint(point.getX(), point.getY(), calculaAltura(point));
    }

    public double getDbRadio() {
        return dbRadio;
    }

    public void setDbRadio(double dbRadio) {
        this.dbRadio = dbRadio;
    }
}
