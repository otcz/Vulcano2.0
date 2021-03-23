package com.company.back;

import com.luciad.model.TLcdModelDescriptor;
import com.luciad.model.TLcdVectorModel;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.view.gxy.ILcdGXYLayer;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.gxy.TLcdGXYLayer;
import com.luciad.view.gxy.TLcdGXYViewFitAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreaInicio2D implements ActionListener {
    public ILcdGXYLayer layers;
    public ILcdGXYView vista2D;

    public AreaInicio2D(ILcdGXYLayer layers, ILcdGXYView vista2D) {
        this.layers = layers;
        this.vista2D=vista2D;
    }

    public void areaInicio(ILcdGXYLayer layers, ILcdGXYView vista) {
        if (layers == null) {
            if (vista instanceof ILcdGXYView) {
                TLcdGXYViewFitAction navegacio = new TLcdGXYViewFitAction(vista);
                TLcdGXYLayer layerGYX = new TLcdGXYLayer(modeloAreaInicio(), "");
                navegacio.fitGXYLayer(layerGYX, vista);
            }

        } else {
            TLcdGXYViewFitAction navegacio = new TLcdGXYViewFitAction(vista);
            navegacio.fitGXYLayer(layers, vista);
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
        areaInicio(getLayers(), getVista2D());
    }

    public ILcdGXYLayer getLayers() {
        return layers;
    }

    public void setLayers(ILcdGXYLayer layers) {
        this.layers = layers;
    }

    public ILcdGXYView getVista2D() {
        return vista2D;
    }

    public void setVista2D(ILcdGXYView vista2D) {
        this.vista2D = vista2D;
    }

}
