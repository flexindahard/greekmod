package com.flexindahard.greekmod.block.geo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PifosEntityEntityBlock extends GenericStaticalEntityBlock {

    public static final VoxelShape PIFOS_SHAPE = Shapes.or(
            Shapes.or(
                    Block.box(3, 0, 3, 13, 2, 13),
                    Block.box(2, 2, 2, 14, 4, 14),
                    Block.box(0, 4, 0, 16, 7, 16),
                    Block.box(15, 30, -2, 18, 32, 18),
                    Block.box(1, 30, -2, 15, 32, 0),
                    Block.box(1, 30, 0, 15, 32, 1),
                    Block.box(-2, 30, -2, 1, 32, 15),
                    Block.box(-2, 30, 15, 15, 32, 18),
                    Block.box(-2, 28, 14, 14, 30, 18),
                    Block.box(14, 28, -2, 18, 30, 18),
                    Block.box(2, 28, -2, 14, 30, 2),
                    Block.box(-2, 28, -2, 2, 30, 14),
                    Block.box(2, 30, 0, 14, 31, 2),
                    Block.box(0, 30, 0, 2, 31, 13),
                    Block.box(0, 30, 14, 16, 31, 16),
                    Block.box(0, 30, 13, 2, 31, 14),
                    Block.box(14, 30, 0, 16, 31, 13),
                    Block.box(14, 30, 13, 16, 31, 14),
                    Block.box(-2, 6, -2, 18, 10, 18),
                    Block.box(-3, 10, -3, 19, 14, 5),
                    Block.box(-3, 24, -3, 19, 28, 0),
                    Block.box(-3, 24, 0, 0, 28, 19),
                    Block.box(0, 24, 16, 19, 28, 19),
                    Block.box(16, 24, 0, 19, 28, 16),
                    Block.box(-5, 14, -5, 16, 24, -1),
                    Block.box(-5, 14, -1, 0, 24, 8),
                    Block.box(-3, 10, 5, 19, 14, 19),
                    Block.box(16, 14, -5, 21, 24, 8),
                    Block.box(-5, 14, 8, 0, 24, 21),
                    Block.box(0, 14, 16, 16, 24, 21),
                    Block.box(16, 14, 8, 21, 24, 21)
            ));

    public PifosEntityEntityBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return PIFOS_SHAPE;
    }
}
