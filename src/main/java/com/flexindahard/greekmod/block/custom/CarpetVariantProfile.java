package com.flexindahard.greekmod.block.custom;

import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public record CarpetVariantProfile (
        Supplier<? extends Block> floorVariant,
        Supplier<? extends Block> wallVariant,
        CarpetEnumProperty straightVertical,
        CarpetEnumProperty straightHorizontal
        ) {
}
