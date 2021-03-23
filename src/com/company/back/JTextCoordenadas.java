package com.company.back;


import com.luciad.lucy.ILcyLucyEnv;
import com.luciad.lucy.map.ILcyGenericMapManagerListener;
import com.luciad.lucy.map.TLcyGenericMapManagerEvent;
import com.luciad.model.ILcdModel;
import com.luciad.model.ILcdModelListener;
import com.luciad.model.TLcdModelChangedEvent;
import com.luciad.model.TLcdVectorModel;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.text.TLcdLonLatPointFormat;
import com.luciad.util.ILcdFireEventMode;
import com.luciad.util.ILcdSelectionListener;
import com.luciad.util.TLcdSelectionChangedEvent;
import com.luciad.view.ILcdLayer;
import com.luciad.view.ILcdView;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspAWTView;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;

import static com.company.front.ToolBar.getVista;

public class JTextCoordenadas extends JFormattedTextField implements PropertyChangeListener, ILcdSelectionListener, ILcdModelListener {
    TLcdEditableMS2525bObject editableMS2525bObject = null;
    TLcdEditableAPP6AObject editableAPP6AObject = null;
    ILcdPoint point = new TLcdLonLatHeightPoint(0, 0, 0);
    TLcdSelectionChangedEvent objetosSelecionados = null;
    TLcdLonLatPointFormat formato = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2);

    public JTextCoordenadas() {
        super(new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2));
        String previousValue = new String(formato.format(point.getX(), point.getY()));
        setText(previousValue);
        setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        setEnabled(false);
        addPropertyChangeListener(this);
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

    @Override
    public void selectionChanged(final TLcdSelectionChangedEvent eventoObjetoSelecionado) {
        objetosSelecionados = eventoObjetoSelecionado;
        Object object = eventoObjetoSelecionado.getElementAt(0);
        if (eventoObjetoSelecionado.elementCount() > 0) {
            if (object instanceof TLcdEditableAPP6AObject && eventoObjetoSelecionado.getSelection().isSelected(object)) {
                editableAPP6AObject = (TLcdEditableAPP6AObject) eventoObjetoSelecionado.getElementAt(0);
                point = editableAPP6AObject.getFocusPoint();
                String[] previousValue = {formato.format(point.getX(), point.getY())};
                setText(previousValue[0]);
                setEnabled(true);
            } else if (object instanceof TLcdEditableMS2525bObject && eventoObjetoSelecionado.getSelection().isSelected(object)) {
                editableMS2525bObject = (TLcdEditableMS2525bObject) eventoObjetoSelecionado.getElementAt(0);
                point = editableMS2525bObject.getFocusPoint();
                String[] previousValue = {formato.format(point.getX(), point.getY())};
                setText(previousValue[0]);
                setEnabled(true);
            }
        } else {
            setEnabled(false);
            point = new TLcdLonLatHeightPoint(0, 0, 0);
            final String[] previousValue = {formato.format(point)};
            setText(previousValue[0]);
        }


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource().equals(this)) {
            if ("value".equals(evt.getPropertyName())) {
                moved(objetosSelecionados.getSelection().selectedObjects());
            }
        }
    }

    @Override
    public void modelChanged(TLcdModelChangedEvent tLcdModelChangedEvent) {
        if (tLcdModelChangedEvent.getCode() == TLcdModelChangedEvent.OBJECT_CHANGED) {
            Enumeration enumeration = tLcdModelChangedEvent.getModel().elements();
            while (enumeration.hasMoreElements() && enumeration.nextElement() instanceof TLcdEditableMS2525bObject) {
                TLcdEditableMS2525bObject editableMS2525bObjects = (TLcdEditableMS2525bObject) tLcdModelChangedEvent.elements().nextElement();
                setText(formato.format(editableMS2525bObjects.getPoint(0)));
            }
            Enumeration enumeration2 = tLcdModelChangedEvent.getModel().elements();
            while (enumeration2.hasMoreElements() && enumeration2.nextElement() instanceof TLcdEditableAPP6AObject) {
                TLcdEditableAPP6AObject editableAPP6AObject = (TLcdEditableAPP6AObject) tLcdModelChangedEvent.elements().nextElement();
                setText(formato.format(editableAPP6AObject.getPoint(0)));
            }
        }
    }

    public void moved(Enumeration enumeration) {
        TLcdEditableMS2525bObject editableMS2525bObject = null;
        TLcdEditableAPP6AObject editableAPP6AObject = null;
        while (enumeration.hasMoreElements()) {
            Object object=enumeration.nextElement();
            if (object instanceof TLcdEditableMS2525bObject) {
                TLcdVectorModel vectorModel = new TLcdVectorModel();
                editableMS2525bObject = (TLcdEditableMS2525bObject) object;
                vectorModel.addElement(editableMS2525bObject, ILcdFireEventMode.FIRE_NOW);
                editableMS2525bObject.move2D((ILcdPoint) getValue());
                vectorModel.elementChanged(editableMS2525bObject, ILcdModel.FIRE_NOW);
            }  if (object instanceof TLcdEditableAPP6AObject) {
                TLcdVectorModel vectorModel = new TLcdVectorModel();
                editableAPP6AObject = (TLcdEditableAPP6AObject) object;
                vectorModel.addElement(editableAPP6AObject, ILcdFireEventMode.FIRE_NOW);
                editableAPP6AObject.move2D((ILcdPoint) getValue());
                vectorModel.elementChanged(editableAPP6AObject, ILcdModel.FIRE_NOW);
            }

        }
    }
}
