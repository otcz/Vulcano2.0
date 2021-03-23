package com.company.back;

import com.company.front.DatosTirofrm;
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
import com.luciad.view.lightspeed.layer.ILspLayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import static com.company.front.ToolBar.getVista3D;

public class ListadoArmamento implements ActionListener, ILcdModelListener {
    DatosTirofrm datosTirofrm= DatosTirofrm.getSingletonInstance();
    private static ListadoArmamento listadoArmamento;
    public TLcdLonLatHeightPoint point = new TLcdLonLatHeightPoint(0, 0, 0);
    public TLcdLonLatPointFormat formato = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2);
    public JComboBox cbSelecionTipoArtilleria, cbSelecionTipoSistemaArilleria;
    public JTextField texCoornadasSistemaArilleria;
    public ArrayList[] sistemas;
    public ArrayList obusPesadoMS2525, obusMediadoMS2525, obusLivianoMS2525, morteroPesadoMS2525,
            morteroMedianoMS2525, morteroLivianoMS2525;

    public ListadoArmamento(JComboBox cbSelecionTipoArtilleria, JComboBox cbSelecionTipoSistemaArilleria, JTextField texCoornadasSistemaArilleria) {
        this.cbSelecionTipoArtilleria = cbSelecionTipoArtilleria;
        this.cbSelecionTipoSistemaArilleria = cbSelecionTipoSistemaArilleria;
        if (texCoornadasSistemaArilleria != null) {
            this.texCoornadasSistemaArilleria = texCoornadasSistemaArilleria;
        }
//        if (cbSelecionTipoArtilleria != null) {
//            cbSelecionTipoArtilleria.addActionListener(new eventoSeleccionTipoArtilleria());
//        }
        obusPesadoMS2525 = new ArrayList();
        obusMediadoMS2525 = new ArrayList();
        obusLivianoMS2525 = new ArrayList();
        morteroPesadoMS2525 = new ArrayList();
        morteroMedianoMS2525 = new ArrayList();
        morteroLivianoMS2525 = new ArrayList();
        cbSelecionTipoSistemaArilleria.addActionListener(this);
        getVista3D().addLayerModelListener(this);


        new VentanaToolTip(cbSelecionTipoSistemaArilleria);
        actualizaTodoLosProcesos();

    }

    public ListadoArmamento() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(cbSelecionTipoSistemaArilleria)) {
            actualiazaTextCoordenadasSistema();
        }

    }

    @Override
    public void modelChanged(TLcdModelChangedEvent tLcdModelChangedEvent) {
        if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_ADDED) {
            actualizaTodoLosProcesos();
        } else if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_CHANGED) {
            actualizaTodoLosProcesos();
        } else if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.SOME_OBJECTS_REMOVED) {
            actualizaTodoLosProcesos();
        }
    }

    public void actualiazaTextCoordenadasSistema() {
        if (texCoornadasSistemaArilleria != null && cbSelecionTipoSistemaArilleria.getSelectedIndex() >= 0)
        {//si no hay nada en el text de coordenadas
            Object object = getObjetoSistema(cbSelecionTipoSistemaArilleria.getSelectedItem().toString());//Crea un objeto filtrando el item seleccionado
            if (object != "null" && object instanceof TLcdEditableMS2525bObject) {//Si el objeto no es nulo y es instancia de Ms2525Object:
                ILcdPoint p = ((TLcdEditableMS2525bObject) object).getPoint(0);//Crea un IlcdPoint extrayendo el punto en el indice 0 del objeto
                point = (TLcdLonLatHeightPoint) p;
                texCoornadasSistemaArilleria.setText(formato.format(point));
            }// --------------------------------------------------------------------fin de Si el objeto no es nulo y es instancia de Ms2525Object:
            else if (object != "null" && object instanceof TLcdEditableAPP6AObject) {
                ILcdPoint p = ((TLcdEditableAPP6AObject) object).getPoint(0);
                point = new TLcdLonLatHeightPoint(p.getX(), p.getY(), p.getZ());
                texCoornadasSistemaArilleria.setText(formato.format(point));
            } else {
                texCoornadasSistemaArilleria.setText(formato.format(0, 0));
            }
        }//Fin de si no hay nada en el text de coordenadas
    }

    public void actualizaArrayListadoArmamneto() {
        int posicionActualSistema = -1;
        if (cbSelecionTipoSistemaArilleria != null) {
            posicionActualSistema = cbSelecionTipoSistemaArilleria.getSelectedIndex();
        }
        this.sistemas = listadoArmamento();
        setObusPesadoMS2525(this.sistemas[0]);
        setObusMediadoMS2525(this.sistemas[1]);
        setObusLivianoMS2525(this.sistemas[2]);
        setMorteroPesadoMS2525(this.sistemas[3]);
        setMorteroMedianoMS2525(this.sistemas[4]);
        setMorteroLivianoMS2525(this.sistemas[5]);
        addListadoObjeto(cbSelecionTipoSistemaArilleria, sistemas[0]);
        if (cbSelecionTipoArtilleria != null) {
            addListadoObjeto(cbSelecionTipoSistemaArilleria, sistemas[cbSelecionTipoArtilleria.getSelectedIndex()]);
        } else {
            addListadoObjeto(cbSelecionTipoSistemaArilleria, sistemas);
        }

         if (cbSelecionTipoSistemaArilleria.getItemCount()>1&&posicionActualSistema<cbSelecionTipoSistemaArilleria.getItemCount()) {
             cbSelecionTipoSistemaArilleria.setSelectedIndex(posicionActualSistema);
         }

    }

    public void actualizaTodoLosProcesos() {
        actualizaArrayListadoArmamneto();
        actualiazaTextCoordenadasSistema();
    }

    public ArrayList[] listadoArmamento() {
        String obusesMS2525Permitidas[] = {"SFGPEWHH-", "SFGPEWHM-", "SFGPEWHL-", "SFGPEWHHS", "SFGPEWHMS", "SFGPEWHLS"};
        String morteroMS2525Permitidas[] = {"SFGPEWOH-", "SFGPEWOM-", "SFGPEWOL-"};
        TLcdEditableMS2525bObject editableMS2525bObject = null;
        TLcdEditableAPP6AObject editableAPP6AObject = null;
        TLcdVectorModel vectorModel = null;
        ArrayList obusPesadoMS2525 = new ArrayList();
        ArrayList obusMediadoMS2525 = new ArrayList();
        ArrayList obusLivianoMS2525 = new ArrayList();
        ArrayList morteroPesadoMS2525 = new ArrayList();
        ArrayList morteroMedianoMS2525 = new ArrayList();
        ArrayList morteroLivianoMS2525 = new ArrayList();
        int c = 0;
        Enumeration enumeration;
        ILspView vista = getVista3D();
        for (int i = 0; i < vista.layerCount(); i++) {
            enumeration = vista.getLayer(i).getModel().elements();
            c = 0;
            while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                vectorModel = (TLcdVectorModel) vista.getLayer(i).getModel();
                editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c);
                for (int i1 = 0; i1 < obusesMS2525Permitidas.length; i1++) {
                    if (editableMS2525bObject.toString().substring(0, 9).equals(obusesMS2525Permitidas[i1]) &&
                            editableMS2525bObject.toString().substring(7, 8).equals("H")) {
                        obusPesadoMS2525.add(editableMS2525bObject);
                    } else if (editableMS2525bObject.toString().substring(0, 9).equals(obusesMS2525Permitidas[i1]) &&
                            editableMS2525bObject.toString().substring(7, 8).equals("M")) {
                        obusMediadoMS2525.add(editableMS2525bObject);
                    } else if (editableMS2525bObject.toString().substring(0, 9).equals(obusesMS2525Permitidas[i1]) &&
                            editableMS2525bObject.toString().substring(7, 8).equals("L")) {
                        obusLivianoMS2525.add(editableMS2525bObject);
                    }
                }
                for (int j1 = 0; j1 < morteroMS2525Permitidas.length; j1++) {
                    if (editableMS2525bObject.toString().substring(0, 9).equals(morteroMS2525Permitidas[j1]) &&
                            editableMS2525bObject.toString().substring(7, 8).equals("H")) {
                        morteroPesadoMS2525.add(editableMS2525bObject);
                    } else if (editableMS2525bObject.toString().substring(0, 9).equals(morteroMS2525Permitidas[j1]) &&
                            editableMS2525bObject.toString().substring(7, 8).equals("M")) {
                        morteroMedianoMS2525.add(editableMS2525bObject);
                    } else if (editableMS2525bObject.toString().substring(0, 9).equals(morteroMS2525Permitidas[j1]) &&
                            editableMS2525bObject.toString().substring(7, 8).equals("L")) {
                        morteroLivianoMS2525.add(editableMS2525bObject);
                    }
                }
                c++;
            }
            enumeration = vista.getLayer(i).getModel().elements();
            c = 0;
            while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                vectorModel = (TLcdVectorModel) vista.getLayer(i).getModel();
                editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                        editableAPP6AObject.toString().substring(15, 16).equals("3") &&
                        editableAPP6AObject.toString().substring(11, 12).equals("7")) {
                    obusPesadoMS2525.add(editableAPP6AObject);
                } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                        editableAPP6AObject.toString().substring(15, 16).equals("2") &&
                        editableAPP6AObject.toString().substring(11, 12).equals("7")) {
                    obusMediadoMS2525.add(editableAPP6AObject);
                } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                        editableAPP6AObject.toString().substring(15, 16).equals("1") &&
                        editableAPP6AObject.toString().substring(11, 12).equals("7")) {
                    obusLivianoMS2525.add(editableAPP6AObject);
                } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                        editableAPP6AObject.toString().substring(15, 16).equals("3") &&
                        editableAPP6AObject.toString().substring(11, 12).equals("9")) {
                    morteroPesadoMS2525.add(editableAPP6AObject);
                } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                        editableAPP6AObject.toString().substring(15, 16).equals("2") &&
                        editableAPP6AObject.toString().substring(11, 12).equals("9")) {
                    morteroMedianoMS2525.add(editableAPP6AObject);
                } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                        editableAPP6AObject.toString().substring(15, 16).equals("1") &&
                        editableAPP6AObject.toString().substring(11, 12).equals("9")) {
                    morteroLivianoMS2525.add(editableAPP6AObject);
                }

                c++;
            }
        }


        return new ArrayList[]{obusPesadoMS2525, obusMediadoMS2525, obusLivianoMS2525,
                morteroPesadoMS2525, morteroMedianoMS2525, morteroLivianoMS2525};
    }


    public Object getObjetoSistema(String nombre) {
        Object object = new TLcdEditableMS2525bObject();
        if (nombre != null && nombre.toString() != "" && nombre.toString() != "null") {
            for (int i = 0; i < sistemas.length; i++) {
                for (int i1 = 0; i1 < sistemas[i].size(); i1++) {
                    if (sistemas[i].get(i1) instanceof TLcdEditableMS2525bObject) {
                        TLcdEditableMS2525bObject editableMS2525bObjects = (TLcdEditableMS2525bObject) sistemas[i].get(i1);
                        if (editableMS2525bObjects.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation).equals(nombre)) {
                            object = editableMS2525bObjects;
                        }
                    } else if (sistemas[i].get(i1) instanceof TLcdEditableAPP6AObject) {
                        TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) sistemas[i].get(i1);
                        if (editableAPP6AObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation).equals(nombre)) {
                            object = editableAPP6AObject;
                        }
                    }


                }
            }
        }

        return object;
    }

    public ILspLayer getCapaSistema(String nombre) {
        ILspView view = getVista3D();
        ILspLayer capa = null;
        for (int i = 0; i < view.layerCount(); i++) {
            if (view.getLayer(i).getLabel().equals(nombre)) {
                capa = view.getLayer(i);
            }
        }


        return capa;
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

    public void addListadoObjeto(JComboBox jComboBox, ArrayList arrayList) {
        jComboBox.removeAllItems();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) arrayList.get(i);
                jComboBox.addItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
                jComboBox.setSelectedItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
            } else if (arrayList.get(i) instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) arrayList.get(i);
                jComboBox.addItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
                jComboBox.setSelectedItem((editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation)));
            }

        }
    }

    public void addListadoObjeto(JComboBox jComboBox, ArrayList arrayList[]) {
        jComboBox.removeAllItems();
        for (int j = 0; j < arrayList.length; j++) {
            for (int i = 0; i < arrayList[j].size(); i++) {
                if (arrayList[j].get(i) instanceof TLcdEditableMS2525bObject) {
                    TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) arrayList[j].get(i);
                    jComboBox.addItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
                    jComboBox.setSelectedItem(editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
                } else if (arrayList[j].get(i) instanceof TLcdEditableAPP6AObject) {
                    TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) arrayList[j].get(i);
                    jComboBox.addItem(editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation));
                    jComboBox.setSelectedItem((editableAPP6AObject.getTextModifierValue(ILcdEditableAPP6ACoded.sUniqueDesignation)));
                }

            }
        }
    }

    public static ListadoArmamento mostrarListadosArmamento(JComboBox cbSelecionTipoArtilleria, JComboBox cbSelecionTipoSistemaArilleria, JTextField texCoornadasSistemaArilleria) {
        if (listadoArmamento == null) {
            listadoArmamento = new ListadoArmamento(cbSelecionTipoArtilleria, cbSelecionTipoSistemaArilleria, texCoornadasSistemaArilleria);
        } else {
        }
        return listadoArmamento;
    }


    //______________________________________________________________________________________________________________________
    public ArrayList getObusPesadoMS2525() {
        return obusPesadoMS2525;
    }

    public void setObusPesadoMS2525(ArrayList obusPesadoMS2525) {
        this.obusPesadoMS2525 = obusPesadoMS2525;
    }

    public ArrayList getObusMediadoMS2525() {
        return obusMediadoMS2525;
    }

    public void setObusMediadoMS2525(ArrayList obusMediadoMS2525) {
        this.obusMediadoMS2525 = obusMediadoMS2525;
    }

    public ArrayList getObusLivianoMS2525() {
        return obusLivianoMS2525;
    }

    public void setObusLivianoMS2525(ArrayList obusLivianoMS2525) {
        this.obusLivianoMS2525 = obusLivianoMS2525;
    }

    public ArrayList getMorteroPesadoMS2525() {
        return morteroPesadoMS2525;
    }

    public void setMorteroPesadoMS2525(ArrayList morteroPesadoMS2525) {
        this.morteroPesadoMS2525 = morteroPesadoMS2525;
    }

    public ArrayList getMorteroMedianoMS2525() {
        return morteroMedianoMS2525;
    }

    public void setMorteroMedianoMS2525(ArrayList morteroMedianoMS2525) {
        this.morteroMedianoMS2525 = morteroMedianoMS2525;
    }

    public ArrayList getMorteroLivianoMS2525() {
        return morteroLivianoMS2525;
    }

    public void setMorteroLivianoMS2525(ArrayList morteroLivianoMS2525) {
        this.morteroLivianoMS2525 = morteroLivianoMS2525;
    }

    public TLcdLonLatHeightPoint getPoint() {
        return point;
    }

    public void setPoint(TLcdLonLatHeightPoint point) {
        this.point = point;
    }

