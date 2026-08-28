package com.flexindahard.greekmod.block.geo.common;

import com.flexindahard.greekmod.block.templates.DoubleEntityBlock;
import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jetbrains.annotations.Nullable;

public class GrayStatue extends DoubleEntityBlock {

    public GrayStatue(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return pState.getValue(HALF) == DoubleBlockHalf.LOWER ? ModBlockEntities.GRAY_STATUE_BLOCK_ENTITY.get().create(pPos, pState) : null;
    }

//    @Override
//    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
//        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
//        if (!pLevel.isClientSide && pLevel.getBlockEntity(pPos) instanceof GrayStatueBlockEntity be) {
//            be.setCounter();
//            be.setChanged();
//            pLevel.sendBlockUpdated(pPos, pState, pState, 3);
//        }
//    }

//    @Override
//    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
//        if (pLevel.getBlockEntity(pPos) instanceof GrayStatueBlockEntity blockEntity && !pLevel.isClientSide)
//        {
//            blockEntity.setCounter();
//        }
//        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
//    }
}
