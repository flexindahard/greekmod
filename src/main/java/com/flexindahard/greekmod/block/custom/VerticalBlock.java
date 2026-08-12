package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.GenericModBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock.FACE;

public class VerticalBlock extends GenericModBlock {
    public VerticalBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACE);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        for (Direction direction : pContext.getNearestLookingDirections()) {
            BlockState state;
            if (direction.getAxis() == Direction.Axis.Y) {
                state = (BlockState) ((BlockState) this.defaultBlockState().setValue(FACE, direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR)).setValue(FACING, pContext.getHorizontalDirection());
            } else {
                state = (BlockState) ((BlockState) this.defaultBlockState().setValue(FACE, AttachFace.WALL)).setValue(FACING, direction.getOpposite());
            }

            if (state.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                return state;
            }
        }
        return null;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return getConnectedDirection(pState).getOpposite() == pFacing && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    protected static Direction getConnectedDirection(BlockState pState) {
        switch ((AttachFace) pState.getValue(FACE)) {
            case CEILING -> {
                return Direction.DOWN;
            }
            case FLOOR -> {
                return Direction.UP;
            }
            default -> {
                return (Direction) pState.getValue(FACING);
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (getConnectedDirection(state)) {
            case UP -> Block.box(0,0,0,16,2,16);
            case DOWN -> Block.box(0,14,0,16,16,16);
            case NORTH-> Block.box(0,0,14,16,16,16);
            case SOUTH -> Block.box(0,0,0,16,16,2);
            case WEST -> Block.box(14,0,0,16,16,16);
            case EAST -> Block.box(0,0,0,2,16,16);
        };
    }

}


