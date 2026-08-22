package com.flexindahard.greekmod.block.geo.statues;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class KariatidaStatue extends GenericStatueBlock {
    public KariatidaStatue(Properties pProperties) {
        super(pProperties);
    }

    public static final VoxelShape KARIATIDA_NORTH = Block.box(2, 0, 4, 14, 16, 14);
    public static final VoxelShape KARIATIDA_SOUTH = Block.box(2, 0, 2, 14, 16, 12);
    public static final VoxelShape KARIATIDA_EAST = Block.box(2, 0, 2, 14, 16, 12);
    public static final VoxelShape KARIATIDA_WEST = Block.box(2, 0, 2, 12, 16, 14);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
       switch (pState.getValue(FACING)) {
           case NORTH -> {
               return KARIATIDA_NORTH;
           }
           case EAST -> {
               return KARIATIDA_EAST;
           }
           case SOUTH -> {
               return KARIATIDA_SOUTH;
           }
           case WEST -> {
               return KARIATIDA_WEST;
           }
       }
       return Shapes.block();
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
            pLevel.setBlockAndUpdate(pPos.above(), pState.setValue(HALF, DoubleBlockHalf.UPPER));
            pLevel.setBlockAndUpdate(pPos.above(2), pState.setValue(HALF, DoubleBlockHalf.UPPER));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf half = state.getValue(HALF);
        boolean isCorrectDirection = (half == DoubleBlockHalf.LOWER) == (direction == Direction.UP);
        if (direction.getAxis() == Direction.Axis.Y && isCorrectDirection) {
            return (neighborState.is(this) && neighborState.getValue(HALF) == half) ? state :
                    (neighborState.is(this) && neighborState.getValue(HALF) != half) ? state : Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        // По-умолчанию считаем, что сломали средний блок.
        BlockPos secondPos = pos.above();
        BlockPos thirdPos = pos.below();

        if (!level.isClientSide && player.isCreative()) {
            DoubleBlockHalf half = state.getValue(HALF);
            BlockState secondState = level.getBlockState(secondPos);
            BlockState thirdState = level.getBlockState(thirdPos);
            // Если сломали самый нижний, то убираем два сверху.
            if (half == DoubleBlockHalf.LOWER)
            {
                 secondPos = pos.above();
                 thirdPos = pos.above(2);
            }
            // Если сломали самый верхний, то ломаем два снизу.
            if (half == DoubleBlockHalf.UPPER && level.getBlockState(pos.below()).getValue(HALF) == DoubleBlockHalf.UPPER)
            {
                    secondPos = pos.below();
                    thirdPos = pos.below(2);
            }
                level.setBlock(secondPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL);
                level.setBlock(thirdPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL);
                level.levelEvent(player, 2001, secondPos, Block.getId(secondState));
                level.levelEvent(player, 2001, thirdPos, Block.getId(thirdState));
        }
        super.playerWillDestroy(level, pos, state, player);
    }
}
