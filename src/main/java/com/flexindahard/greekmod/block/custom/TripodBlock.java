package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.templates.GenericModBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TripodBlock extends GenericModBlock {

    public static final VoxelShape TRIPOD = Block.box(2, 0, 2, 14, 17, 14);

    public TripodBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return TRIPOD;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            double x0 = (double)pPos.getX() + 0.55D;
            double y0 = (double)pPos.getY() + 0.9D;
            double z0 = (double)pPos.getZ() + 0.5D;
            if (pRandom.nextDouble() < 0.1D) {
                pLevel.playLocalSound(x0, y0, z0, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
            double d4 = pRandom.nextDouble() * 0.6D - 0.3D;
            double d6 = pRandom.nextDouble() * 6.0D / 16.0D;
            pLevel.addParticle(ParticleTypes.SMOKE, x0, y0, z0, 0.0D, 0.01D, 0.0D);
            pLevel.addParticle(ParticleTypes.FLAME, x0, y0, z0, 0.0D, 0.01D, 0.0D);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return SoundType.COPPER;
    }
}
