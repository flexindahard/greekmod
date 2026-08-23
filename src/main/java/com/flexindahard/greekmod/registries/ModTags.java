package com.flexindahard.greekmod.registries;

import com.flexindahard.greekmod.Greekmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> NEEDS_BRONZE_TOOL = tag("needs_bronze_tool");

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(ResourceLocation.tryBuild(Greekmod.MODID, name));
    }
}
