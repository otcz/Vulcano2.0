package com.company;

import com.luciad.model.ILcdModel;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.ILspLayerFactory;

import java.util.Collection;

public class capaPrueba implements ILspLayerFactory {


    @Override
    public boolean canCreateLayers(ILcdModel iLcdModel) {
        return false;
    }

    @Override
    public Collection<ILspLayer> createLayers(ILcdModel iLcdModel) {
        return null;
    }
}
