package com.company.back;


import com.luciad.model.ILcdModel;
import com.luciad.model.TLcdVectorModel;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.text.TLcdLonLatPointFormat;
import com.luciad.util.ILcdFireEventMode;
import com.luciad.util.ILcdSelectionListener;
import com.luciad.util.TLcdSelectionChangedEvent;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;

public class SeleccionObjetoVista implements PropertyChangeListener, ILcdSelectionListener {
    TLcdEditableMS2525bObject editableMS2525bObject = null;
    TLcdEditableAPP6AObject editableAPP6AObject = null;
    ILcdPoint puntoInicio = new TLcdLonLatHeightPoint(0, 0, 0);
    JFormattedTextField texCoordenadas;
    TLcdSelectionChangedEvent eventoObjetoSelecionado;
    TLcdLonLatPointFormat formato = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2);


    public  SeleccionObjetoVista(JFormattedTextField texCoordenada) {
        this.texCoordenadas = texCoordenada;
        this.texCoordenadas.addPropertyChangeListener(this);
    }

    @Override
    public void selectionChanged(final TLcdSelectionChangedEvent eventoObjetoSelecionado) {
        this.eventoObjetoSelecionado = eventoObjetoSelecionado;

        if (eventoObjetoSelecionado.getElementAt(0) instanceof TLcdEditableAPP6AObject) {
            editableAPP6AObject = (TLcdEditableAPP6AObject) eventoObjetoSelecionado.getElementAt(0);
            puntoInicio = editableAPP6AObject.getFocusPoint();
            final String[] previousValue = {formato.format(puntoInicio.getX(), puntoInicio.getY())};
            this.texCoordenadas.setText(previousValue[0]);
            this.texCoordenadas.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            this.texCoordenadas.setEnabled(true);
        } else if (eventoObjetoSelecionado.getElementAt(0) instanceof TLcdEditableMS2525bObject) {
            editableMS2525bObject = (TLcdEditableMS2525bObject) eventoObjetoSelecionado.getElementAt(0);
            puntoInicio = editableMS2525bObject.getFocusPoint();
            String[] previousValue = {formato.format(puntoInicio.getX(), puntoInicio.getY())};
            this.texCoordenadas.setText(previousValue[0]);
            this.texCoordenadas.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            this.texCoordenadas.setEnabled(true);
        } else {
            this.texCoordenadas.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            this.texCoordenadas.setEnabled(false);
            puntoInicio = new TLcdLonLatHeightPoint(0, 0, 0);
            final String[] previousValue = {formato.format(puntoInicio)};
            this.texCoordenadas.setText(previousValue[0]);
        }
        if (eventoObjetoSelecionado.getSelection().getSelectionCount()==0) {
            this.texCoordenadas.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
            this.texCoordenadas.setEnabled(false);
            puntoInicio = new TLcdLonLatHeightPoint(0, 0, 0);
            final String[] previousValue = {formato.format(puntoInicio)};
            this.texCoordenadas.setText(previousValue[0]);
        }
    }

    public void moved(Enumeration enumeration) {
        TLcdEditableMS2525bObject editableMS2525bObject = null;
        while (enumeration.hasMoreElements()) {
            TLcdVectorModel vectorModel = new TLcdVectorModel();
            editableMS2525bObject = (TLcdEditableMS2525bObject) enumeration.nextElement();
            vectorModel.addElement(editableMS2525bObject, ILcdFireEventMode.FIRE_NOW);
            editableMS2525bObject.move2D((ILcdPoint) this.texCoordenadas.getValue());
            vectorModel.elementChanged(editableMS2525bObject, ILcdModel.FIRE_NOW);

        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource().equals(this.texCoordenadas)) {
            if ("value".equals(evt.getPropertyName())) {
                moved(this.eventoObjetoSelecionado.getSelection().selectedObjects());
            }
        }
    }


}
