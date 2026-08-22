package com.flexindahard.greekmod.block.geo.statues;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ApollonStatue extends GenericStatueBlock {
    public ApollonStatue(Properties pProperties) {
        super(pProperties);
    }
    public static final VoxelShape APOLLON_LOWER = Shapes.join(Block.box(3, 4, 3, 13, 16, 13), Block.box(0, 0, 0, 16, 4, 16), BooleanOp.OR);
    public static final VoxelShape APOLLON_UPPER = Block.box(3, 0, 3, 15, 16, 13.5);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(HALF)) {
            case LOWER -> {
                return APOLLON_LOWER;
            }
            case UPPER -> {
                return APOLLON_UPPER;
            }
        }
       return Shapes.block();
    }
}
