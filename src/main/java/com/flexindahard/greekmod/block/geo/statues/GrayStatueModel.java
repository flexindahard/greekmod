package com.flexindahard.greekmod.block.geo.statues;

import com.flexindahard.greekmod.Greekmod;
import com.flexindahard.greekmod.blockentity.GrayStatueBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.model.GeoModel;

public class GrayStatueModel extends GeoModel<GrayStatueBlockEntity> {

    private String getPath(GrayStatueBlockEntity animatable) {
        return ForgeRegistries.BLOCKS.getKey(animatable.getBlockState().getBlock()).getPath();
    }

    private int getCounter(GrayStatueBlockEntity animatable){
        return animatable.getCounter();
    }

    @Override
    public ResourceLocation getModelResource(GrayStatueBlockEntity animatable) {
            return ResourceLocation.fromNamespaceAndPath(Greekmod.MODID, ("geo/" + getPath(animatable)
              + "_" + getCounter(animatable)  + ".geo.json"));

    }

    @Override
    public ResourceLocation getTextureResource(GrayStatueBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Greekmod.MODID, ("textures/block/" + getPath(animatable)
                + "_" + getCounter(animatable) + ".png"));
    }

    @Override
    public ResourceLocation getAnimationResource(GrayStatueBlockEntity animatable) {
        return null;
    }
}
