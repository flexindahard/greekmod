package com.flexindahard.greekmod.registries;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ModArmorMaterial {
    public static final BronzeArmorMaterial BRONZE = new BronzeArmorMaterial(
            new int[]{200, 350, 300, 200},
            new int[]{2, 5, 4, 2},
            5,
            SoundEvents.ARMOR_EQUIP_CHAIN,
            () -> Ingredient.of(Items.COPPER_INGOT.asItem()),
            "bronze",
            0f,
            0.1f);
}
