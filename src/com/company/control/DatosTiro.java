package com.company.control;

import com.company.ShowReadMeAddOn;
import com.company.back.GeneratePDFFileIText;
import com.company.back.ListadoArmamento;
import com.company.back.ListadoBlancos;
import com.company.back.ListadoObservadoresAdelantado;
import com.company.balistic.Configuracion;
import com.company.balistic.PerfilTerreno;
import com.company.balistic.Posicion;
import com.company.balistic.Trayectoria;
import com.company.front.DatosTirofrm;
import com.company.front.PerfilTrayectoria;
import com.company.front.ToolBar;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;

import java.awt.*;


public class DatosTiro {
    ToolBar toolBar = ToolBar.getSingletonInstance();
    DatosTirofrm datosTirofrm = DatosTirofrm.getSingletonInstance();
    Configuracion configuracion = Configuracion.getSingletonInstance();
    private static DatosTiro datosTiro;
    public ListadoArmamento listadoArmamento;
    public ListadoObservadoresAdelantado listadoObservadoresAdelantado;
    public ListadoBlancos listadoBlancos;
    PerfilTrayectoria perfilTrayectoria = PerfilTrayectoria.getSingletonInstance();
    Trayectoria trayectoria;


    Posicion posicion;


    public TLcdLonLatHeightPoint pieza = new TLcdLonLatHeightPoint();
    public TLcdLonLatHeightPoint observador = new TLcdLonLatHeightPoint();
    public TLcdLonLatHeightPoint blanco = new TLcdLonLatHeightPoint();


    public DatosTiro() {

        datosTirofrm.btnCalcular.addActionListener(e -> {
            listadoArmamento = ListadoArmamento.getSingletonInstance();
            listadoBlancos = ListadoBlancos.getSingletonInstance();
            listadoObservadoresAdelantado = ListadoObservadoresAdelantado.getSingletonInstance();
            observador = listadoObservadoresAdelantado.point;


            ///Inicia a calcular los datos de tiro:
            setPosicion(new Posicion(listadoArmamento.point, listadoBlancos.point));
            datosTirofrm.lbDistancia.setText("Distancia: " + String.valueOf(posicion.getDistancia()));
            datosTirofrm.lbAzimut.setText("Azimut: " + String.valueOf(posicion.getAzimut()));
            datosTirofrm.lbIntervalo.setText("Intervalo: " + String.valueOf(posicion.getIntervalo()));
            trayectoria = new Trayectoria(posicion);
            datosTirofrm.lbCuadrante.setText("Cuadrante: " + String.valueOf((int) trayectoria.calculaAnguloMils()));
            datosTirofrm.lbCarga.setText("Carga: " + String.valueOf(configuracion.getCarga()));
            datosTirofrm.lbTiempoVuelo.setText("Tiempo vuelo: " + trayectoria.getTiempoVuelo());

            ///Grafica la treyectoria

//            Dimension dimensionPanelTray=ShowReadMeAddOn.getSingletonInstance().content.getSize();//Se obtiene la dimension del panel
            Dimension dimension = new Dimension(1200, 180);
            trayectoria.setDimension(dimension);
            trayectoria.setPuntosGraficaPX();
            perfilTrayectoria.setProporcionalX(trayectoria.getProporcionalX());
            perfilTrayectoria.setProporcionalY(trayectoria.getProporcionalY());
            perfilTrayectoria.valoresX_Trayectoria = trayectoria.X_TrayectoriaPx;
            perfilTrayectoria.valoresY_Trayectoria = trayectoria.Y_TrayectoriaPx;
            ShowReadMeAddOn showReadMeAddOn = ShowReadMeAddOn.getSingletonInstance();
            showReadMeAddOn.setPosicion(posicion);
            System.out.println("DatosTiro.DatosTiro");
            PerfilTerreno perfilTerreno = PerfilTerreno.getSingletonInstance();
//            perfilTerreno.setProporcionalX(trayectoria.getProporcionalX());
//            perfilTerreno.setProporcionalY(trayectoria.getProporcionalY());
            perfilTerreno.setPosicion(posicion);
//            perfilTerreno.setVistaActiva(showReadMeAddOn.getSingletonInstance().getVista());
//            perfilTerreno.calcularPerfilTerreno();
//            perfilTrayectoria.setPoligono2(perfilTerreno.getPolygon());


        });

        datosTirofrm.btnImprimir.addActionListener(e -> {
            GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
            generatePDFFileIText.setsDistancia(String.valueOf(posicion.getDistancia()));
            generatePDFFileIText.setsAzimut(String.valueOf(posicion.getAzimut()));
            generatePDFFileIText.setsIntervalo(String.valueOf(posicion.getIntervalo()));
            generatePDFFileIText.setsTiempoVuelo(String.valueOf(trayectoria.getTiempoVuelo()));
            generatePDFFileIText.setsCuadrante(String.valueOf((int) trayectoria.calculaAnguloMils()));
            //generatePDFFileIText.setsTiempoVuelo(String.valueOf(trayectoria.getTiempoVuelo()));

            generatePDFFileIText.setScarga(String.valueOf(configuracion.getCarga()));

            generatePDFFileIText.generarPDF();


        });

        accionBotonCDT();

    }


    public void accionBotonCDT() {


        toolBar.botonCDT.addActionListener(e -> {
//            listadoArmamento = ListadoArmamento.getSingletonInstance();
            datosTirofrm = DatosTirofrm.getSingletonInstance();
            datosTirofrm.setVisible(true);
            listadoArmamento.mostrarListadosArmamento(null, datosTirofrm.cbArma, datosTirofrm.texResultadoCoordenadasC);
            listadoObservadoresAdelantado.mostrarListadoObservadoresAdelantado(datosTirofrm.cbObservadorAdelantado, datosTirofrm.texResultadoCoordenadasA);
            listadoBlancos.mostrarListadoBlancos(datosTirofrm.cbBlanco, datosTirofrm.texResultadoCoordenadasB);


        });
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public static DatosTiro getSingletonInstance() {
        if (datosTiro == null) {
            datosTiro = new DatosTiro();

        } else {

        }
        return datosTiro;
    }


}
