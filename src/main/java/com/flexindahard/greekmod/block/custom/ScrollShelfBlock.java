package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.templates.GenericModBlock;
import com.flexindahard.greekmod.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ScrollShelfBlock extends GenericModBlock {

    public static final BooleanProperty SCROLL = BooleanProperty.create("scroll");

    public ScrollShelfBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(defaultBlockState().setValue(SCROLL, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(SCROLL);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND)
        {
            if (!pState.getValue(SCROLL) && pPlayer.getMainHandItem().getItem() == ModBlocks.SCROLL_STACK.get().asItem()) {
                pLevel.playSound(null, pPos, SoundEvents.BOOK_PUT, SoundSource.BLOCKS);
                pLevel.setBlock(pPos, pState.cycle(SCROLL), 3);
                return InteractionResult.SUCCESS;
            }
            else if (pState.getValue(SCROLL) && pPlayer.getMainHandItem().isEmpty())
            {
                pLevel.playSound(null, pPos, SoundEvents.CHISELED_BOOKSHELF_PICKUP, SoundSource.BLOCKS);
                pLevel.setBlock(pPos, pState.cycle(SCROLL), 3);
                if (!pPlayer.isCreative())
                    pPlayer.addItem(ModBlocks.SCROLL_STACK.get().asItem().getDefaultInstance());
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }
}
