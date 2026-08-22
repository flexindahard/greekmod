package com.flexindahard.greekmod.client.renderer;

import com.flexindahard.greekmod.blockentity.GrayStatueBlockEntity;
import com.flexindahard.greekmod.block.geo.statues.GrayStatueModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GrayStatueRenderer extends GeoBlockRenderer<GrayStatueBlockEntity> {
    public GrayStatueRenderer(BlockEntityRendererProvider.Context context) {
        super(new GrayStatueModel());
    }
}
