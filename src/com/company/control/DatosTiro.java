package com.company.control;

import com.company.back.*;
import com.company.balistic.Configuracion;
import com.company.balistic.Posicion;
import com.company.balistic.Trayectoria;
import com.company.front.DatosTirofrm;
import com.company.front.PerfilTrayectoria;
import com.luciad.earth.tileset.ILcdEarthTileSet;
import com.luciad.earth.tileset.terrain.TLcdEarthTileSetElevationProvider;
import com.luciad.model.ILcdModelListener;
import com.luciad.model.TLcdModelChangedEvent;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.symbology.app6a.model.ILcdEditableAPP6ACoded;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.ILcdMS2525bCoded;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.text.TLcdLonLatPointFormat;
import com.luciad.util.ILcdSelectionListener;
import com.luciad.util.TLcdSelectionChangedEvent;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspAWTView;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.text.ParseException;
import java.util.Enumeration;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;
import static com.company.front.ToolBar.getVista;

public class DatosTiro implements ActionListener, ILcdModelListener, ILcdSelectionListener {
    private ListadoSistemasArtilleria sistemasArtilleria = new ListadoSistemasArtilleria();
    private ListadoObservadoresAdelantado observadoresAdelantados = new ListadoObservadoresAdelantado();
    private ListadoBlancos blancos = new ListadoBlancos();
    private DatosTirofrm datosTirofrm;
    private TLcdLonLatPointFormat formato = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2);
    private PerfilTrayectoria perfilTrayectoria = PerfilTrayectoria.getSingletonInstance();
    private Configuracion configuracion = Configuracion.getSingletonInstance();
    private Posicion posicion;


    public DatosTiro(DatosTirofrm datosTirofrm) {
        this.datosTirofrm = datosTirofrm;
        datosTirofrm.cbArma.addActionListener(this);
        datosTirofrm.cbObservadorAdelantado.addActionListener(this);
        datosTirofrm.cbBlanco.addActionListener(this);
        datosTirofrm.btnCalcular.addActionListener(this);
        datosTirofrm.btnImprimir.addActionListener(this);

        //new VentanaToolTip(this.datosTirofrm.cbArma);
        //new VentanaToolTip(this.datosTirofrm.cbBlanco);
        //new VentanaToolTip(this.datosTirofrm.cbObservadorAdelantado);
        actualizarListadosParaVistaActiva();
        iniciaListadoUnidadesParaCalculoDetiro();
        iniciaListadosParaVistaActual();

    }

    //_______________________________EVENTOS____________________________________________________________________________
    @Override
    public void modelChanged(TLcdModelChangedEvent tLcdModelChangedEvent) {
        if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_ADDED) {
//______________________________________SISTEMA OBJECT_ADDED____________________________________________________________
            actualizaArraListSistemaArtilleria(tLcdModelChangedEvent.elements());
            muestraUbicacionSistemaArtilleriaTextField();
//_____________________________________OBSERVADOR OBJECT_ADDED__________________________________________________________
            actualizaArraListObservadorAdelantado(tLcdModelChangedEvent.elements());
            muestraUbicacionObservadorAdelantadoTextField();
//_______________________________________BLANCO OBJECT_ADDED____________________________________________________________
            actualizaArraListBlanco(tLcdModelChangedEvent.elements());
            muestraUbicacionBlancoTextField();
        } else if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECTS_CHANGED) {
//______________________________________SISTEMA OBJECT_CHANGED__________________________________________________________
            actualizaArraListSistemaArtilleria(tLcdModelChangedEvent.elements());
            muestraUbicacionSistemaArtilleriaTextField();
//_____________________________________OBSERVADOR OBJECT_CHANGED________________________________________________________
            actualizaArraListObservadorAdelantado(tLcdModelChangedEvent.elements());
            muestraUbicacionObservadorAdelantadoTextField();
//_______________________________________BLANCO OBJECT_CHANGED__________________________________________________________
            actualizaArraListBlanco(tLcdModelChangedEvent.elements());
            muestraUbicacionBlancoTextField();

        } else if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.SOME_OBJECTS_REMOVED) {
//______________________________________SISTEMA SOME_OBJECTS_REMOVED____________________________________________________
            eliminaSistemas(tLcdModelChangedEvent.elements());
//_____________________________________OBSERVADOR SOME_OBJECTS_REMOVED__________________________________________________
            eliminaObservadorAdelantado(tLcdModelChangedEvent.elements());
//_______________________________________BLANCO SOME_OBJECTS_REMOVED____________________________________________________
            eliminaBlanco(tLcdModelChangedEvent.elements());
        }
    }

    @Override
    public void selectionChanged(TLcdSelectionChangedEvent tLcdSelectionChangedEvent) {
        muestraUbicacionSistemaArtilleriaTextField();
        muestraUbicacionObservadorAdelantadoTextField();
        muestraListadoBlancos();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(datosTirofrm.btnCalcular)) {
            calculosComandosFuego();
        } else if (e.getSource().equals(datosTirofrm.btnImprimir)) {
            imprimirBolitinFuego();
        } else if (e.getSource().equals(datosTirofrm.cbArma)) {
            muestraUbicacionSistemaArtilleriaTextField();
        } else if (e.getSource().equals(datosTirofrm.cbObservadorAdelantado)) {
            muestraUbicacionObservadorAdelantadoTextField();
        } else if (e.getSource().equals(datosTirofrm.cbBlanco)) {
            muestraUbicacionBlancoTextField();
        }


    }

