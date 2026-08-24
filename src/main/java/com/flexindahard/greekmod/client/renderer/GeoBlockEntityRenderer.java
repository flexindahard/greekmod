package com.flexindahard.greekmod.client.renderer;

import com.flexindahard.greekmod.blockentity.SimpleStatueBlockEntity;
import com.flexindahard.greekmod.block.geo.StaticalGeoModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GeoBlockEntityRenderer extends GeoBlockRenderer<SimpleStatueBlockEntity> {

    // Рендерер, который принимает все модели из класса StaticalGeoModel
    public GeoBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super (new StaticalGeoModel());
        // this.withScale();
        // addRenderLayer(new AutoGlowingGeoLayer(this));
    }

    @Override
    public int getViewDistance() {
        // Получает расстояние в чанках, умножаем на 16 блоков.
        return Minecraft.getInstance().options.renderDistance().get()*16;
    }
}
