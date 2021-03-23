package com.company.back;

import com.luciad.model.ILcdModelListener;
import com.luciad.model.TLcdModelChangedEvent;
import com.luciad.model.TLcdVectorModel;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.symbology.app6a.model.ILcdEditableAPP6ACoded;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.ILcdMS2525bCoded;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.text.TLcdLonLatPointFormat;
import com.luciad.view.lightspeed.ILspView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import static com.company.front.ToolBar.getVista2D;
import static com.company.front.ToolBar.getVista3D;

public class ListadoBlancos implements ActionListener, ILcdModelListener {
    public ArrayList blancos;
    private static ListadoBlancos listadoBlancos;
    public JComboBox cbBlancos;
    public JTextField texCoordenadas;
    public TLcdLonLatPointFormat formato = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2);
    public TLcdLonLatHeightPoint point = new TLcdLonLatHeightPoint(0, 0, 0);

    public ListadoBlancos() {
    }

    public ListadoBlancos(JComboBox cbBlancos, JTextField texCoordenadas) {
        this.cbBlancos = cbBlancos;
        this.texCoordenadas = texCoordenadas;
        this.blancos = listadoBlancos();
        actualizaListadoBlancos();
        iniciaListadoListadoBlancos();
        new VentanaToolTip(cbBlancos);
        cbBlancos.addActionListener(this);
        actualiazaTextCoordenadasSistema();
        if (getVista3D() != null) {
            getVista3D().addLayerModelListener(this);
        }
        if (getVista2D() != null) {
            getVista2D().addModelListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cbBlancos)) {
            actualiazaTextCoordenadasSistema();
        }
    }

    @Override
    public void modelChanged(TLcdModelChangedEvent tLcdModelChangedEvent) {
        if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_ADDED) {
            actualizaListadoBlancos();
            iniciaListadoListadoBlancos();
            actualiazaTextCoordenadasSistema();
        } else if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_CHANGED) {
            actualizaListadoBlancos();
            iniciaListadoListadoBlancos();
            actualiazaTextCoordenadasSistema();
        } else if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.SOME_OBJECTS_REMOVED) {
            actualizaListadoBlancos();
            iniciaListadoListadoBlancos();
            actualiazaTextCoordenadasSistema();
        }

    }

    public static ArrayList listadoBlancos() {

        TLcdEditableMS2525bObject editableMS2525bObject = null;
        TLcdEditableAPP6AObject editableAPP6AObject = null;
        TLcdVectorModel vectorModel = null;
        ArrayList blancos = new ArrayList();

        int c = 0;
        Enumeration enumeration;
        if (getVista3D() instanceof ILspView && getVista3D() != null) {
            ILspView vista = (ILspView) getVista3D();
            //RECORRE TODAS LAS CAPAS
            for (int i = 0; i < vista.layerCount(); i++) {
                enumeration = vista.getLayer(i).getModel().elements();
                c = 0;
                while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                    vectorModel = (TLcdVectorModel) vista.getLayer(i).getModel();
                    editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c);
                    if (editableMS2525bObject.toString().substring(1, 2).equals("F") &&
                            editableMS2525bObject.toString().substring(4, 7).equals("PTS")) {
                        blancos.add(editableMS2525bObject);
                    }

                    c++;
                }
                enumeration = vista.getLayer(i).getModel().elements();
                while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                    vectorModel = (TLcdVectorModel) vista.getLayer(i).getModel();
                    editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                    if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                            editableAPP6AObject.toString().substring(10, 20).equals("2401000000")) {
                        blancos.add(editableAPP6AObject);
                    }

                    c++;
                }
            }
        }
        return blancos;
    }


    public Object getObjetoSistema(String nombre) {
        Object object = "null";
        if (nombre != null && nombre.toString() != "") {
            for (int i1 = 0; i1 < blancos.size(); i1++) {
                if (blancos.get(i1) instanceof TLcdEditableMS2525bObject) {
                    TLcdEditableMS2525bObject editableMS2525bObjects = (TLcdEditableMS2525bObject) blancos.get(i1);
                    if (editableMS2525bObjects.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation).equals(nombre)) {
                        object = editableMS2525bObjects;
                    }
                } else if (blancos.get(i1) instanceof TLcdEditableAPP6AObject) {
                    TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) blancos.get(i1);
                    if (editableAPP6AObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation).equals(nombre)) {
                        object = editableAPP6AObject;
                    }
                }


            }

        }

        return object;
    }

    public Object getObjeto(String nombre, ArrayList objeto) {
        Object object = null;
        if (nombre != null && nombre.toString() != "") {
            for (int i1 = 0; i1 < objeto.size(); i1++) {
                if (objeto.get(i1) instanceof TLcdEditableMS2525bObject) {
                    TLcdEditableMS2525bObject editableMS2525bObjects = (TLcdEditableMS2525bObject) objeto.get(i1);
                    if (editableMS2525bObjects.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation).equals(nombre)) {
                        object = editableMS2525bObjects;
                    }
                } else if (objeto.get(i1) instanceof TLcdEditableAPP6AObject) {
                    TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) objeto.get(i1);
                    if (editableAPP6AObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation).equals(nombre)) {
                        object = editableAPP6AObject;
                    }
                }

            }
        }

        return object;
    }

    public void verificaNombre(JComboBox jComboBox, String nombre) {
        for (int i = 0; i < jComboBox.getItemCount(); i++) {
            if (jComboBox.getItemAt(i).toString().equals(nombre)) {
                jComboBox.setSelectedIndex(i);
            }
        }

    }

    public void addListadoObjeto(JComboBox jComboBox, ArrayList arrayList) {
        jComboBox.removeAllItems();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) arrayList.get(i);
                jComboBox.addItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
            } else if (arrayList.get(i) instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) arrayList.get(i);
                jComboBox.addItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
            }

        }
    }

    public void iniciaListadoListadoBlancos() {
        String nombre = " ";
        if (cbBlancos != null && cbBlancos.getSelectedIndex() >= 0) {
            nombre = cbBlancos.getSelectedItem().toString();
        }
        addListadoObjeto(cbBlancos, this.blancos);
        verificaNombre(cbBlancos, nombre);
    }

    public void actualizaListadoBlancos() {
        this.blancos = listadoBlancos();
    }

    public void actualiazaTextCoordenadasSistema() {
        if (texCoordenadas != null && cbBlancos.getItemCount() > 0) {
            Object object = getObjetoSistema(cbBlancos.getSelectedItem().toString());
            if (object != "null" && object instanceof TLcdEditableMS2525bObject) {
                ILcdPoint p = ((TLcdEditableMS2525bObject) object).getPoint(0);
                point = new TLcdLonLatHeightPoint(p.getX(), p.getY(), p.getZ());
                texCoordenadas.setText(formato.format(point));
            } else if (object != "null" && object instanceof TLcdEditableAPP6AObject) {
                ILcdPoint p = ((TLcdEditableAPP6AObject) object).getPoint(0);
                point = new TLcdLonLatHeightPoint(p.getX(), p.getY(), p.getZ());
                texCoordenadas.setText(formato.format(point));

            }
        }
        else {
            point = new TLcdLonLatHeightPoint(0, 0, 0);
            texCoordenadas.setText(formato.format(point));
        }


    }

    public static ListadoBlancos mostrarListadoBlancos(JComboBox cbBlancos, JTextField texCoordenadas) {
        if (listadoBlancos == null) {
            listadoBlancos = new ListadoBlancos(cbBlancos, texCoordenadas);
        } else {
            listadoBlancos.actualizaListadoBlancos();
            listadoBlancos.iniciaListadoListadoBlancos();
        }
        return listadoBlancos;
    }


    //______________________________________________________________________________________________________________________
    public ArrayList getBlancos() {
        return blancos;
    }

    public void setBlancos(ArrayList blancos) {
        this.blancos = blancos;
    }

    public static ListadoBlancos getListadoBlancos() {
        return listadoBlancos;
    }

    public static void setListadoBlancos(ListadoBlancos listadoBlancos) {
        ListadoBlancos.listadoBlancos = listadoBlancos;
    }

    public TLcdLonLatHeightPoint getPoint() {
        return point;
    }

    public void setPoint(TLcdLonLatHeightPoint point) {
        this.point = point;
    }

    public static ListadoBlancos getSingletonInstance() {
        if (listadoBlancos == null) {
            listadoBlancos = new ListadoBlancos();
        } else {

        }
        return listadoBlancos;
    }
}
