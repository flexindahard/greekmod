package com.flexindahard.greekmod.block.geo;

import com.flexindahard.greekmod.Greekmod;
import com.flexindahard.greekmod.blockentity.SimpleStatueBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.model.GeoModel;

public class StaticalGeoModel extends GeoModel<SimpleStatueBlockEntity> {

    //В зависимости от названия зарегистрированного блока подгружаются соответствующие расположения файлов моделей и текстур.
    private String getPath(SimpleStatueBlockEntity animatable) {
        return ForgeRegistries.BLOCKS.getKey(animatable.getBlockState().getBlock()).getPath();
    }

    @Override
    public ResourceLocation getModelResource(SimpleStatueBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Greekmod.MODID, ("geo/" + getPath(animatable) + ".geo.json"));
    }

    @Override
    public ResourceLocation getTextureResource(SimpleStatueBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Greekmod.MODID, ("textures/block/" +getPath(animatable) + ".png"));
    }

    @Override
    public ResourceLocation getAnimationResource(SimpleStatueBlockEntity animatable) {
        return null;
    }
}
