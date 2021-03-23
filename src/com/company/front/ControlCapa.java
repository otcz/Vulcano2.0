package com.company.front;

import com.company.back.*;
import com.luciad.lucy.gui.TLcyAlwaysFitJToolBar;
import com.luciad.lucy.map.ILcyGenericMapManagerListener;
import com.luciad.lucy.map.TLcyGenericMapManagerEvent;
import com.luciad.view.ILcdLayer;
import com.luciad.view.ILcdView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;

public class ControlCapa {
    private TLcyAlwaysFitJToolBar alwaysFitJToolBar = null;

    public ControlCapa() {
        getiLcyLucyEnv().getMapManager().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (alwaysFitJToolBar == null) {
                    alwaysFitJToolBar = (TLcyAlwaysFitJToolBar) getiLcyLucyEnv().getMapManager().getActiveMapComponent().getLayerControl().getActionBar().getComponent();
                    alwaysFitJToolBar.add(new JButtonImportarExcel());
                }
            }
        });
        getiLcyLucyEnv().getCombinedMapManager().addMapManagerListener(new ILcyGenericMapManagerListener<ILcdView, ILcdLayer>() {
            @Override
            public void mapManagerChanged(TLcyGenericMapManagerEvent<? extends ILcdView, ? extends ILcdLayer> tLcyGenericMapManagerEvent) {
                if (alwaysFitJToolBar != null) {
                    alwaysFitJToolBar = (TLcyAlwaysFitJToolBar) tLcyGenericMapManagerEvent.getMapComponent().getLayerControl().getActionBar().getComponent();
                    alwaysFitJToolBar.add(new JButtonImportarExcel());
                }
            }
        });

    }


}
