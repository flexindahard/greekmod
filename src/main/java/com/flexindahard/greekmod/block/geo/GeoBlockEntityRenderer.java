package com.flexindahard.greekmod.block.geo;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GeoBlockEntityRenderer extends GeoBlockRenderer<GenericStaticalGeoBlockEntity> {
    // Рендерер, который принимает все модели из класса StaticalGeoModel
    public GeoBlockEntityRenderer(EntityRendererProvider.Context context) {
        super(new StaticalGeoModel());
    }
}
