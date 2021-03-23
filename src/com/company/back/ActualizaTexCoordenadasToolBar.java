package com.company.back;

import com.luciad.model.ILcdModelListener;
import com.luciad.model.TLcdModelChangedEvent;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.text.TLcdLonLatPointFormat;

import javax.swing.*;
import java.util.Enumeration;

public class ActualizaTexCoordenadasToolBar implements ILcdModelListener {
    JTextField texCoordenadas;
    TLcdLonLatPointFormat formato = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2);

    public ActualizaTexCoordenadasToolBar(JTextField texCoordenadas) {
        this.texCoordenadas = texCoordenadas;

    }

    @Override
    public void modelChanged(TLcdModelChangedEvent tLcdModelChangedEvent) {
        if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_CHANGED) {
            Enumeration enumeration =tLcdModelChangedEvent.getModel().elements();
            while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObjects = (TLcdEditableMS2525bObject) tLcdModelChangedEvent.elements().nextElement();
                texCoordenadas.setText(formato.format(editableMS2525bObjects.getPoint(0)));

            }
            while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) tLcdModelChangedEvent.elements().nextElement();
                texCoordenadas.setText(formato.format(editableAPP6AObject.getPoint(0)));
            }
        }

    }

    public void actualizaCoordenadas(Enumeration enumeration) {



    }
}
