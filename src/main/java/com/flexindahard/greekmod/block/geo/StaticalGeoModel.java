package com.flexindahard.greekmod.block.geo;

import com.flexindahard.greekmod.Greekmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.model.GeoModel;

public class StaticalGeoModel extends GeoModel<GenericStaticalGeoBlockEntity> {

    //В зависимости от названия зарегистрированного блока подгружаются соответствующие расположения файлов моделей и текстур.
    private String getPath(GenericStaticalGeoBlockEntity animatable) {
        return ForgeRegistries.BLOCKS.getKey(animatable.getBlockState().getBlock()).getPath();
    }

    @Override
    public ResourceLocation getModelResource(GenericStaticalGeoBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Greekmod.MODID, ("geo/" + getPath(animatable) + ".geo.json"));
    }

    @Override
    public ResourceLocation getTextureResource(GenericStaticalGeoBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Greekmod.MODID, ("textures/block/" +getPath(animatable) + ".png"));
    }

    @Override
    public ResourceLocation getAnimationResource(GenericStaticalGeoBlockEntity animatable) {
        return null;
    }
}
