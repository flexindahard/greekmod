package com.flexindahard.greekmod.item;

import com.flexindahard.greekmod.Greekmod;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class HermesBootsItem extends ArmorItem {

    public HermesBootsItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    public static void hermesJump(LevelAccessor level, Player player) {
        Vec3 motion = player.getDeltaMovement();
        player.setDeltaMovement(motion.x, motion.y + 0.3, motion.z);
        player.hurtMarked = true; // форсирует отправку скорости клиенту
                Greekmod.LOGGER.info("hermesJump void activated");
    }
}

