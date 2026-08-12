package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.GenericModBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CarpetBlock extends GenericModBlock {

   public static final EnumProperty<CarpetEnumProperty> CARPET_STATE = EnumProperty.create("carpet_state", CarpetEnumProperty.class);
    public static final BooleanProperty WALL_STATE = BooleanProperty.create("wall");
    public CarpetBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(defaultBlockState().setValue(CARPET_STATE, CarpetEnumProperty.PLAIN)
                .setValue(FACING, Direction.NORTH).setValue(WALL_STATE, false));
    }

    // Улучшить проверку есть ли соседние ковры

//    public static Boolean UpNeighbour (BlockPos pPos, LevelAccessor level){
//        if (level.getBlockState(pPos).getBlock() instanceof CarpetBlock)
//        {
//            if (level.getBlockState(pPos).getValue(WALL_STATE))
//            {
//
//            }
//        }
//        return level.getBlockState(pPos.offset(1,0,0)).getBlock() instanceof CarpetBlock;
//    }

    public static Boolean northNeighbour(BlockPos pPos, LevelAccessor level){
        return level.getBlockState(pPos.north()).getBlock() instanceof CarpetBlock;
    }
    public static Boolean southNeighbour(BlockPos pPos, LevelAccessor level){
        return level.getBlockState(pPos.south()).getBlock() instanceof CarpetBlock;
    }
    public static Boolean eastNeighbour(BlockPos pPos, LevelAccessor level){
        return level.getBlockState(pPos.east()).getBlock() instanceof CarpetBlock;
    }
    public static Boolean westNeighbour(BlockPos pPos, LevelAccessor level){
        return level.getBlockState(pPos.west()).getBlock() instanceof CarpetBlock;
    }
    public static Boolean upNeighbour(BlockPos pPos, LevelAccessor level){
        return level.getBlockState(pPos.above()).getBlock() instanceof CarpetBlock;
    }
    public static Boolean downNeighbour(BlockPos pPos, LevelAccessor level){
        return level.getBlockState(pPos.below()).getBlock() instanceof CarpetBlock;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(CARPET_STATE);
        pBuilder.add(WALL_STATE);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(0,0,0,16,0.5f,16);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction lookingDirection = pContext.getNearestLookingDirection();
        BlockState wallDefinedState = defaultBlockState().setValue(WALL_STATE, !(lookingDirection == Direction.DOWN));
            LevelAccessor level = pContext.getLevel();
            BlockPos pos = pContext.getClickedPos();
            boolean hasNorthNeighbour = northNeighbour(pos, level);
            boolean hasSouthNeighbour = southNeighbour(pos, level);
            boolean hasEastNeighbour = eastNeighbour(pos, level);
            boolean hasWestNeighbour = westNeighbour(pos, level);
            if (hasNorthNeighbour && hasSouthNeighbour && hasWestNeighbour && hasEastNeighbour)
                //Настроить направление центрального ковра.
                return wallDefinedState.setValue(CARPET_STATE, CarpetEnumProperty.MIDDLE).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
            else if (hasNorthNeighbour && hasSouthNeighbour) {
                return wallDefinedState.setValue(FACING, Direction.NORTH);
            } else if (hasEastNeighbour && hasWestNeighbour) {
                return wallDefinedState.setValue(FACING, Direction.WEST);
            } else if (hasNorthNeighbour && hasWestNeighbour) {
                return wallDefinedState.setValue(CARPET_STATE, CarpetEnumProperty.CORNER).setValue(FACING, Direction.EAST);
            } else if (hasNorthNeighbour && hasEastNeighbour) {
                return wallDefinedState.setValue(CARPET_STATE, CarpetEnumProperty.CORNER).setValue(FACING, Direction.SOUTH);
            } else if (hasSouthNeighbour && hasEastNeighbour) {
                return wallDefinedState.setValue(CARPET_STATE, CarpetEnumProperty.CORNER).setValue(FACING, Direction.WEST);
            } else if (hasSouthNeighbour && hasWestNeighbour) {
                return wallDefinedState.setValue(CARPET_STATE, CarpetEnumProperty.CORNER).setValue(FACING, Direction.NORTH);
            }
            return wallDefinedState.setValue(CARPET_STATE, CarpetEnumProperty.PLAIN).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
        }
    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor level, BlockPos pos, BlockPos pFacingPos) {
        boolean hasNorthNeighbour = northNeighbour(pos, level);
        boolean hasSouthNeighbour = southNeighbour(pos, level);
        boolean hasEastNeighbour = eastNeighbour(pos, level);
        boolean hasWestNeighbour = westNeighbour(pos, level);
        boolean hasUpNeighbour = upNeighbour(pos, level);
        boolean hasDownNeighbour = downNeighbour(pos, level);
        if (pState.getValue(WALL_STATE)){
            if ((hasUpNeighbour && hasDownNeighbour && hasWestNeighbour && hasEastNeighbour) || (hasUpNeighbour && hasDownNeighbour && hasNorthNeighbour && hasSouthNeighbour))
                return pState.setValue(CARPET_STATE, CarpetEnumProperty.MIDDLE);
            if (hasUpNeighbour && hasEastNeighbour || hasUpNeighbour && hasWestNeighbour)
                return pState.setValue(CARPET_STATE, CarpetEnumProperty.CORNER);
            if (hasUpNeighbour && hasDownNeighbour)
                return level.getBlockState(pos.below());
        }
        else {
            if (hasNorthNeighbour && hasSouthNeighbour && hasWestNeighbour && hasEastNeighbour)
                //Настроить направление центрального ковра.
                return pState.setValue(CARPET_STATE, CarpetEnumProperty.MIDDLE).setValue(FACING, Direction.NORTH);
            else if (hasNorthNeighbour && hasSouthNeighbour) {
                return pState.setValue(FACING, Direction.NORTH);
            } else if (hasEastNeighbour && hasWestNeighbour) {
                return pState.setValue(FACING, Direction.WEST);
            } else if (hasNorthNeighbour && hasWestNeighbour) {
                return pState.setValue(CARPET_STATE, CarpetEnumProperty.CORNER).setValue(FACING, Direction.EAST);
            } else if (hasNorthNeighbour && hasEastNeighbour) {
                return pState.setValue(CARPET_STATE, CarpetEnumProperty.CORNER).setValue(FACING, Direction.SOUTH);
            } else if (hasSouthNeighbour && hasEastNeighbour) {
                return pState.setValue(CARPET_STATE, CarpetEnumProperty.CORNER).setValue(FACING, Direction.WEST);
            } else if (hasSouthNeighbour && hasWestNeighbour) {
                return pState.setValue(CARPET_STATE, CarpetEnumProperty.CORNER).setValue(FACING, Direction.NORTH);
            }
        }
            return pState;
    }
}
