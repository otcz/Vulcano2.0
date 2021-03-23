package com.company.back;

import com.luciad.model.TLcdModelDescriptor;
import com.luciad.model.TLcdVectorModel;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.util.TLcdNoBoundsException;
import com.luciad.util.TLcdOutOfBoundsException;
import com.luciad.view.ILcdLayer;
import com.luciad.view.ILcdView;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.gxy.TLcdGXYLayer;
import com.luciad.view.gxy.TLcdGXYViewFitAction;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.TLspAWTView;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspLayer;
import com.luciad.view.lightspeed.util.TLspViewNavigationUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;

public class JButtonAreaInicio extends JButton implements ActionListener {

    public JButtonAreaInicio() {
        setIcon(new ImageIcon((getClass().getResource("mapaInicio.png"))));
        addActionListener(this);

    }

    private void areaInicio(ILcdView vista) {
        if (vista instanceof ILcdGXYView) {
            ILcdGXYView vista2D = (ILcdGXYView) vista;
            TLcdGXYViewFitAction navegacio = new TLcdGXYViewFitAction(vista2D);
            TLcdGXYLayer layerGYX = new TLcdGXYLayer(modeloAreaInicio(), "");
            navegacio.fitGXYLayer(layerGYX, vista2D);
        } else if (vista instanceof ILspView) {
            ILspView vista3D = (ILspView) vista;
            try {
                TLspViewNavigationUtil navigationUtil = new TLspViewNavigationUtil(vista3D);
                ILcdLayer layer = new TLspLayer(modeloAreaInicio());
                navigationUtil.fit((ILspLayer) layer);
            } catch (TLcdNoBoundsException e) {
            } catch (TLcdOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "No se configur\u00f3  la vista correctamente", "Error", 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se configur\u00f3  la vista correctamente", "Error", 2);
        }


    }

    private TLcdVectorModel modeloAreaInicio() {
        ILcdPoint point1 = new TLcdLonLatHeightPoint(-78.9167, 12.3333, 0);
        ILcdPoint point2 = new TLcdLonLatHeightPoint(-78.9167, -4.2167, 0);
        ILcdPoint point3 = new TLcdLonLatHeightPoint(-66.8267, -4.2167, 0);
        ILcdPoint point4 = new TLcdLonLatHeightPoint(-66.8267, 12.3333, 0);
        TLcdVectorModel vectorModel = new TLcdVectorModel(new TLcdGeodeticReference(), new TLcdModelDescriptor());
        vectorModel.addElement(point1, 0);
        vectorModel.addElement(point2, 0);
        vectorModel.addElement(point3, 0);
        vectorModel.addElement(point4, 0);
        return vectorModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            areaInicio(getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView());
        }

    }


}
