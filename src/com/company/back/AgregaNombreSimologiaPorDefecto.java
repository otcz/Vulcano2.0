package com.company.back;

import com.luciad.datamodel.ILcdAnnotation;
import com.luciad.datamodel.ILcdDataObject;
import com.luciad.datamodel.TLcdDataProperty;
import com.luciad.datamodel.TLcdDataPropertyBuilder;
import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelListener;
import com.luciad.model.TLcdModelChangedEvent;
import com.luciad.model.TLcdVectorModel;
import com.luciad.symbology.app6a.model.ILcdAPP6ACoded;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.ILcdMS2525bCoded;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import javafx.beans.binding.ObjectExpression;

import java.util.Calendar;
import java.util.Enumeration;

import static com.luciad.internal.ogc.wfs.common.model.TLinWFS20DataTypes.PropertyName;

public class  AgregaNombreSimologiaPorDefecto implements ILcdModelListener {
    @Override
    public void modelChanged(TLcdModelChangedEvent tLcdModelChangedEvent) {
        if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_ADDED) {
            if (tLcdModelChangedEvent.getModel() instanceof TLcdVectorModel) {
                agregarNombre(tLcdModelChangedEvent.getModel());
            }
        }
    }

    public void agregarNombre(ILcdModel model) {
        Calendar calen = Calendar.getInstance();
        String fecha = "" + calen.get(Calendar.DAY_OF_MONTH) + calen.get(Calendar.HOUR_OF_DAY) +
                calen.get(Calendar.MINUTE) + calen.get(Calendar.SECOND) + (calen.get(Calendar.MONTH) + 1) + calen.get(Calendar.YEAR);
        Enumeration enumeration = model.elements();
        int c = 0;
        while (enumeration.hasMoreElements()) {
            TLcdVectorModel vectorModel = (TLcdVectorModel) model;
            Object objeto = enumeration.nextElement();
            if (objeto instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObject = (TLcdEditableMS2525bObject) vectorModel.elementAt(c);
                if (editableMS2525bObject.getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation) == null) {
                    editableMS2525bObject.putTextModifier(ILcdMS2525bCoded.sUniqueDesignation, "Nuevo simbolo" + fecha);


                }
            } else if (objeto instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) vectorModel.elementAt(c);
                if (editableAPP6AObject.getTextModifierValue(ILcdAPP6ACoded.sUniqueDesignation) == null) {
                    editableAPP6AObject.putTextModifier(ILcdAPP6ACoded.sName, "Nuevo simbolo" + fecha);
                    editableAPP6AObject.putTextModifier(ILcdAPP6ACoded.sUniqueDesignation, "Nuevo simbolo" + fecha);

                }
            }
            c++;

        }

    }
}
