package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.GenericModBlock;
import com.flexindahard.greekmod.registries.ModBlocks;
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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Supplier;

public class CarpetBlock extends GenericModBlock {

   public static final EnumProperty<CarpetEnumProperty> NEIGHBOURS = EnumProperty.create("neighbours", CarpetEnumProperty.class);
   public static final BooleanProperty IS_CORNER = BooleanProperty.create("is_corner");
   private final CarpetVariantProfile profile;
   private final boolean isWallVariant;

    // Возможные расположения соседних ковров:     {↑ → ↓ ←}
    public static final int[] northEast = new int[]{1,1,0,0};
    // Можно представить в двоичной системе, потом с помощью switch описать все возможные состояния и легко олучать их.
    public static final int[] northWest = new int[]{1,0,0,1};   // 9
    public static final int[] southEast = new int[]{0,1,1,0};   // 6
    public static final int[] southWest = new int[]{0,0,1,1};   // 3
    public static final int[] upDown = new int[]{1,0,1,0};      // 10
    public static final int[] leftRight = new int[]{0,1,0,1};   // 5
    public static final int[] north = new int[]{1,0,0,0};   // 8
    public static final int[] east = new int[]{0,1,0,0};    // 4
    public static final int[] south = new int[]{0,0,1,0};   // 2
    public static final int[] west = new int[]{0,0,0,1};    // 1

    public CarpetBlock(Properties pProperties, CarpetVariantProfile profile, boolean isWallVariant) {
        super(pProperties);
        this.profile = profile;
        this.isWallVariant = isWallVariant;

        this.registerDefaultState(defaultBlockState()
                .setValue(IS_CORNER, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_EAST));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(IS_CORNER);
        pBuilder.add(NEIGHBOURS);
    }

