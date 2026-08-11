package com.flexindahard.greekmod.block.geo;

import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class GenericStaticalEntityBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public GenericStaticalEntityBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
        );
    }

    //Определяем свойства.
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    // Создаём BlockEntity на месте блока.
    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntities.GENERIC_STATICAL_BLOCK_ENTITY.get().create(pPos, pState);
    }

    // Проверяем есть ли место для установки блока.
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (level.getBlockState(pos.above()).canBeReplaced(context)) {
            return this.defaultBlockState()
                    .setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        return null;
    }

    // Смена с ванильного режима отображения блока. База рендерится с помощью GekoLib, плейсхолдер скипает рендер.
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    // Если нужен кастомный шейп для конкретного блока, можно использовать условие:
    // if(this.defaultBlockState().getBlock() == ModBlocks.MY_BLOCK.get())
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.block();
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 0.9f;
    }
}
