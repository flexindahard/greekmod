package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.GenericModBlock;
import com.flexindahard.greekmod.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CarpetBlock extends GenericModBlock {

    public static final EnumProperty<CarpetEnumProperty> NEIGHBOURS = EnumProperty.create("neighbours", CarpetEnumProperty.class);
    public static final BooleanProperty IS_CORNER = BooleanProperty.create("is_corner");

    private final CarpetVariantProfile profile;
    private final boolean isWallVariant;

    // Возможные расположения соседних ковров: {↑ → ↓ ←}
    // int [northWest: n + w = 9, southEast: s + e = 6, southWest: s + w = 3, upDown: n + s = 10, leftRight: e + w = 5]

        public static final int north = 8;   // 8
        public static final int east = 4;    // 4
        public static final int south = 2;   // 2
        public static final int west = 1;   // 1

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

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (!isWallVariant)
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

    public static int getNeighboursPos(LevelAccessor level, BlockState blockState, BlockPos pos) {
        int neighbour = 0;
        Block block = blockState.getBlock();
        Direction direction = blockState.getValue(FACING);
        if (block == ModBlocks.RED_CARPET.get() || block == ModBlocks.BLUE_CARPET.get())
        {
            if (level.getBlockState(pos.north()).getBlock() instanceof CarpetBlock)
                neighbour += 8; //   N  ↑
            if (level.getBlockState(pos.east()).getBlock() instanceof CarpetBlock)
                neighbour += 4;  //   E  →
            if (level.getBlockState(pos.south()).getBlock() instanceof CarpetBlock)
                neighbour += 2; //   S  ↓
            if (level.getBlockState(pos.west()).getBlock() instanceof CarpetBlock)
                neighbour += 1; //    W  ←
            return neighbour;
        }
        else if (direction == Direction.NORTH || direction == Direction.SOUTH)
        {
            if (level.getBlockState(pos.above()).getBlock() instanceof CarpetBlock)
                neighbour += 8;
            if (level.getBlockState(pos.east()).getBlock() instanceof CarpetBlock)
                neighbour += 4;
            if (level.getBlockState(pos.below()).getBlock() instanceof CarpetBlock)
                neighbour += 2;
            if (level.getBlockState(pos.west()).getBlock() instanceof CarpetBlock)
                neighbour += 1;
            return neighbour;
        }
        else if(direction == Direction.WEST || direction == Direction.EAST)
        {
            if (level.getBlockState(pos.above()).getBlock() instanceof CarpetBlock)
                neighbour += 8;
            if (level.getBlockState(pos.north()).getBlock() instanceof CarpetBlock)
                neighbour += 4;
            if (level.getBlockState(pos.below()).getBlock() instanceof CarpetBlock)
                neighbour += 2;
            if (level.getBlockState(pos.south()).getBlock() instanceof CarpetBlock)
                neighbour += 1;
            return neighbour;
        }
        return 0;
    }

    // Применяем угловое состояние для всех ковров, которые стоят в углу.
    private BlockState applyCornerState(BlockState state, int neighbourPos){
       return switch (neighbourPos)
       {
            case north | east -> state.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_EAST).setValue(IS_CORNER, true);
            case north | west -> state.setValue(NEIGHBOURS, CarpetEnumProperty.NORTH_WEST).setValue(IS_CORNER, true);
            case south | east -> state.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_EAST).setValue(IS_CORNER, true);
            case south | west -> state.setValue(NEIGHBOURS, CarpetEnumProperty.SOUTH_WEST).setValue(IS_CORNER, true);
            default -> state.setValue(IS_CORNER, false);
       };
    }

    // Для остальных ковров выставляем направление.
    private BlockState applyOrientation(BlockState state, int neighbourPos){
        if (state.getBlock() == profile.wallVariant().get())
        {
           return switch (neighbourPos)
           {
                case north | south -> state.setValue(NEIGHBOURS, profile.straightVertical());
               default -> state.setValue(NEIGHBOURS, profile.straightHorizontal());
            };
        }
        else {
            return switch (neighbourPos){
                case north | south -> state.setValue(FACING, Direction.NORTH);
                case east | west -> state.setValue(FACING, Direction.EAST);
                case north ->  state.setValue(FACING, Direction.SOUTH);
                case east -> state.setValue(FACING, Direction.WEST);
                case south -> state.setValue(FACING, Direction.NORTH);
                case west -> state.setValue(FACING, Direction.EAST);
                default -> state.setValue(FACING, Direction.NORTH);
            };
        }
    }

    // Настенный ковёр может иметь соседей в горизонтальной плоскости только с двух сторон, в зависимости от его начального FACING.
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {

        LevelAccessor level = pContext.getLevel();

        // Определяем напольный или настенный ковёр
        Block targetBlock = pContext.getClickedFace() == Direction.UP ?
                profile.floorVariant().get() : profile.wallVariant().get();
        BlockState placementState = targetBlock.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());

        // Получаем соседей
        int neighbours = getNeighboursPos(level, placementState, pContext.getClickedPos());

        // Учитываем все комбинации углов
        placementState = applyCornerState(placementState, neighbours);

        // Выставляем правильное положение
        if (placementState.getValue(IS_CORNER)) {
            return placementState;
        }
        placementState = applyOrientation(placementState, neighbours);
        return placementState;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction pFacing, BlockState pFacingState, LevelAccessor level, BlockPos pos, BlockPos pFacingPos) {
        int neighbours = getNeighboursPos(level, state, pos);
        state = applyCornerState(state, neighbours);
            if (state.getValue(IS_CORNER))
                return state;
        return applyOrientation(state, neighbours);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return SoundType.WOOL;
    }
}