    public static int[] getNeighboursPos(LevelAccessor level, BlockState blockState, BlockPos pos) {
        int[] neighbour = new int[4];
        Block block = blockState.getBlock();
        Direction direction = blockState.getValue(FACING);
        if (block == ModBlocks.RED_CARPET.get() || block == ModBlocks.BLUE_CARPET.get()) {
            if (level.getBlockState(pos.north()).getBlock() instanceof CarpetBlock)
                neighbour[0] = 1; //   N  ↑
            if (level.getBlockState(pos.east()).getBlock() instanceof CarpetBlock)
                neighbour[1] = 1;  //   E  →
            if (level.getBlockState(pos.south()).getBlock() instanceof CarpetBlock)
                neighbour[2] = 1; //   S  ↓
            if (level.getBlockState(pos.west()).getBlock() instanceof CarpetBlock)
                neighbour[3] = 1; //    W  ←
            return neighbour;
        }
        else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            if (level.getBlockState(pos.above()).getBlock() instanceof CarpetBlock)
                neighbour[0] = 1;
            if (level.getBlockState(pos.east()).getBlock() instanceof CarpetBlock)
                neighbour[1] = 1;
            if (level.getBlockState(pos.below()).getBlock() instanceof CarpetBlock)
                neighbour[2] = 1;
            if (level.getBlockState(pos.west()).getBlock() instanceof CarpetBlock)
                neighbour[3] = 1;
            return neighbour;
        }
        else if(direction == Direction.WEST || direction == Direction.EAST) {
            if (level.getBlockState(pos.above()).getBlock() instanceof CarpetBlock)
                neighbour[0] = 1;
            if (level.getBlockState(pos.north()).getBlock() instanceof CarpetBlock)
                neighbour[1] = 1;
            if (level.getBlockState(pos.below()).getBlock() instanceof CarpetBlock)
                neighbour[2] = 1;
            if (level.getBlockState(pos.south()).getBlock() instanceof CarpetBlock)
                neighbour[3] = 1;
            return neighbour;
        }
            return null;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getBlock() == ModBlocks.RED_CARPET.get() || pState.getBlock() == ModBlocks.BLUE_CARPET.get())
            return Block.box(0,0,0,16,1,16);
        else
            switch (pState.getValue(FACING)) {
                case NORTH-> {
                    return Block.box(0,0,15,16,16,16);
                }
                case SOUTH -> {
                    return Block.box(0,0,0,16,16,1);
                }
                case WEST -> {
                    return Block.box(15,0,0,16,16,16);
                }
                case EAST -> {
                    return Block.box(0,0,0,1,16,16);
                }
            }
        return Shapes.block();
    }

    // Настенный ковёр может иметь соседей в горизонтальной плоскости только с двух сторон, в зависимости от его начального FACING.
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        LevelAccessor level = pContext.getLevel();
        BlockState wallDefinedState = defaultBlockState(); // Из-за этого сразу не ставятся угловые!!!
        Direction direction = pContext.getClickedFace();
        Block block = this.asBlock();
            if (direction == Direction.UP) { // Кликнули на верхнюю сторону блока
                if (block == ModBlocks.RED_CARPET.get())
               wallDefinedState = ModBlocks.RED_CARPET.get().defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
                else
                    wallDefinedState = ModBlocks.BLUE_CARPET.get().defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
            }
                else {
                    if (block == ModBlocks.RED_CARPET.get())
                        wallDefinedState = ModBlocks.RED_CARPET_WALL.get().defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
                    else
                        wallDefinedState = ModBlocks.BLUE_CARPET_WALL.get().defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
                    }
        int[] neighbours = getNeighboursPos(level, wallDefinedState, pContext.getClickedPos());
            // Учитываем все комбинации углов
            if(Arrays.equals(neighbours, northEast)) return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_EAST).setValue(IS_CORNER, true);
            if(Arrays.equals(neighbours, northWest)) return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_WEST).setValue(IS_CORNER, true);
            if(Arrays.equals(neighbours, southEast)) return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_EAST).setValue(IS_CORNER, true);
            if(Arrays.equals(neighbours, southWest)) return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_WEST).setValue(IS_CORNER, true);
            if (wallDefinedState.getBlock() == ModBlocks.RED_CARPET_WALL.get()) {
                if (Arrays.equals(neighbours, upDown))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_WEST).setValue(IS_CORNER, false);
                if (Arrays.equals(neighbours, leftRight))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_EAST).setValue(IS_CORNER, false);
            }
            else if (wallDefinedState.getBlock() == ModBlocks.BLUE_CARPET_WALL.get()) {
                if (Arrays.equals(neighbours, upDown))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_WEST).setValue(IS_CORNER, false);
                if (Arrays.equals(neighbours, leftRight))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_EAST).setValue(IS_CORNER, false);
            }
            // Два условия специально для настенных прямых ковров
            // (Чтобы они по-дефолту ставились горизонтально, а когда начинаешь ставить их ещё выше, продолжая стену, они ставились вертикально)
            if (block == ModBlocks.RED_CARPET_WALL.get()) {
                if (Arrays.equals(neighbours, north) || Arrays.equals(neighbours, south))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_WEST);
                if (Arrays.equals(neighbours, east) || Arrays.equals(neighbours, west))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_EAST);
            } else if (block == ModBlocks.BLUE_CARPET_WALL.get())  {
            if (Arrays.equals(neighbours, north) || Arrays.equals(neighbours, south))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_WEST);
            if (Arrays.equals(neighbours, east) || Arrays.equals(neighbours, west))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_EAST);
            }
            // Два условия специально для напольных прямых ковров
            // (Чтобы крутить их правильно и только через North East South West в blockstates.json)
            else if (block == ModBlocks.RED_CARPET.get()) {
                if (Arrays.equals(neighbours, north) || Arrays.equals(neighbours, south))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_WEST);
                if (Arrays.equals(neighbours, east) || Arrays.equals(neighbours, west))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_EAST);
            } else if (block == ModBlocks.BLUE_CARPET.get()) {
                if (Arrays.equals(neighbours, north) || Arrays.equals(neighbours, south))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_WEST);
                if (Arrays.equals(neighbours, east) || Arrays.equals(neighbours, west))
                    return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_EAST);
            }
        return wallDefinedState;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor level, BlockPos pos, BlockPos pFacingPos) {
        int[] neighbours = getNeighboursPos(level, pState, pos);
        BlockState wallDefinedState = pState;
        Block block = pState.getBlock();
        // Учитываем все комбинации углов
        if(Arrays.equals(neighbours, northEast)) return pState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_EAST).setValue(IS_CORNER, true);
        if(Arrays.equals(neighbours, northWest)) return pState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_WEST).setValue(IS_CORNER, true);
        if(Arrays.equals(neighbours, southEast)) return pState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_EAST).setValue(IS_CORNER, true);
        if(Arrays.equals(neighbours, southWest)) return pState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_WEST).setValue(IS_CORNER, true);
        // Убираем угловой ковёр если он уже не нужен
        if(Arrays.equals(neighbours, north) || Arrays.equals(neighbours, south) ||
                Arrays.equals(neighbours, east) || Arrays.equals(neighbours, west) ||
                Arrays.equals(neighbours, upDown) || Arrays.equals(neighbours, leftRight))
            return pState.setValue(IS_CORNER, false);
        if (wallDefinedState.getBlock() == ModBlocks.RED_CARPET_WALL.get()) {
            if (Arrays.equals(neighbours, upDown))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_WEST).setValue(IS_CORNER, false);
            if (Arrays.equals(neighbours, leftRight))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_EAST).setValue(IS_CORNER, false);
        }

        // Синий настенный требует всех 4 состояний соседств, у меня пока только 2, Поэтому пока невозможно пофиксить текстуры.
        else if (wallDefinedState.getBlock() == ModBlocks.BLUE_CARPET_WALL.get()) {
            if (Arrays.equals(neighbours, upDown))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_WEST).setValue(IS_CORNER, false);
            if (Arrays.equals(neighbours, leftRight))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_EAST).setValue(IS_CORNER, false);
        }
        // Два условия специально для настенных прямых ковров
        // (Чтобы они по-дефолту ставились горизонтально, а когда начинаешь ставить их ещё выше, продолжая стену, они ставились вертикально)
        if (block == ModBlocks.RED_CARPET_WALL.get()) {
            if (Arrays.equals(neighbours, north) || Arrays.equals(neighbours, south))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_WEST);
            if (Arrays.equals(neighbours, east) || Arrays.equals(neighbours, west))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_EAST);
        }
        else if  (block == ModBlocks.BLUE_CARPET_WALL.get()) {
            if (Arrays.equals(neighbours, north) || Arrays.equals(neighbours, south))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_WEST);
            if (Arrays.equals(neighbours, east) || Arrays.equals(neighbours, west))
                return wallDefinedState.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_EAST);
        }
        return pState;
    }
}

