package com.flexindahard.greekmod.registries;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier BRONZE = new ForgeTier(
            3,
            200,
            5,
            1.5f,
            10,
            ModTags.NEEDS_BRONZE_TOOL,
            () -> Ingredient.of(Items.COPPER_INGOT)
    );
}
