package com.company.back;

import com.luciad.earth.tileset.ILcdEarthTileSet;
import com.luciad.earth.tileset.terrain.TLcdEarthTileSetElevationProvider;
import com.luciad.model.ILcdModel;
import com.luciad.model.TLcdVectorModel;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.tea.ILcdLOSCoverageMatrix;
import com.luciad.tea.TLcdCoverageAltitudeMode;
import com.luciad.tea.lightspeed.los.TLspLOSCalculator;
import com.luciad.tea.lightspeed.los.TLspLOSProperties;
import com.luciad.tea.lightspeed.los.view.TLspLOSCoveragePainter;
import com.luciad.tea.lightspeed.los.view.TLspLOSCoverageStyle;
import com.luciad.util.TLcdLonLatParser;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.layer.ILspInteractivePaintableLayer;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspPaintState;
import com.luciad.view.lightspeed.layer.shape.TLspShapeLayerBuilder;
import com.luciad.view.lightspeed.style.ILspWorldElevationStyle;


/**
 * Created by USUARIO on 18/07/2016.
 */
public class LineaVista {

    public static ILspInteractivePaintableLayer capaLOS;
    public static ILspInteractivePaintableLayer capaCentro;

      public LineaVista()  {

//         Escuchador cambioPunto = new Escuchador(modelo, calculador, propiedadesLOS, proveedorAltitud, new TLcdGeodeticReference(), matriz);
//         modelocentro.addModelListener(cambioPunto);

    }

    public void graficarLOS(ILspView aView, TLcdLonLatHeightPoint point, double dbRadio){

        TLspLOSProperties propiedadesLOS = new TLspLOSProperties();
        propiedadesLOS.setCenterPoint(point);
        propiedadesLOS.setRadius(dbRadio);

        ILcdEarthTileSet elevationTileSet = aView.getServices().getTerrainSupport().getElevationTileSet();
        TLcdEarthTileSetElevationProvider proveedorElevacion = new TLcdEarthTileSetElevationProvider(elevationTileSet);
        EarthTerrainElevationAdapter proveedorAltitud = new EarthTerrainElevationAdapter(proveedorElevacion);


        TLspLOSCalculator calculador = new TLspLOSCalculator();
        calculador.setCoverageAltitudeMode(TLcdCoverageAltitudeMode.ABOVE_GROUND_LEVEL);

        ILcdLOSCoverageMatrix matriz = calculador.calculateLOS(propiedadesLOS, proveedorAltitud, new TLcdGeodeticReference());

        TLcdVectorModel modelo = new TLcdVectorModel();
        modelo.setModelReference(new TLcdGeodeticReference());
        modelo.addElement(matriz, ILcdModel.FIRE_NOW);

        capaLOS = TLspShapeLayerBuilder.newBuilder()
                .label("LOS")
                .bodyEditable(true)
                .model(modelo)
                .bodyPainter(new TLspLOSCoveragePainter())
                .layerType(ILspLayer.LayerType.EDITABLE)
                .bodyStyles(TLspPaintState.REGULAR, TLspLOSCoverageStyle.newBuilder().elevationMode(ILspWorldElevationStyle.ElevationMode.ON_TERRAIN)
                        .build())
                .build();

        aView.addLayer(capaLOS);

        //ubicar el simbolo del observador adelantado
        TLcdVectorModel modelocentro = new TLcdVectorModel();
        modelocentro.setModelReference(new TLcdGeodeticReference());
//        modelocentro.addElement(point, ILcdModel.FIRE_NOW);

//        capaCentro = TLspShapeLayerBuilder.newBuilder()
//                .label("Punto centro")
//                .bodyEditable(true)
//                .model(modelo)
//                .layerType(ILspLayer.LayerType.EDITABLE)
//                .build();
//        aView.addLayer(capaCentro);

    }

}
