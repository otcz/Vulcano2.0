package com.company.back;

import com.company.control.DatosTiro;
import com.company.front.DatosTirofrm;
import com.luciad.lucy.map.ILcyGenericMapManagerListener;
import com.luciad.lucy.map.TLcyGenericMapManagerEvent;
import com.luciad.view.ILcdLayer;
import com.luciad.view.ILcdView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;
import static com.company.front.ToolBar.getVista;

public class JButtonCDT extends JButton implements ActionListener, MouseListener {
    DatosTirofrm datosTirofrm = null;
    DatosTiro datosTiro = null;
    boolean validoParaCalculos = false;
    int c = 0;

    public JButtonCDT() {
        setIcon(new ImageIcon((getClass().getResource("reglaCDT.png"))));
        setToolTipText("No se puede iniciar vista para c\u00e1lculo de Comandos de Fuego \n " +
                ", Debe tener: 1.“Sistema”, 2.“PR/OBJ” y 3.“O/A”");
        addActionListener(this);
        addMouseListener(this);
        esDisponibleBotonCDT();
        getiLcyLucyEnv().getCombinedMapManager().addMapManagerListener(new ILcyGenericMapManagerListener<ILcdView, ILcdLayer>() {
            @Override
            public void mapManagerChanged(TLcyGenericMapManagerEvent<? extends ILcdView, ? extends ILcdLayer> tLcyGenericMapManagerEvent) {
                if (tLcyGenericMapManagerEvent.getId() == TLcyGenericMapManagerEvent.MAP_COMPONENT_REMOVED) {
                    if (getDatosTirofrm() != null) {
                        getDatosTirofrm().dispose();
                        datosTiro = null;
                    }
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this) & validoParaCalculos) {
            c++;
            if (c == 1) {
                datosTirofrm = new DatosTirofrm();
                datosTirofrm.lbPrintTitulo.setText(datosTirofrm.lbPrintTitulo.getText() + " - Visualizaci\u00f3n (" + getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMapName() + ")");
                datosTiro = new DatosTiro(datosTirofrm);
                datosTiro.iniciaListadoUnidadesParaCalculoDetiro();

            } else {
                datosTiro.iniciaListadoUnidadesParaCalculoDetiro();
                datosTirofrm.setVisible(true);
                datosTirofrm.toFront();
                datosTirofrm.setLocationRelativeTo(this);

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
            if (esDisponibleBotonCDT()) {
                setValidoParaCalculos(true);
                setToolTipText("Ver sugerencias para c\u00e1lculo de Comandos de Fuego");
            } else {
                setValidoParaCalculos(false);
                setToolTipText("No se puede iniciar vista para c\u00e1lculo de Comandos de Fuego \n " +
                        ", Debe tener: 1.“Sistema”, 2.“PR/OBJ” y 3.“O/A”");
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean esDisponibleBotonCDT() {
        boolean sistemaDisponible = false, observadorDisponible = false, blancoDisponible = false;
        ListadoSistemasArtilleria sistemasArtilleria = new ListadoSistemasArtilleria();
        ListadoObservadoresAdelantado observadoresAdelantados = new ListadoObservadoresAdelantado();
        ListadoBlancos blancos = new ListadoBlancos();
        sistemasArtilleria.obtieneSistemasArtilleriaDesdeVista(getVista());
        observadoresAdelantados.obtieneObservadoresAdelantadosDesdeVista(getVista());
        blancos.obtieneBlancosDesdeVista(getVista());
        for (int i = 0; i < sistemasArtilleria.getSistemas().size(); i++) {
            if (sistemasArtilleria.esSistemaArtilleriaPermitido(sistemasArtilleria.getSistemas().get(i))) {
                sistemaDisponible = true;
                break;
            } else {
                sistemaDisponible = false;
            }
        }
        for (int i = 0; i < observadoresAdelantados.getObservadores().size(); i++) {
            if (observadoresAdelantados.esObservadorAdelantadoPermitido(observadoresAdelantados.getObservadores().get(i))) {
                observadorDisponible = true;
                break;
            } else {
                observadorDisponible = false;
            }
        }
        for (int i = 0; i < blancos.getBlancos().size(); i++) {
            if (blancos.esBlancoPermitido(blancos.getBlancos().get(i))) {
                blancoDisponible = true;
                break;
            } else {
                blancoDisponible = false;
            }
        }

        if (sistemaDisponible && observadorDisponible && blancoDisponible) {
            return true;
        } else {
            return false;

        }
    }

    public DatosTirofrm getDatosTirofrm() {
        return datosTirofrm;
    }

    public void setDatosTirofrm(DatosTirofrm datosTirofrm) {
        this.datosTirofrm = datosTirofrm;
    }

    public DatosTiro getDatosTiro() {
        return datosTiro;
    }

    public void setDatosTiro(DatosTiro datosTiro) {
        this.datosTiro = datosTiro;
    }

    public boolean isValidoParaCalculos() {
        return validoParaCalculos;
    }

    public void setValidoParaCalculos(boolean validoParaCalculos) {
        this.validoParaCalculos = validoParaCalculos;
    }
}
