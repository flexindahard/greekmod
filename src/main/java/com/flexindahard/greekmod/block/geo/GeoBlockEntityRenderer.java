package com.flexindahard.greekmod.block.geo;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class GeoBlockEntityRenderer extends GeoBlockRenderer<GenericStaticalGeoBlockEntity> {

    // Рендерер, который принимает все модели из класса StaticalGeoModel
    public GeoBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super (new StaticalGeoModel());
        // this.withScale();
        // addRenderLayer(new AutoGlowingGeoLayer(this));
    }
}
