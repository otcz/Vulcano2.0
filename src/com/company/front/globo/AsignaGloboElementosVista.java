package com.company.front.globo;

import com.luciad.gui.swing.TLcdOverlayLayout;
import com.luciad.view.ILcdView;
import com.luciad.view.lightspeed.TLspAWTView;
import com.luciad.view.lightspeed.swing.TLspBalloonManager;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;

public class AsignaGloboElementosVista {
   private ILcdView iLcdView;

    public AsignaGloboElementosVista(ILcdView iLcdView) {
        this.iLcdView = iLcdView;
    }

    public void asignarGlobo() {
        if (getiLcdView() instanceof TLspAWTView) {
            TLspAWTView vista = (TLspAWTView) getiLcdView();
            TLspBalloonManager fBalloonManager = new TLspBalloonManager(vista, vista.getOverlayComponent(), TLcdOverlayLayout.Location.NO_LAYOUT, new mensaje());
            Seleccion listener = new Seleccion(vista, fBalloonManager);
            vista.addLayeredListener(listener);
            vista.addLayerSelectionListener(listener);
        }
    }


    private ILcdView getiLcdView() {
        return iLcdView;
    }

    private void setiLcdView(ILcdView iLcdView) {
        this.iLcdView = iLcdView;
    }

}