//METODOS GENERALES_________________________________________________________________________________________________

    public void calculosComandosFuego() {
        setPosicion(new Posicion(getPosicionSistemaSeleccionado(), getPosicionBlancoSeleccionado()));
        datosTirofrm.lbDistancia.setText("Distancia: " + String.valueOf(posicion.getDistancia()));
        datosTirofrm.lbAzimut.setText("Azimut: " + String.valueOf(posicion.getAzimut()));
        datosTirofrm.lbIntervalo.setText("Intervalo: " + String.valueOf(posicion.getIntervalo()));
        datosTirofrm.lbSituacion.setText("Situación: " + String.valueOf(Math.atan(posicion.getIntervalo() / posicion.getDistancia()) * 1000));
        Trayectoria trayectoria = new Trayectoria(posicion);
        datosTirofrm.lbCuadrante.setText("Cuadrante: " + String.valueOf((int) trayectoria.calculaAnguloMils()));
        datosTirofrm.lbOrientacion.setText("Orientación: " + String.valueOf(posicion.getAzimut() - trayectoria.getDeriva()));
        datosTirofrm.lbCarga.setText("Carga: " + String.valueOf(configuracion.getCarga()));
        datosTirofrm.lbTiempoVuelo.setText("Tiempo vuelo: " + trayectoria.getTiempoVuelo());
        if (getVista() instanceof ILspView) {
            datosTirofrm.btnTrayectoria.setEnabled(true);
        }
    }

    public void imprimirBolitinFuego() {
        GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
        generatePDFFileIText.setsDistancia(String.valueOf(posicion.getDistancia()));
        generatePDFFileIText.setsAzimut(String.valueOf(posicion.getAzimut()));
        generatePDFFileIText.setsIntervalo(String.valueOf(posicion.getIntervalo()));
        generatePDFFileIText.setsSituacion(String.valueOf(Math.atan(posicion.getIntervalo() / posicion.getDistancia()) * 1000));
        Trayectoria trayectoria = new Trayectoria(posicion);
        generatePDFFileIText.setsTiempoVuelo(String.valueOf(trayectoria.getTiempoVuelo()));
        generatePDFFileIText.setsCuadrante(String.valueOf((int) trayectoria.calculaAnguloMils()));
        generatePDFFileIText.setsOrientacion(String.valueOf(posicion.getAzimut() - trayectoria.getDeriva()));
        generatePDFFileIText.setScarga(String.valueOf(configuracion.getCarga()));
        generatePDFFileIText.generarPDF();
    }

    public double calculaAltura(ILcdPoint point) {
        ILcdEarthTileSet elevationTileSet = null;
        if (getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof ILspView) {
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

    public void iniciaListadoUnidadesParaCalculoDetiro() {

//______________________INICIA SISTEMAS DE ARTILLERIA___________________________________________________________________
        muestraListadoSistemasArtilleria();
        muestraUbicacionSistemaArtilleriaTextField();
//_____________________INICIA OBSERVADOR ADELANTADO_____________________________________________________________________
        muestraListadoObservadorAdelantado();
        muestraUbicacionObservadorAdelantadoTextField();

//_____________________INICIA BLANCOS___________________________________________________________________________________
        muestraListadoBlancos();
        muestraUbicacionBlancoTextField();
    }

    //PROCESOS PARA SISTEMAS DE ARTILLERIA______________________________________________________________________________
    public void muestraListadoSistemasArtilleria() {
        datosTirofrm.cbArma.removeAllItems();
        for (int i = 0; i < sistemasArtilleria().getSistemas().size(); i++) {
            if (sistemasArtilleria().getSistemas().get(i) instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) sistemasArtilleria().getSistemas().get(i);
                datosTirofrm.cbArma.addItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
            } else if (sistemasArtilleria().getSistemas().get(i) instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) sistemasArtilleria().getSistemas().get(i);
                datosTirofrm.cbArma.addItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
            }

        }
    }

    private void muestraUbicacionSistemaArtilleriaTextField() {
        if (datosTirofrm.texResultadoCoordenadasC != null && datosTirofrm.cbArma.getItemCount() > 0 && sistemasArtilleria().getSistemas().size() > 0) {
            Object object = null;
            if (datosTirofrm.cbArma.getSelectedIndex() > -1) {
                object = sistemasArtilleria().getSistemas().get(datosTirofrm.cbArma.getSelectedIndex());
            }

            if (object != null && object instanceof TLcdEditableMS2525bObject) {
                datosTirofrm.texResultadoCoordenadasC.setText(formato.format(((TLcdEditableMS2525bObject) object).getPoint(0)));
            } else if (object != null && object instanceof TLcdEditableAPP6AObject) {
                datosTirofrm.texResultadoCoordenadasC.setText(formato.format(((TLcdEditableAPP6AObject) object).getPoint(0)));
            }

        } else {
            datosTirofrm.texResultadoCoordenadasC.setText(formato.format(0, 0));
        }
    }

    public void actualizaArraListSistemaArtilleria(Enumeration enumeration) {
        String sistema = "null";
        if (datosTirofrm.cbArma.getSelectedIndex() >= 0) {
            sistema = datosTirofrm.cbArma.getSelectedItem().toString();
        }
        while (enumeration.hasMoreElements()) {
            Object object = enumeration.nextElement();
            if (sistemasArtilleria().esSistemaArtilleriaPermitido(object)) {
                if (sistemasArtilleria().yaExisteSistemaArtilleria(object)) {
                    for (int i = 0; i < sistemasArtilleria().getSistemas().size(); i++) {
                        if (sistemasArtilleria().getSistemas().get(i).equals(object)) {
                            if (object instanceof TLcdEditableMS2525bObject) {
                                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                                datosTirofrm.cbArma.insertItemAt(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation), i);
                                datosTirofrm.cbArma.removeItemAt(i + 1);
                            } else if (object instanceof TLcdEditableAPP6AObject) {
                                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                                datosTirofrm.cbArma.insertItemAt(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation), i);
                                datosTirofrm.cbArma.removeItemAt(i + 1);
                            }
                            sistemasArtilleria().getSistemas().set(i, object);
                        }
                    }
                } else {
                    if (object instanceof TLcdEditableMS2525bObject) {
                        TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                        datosTirofrm.cbArma.addItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
                    } else if (object instanceof TLcdEditableAPP6AObject) {
                        TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                        datosTirofrm.cbArma.addItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
                    }
                    sistemasArtilleria().getSistemas().add(object);
                }
            } else {
                if (sistemasArtilleria().yaExisteSistemaArtilleria(object)) {
                    for (int i = 0; i < sistemasArtilleria().getSistemas().size(); i++) {
                        if (sistemasArtilleria().getSistemas().get(i).equals(object)) {
                            datosTirofrm.cbArma.removeItemAt(i);
                        }
                    }
                    sistemasArtilleria().getSistemas().remove(object);
                }
            }
        }
        if (datosTirofrm.cbArma.getSelectedIndex() >= 0) {
            datosTirofrm.cbArma.setSelectedItem(sistema);
        }

    }

    public void eliminaSistemas(Enumeration enumeration) {
        while (enumeration.hasMoreElements()) {
            Object object = enumeration.nextElement();
            if (object instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                datosTirofrm.cbArma.removeItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
            } else if (object instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                datosTirofrm.cbArma.removeItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
            }
            if (sistemasArtilleria().getSistemas().remove(object) && datosTirofrm.cbArma.getItemCount() == 0) {
                datosTirofrm.dispose();
            }
        }
    }

    public TLcdLonLatHeightPoint getPosicionSistemaSeleccionado() {
        ILcdPoint point = null;
        try {
            point = formato.parseObject(datosTirofrm.texResultadoCoordenadasC.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new TLcdLonLatHeightPoint(point.getX(), point.getY(), calculaAltura(point));
    }

    public Object getSistemaSeleccionado() {
        return sistemasArtilleria().getSistemas().get(datosTirofrm.cbArma.getSelectedIndex());
    }

    //PROCESOS PARA OBSERVADORES________________________________________________________________________________________
    public void muestraListadoObservadorAdelantado() {
        datosTirofrm.cbObservadorAdelantado.removeAllItems();
        for (int i = 0; i < observadoresAdelantados().getObservadores().size(); i++) {
            if (observadoresAdelantados().getObservadores().get(i) instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) observadoresAdelantados().getObservadores().get(i);
                datosTirofrm.cbObservadorAdelantado.addItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
            } else if (observadoresAdelantados().getObservadores().get(i) instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) observadoresAdelantados().getObservadores().get(i);
                datosTirofrm.cbObservadorAdelantado.addItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
            }
        }
    }

    private void muestraUbicacionObservadorAdelantadoTextField() {
        if (datosTirofrm.texResultadoCoordenadasA != null && datosTirofrm.cbObservadorAdelantado.getSelectedIndex() >= 0 &&
                observadoresAdelantados().getObservadores().size() > 0) {
            Object object = observadoresAdelantados().getObservadores().get(datosTirofrm.cbObservadorAdelantado.getSelectedIndex());
            if (object != null && object instanceof TLcdEditableMS2525bObject) {
                datosTirofrm.texResultadoCoordenadasA.setText(formato.format(((TLcdEditableMS2525bObject) object).getPoint(0)));
            } else if (object != null && object instanceof TLcdEditableAPP6AObject) {
                datosTirofrm.texResultadoCoordenadasA.setText(formato.format(((TLcdEditableAPP6AObject) object).getPoint(0)));
            }

        } else {
            datosTirofrm.texResultadoCoordenadasA.setText(formato.format(0, 0));
        }
    }

    public void actualizaArraListObservadorAdelantado(Enumeration enumeration) {
        String obervadorAdelantado = "null";
        if (datosTirofrm.cbObservadorAdelantado.getSelectedIndex() >= 0) {
            obervadorAdelantado = datosTirofrm.cbObservadorAdelantado.getSelectedItem().toString();
        }
        while (enumeration.hasMoreElements()) {
            Object object = enumeration.nextElement();
            if (observadoresAdelantados().esObservadorAdelantadoPermitido(object)) {
                if (observadoresAdelantados().yaExisteObservadorAdelantado(object)) {
                    for (int i = 0; i < observadoresAdelantados().getObservadores().size(); i++) {
                        if (observadoresAdelantados().getObservadores().get(i).equals(object)) {
                            if (object instanceof TLcdEditableMS2525bObject) {
                                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                                datosTirofrm.cbObservadorAdelantado.insertItemAt(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation), i);
                                datosTirofrm.cbObservadorAdelantado.removeItemAt(i + 1);
                            } else if (object instanceof TLcdEditableAPP6AObject) {
                                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                                datosTirofrm.cbObservadorAdelantado.insertItemAt(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation), i);
                                datosTirofrm.cbObservadorAdelantado.removeItemAt(i + 1);
                            }
                            observadoresAdelantados().getObservadores().set(i, object);

                        }
                    }
                } else {
                    if (object instanceof TLcdEditableMS2525bObject) {
                        TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                        datosTirofrm.cbObservadorAdelantado.addItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
                    } else if (object instanceof TLcdEditableAPP6AObject) {
                        TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                        datosTirofrm.cbObservadorAdelantado.addItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
                    }
                    observadoresAdelantados().getObservadores().add(object);
                }
            } else {
                if (observadoresAdelantados().yaExisteObservadorAdelantado(object)) {
                    for (int i = 0; i < observadoresAdelantados().getObservadores().size(); i++) {
                        if (observadoresAdelantados().getObservadores().get(i).equals(object)) {
                            datosTirofrm.cbObservadorAdelantado.removeItemAt(i);
                        }
                    }
                    observadoresAdelantados().getObservadores().remove(object);
                }
            }
        }
        if (datosTirofrm.cbObservadorAdelantado.getSelectedIndex() >= 0) {
            datosTirofrm.cbObservadorAdelantado.setSelectedItem(obervadorAdelantado);
        }

    }

    public void eliminaObservadorAdelantado(Enumeration enumeration) {
        while (enumeration.hasMoreElements()) {
            Object object = enumeration.nextElement();
            if (object instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                datosTirofrm.cbObservadorAdelantado.removeItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
            } else if (object instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                datosTirofrm.cbObservadorAdelantado.removeItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
            }
            if (observadoresAdelantados().getObservadores().remove(object) && datosTirofrm.cbObservadorAdelantado.getItemCount() == 0) {
                datosTirofrm.dispose();
            }
        }
    }

    public TLcdLonLatHeightPoint getPosicionObservadorAdelantadoSeleccionado() {
        ILcdPoint point = null;
        try {
            point = formato.parseObject(datosTirofrm.texResultadoCoordenadasA.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new TLcdLonLatHeightPoint(point.getX(), point.getY(), calculaAltura(point));
    }

    public Object getObservadorAdelantadoSeleccionado() {
        return observadoresAdelantados().getObservadores().get(datosTirofrm.cbObservadorAdelantado.getSelectedIndex());
    }

    //PROCESOS PARA BLANCOS_____________________________________________________________________________________________
    public void muestraListadoBlancos() {
        datosTirofrm.cbBlanco.removeAllItems();
        for (int i = 0; i < blancos().getBlancos().size(); i++) {
            if (blancos().getBlancos().get(i) instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) blancos().getBlancos().get(i);
                datosTirofrm.cbBlanco.addItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
            } else if (blancos().getBlancos().get(i) instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) blancos().getBlancos().get(i);
                datosTirofrm.cbBlanco.addItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
            }
        }
    }

    private void muestraUbicacionBlancoTextField() {
        if (datosTirofrm.texResultadoCoordenadasB != null && datosTirofrm.cbBlanco.getSelectedIndex() >= 0 &&
                blancos().getBlancos().size() > 0) {
            Object object = blancos().getBlancos().get(datosTirofrm.cbBlanco.getSelectedIndex());
            if (object != null && object instanceof TLcdEditableMS2525bObject) {
                datosTirofrm.texResultadoCoordenadasB.setText(formato.format(((TLcdEditableMS2525bObject) object).getPoint(0)));
            } else if (object != null && object instanceof TLcdEditableAPP6AObject) {
                datosTirofrm.texResultadoCoordenadasB.setText(formato.format(((TLcdEditableAPP6AObject) object).getPoint(0)));
            }

        } else {
            datosTirofrm.texResultadoCoordenadasB.setText(formato.format(0, 0));

        }
    }

    public void actualizaArraListBlanco(Enumeration enumeration) {
        String blanco = "null";
        if (datosTirofrm.cbBlanco.getSelectedIndex() >= 0) {
            blanco = datosTirofrm.cbBlanco.getSelectedItem().toString();
        }
        while (enumeration.hasMoreElements()) {
            Object object = enumeration.nextElement();
            if (blancos().esBlancoPermitido(object)) {
                if (blancos().yaExisteBlanco(object)) {
                    for (int i = 0; i < blancos().getBlancos().size(); i++) {
                        if (blancos().getBlancos().get(i).equals(object)) {
                            if (object instanceof TLcdEditableMS2525bObject) {
                                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                                datosTirofrm.cbBlanco.insertItemAt(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation), i);
                                datosTirofrm.cbBlanco.removeItemAt(i + 1);
                            } else if (object instanceof TLcdEditableAPP6AObject) {
                                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                                datosTirofrm.cbBlanco.insertItemAt(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation), i);
                                datosTirofrm.cbBlanco.removeItemAt(i + 1);
                            }
                            blancos().getBlancos().set(i, object);
                        }
                    }
                } else {
                    if (object instanceof TLcdEditableMS2525bObject) {
                        TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                        datosTirofrm.cbBlanco.addItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
                    } else if (object instanceof TLcdEditableAPP6AObject) {
                        TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                        datosTirofrm.cbBlanco.addItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
                    }
                    blancos().getBlancos().add(object);
                }
            } else {
                if (blancos().yaExisteBlanco(object)) {
                    for (int i = 0; i < blancos().getBlancos().size(); i++) {
                        if (blancos().getBlancos().get(i).equals(object)) {
                            datosTirofrm.cbBlanco.removeItemAt(i);
                        }
                    }
                    blancos().getBlancos().remove(object);
                }
            }
        }
        if (datosTirofrm.cbBlanco.getSelectedIndex() >= 0) {
            datosTirofrm.cbBlanco.setSelectedItem(blanco);
        }

    }

    public void eliminaBlanco(Enumeration enumeration) {
        while (enumeration.hasMoreElements()) {
            Object object = enumeration.nextElement();
            if (object instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                datosTirofrm.cbBlanco.removeItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
            } else if (object instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                datosTirofrm.cbBlanco.removeItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
            }
            if (blancos().getBlancos().remove(object) && datosTirofrm.cbBlanco.getItemCount() == 0) {
                datosTirofrm.dispose();
            }
        }
    }

    public void actualizarListadosParaVistaActiva() {
        if (getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof TLspAWTView) {
            ILspView vista = (ILspView) getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
            sistemasArtilleria().obtieneSistemasArtilleriaDesdeVista(vista);
            observadoresAdelantados().obtieneObservadoresAdelantadosDesdeVista(vista);
            blancos().obtieneBlancosDesdeVista(vista);
        } else if (getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof ILcdGXYView) {
            ILcdGXYView vista = (ILcdGXYView) getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
            sistemasArtilleria().obtieneSistemasArtilleriaDesdeVista(vista);
            observadoresAdelantados().obtieneObservadoresAdelantadosDesdeVista(vista);
            blancos().obtieneBlancosDesdeVista(vista);
        }


    }

    public void iniciaListadosParaVistaActual() {
        if (getVista() instanceof ILcdGXYView) {
            ILcdGXYView vista2D = (ILcdGXYView) getVista();
            vista2D.addModelListener(this);
            vista2D.addLayerSelectionListener(this);
        } else if (getVista() instanceof TLspAWTView) {
            TLspAWTView vista3D = (TLspAWTView) getVista();
            vista3D.addLayerModelListener(this);
            vista3D.addLayerSelectionListener(this);
        }
    }

    public TLcdLonLatHeightPoint getPosicionBlancoSeleccionado() {
        ILcdPoint point = null;
        try {
            point = formato.parseObject(datosTirofrm.texResultadoCoordenadasB.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new TLcdLonLatHeightPoint(point.getX(), point.getY(), calculaAltura(point));
    }

    public Object getBlancoSeleccionado() {
        return blancos().getBlancos().get(datosTirofrm.cbBlanco.getSelectedIndex());
    }

    //GET Y SET_________________________________________________________________________________________________________
    public ListadoSistemasArtilleria sistemasArtilleria() {
        return sistemasArtilleria;
    }

    public void setSistemasArtilleria(ListadoSistemasArtilleria sistemasArtilleria) {
        this.sistemasArtilleria = sistemasArtilleria;
    }

    public ListadoObservadoresAdelantado observadoresAdelantados() {
        return observadoresAdelantados;
    }

    public void setObservadoresAdelantados(ListadoObservadoresAdelantado observadoresAdelantados) {
        this.observadoresAdelantados = observadoresAdelantados;
    }

    public ListadoBlancos blancos() {
        return blancos;
    }

    public void setBlancos(ListadoBlancos blancos) {
        this.blancos = blancos;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
}
