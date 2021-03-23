package com.luciad.osgi.sample.lightspeed.fundamentals.basicapplication;

import com.luciad.model.ILcdModel;
import com.luciad.view.lightspeed.layer.ALspSingleLayerFactory;
import com.luciad.view.lightspeed.layer.ILspLayer;
import com.luciad.view.lightspeed.layer.shape.TLspShapeLayerBuilder;

/**
 * Basic ILspLayerFactory implementation that supports SHP data (vector).
 */
public class BasicLayerFactory extends ALspSingleLayerFactory {

  @Override
  public boolean canCreateLayers(ILcdModel aModel) {
    //for this sample, this layer factory can handle all models.
    return true;
  }

  @Override
  public ILspLayer createLayer(ILcdModel aModel) {
    //create a layer that paints the shapes in the model.
    return TLspShapeLayerBuilder.newBuilder()
                                .model(aModel)
                                .layerType(ILspLayer.LayerType.BACKGROUND)
                                .build();
  }
}
