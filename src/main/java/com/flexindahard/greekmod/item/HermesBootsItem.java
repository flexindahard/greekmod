package com.flexindahard.greekmod.item;

import com.mojang.logging.LogUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.slf4j.Logger;

public class HermesBootsItem extends ArmorItem {

    static final Logger LOGGER = LogUtils.getLogger();

    public HermesBootsItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    // hermesJump без проверки !level.isClientSide() + use с проверкой не работает.
    // пока что работает только без проверок вообще
    public static void hermesJump(LevelAccessor level, Player player) {
            if (!level.isClientSide()) {
                player.jumpFromGround();
                LOGGER.info("hermesJump void activated");
            }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getMainHandItem();
        if (!pLevel.isClientSide) {
            LOGGER.info("Using Hermes boots");
        }
        hermesJump(pLevel, pPlayer);
        return InteractionResultHolder.success(itemStack);
    }
}

