package com.company.back;

import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelListener;
import com.luciad.model.TLcdModelChangedEvent;
import com.luciad.model.TLcdVectorModel;
import com.luciad.symbology.app6a.model.ILcdAPP6ACoded;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.ILcdMS2525bCoded;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;

import java.util.Enumeration;

public class EventoModelosVista implements ILcdModelListener {


    @Override
    public void modelChanged(TLcdModelChangedEvent tLcdModelChangedEvent) {
        if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_ADDED) {
            try {
                agregarNombre(tLcdModelChangedEvent.getModel());
            } catch (Exception e) {

            }
        } else if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_CHANGED) {
            agregarNombre(tLcdModelChangedEvent.getModel());
        }
    }

    public void agregarNombre(ILcdModel model) {
        Enumeration enumeration = model.elements();
        int c = 0;
        while (enumeration.hasMoreElements()) {
            TLcdVectorModel vectorModel = (TLcdVectorModel) model;
            Object objeto = enumeration.nextElement();
            if (objeto instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c);
                if (editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation) == null) {
                    editableMS2525bObject.putTextModifier(ILcdMS2525bCoded.sUniqueDesignation, "Nuevo simbolo");
                }
            } else if (objeto instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                if (editableAPP6AObject.getTextModifierValue(ILcdAPP6ACoded.sUniqueDesignation) == null) {
                    editableAPP6AObject.putTextModifier(ILcdAPP6ACoded.sUniqueDesignation, "Nuevo simbolo");
                }
            }

            c++;
        }


    }
}
