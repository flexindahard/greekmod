package com.flexindahard.greekmod.block.geo.gods;

import com.flexindahard.greekmod.block.templates.GodStatueBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DemetraStatue extends GodStatueBlock {

    public static final ItemStack REWARD = Items.HAY_BLOCK.getDefaultInstance();

    public DemetraStatue(Properties pProperties) {
        super(pProperties);
        this.altarReward = REWARD;
    }
}
