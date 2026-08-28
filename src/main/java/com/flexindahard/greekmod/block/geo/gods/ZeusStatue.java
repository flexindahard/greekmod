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

public class ZeusStatue extends GodStatueBlock {

    public static final ItemStack REWARD = Items.GLOWSTONE.getDefaultInstance();

    public ZeusStatue(Properties pProperties) {
        super(pProperties);
        this.altarReward = REWARD;
    }

    public static final VoxelShape ZEUS_CHAIR = Block.box(1, 0, 1, 15, 16, 15);
    public static final VoxelShape ZEUS_UPPER = Block.box(2.5, 0, 2.5, 13.5, 16, 10);
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(HALF)) {
            case LOWER -> {
                return ZEUS_CHAIR;
            }
            case UPPER -> {
                return ZEUS_UPPER ;
            }
        }
       return Shapes.block();
    }
}
