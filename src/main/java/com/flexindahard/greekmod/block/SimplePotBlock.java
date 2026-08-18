package com.flexindahard.greekmod.block;

import com.flexindahard.greekmod.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SimplePotBlock extends GenericModBlock{
    public SimplePotBlock(Properties pProperties) {
        super(pProperties);
    }

    public static final VoxelShape VASE = Block.box(2, 0, 1, 14, 17, 15);
    public static final VoxelShape PSICTER = Block.box(4, 0, 4, 12, 11.5, 12);
    public static final VoxelShape KILIK = Block.box(1, 0, 3, 15, 6.5, 13);
    public static final VoxelShape KANFAR = Block.box(3, 0, 5, 13, 12.5, 11);
    public static final VoxelShape AMFORA = Block.box(4, 0, 3.5, 12, 24, 12.5);
    public static final VoxelShape AMFORA_NO_PATTERN = Block.box(-3, 0, 4, 18, 8, 12);


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getBlock() == ModBlocks.VASE.get())
            return VASE;
        if (pState.getBlock() == ModBlocks.PSICTER.get())
            return PSICTER;
        if (pState.getBlock() == ModBlocks.KILIK.get())
            return KILIK;
        if (pState.getBlock() == ModBlocks.KANFAR.get())
            return KANFAR;
        if (pState.getBlock() == ModBlocks.AMFORA.get())
            return AMFORA;
        if (pState.getBlock() == ModBlocks.AMFORA_NO_PATTERN.get())
            return AMFORA_NO_PATTERN;
        return Shapes.block();
    }
}
