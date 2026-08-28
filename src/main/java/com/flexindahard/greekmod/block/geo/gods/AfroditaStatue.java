package com.flexindahard.greekmod.block.geo.gods;

import com.flexindahard.greekmod.block.templates.GodStatueBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AfroditaStatue extends GodStatueBlock {

    public static final ItemStack REWARD = Items.GOLDEN_APPLE.getDefaultInstance();

    public AfroditaStatue(Properties pProperties) {
        super(pProperties);
        this.altarReward = REWARD;
    }
}
