package com.flexindahard.greekmod.block.staticalComplexModels;

import com.flexindahard.greekmod.Greekmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class StaticalGeoModel extends GeoModel {

    //В зависимости от названия зарегистрированного блока подгружаются соответствующие расположения файлов моделей и текстур.

    @Override
    public ResourceLocation getModelResource(GeoAnimatable animatable) {
        return new ResourceLocation(Greekmod.MODID, ("geo/" + getPath((GenericStaticalGeoBlockEntity) animatable) + ".geo.json"));
    }

    @Override
    public ResourceLocation getTextureResource(GeoAnimatable animatable) {
        return new ResourceLocation(Greekmod.MODID, ("textures/block/" +getPath((GenericStaticalGeoBlockEntity) animatable) + ".png"));
    }

    @Override
    public ResourceLocation getAnimationResource(GeoAnimatable animatable) {
        return null;
    }

    private String getPath(GenericStaticalGeoBlockEntity animatable) {
        return ForgeRegistries.BLOCKS.getKey(animatable.getBlockState().getBlock()).getPath();
    }

}
