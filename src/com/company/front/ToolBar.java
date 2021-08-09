package com.company.front;

import com.company.back.*;
import com.company.front.globo.AsignaGloboElementosVista;
import com.luciad.lucy.map.ILcyGenericMapManagerListener;
import com.luciad.lucy.map.TLcyCombinedMapManager;
import com.luciad.lucy.map.TLcyGenericMapManagerEvent;
import com.luciad.view.ILcdLayer;
import com.luciad.view.ILcdView;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.TLspAWTView;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;


public class ToolBar {
    public JToolBar toolBar;
    public static ILcdView vista;
    public static boolean bCerroVista, bAbrioVista;

    public ToolBar() {
        getiLcyLucyEnv().getCombinedMapManager().addMapManagerListener(new ILcyGenericMapManagerListener<ILcdView, ILcdLayer>() {
            @Override
            public void mapManagerChanged(TLcyGenericMapManagerEvent<? extends ILcdView, ? extends ILcdLayer> tLcyGenericMapManagerEvent) {
                if (tLcyGenericMapManagerEvent.getId() == TLcyGenericMapManagerEvent.MAP_COMPONENT_ADDED) {
                    setVista(tLcyGenericMapManagerEvent.getMapComponent().getMainView());
                    toolBar = (JToolBar) tLcyGenericMapManagerEvent.getMapComponent().getToolBar().getComponent();
                    toolBar.add(new JButtonAreaInicio());
                    toolBar.add(new JButtonAddCartografia());
                    toolBar.add(new JTextCoordenadas());
                    toolBar.add(new JButtonCDT());
                    toolBar.add(new JButtonLineaVista());
                    if (getVista() instanceof ILcdGXYView) {
                        ILcdGXYView vista2D = (ILcdGXYView) getVista();
                        vista2D.addModelListener(new AgregaNombreSimologiaPorDefecto());
                    } else if (getVista() instanceof TLspAWTView) {
                        TLspAWTView vista3D = (TLspAWTView) getVista();
                        vista3D.addLayerModelListener(new AgregaNombreSimologiaPorDefecto());
                    }
                }


            }
        });

        getiLcyLucyEnv().getCombinedMapManager().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    setVista(((TLcyCombinedMapManager) evt.getSource()).getActiveMapComponent().getMainView());
                    if (evt.getNewValue() == null) {
                        bCerroVista = true;
                        bAbrioVista = false;
                    } else if (evt.getNewValue() != null) {
                        bAbrioVista = true;
                        bCerroVista = false;
                      //  AsignaGloboElementosVista asignaGloboElementosVista = new AsignaGloboElementosVista(getVista());
                      //  asignaGloboElementosVista.asignarGlobo();
                    }

                } catch (Exception e) {

                }

            }
        });
    }


    public static ILcdView getVista() {
        return vista;
    }

    public static void setVista(ILcdView vista) {
        ToolBar.vista = vista;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public static boolean isbCerroVista() {
        return bCerroVista;
    }

    public static void setbCerroVista(boolean bCerroVista) {
        ToolBar.bCerroVista = bCerroVista;
    }

    public static boolean isbAbrioVista() {
        return bAbrioVista;
    }

    public static void setbAbrioVista(boolean bAbrioVista) {
        ToolBar.bAbrioVista = bAbrioVista;
    }
}
