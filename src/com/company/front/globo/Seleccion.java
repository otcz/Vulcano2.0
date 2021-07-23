package com.company.front.globo;

import com.luciad.util.ILcdSelectionListener;
import com.luciad.util.TLcdSelectionChangedEvent;
import com.luciad.view.*;
import com.luciad.view.swing.ALcdBalloonDescriptor;
import com.luciad.view.swing.ALcdBalloonManager;
import com.luciad.view.swing.TLcdModelElementBalloonDescriptor;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;

public class Seleccion implements ILcdSelectionListener, ILcdLayeredListener,
        PropertyChangeListener {

    private Timer fTimer;
    private ILcdView fView;
    private ALcdBalloonManager mensajeManeger;
    private ALcdBalloonDescriptor fLastDescriptor;

    public Seleccion(ILcdView aView, ALcdBalloonManager aBalloonManager) {
        if (aView == null || aBalloonManager == null) {
            throw new IllegalArgumentException("Arguments of ViewSelectionBalloonListener must not be null");
        }

        fView = aView;
        mensajeManeger = aBalloonManager;
        //use a delay of 150 ms
        fTimer = new Timer(150, new MyActionListener());
        fTimer.setRepeats(false);
    }

    public void selectionChanged(TLcdSelectionChangedEvent aSelectionEvent) {
        notifyUpdate();
    }

    public void layeredStateChanged(TLcdLayeredEvent e) {
        if (e.getID() == TLcdLayeredEvent.LAYER_REMOVED || e.getID() == TLcdLayeredEvent.LAYER_ADDED|| e.getID() == TLcdLayeredEvent.STATE_CHANGED
                || e.getID() == TLcdLayeredEvent.LAYER_MOVED) {
            notifyUpdate();
        }
    }
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private void notifyUpdate() {
        if (mensajeManeger != null) {
            fLastDescriptor = mensajeManeger.getBalloonDescriptor();
        }
        if (fTimer.isRunning()) {
            fTimer.restart();
        } else {
            fTimer.start();
        }
    }

    public class MyActionListener implements ActionListener {
        private Object objetoSeleccionado;
        private ILcdLayer layer;

        public void actionPerformed(ActionEvent aActionEvent) {
            try {
                if (mensajeManeger != null && !(isNewDescriptorSet(mensajeManeger))) {
                    calculateSelectedObject();
                    if (objetoSeleccionado != null && layer != null) {
                        mensajeManeger.setBalloonDescriptor(new TLcdModelElementBalloonDescriptor(objetoSeleccionado, layer.getModel(), layer));
                    } else {
                        mensajeManeger.setBalloonDescriptor(null);
                    }
                }
            } catch (NullPointerException e) {
            }

            objetoSeleccionado = null;
            layer = null;
            fLastDescriptor = null;
        }

        private boolean isNewDescriptorSet(ALcdBalloonManager aBalloonManager) {
            ALcdBalloonDescriptor descriptor = aBalloonManager.getBalloonDescriptor();
            if (fLastDescriptor == null) {
                return !(descriptor == null);
            } else {
                return descriptor == null || fLastDescriptor != descriptor;
            }
        }

        private void calculateSelectedObject() {
            //clear the fields
            objetoSeleccionado = null;
            layer = null;
            if (fView instanceof ILcdLayered) {
                Enumeration layers = ((ILcdLayered) fView).layers();
                boolean dejarBuscar = false;
                while (layers.hasMoreElements() && !dejarBuscar) {
                    ILcdLayer layer = (ILcdLayer) layers.nextElement();
                    Enumeration enumeration = layer.selectedObjects();
                    while (enumeration.hasMoreElements() && !dejarBuscar) {
                        Object objetoSeleccionado = enumeration.nextElement();
                        if (this.objetoSeleccionado == null) {
                            this.layer = layer;
                            this.objetoSeleccionado = objetoSeleccionado;
                        } else {
                            dejarBuscar = true;
                            this.layer = null;
                            this.objetoSeleccionado = null;
                        }
                    }
                }
            }
        }

    }
}
