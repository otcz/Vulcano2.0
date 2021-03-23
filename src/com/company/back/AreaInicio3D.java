package com.company.back;

import com.luciad.model.TLcdModelDescriptor;
import com.luciad.model.TLcdVectorModel;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.util.TLcdNoBoundsException;
import com.luciad.util.TLcdOutOfBoundsException;
import com.luciad.view.ILcdLayer;
import com.luciad.view.lightspeed.ILspView;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.TLspLayer;
import com.luciad.view.lightspeed.util.TLspViewNavigationUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreaInicio3D implements ActionListener {
    public ILcdLayer layers;
    public ILspView vista3D;

    public AreaInicio3D(ILcdLayer layers, ILspView vista3D) {
        this.layers = layers;
        this.vista3D = vista3D;

    }

    public void areaInicio(ILcdLayer layers, ILspView vista) {
        if (layers == null) {
            if (vista instanceof ILspView) {
                TLspViewNavigationUtil navigationUtil = new TLspViewNavigationUtil(vista);
                ILcdLayer layer = new TLspLayer(modeloAreaInicio());
                try {
                    navigationUtil.fit((ILspLayer) layer);
                } catch (TLcdNoBoundsException e) {
                } catch (TLcdOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }

        } else {
            TLspViewNavigationUtil navigationUtil = new TLspViewNavigationUtil(vista);
            try {
                navigationUtil.fit((ILspLayer) layers);
            } catch (TLcdNoBoundsException e) {
                e.printStackTrace();
            } catch (TLcdOutOfBoundsException e) {
                e.printStackTrace();
            }
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
        areaInicio(getLayers(), getVista3D());
    }

    public ILcdLayer getLayers() {
        return layers;
    }

    public void setLayers(ILcdLayer layers) {
        this.layers = layers;
    }

    public ILspView getVista3D() {
        return vista3D;
    }

    public void setVista3D(ILspView vista3D) {
        this.vista3D = vista3D;
    }
}
