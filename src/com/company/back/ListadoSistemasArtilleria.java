package com.company.back;

import com.luciad.model.TLcdVectorModel;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.view.ILcdView;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.ILspView;
import java.util.ArrayList;
import java.util.Enumeration;


public class ListadoSistemasArtilleria {
    private ArrayList obusPesado = new ArrayList();
    private ArrayList obusMediado = new ArrayList();
    private ArrayList obusLiviano = new ArrayList();
    private ArrayList morteroPesado = new ArrayList();
    private ArrayList morteroMediano = new ArrayList();
    private ArrayList morteroLiviano = new ArrayList();
    private ArrayList sistemas = new ArrayList();


    public ArrayList obtieneSistemasArtilleriaDesdeVista(ILcdView vista) {
        TLcdEditableMS2525bObject editableMS2525bObject = null;
        TLcdEditableAPP6AObject editableAPP6AObject = null;
        TLcdVectorModel vectorModel = null;
        int c = 0;
        Enumeration enumeration;
        if (vista instanceof ILspView) {
            ILspView vista3D = (ILspView) vista;
            for (int i = 0; i < vista3D.layerCount(); i++) {
                enumeration = vista3D.getLayer(i).getModel().elements();
                c = 0;
                while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                    vectorModel = (TLcdVectorModel) vista3D.getLayer(i).getModel();
                    editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c);
                    if (esSistemaArtilleriaPermitido(editableMS2525bObject)) {
                        sistemas.add(editableMS2525bObject);
                    }
                    c++;
                }
                enumeration = vista3D.getLayer(i).getModel().elements();
                c = 0;
                while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                    vectorModel = (TLcdVectorModel) vista3D.getLayer(i).getModel();
                    editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                    if (esSistemaArtilleriaPermitido(editableAPP6AObject)) {
                        sistemas.add(editableAPP6AObject);
                    }
                    c++;
                }
            }
        }
        if (vista instanceof ILcdGXYView) {
            ILcdGXYView vista3D = (ILcdGXYView) vista;
            for (int i = 0; i < vista3D.layerCount(); i++) {
                enumeration = vista3D.getLayer(i).getModel().elements();
                c = 0;
                while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                    vectorModel = (TLcdVectorModel) vista3D.getLayer(i).getModel();
                    editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c);
                    if (esSistemaArtilleriaPermitido(editableMS2525bObject)) {
                        sistemas.add(editableMS2525bObject);
                    }
                    c++;
                }
                enumeration = vista3D.getLayer(i).getModel().elements();
                c = 0;
                while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                    vectorModel = (TLcdVectorModel) vista3D.getLayer(i).getModel();
                    editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                    if (esSistemaArtilleriaPermitido(editableAPP6AObject)) {
                        sistemas.add(editableAPP6AObject);
                    }
                    c++;
                }
            }

        }


        return sistemas;
    }

    public boolean esSistemaArtilleriaPermitido(Object sistema) {
        String obusesMS2525Permitidas[] = {"SFGPEWHH-", "SFGPEWHM-", "SFGPEWHL-", "SFGPEWHHS", "SFGPEWHMS", "SFGPEWHLS"};
        String morteroMS2525Permitidas[] = {"SFGPEWOH-", "SFGPEWOM-", "SFGPEWOL-"};
        TLcdEditableMS2525bObject editableMS2525bObject = null;
        TLcdEditableAPP6AObject editableAPP6AObject = null;
        if (sistema instanceof TLcdEditableMS2525bObject) {
            editableMS2525bObject = (TLcdEditableMS2525bObject) sistema;
            for (int i1 = 0; i1 < obusesMS2525Permitidas.length; i1++) {
                if (editableMS2525bObject.toString().substring(0, 9).equals(obusesMS2525Permitidas[i1]) &&
                        editableMS2525bObject.toString().substring(7, 8).equals("H")) {
                    return true;
                } else if (editableMS2525bObject.toString().substring(0, 9).equals(obusesMS2525Permitidas[i1]) &&
                        editableMS2525bObject.toString().substring(7, 8).equals("M")) {
                    return true;
                } else if (editableMS2525bObject.toString().substring(0, 9).equals(obusesMS2525Permitidas[i1]) &&
                        editableMS2525bObject.toString().substring(7, 8).equals("L")) {
                    return true;
                }
            }
            for (int j1 = 0; j1 < morteroMS2525Permitidas.length; j1++) {
                if (editableMS2525bObject.toString().substring(0, 9).equals(morteroMS2525Permitidas[j1]) &&
                        editableMS2525bObject.toString().substring(7, 8).equals("H")) {
                    return true;
                } else if (editableMS2525bObject.toString().substring(0, 9).equals(morteroMS2525Permitidas[j1]) &&
                        editableMS2525bObject.toString().substring(7, 8).equals("M")) {
                    return true;
                } else if (editableMS2525bObject.toString().substring(0, 9).equals(morteroMS2525Permitidas[j1]) &&
                        editableMS2525bObject.toString().substring(7, 8).equals("L")) {
                    return true;
                }
            }


        } else if (sistema instanceof TLcdEditableAPP6AObject) {
            editableAPP6AObject = (TLcdEditableAPP6AObject) sistema;
            if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                    editableAPP6AObject.toString().substring(15, 16).equals("3") &&
                    editableAPP6AObject.toString().substring(11, 12).equals("7")) {
                return true;
            } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                    editableAPP6AObject.toString().substring(15, 16).equals("2") &&
                    editableAPP6AObject.toString().substring(11, 12).equals("7")) {
                return true;
            } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                    editableAPP6AObject.toString().substring(15, 16).equals("1") &&
                    editableAPP6AObject.toString().substring(11, 12).equals("7")) {
                return true;
            } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                    editableAPP6AObject.toString().substring(15, 16).equals("3") &&
                    editableAPP6AObject.toString().substring(11, 12).equals("9")) {
            } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                    editableAPP6AObject.toString().substring(15, 16).equals("2") &&
                    editableAPP6AObject.toString().substring(11, 12).equals("9")) {
                return true;
            } else if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                    editableAPP6AObject.toString().substring(15, 16).equals("1") &&
                    editableAPP6AObject.toString().substring(11, 12).equals("9")) {
                return true;
            }
        }

        return false;
    }

    public boolean yaExisteSistemaArtilleria(Object sistemaArtilleria) {
        if (getSistemas().size() > 0) {
            for (int i1 = 0; i1 < getSistemas().size(); i1++) {
                if (getSistemas().get(i1) instanceof TLcdEditableMS2525bObject && sistemaArtilleria instanceof TLcdEditableMS2525bObject) {
                    TLcdEditableMS2525bObject editableMS2525bObjects = (TLcdEditableMS2525bObject) getSistemas().get(i1);
                    TLcdEditableMS2525bObject editableMS2525bObjectsNuevo = (TLcdEditableMS2525bObject) sistemaArtilleria;
                    if (editableMS2525bObjects.equals(editableMS2525bObjectsNuevo)) {
                        return true;
                    }
                } else if (getSistemas().get(i1) instanceof TLcdEditableAPP6AObject && sistemaArtilleria instanceof TLcdEditableAPP6AObject) {
                    TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) getSistemas().get(i1);
                    TLcdEditableAPP6AObject editableAPP6AObjectNuevo = (TLcdEditableAPP6AObject) sistemaArtilleria;
                    if (editableAPP6AObject.equals(editableAPP6AObjectNuevo)) {
                        return true;

                    }
                }
            }
        }

        return false;
    }


    //______________________________________________________________________________________________________________________

    public ArrayList getSistemas() {
        return sistemas;
    }

    public void setSistemas(ArrayList sistemas) {
        this.sistemas = sistemas;
    }

    public ArrayList getObusPesado() {
        return obusPesado;
    }

    public void setObusPesado(ArrayList obusPesado) {
        this.obusPesado = obusPesado;
    }

    public ArrayList getObusMediado() {
        return obusMediado;
    }

    public void setObusMediado(ArrayList obusMediado) {
        this.obusMediado = obusMediado;
    }

    public ArrayList getObusLiviano() {
        return obusLiviano;
    }

    public void setObusLiviano(ArrayList obusLiviano) {
        this.obusLiviano = obusLiviano;
    }

    public ArrayList getMorteroPesado() {
        return morteroPesado;
    }

    public void setMorteroPesado(ArrayList morteroPesado) {
        this.morteroPesado = morteroPesado;
    }

    public ArrayList getMorteroMediano() {
        return morteroMediano;
    }

    public void setMorteroMediano(ArrayList morteroMediano) {
        this.morteroMediano = morteroMediano;
    }

    public ArrayList getMorteroLiviano() {
        return morteroLiviano;
    }

    public void setMorteroLiviano(ArrayList morteroLiviano) {
        this.morteroLiviano = morteroLiviano;
    }

}
