package com.flexindahard.greekmod.block.geo.statues;

import com.flexindahard.greekmod.block.geo.TwoBlockTallStatueEntityBLock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
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

public class AidStatue extends TwoBlockTallStatueEntityBLock {
    public AidStatue(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
       return Shapes.block();
    }

    // Проверяем есть ли место для установки блока.
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        // Указываем в какую сторону ставятся доп блоки.
        Direction direction = context.getHorizontalDirection().getOpposite().getClockWise();
        BlockState state_1 = level.getBlockState(pos.above());
        BlockState state_2 = level.getBlockState(pos.relative(direction));
        BlockState state_3 = level.getBlockState(pos.above().relative(direction));
        if (state_1.canBeReplaced(context) && state_2.canBeReplaced(context) && state_3.canBeReplaced(context)
        ) {
            return this.defaultBlockState()
                    .setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        return null;
    }

    // Устанавливаем Dummy блоки.
    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
            Direction direction = pState.getValue(FACING).getClockWise();
            pLevel.setBlockAndUpdate(pPos.above(), pState.setValue(HALF, DoubleBlockHalf.UPPER));
            pLevel.setBlockAndUpdate(pPos.relative(direction), pState.setValue(HALF, DoubleBlockHalf.UPPER));
            pLevel.setBlockAndUpdate(pPos.above().relative(direction), pState.setValue(HALF, DoubleBlockHalf.UPPER));
    }

    // TODO: дописать метод чтобы убирать блоки после взрывов или поршней.
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return state;
    }

    // Убираем все остальные части статуи, когда игрок разрушает хотя бы один из блоков статуи.
    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && player.isCreative()) {
        Direction direction = state.getValue(FACING);
        DoubleBlockHalf half = state.getValue(HALF);
        // По-умолчанию считаем, что сломали основной блок.
        BlockPos secondPos = pos.above();
        BlockPos thirdPos = pos.relative(direction.getClockWise());
        BlockPos fourthPos = pos.relative(direction.getClockWise()).above();

            BlockState secondState = level.getBlockState(secondPos);
            BlockState thirdState = level.getBlockState(thirdPos);
            BlockState fourthState = level.getBlockState(fourthPos);

            // Если сломали верхний
            if (half == DoubleBlockHalf.UPPER )
            {
                // Если снизу есть блоки:
                if (level.getBlockState(pos.below()).getBlock() instanceof AidStatue) {
                    // Если снизу фейк блок, то есть ещё два справа.
                    if (level.getBlockState(pos.below()).getValue(HALF) == DoubleBlockHalf.UPPER)
                    {
                        secondPos = pos.below();
                        thirdPos = pos.relative(direction.getCounterClockWise());
                        fourthPos = thirdPos.below();
                    }
                    // Если снизу основной блок, то есть ещё два слева.
                    else
                    {
                        secondPos = pos.below();
                        thirdPos = pos.relative(direction.getClockWise());
                        fourthPos = thirdPos.below();
                    }
                }
                // Если снизу нет блоков - ищем сверху и это значит, что все остальные справа.
                else
                {
                    secondPos = pos.above();
                    thirdPos = pos.relative(direction.getCounterClockWise());
                    fourthPos = thirdPos.above();
                }
            }
                level.setBlock(secondPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL);
                level.setBlock(thirdPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL);
                level.setBlock(fourthPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL);
                level.levelEvent(player, 2001, secondPos, Block.getId(secondState));
                level.levelEvent(player, 2001, thirdPos, Block.getId(thirdState));
                level.levelEvent(player, 2001, fourthPos, Block.getId(fourthState));
        }
        super.playerWillDestroy(level, pos, state, player);
    }
}
