package com.flexindahard.greekmod.block.geo;

import ca.weblite.objc.Client;
import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetSimulationDistancePacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GeoBlockEntityRenderer extends GeoBlockRenderer<GenericStaticalGeoBlockEntity> {
    // Рендерер, который принимает все модели из класса StaticalGeoModel
    public GeoBlockEntityRenderer(EntityRendererProvider.Context context) {
        super(new StaticalGeoModel());
    }
}
