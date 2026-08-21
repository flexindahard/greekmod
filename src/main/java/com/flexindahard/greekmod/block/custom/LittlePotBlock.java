package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.SimplePotBlock;
import com.flexindahard.greekmod.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LittlePotBlock extends SimplePotBlock {

    public static final VoxelShape LITTLE_POT = Block.box(0, 0, 0, 16, 7, 16);

    public LittlePotBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(POTS, 1).setValue(FACING, Direction.NORTH));
    }

    public static final IntegerProperty POTS;

    static {
        POTS = IntegerProperty.create("pots", 1, 4);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(POTS);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return LITTLE_POT;
    }

    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pPlayer.isCreative())
        {
            pLevel.playSound(null, pPos, SoundEvents.DECORATED_POT_BREAK, SoundSource.BLOCKS, 1f ,1f);
            popResourceFromFace(pLevel, pPos, pState.getValue(FACING), new ItemStack(ModBlocks.LITTLE_POT.get(), pState.getValue(POTS)));
        }
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int pots = pState.getValue(POTS);
        if (!pLevel.isClientSide) {
            if (!pPlayer.isCreative() && pots>1 && pots<4 && !(pPlayer.getMainHandItem() == ModBlocks.LITTLE_POT.get().asItem().getDefaultInstance()))
            {
                popResourceFromFace(pLevel, pPos, pPlayer.getDirection().getOpposite(), ModBlocks.LITTLE_POT.get().asItem().getDefaultInstance());
                pLevel.setBlock(pPos, pState.setValue(POTS, pots - 1), 3);
                return InteractionResult.SUCCESS;
            }
            if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is((ModBlocks.LITTLE_POT.get().asItem())) && pots < 4)
            {
                pLevel.setBlock(pPos, pState.cycle(POTS), 3);
                pLevel.playSound(null, pPos, SoundEvents.DECORATED_POT_PLACE, SoundSource.BLOCKS, 1f ,1f);
                return InteractionResult.SUCCESS;
            }
            if (!pPlayer.getMainHandItem().is((ModBlocks.LITTLE_POT.get().asItem())) && pots >1)
            {
                pLevel.setBlock(pPos, pState.setValue(POTS, pots - 1), 3);
                pLevel.playSound(null, pPos, SoundEvents.DECORATED_POT_SHATTER, SoundSource.BLOCKS, 1f ,1f);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }

}






