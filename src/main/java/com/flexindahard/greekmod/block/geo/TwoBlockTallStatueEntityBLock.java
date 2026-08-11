package com.flexindahard.greekmod.block.geo;

import com.flexindahard.greekmod.registries.ModBlockEntities;
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
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TwoBlockTallStatueEntityBLock extends GenericStaticalEntityBlock {

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public TwoBlockTallStatueEntityBLock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(HALF, DoubleBlockHalf.LOWER)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(HALF);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return pState.getValue(HALF) == DoubleBlockHalf.LOWER
                ? ModBlockEntities.GENERIC_STATICAL_BLOCK_ENTITY.get().create(pPos, pState)
                : null;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        pLevel.setBlockAndUpdate(pPos.above(), pState.setValue(HALF, DoubleBlockHalf.UPPER));
    }

    // Сделать кастомные шейпы для статуй
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return super.getShape(pState, pLevel, pPos, pContext);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return pState.getValue(HALF) == DoubleBlockHalf.LOWER
                ? RenderShape.ENTITYBLOCK_ANIMATED
                : RenderShape.INVISIBLE;
    }

    // Если случайно уничтожена одна часть(из-за взрыва или setBlock), вторая тоже убирается.
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf half = state.getValue(HALF);
        boolean isCorrectDirection = (half == DoubleBlockHalf.LOWER) == (direction == Direction.UP);
        if (direction.getAxis() == Direction.Axis.Y && isCorrectDirection) {
            return (neighborState.is(this) && neighborState.getValue(HALF) != half)
                    ? state
                    : Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    // Если игрок ломает одну часть, проверяем логически по направлению какую он сломал, какая осталась, и убираем оставшуюся.
    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && player.isCreative()) {
            DoubleBlockHalf half = state.getValue(HALF);
            BlockPos otherPos = half == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
            BlockState otherState = level.getBlockState(otherPos);
            if (otherState.is(this) && otherState.getValue(HALF) != half) {
                level.setBlock(otherPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL);
                level.levelEvent(player, 2001, otherPos, Block.getId(otherState));
            }
        }
        super.playerWillDestroy(level, pos, state, player);
    }
}