//    public class eventoSeleccionTipoArtilleria implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource().equals(cbSelecionTipoArtilleria)) {
//                switch (cbSelecionTipoArtilleria.getSelectedIndex()) {
//                    case 0:
//                        addListadoObjeto(cbSelecionTipoSistemaArilleria, obusPesadoMS2525);
//                        break;
//                    case 1:
//                        addListadoObjeto(cbSelecionTipoSistemaArilleria, obusMediadoMS2525);
//                        break;
//                    case 2:
//                        addListadoObjeto(cbSelecionTipoSistemaArilleria, obusLivianoMS2525);
//                        break;
//                    case 3:
//                        addListadoObjeto(cbSelecionTipoSistemaArilleria, morteroPesadoMS2525);
//                        break;
//                    case 4:
//                        addListadoObjeto(cbSelecionTipoSistemaArilleria, morteroMedianoMS2525);
//                        break;
//                    case 5:
//                        addListadoObjeto(cbSelecionTipoSistemaArilleria, morteroLivianoMS2525);
//                        break;
//                }
//
//            } else if (e.getSource().equals(cbSelecionTipoSistemaArilleria)) {
//                if (texCoornadasSistemaArilleria != null) {
//                    if (cbSelecionTipoSistemaArilleria.getSelectedItem() != null) {
//                        actualiazaTextCoordenadasSistema();
//                    } else if (cbSelecionTipoSistemaArilleria.getSelectedItem() == null) {
//                        texCoornadasSistemaArilleria.setText(formato.format(0, 0));
//                    }
//                }
//
//            }
//        }
//    }

    public static ListadoArmamento getSingletonInstance() {
        if (listadoArmamento == null) {
            listadoArmamento = new ListadoArmamento();
        } else {

        }
        return listadoArmamento;
    }

}
