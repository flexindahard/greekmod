package com.flexindahard.greekmod.block.geo.gods;

import com.flexindahard.greekmod.block.templates.GodStatueBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HermesStatue extends GodStatueBlock {

    public static final ItemStack REWARD = Items.GOLDEN_BOOTS.getDefaultInstance();

    public HermesStatue(Properties pProperties) {
        super(pProperties);
        this.altarReward = REWARD;
    }

    public static final VoxelShape HERMES_NORTH = Block.box(2.5, 0, 2, 13.5, 16, 9.5);
    public static final VoxelShape HERMES_SOUTH = Block.box(2.5, 0, 6.5, 13.5, 16, 14);
    public static final VoxelShape HERMES_EAST = Block.box(6.5, 0, 2.5, 14, 16, 13.5);
    public static final VoxelShape HERMES_WEST = Block.box(2, 0, 2.5, 9.5, 16, 13.5);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
       switch (pState.getValue(FACING)) {
           case NORTH -> {
               return HERMES_NORTH;
           }
           case EAST -> {
               return HERMES_EAST;
           }
           case SOUTH -> {
               return HERMES_SOUTH;
           }
           case WEST -> {
               return HERMES_WEST;
           }
       }
       return Shapes.block();
    }
}
