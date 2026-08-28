package com.flexindahard.greekmod.client.renderer;

import com.flexindahard.greekmod.blockentity.GrayStatueBlockEntity;
import com.flexindahard.greekmod.block.geo.GrayStatueModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GrayStatueRenderer extends GeoBlockRenderer<GrayStatueBlockEntity> {
    public GrayStatueRenderer(BlockEntityRendererProvider.Context context) {
        super(new GrayStatueModel());
    }

    @Override
    public int getViewDistance() {
        // Получает расстояние в чанках, умножаем на 16 блоков.
        return Minecraft.getInstance().options.renderDistance().get()*16;
    }
}

