package com.flexindahard.greekmod.block.geo.statues;

import com.flexindahard.greekmod.block.geo.TwoBlockTallStatueEntityBLock;
import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class GrayStatue extends TwoBlockTallStatueEntityBLock {

    public GrayStatue(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return pState.getValue(HALF) == DoubleBlockHalf.LOWER ? ModBlockEntities.GRAY_STATUE_BLOCK_ENTITY.get().create(pPos, pState) : null;
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        if (pLevel.getBlockEntity(pPos) instanceof GrayStatueBlockEntity blockEntity)
        {
            blockEntity.setCounter();
        }
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            if (pLevel.getBlockEntity(pPos) instanceof GrayStatueBlockEntity blockEntity) {
                if (!pPlayer.isSteppingCarefully())
                {
                    blockEntity.incrementCounter();
                    pPlayer.sendSystemMessage(Component.literal("Counter increased and now = " + blockEntity.getCounter()));
                }
                else
                {
                    blockEntity.decrementCounter();
                    pPlayer.sendSystemMessage(Component.literal("Counter decresed and now = " + blockEntity.getCounter()));
                }
            }

        }
        return InteractionResult.sidedSuccess(!pLevel.isClientSide);
    }
}
