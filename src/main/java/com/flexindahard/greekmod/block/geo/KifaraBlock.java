package com.flexindahard.greekmod.block.geo;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class KifaraBlock extends TwoBlockTallStatueEntityBLock{

    public static final VoxelShape KIFARA_NORTH = Block.box(0, 0, 5, 16, 16, 11);
    public static final VoxelShape KIFARA_EAST = Block.box(5, 0, 0, 11, 16, 16);

    public KifaraBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(pState.getValue(FACING) == Direction.NORTH || pState.getValue(FACING) == Direction.SOUTH)
        {
            return KIFARA_NORTH;
        }
            else return KIFARA_EAST;
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return SoundType.WOOD;
    }
}
