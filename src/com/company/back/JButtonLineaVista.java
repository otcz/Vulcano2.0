package com.company.back;

import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.symbology.app6a.model.ILcdAPP6ACoded;
import com.luciad.symbology.app6a.model.TLcdEditableAPP6AObject;
import com.luciad.symbology.milstd2525b.model.ILcdMS2525bCoded;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.util.ILcdSelectionListener;
import com.luciad.util.TLcdSelectionChangedEvent;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspAWTView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import static com.company.front.ToolBar.getVista;

public class JButtonLineaVista extends JButton implements ActionListener, ILcdSelectionListener, MouseListener {
    TLcdSelectionChangedEvent objetoSeleccionado = null;
    boolean validoParaLineaVista = false;

    public JButtonLineaVista() {
        setIcon(new ImageIcon((getClass().getResource("observacionLOS.png"))));
        addActionListener(this);
        addMouseListener(this);
        eventosParaVistaActiva();
    }

    @Override
    public void selectionChanged(TLcdSelectionChangedEvent tLcdSelectionChangedEvent) {
        setObjetoSeleccionado(tLcdSelectionChangedEvent);
        if (activaButtonLineaVista(tLcdSelectionChangedEvent.getSelection().selectedObjects())) {
            validoParaLineaVista = true;
        } else {
            validoParaLineaVista = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this) && validoParaLineaVista) {
            Enumeration enumeration = getObjetoSeleccionado().getSelection().selectedObjects();
            while (enumeration.hasMoreElements()) {
                Object object = enumeration.nextElement();
                if (object instanceof TLcdEditableMS2525bObject) {
                    ILcdPoint p = ((TLcdEditableMS2525bObject) object).getFocusPoint();
                    LineaVista lineaVista = new LineaVista((ILspView) getVista(), new TLcdLonLatHeightPoint(p.getX(), p.getY(), 0), 600);
                    lineaVista.setNombreCapa(lineaVista.getNombreCapa() + " " + ((TLcdEditableMS2525bObject) object).getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation));
                    lineaVista.graficarLineaVista();
                } else if (object instanceof TLcdEditableAPP6AObject) {
                    ILcdPoint p = ((TLcdEditableAPP6AObject) object).getFocusPoint();
                    LineaVista lineaVista = new LineaVista((ILspView) getVista(), new TLcdLonLatHeightPoint(p.getX(), p.getY(), 0), 600);
                    lineaVista.setNombreCapa(lineaVista.getNombreCapa() + " " + ((TLcdEditableAPP6AObject) object).getTextModifierValue(ILcdAPP6ACoded.sUniqueDesignation));
                    lineaVista.graficarLineaVista();
                }
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(this)) {
            if (validoParaLineaVista) {
                setToolTipText("Ver l\u00ednea de vista de una unidad sobre el terreno");
            } else {
                setToolTipText("No se puede calcular l\u00ednea de vista para esta Visualizaci\u00f3n u Objeto");
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean activaButtonLineaVista(Enumeration enumeration) {
        if (enumeration.hasMoreElements()) {
            while (enumeration.hasMoreElements()) {
                Object object = enumeration.nextElement();
                if (object instanceof TLcdEditableMS2525bObject) {
                    return true;
                } else if (object instanceof TLcdEditableAPP6AObject) {
                    return true;
                } else {
                    return false;
                }

            }
        } else {
            return false;
        }
        return false;
    }

    public void eventosParaVistaActiva() {
        if (getVista() instanceof ILcdGXYView) {
            setEnabled(false);
        } else if (getVista() instanceof TLspAWTView) {
            TLspAWTView vista3D = (TLspAWTView) getVista();
            vista3D.addLayerSelectionListener(this);
        }
    }

    public TLcdSelectionChangedEvent getObjetoSeleccionado() {
        return objetoSeleccionado;
    }

    public void setObjetoSeleccionado(TLcdSelectionChangedEvent objetoSeleccionado) {
        this.objetoSeleccionado = objetoSeleccionado;
    }


}
