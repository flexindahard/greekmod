package com.flexindahard.greekmod.blockentity;

import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AltarBlockEntity extends BlockEntity {

    public AltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ALTAR_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

}
