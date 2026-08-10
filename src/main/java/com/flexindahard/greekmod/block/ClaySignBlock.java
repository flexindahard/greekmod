package com.flexindahard.greekmod.block;

import com.flexindahard.greekmod.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ClaySignBlock extends GenericModBlock {
    public ClaySignBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(defaultBlockState().setValue(SIGN, 1).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(SIGN);
    }

    public static final IntegerProperty SIGN;

    static {
        SIGN = IntegerProperty.create("sign", 1,2);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int sign = pState.getValue(SIGN);
        if (!pLevel.isClientSide) {
            if (pPlayer.getMainHandItem().is((ModBlocks.CLAY_SIGN.get().asItem())))
            {
                if (sign == 1)
                {
                    pLevel.setBlock(pPos, pState.setValue(SIGN, 2), 3);
                    return InteractionResult.SUCCESS;
                }
            }
            else if (sign == 2)
            {
                pLevel.setBlock(pPos, pState.setValue(SIGN, 1), 3);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }
}
