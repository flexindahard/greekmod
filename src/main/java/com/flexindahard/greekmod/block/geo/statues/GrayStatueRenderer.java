package com.flexindahard.greekmod.block.geo.statues;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GrayStatueRenderer extends GeoBlockRenderer<GrayStatueBlockEntity> {
    public GrayStatueRenderer(BlockEntityRendererProvider.Context context) {
        super(new GrayStatueModel());
    }
}
