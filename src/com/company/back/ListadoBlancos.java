package com.company.back;

import com.luciad.model.TLcdVectorModel;
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


public class ListadoBlancos {
    public ArrayList blancos = new ArrayList();

    public ArrayList obtieneBlancosDesdeVista(ILcdView vista) {
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
                    if (esBlancoPermitido(editableMS2525bObject)) {
                        blancos.add(editableMS2525bObject);
                    }
                    c++;
                }
                enumeration = vista3D.getLayer(i).getModel().elements();
                c = 0;
                while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                    vectorModel = (TLcdVectorModel) vista3D.getLayer(i).getModel();
                    editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                    if (esBlancoPermitido(editableAPP6AObject)) {
                        blancos.add(editableAPP6AObject);
                    }
                    c++;
                }
            }
        } else if (vista instanceof ILcdGXYView) {
            ILcdGXYView vista2D = (ILcdGXYView) vista;
            for (int i = 0; i < vista2D.layerCount(); i++) {
                enumeration = vista2D.getLayer(i).getModel().elements();
                c = 0;
                while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                    vectorModel = (TLcdVectorModel) vista2D.getLayer(i).getModel();
                    editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c);
                    if (esBlancoPermitido(editableMS2525bObject)) {
                        blancos.add(editableMS2525bObject);
                    }
                    c++;
                }
                enumeration = vista2D.getLayer(i).getModel().elements();
                c = 0;
                while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                    vectorModel = (TLcdVectorModel) vista2D.getLayer(i).getModel();
                    editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                    if (esBlancoPermitido(editableAPP6AObject)) {
                        blancos.add(editableAPP6AObject);
                    }
                    c++;
                }
            }

        }

        return blancos;
    }

    public boolean esBlancoPermitido(Object blanco) {
        TLcdEditableMS2525bObject editableMS2525bObject = null;
        TLcdEditableAPP6AObject editableAPP6AObject = null;
        if (blanco instanceof TLcdEditableMS2525bObject) {
            editableMS2525bObject = (TLcdEditableMS2525bObject) blanco;
            if (editableMS2525bObject.toString().substring(1, 2).equals("F") &&
                    editableMS2525bObject.toString().substring(4, 7).equals("PTS")||
                    editableMS2525bObject.toString().substring(4, 6).equals("LT")||
                    editableMS2525bObject.toString().substring(4, 7).equals("ATR")||
                    editableMS2525bObject.toString().substring(4, 7).equals("ATC")||
                    editableMS2525bObject.toString().substring(4, 7).equals("ATG")) {
                return true;
            }
        } else if (blanco instanceof TLcdEditableAPP6AObject) {
            editableAPP6AObject = (TLcdEditableAPP6AObject) blanco;
            if (editableAPP6AObject.toString().substring(3, 4).equals("2") &&
                    editableAPP6AObject.toString().substring(10, 20).equals("2401000000")) {
                return true;
            }
        }


        return false;
    }

    public boolean yaExisteBlanco(Object blancos) {
        if (this.blancos.size() > 0) {
            for (int i1 = 0; i1 < this.blancos.size(); i1++) {
                if (this.blancos.get(i1) instanceof TLcdEditableMS2525bObject && blancos instanceof TLcdEditableMS2525bObject) {
                    TLcdEditableMS2525bObject editableMS2525bObjects = (TLcdEditableMS2525bObject) this.blancos.get(i1);
                    TLcdEditableMS2525bObject editableMS2525bObjectsNuevo = (TLcdEditableMS2525bObject) blancos;
                    if (editableMS2525bObjects.equals(editableMS2525bObjectsNuevo)) {
                        return true;
                    }
                } else if (this.blancos.get(i1) instanceof TLcdEditableAPP6AObject && blancos instanceof TLcdEditableAPP6AObject) {
                    TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) this.blancos.get(i1);
                    TLcdEditableAPP6AObject editableAPP6AObjectNuevo = (TLcdEditableAPP6AObject) blancos;
                    if (editableAPP6AObject.equals(editableAPP6AObjectNuevo)) {
                        return true;

                    }
                }
            }
        }

        return false;
    }

    //______________________________________________________________________________________________________________________
    public ArrayList getBlancos() {
        return blancos;
    }

    public void setBlancos(ArrayList blancos) {
        this.blancos = blancos;
    }


}
