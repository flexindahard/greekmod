package com.flexindahard.greekmod.block;

import com.flexindahard.greekmod.registries.ModBlockEntities;
import com.flexindahard.greekmod.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends GenericModBlock implements EntityBlock {

    public static final VoxelShape ALTAR = Block.box(1, 0, 1, 15, 5, 15);

    public AltarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntities.ALTAR_BLOCK_ENTITY.get().create(pPos, pState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALTAR;
    }


    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
       if (!level.isClientSide && hand == InteractionHand.MAIN_HAND)
       {
           Direction direction = player.getDirection();
           ServerLevel serverLevelAccessor = level.getServer().overworld();
           if (level.getBlockState(pos.relative(direction, 1).above()).getBlock() == ModBlocks.AFINA.get())
           {
               level.playSound(null, pos, SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS);
               serverLevelAccessor.sendParticles(ParticleTypes.CLOUD,
                       pos.getX() + 0.5f, pos.getY()+1, pos.getZ() + 0.5f, 30, 0f, 0.1f, 0.1f, 1);
               player.getInventory().add(new ItemStack(Items.GOLD_BLOCK));
               return InteractionResult.CONSUME;
           }
           if (level.getBlockState(pos.relative(direction).above()).getBlock() == ModBlocks.POSEIDON.get())
           {
               serverLevelAccessor.sendParticles(ParticleTypes.ENCHANTED_HIT,
                       pos.getX() + 0.5f, pos.getY()+1, pos.getZ() + 0.5f, 50, 0.1f, 0.1f, 0, 1);
               level.playSound(null, pos, SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS);
               player.getInventory().add(new ItemStack(Items.DIAMOND_BLOCK, 1));
               return InteractionResult.CONSUME;
           }
           return InteractionResult.CONSUME;
       }
        return InteractionResult.CONSUME;
    }
}
