package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.GenericModBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ClayTableBlock extends GenericModBlock {
    public ClayTableBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        double d0 = (double)pPos.getX() + 0.5D;
        double d1 = (double)pPos.getY() + 1D;
        double d2 = (double)pPos.getZ() + 0.5D;
        pLevel.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, d0,d1,d2,0,0,0);
    }
}
