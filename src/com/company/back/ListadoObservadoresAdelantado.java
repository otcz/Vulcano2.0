package com.company.back;


import com.luciad.model.TLcdVectorModel;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.text.TLcdLonLatPointFormat;
import com.luciad.view.ILcdView;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspAWTView;

import java.util.ArrayList;
import java.util.Enumeration;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;


public class ListadoObservadoresAdelantado {
    public ArrayList observadores = new ArrayList();

    public ArrayList obtieneObservadoresAdelantadosDesdeVista(ILcdView vista) {
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
                        if (esObservadorAdelantadoPermitido(editableMS2525bObject)) {
                            observadores.add(editableMS2525bObject);
                        }
                        c++;
                    }
                    enumeration = vista3D.getLayer(i).getModel().elements();
                    c = 0;
                    while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                        vectorModel = (TLcdVectorModel) vista3D.getLayer(i).getModel();
                        editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                        if (esObservadorAdelantadoPermitido(editableAPP6AObject)) {
                            observadores.add(editableAPP6AObject);
                        }
                        c++;
                    }
                }
            } else if (vista instanceof ILcdGXYView) {
                ILcdGXYView vista3D = (ILcdGXYView) vista;
                for (int i = 0; i < vista3D.layerCount(); i++) {
                    enumeration = vista3D.getLayer(i).getModel().elements();
                    c = 0;
                    while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                        vectorModel = (TLcdVectorModel) vista3D.getLayer(i).getModel();
                        editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c);
                        if (esObservadorAdelantadoPermitido(editableMS2525bObject)) {
                            observadores.add(editableMS2525bObject);
                        }
                        c++;
                    }
                    enumeration = vista3D.getLayer(i).getModel().elements();
                    c = 0;
                    while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                        vectorModel = (TLcdVectorModel) vista3D.getLayer(i).getModel();
                        editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                        if (esObservadorAdelantadoPermitido(editableAPP6AObject)) {
                            observadores.add(editableAPP6AObject);
                        }
                        c++;
                    }
                }
            }





        return observadores;
    }

    public boolean esObservadorAdelantadoPermitido(Object observadorAdelantado) {
        TLcdEditableMS2525bObject editableMS2525bObject = null;
        TLcdEditableAPP6AObject editableAPP6AObject = null;
        if (observadorAdelantado instanceof TLcdEditableMS2525bObject) {
            editableMS2525bObject = (TLcdEditableMS2525bObject) observadorAdelantado;
            if (observadorAdelantado.toString().substring(1, 2).equals("F") &&
                    editableMS2525bObject.toString().substring(6, 8).equals("OF")) {
                return true;
            }
        } else if (observadorAdelantado instanceof TLcdEditableAPP6AObject) {
            editableAPP6AObject = (TLcdEditableAPP6AObject) observadorAdelantado;
            if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                    editableAPP6AObject.toString().substring(10, 20).equals("1603000000")) {
                return true;
            }
        }

        return false;
    }

    public boolean yaExisteObservadorAdelantado(Object observadorAdelantado) {
        if (observadores.size() > 0) {
            for (int i1 = 0; i1 < observadores.size(); i1++) {
                if (observadores.get(i1) instanceof TLcdEditableMS2525bObject && observadorAdelantado instanceof TLcdEditableMS2525bObject) {
                    TLcdEditableMS2525bObject editableMS2525bObjects = (TLcdEditableMS2525bObject) observadores.get(i1);
                    TLcdEditableMS2525bObject editableMS2525bObjectsNuevo = (TLcdEditableMS2525bObject) observadorAdelantado;
                    if (editableMS2525bObjects.equals(editableMS2525bObjectsNuevo)) {
                        return true;
                    }
                } else if (observadores.get(i1) instanceof TLcdEditableAPP6AObject && observadorAdelantado instanceof TLcdEditableAPP6AObject) {
                    TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) observadores.get(i1);
                    TLcdEditableAPP6AObject editableAPP6AObjectNuevo = (TLcdEditableAPP6AObject) observadorAdelantado;
                    if (editableAPP6AObject.equals(editableAPP6AObjectNuevo)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //__________________________________________________________________________________________________________________
    public ArrayList getObservadores() {
        return observadores;
    }

    public void setObservadores(ArrayList observadores) {
        this.observadores = observadores;
    }


}
