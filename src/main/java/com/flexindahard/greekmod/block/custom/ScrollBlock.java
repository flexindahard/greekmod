package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.GenericModBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ScrollBlock extends GenericModBlock {

    public static final BooleanProperty EXPANDED = BooleanProperty.create("expanded");

    public ScrollBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(EXPANDED);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND){
            pLevel.setBlock(pPos, pState.cycle(EXPANDED), 3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }
}
