package com.flexindahard.greekmod.block;

import com.flexindahard.greekmod.Greekmod;
import com.flexindahard.greekmod.blockentity.AltarBlockEntity;
import com.flexindahard.greekmod.registries.ModBlockEntities;
import com.flexindahard.greekmod.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
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

    // Сделать столб дыма.
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND && level.getBlockEntity(pos) instanceof AltarBlockEntity be) {
            ItemStackHandler inventory = be.getInventory();
            ItemStack handStack = player.getMainHandItem();
            Direction direction = player.getDirection();
            ServerLevel serverLevelAccessor = level.getServer().overworld();
            if (handStack.getItem() == Items.FLINT_AND_STEEL && !inventory.getStackInSlot(0).isEmpty()) {
                if (level.getBlockState(pos.relative(direction, 1).above()).getBlock() == ModBlocks.AFINA.get()) {
                    level.playSound(null, pos, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS);
                    serverLevelAccessor.sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                            pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, 0, 0f, 0.2f, 0f, 0.5f);
                    inventory.setStackInSlot(0, ItemStack.EMPTY);
                    level.addFreshEntity(new ItemEntity(level, pos.getX()+0.5, pos.getY()+2, pos.getZ()+0.5, Items.GOLD_INGOT.getDefaultInstance()));
                    level.sendBlockUpdated(pos, state, state, 3);
                    return InteractionResult.SUCCESS;
                }
                if (level.getBlockState(pos.relative(direction).above()).getBlock() == ModBlocks.POSEIDON.get()) {
                        serverLevelAccessor.sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                                pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, 0, 0f, 0.2f, 0f, 0.5f);
                    level.playSound(null, pos, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS);
                    inventory.setStackInSlot(0, ItemStack.EMPTY);
                    level.addFreshEntity(new ItemEntity(level, pos.getX()+0.5, pos.getY()+2, pos.getZ()+0.5, Items.DIAMOND.getDefaultInstance()));
                    level.sendBlockUpdated(pos, state, state, 3);
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
            }

            if (handStack.isEmpty()) {
                if (inventory.getStackInSlot(0).isEmpty()) {
                    // И слот и рука пустые
                    return InteractionResult.CONSUME;
                } else {
                    ItemStack extracted = inventory.extractItem(0, inventory.getSlotLimit(0), false);
                    // Вынимаем из алтаря
                    player.setItemInHand(InteractionHand.MAIN_HAND, extracted);
                    level.sendBlockUpdated(pos, state, state, 3);
                    return InteractionResult.SUCCESS;
                }
            } else {
                if (inventory.getStackInSlot(0).isEmpty()) {
                    // Кладём в алтарь
                    ItemStack toInsert = handStack.copy();
                    inventory.insertItem(0, toInsert, false);
                    level.sendBlockUpdated(pos, state, state, 3);
                    player.getMainHandItem().setCount(0);
                    return InteractionResult.SUCCESS;
                } else {
                    // Инвентарь полон
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.CONSUME;
    }

}
