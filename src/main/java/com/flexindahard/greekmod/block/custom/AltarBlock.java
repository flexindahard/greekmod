package com.flexindahard.greekmod.block.custom;

import com.flexindahard.greekmod.block.templates.GenericModBlock;
import com.flexindahard.greekmod.block.templates.GodStatueBlock;
import com.flexindahard.greekmod.blockentity.AltarBlockEntity;
import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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

//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockEntityType<T> pBlockEntityType) {
//        return this.getBlock() instanceof EntityBlock ? ((EntityBlock)this.getBlock()).getTicker(pLevel, this.asState(), pBlockEntityType) : null;
//    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return !level.isClientSide() ? null :(level1, pos1, state1, blockEntity) ->
                ((AltarBlockEntity)blockEntity).tick(pos1, (AltarBlockEntity) blockEntity);
    }

//    serverLevelAccessor.sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
//            pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, 0, 0f, 0.2f, 0f, 0.5f);

    // Сделать столб дыма.
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND && level.getBlockEntity(pos) instanceof AltarBlockEntity altar) {
            ItemStackHandler altarInventory = altar.getInventory();
            ItemStack handStack = player.getMainHandItem();
            Direction direction = player.getDirection();
//            ServerLevel serverLevelAccessor = level.getServer().overworld();
            // Если в руках огниво и на алтаре есть жертва
            if (handStack.getItem() == Items.FLINT_AND_STEEL && !altarInventory.getStackInSlot(0).isEmpty()) {
                // Проверяем есть ли статуя для поклонения
                if (level.getBlockState(pos.relative(direction, 1).above()).getBlock() instanceof GodStatueBlock god) {
                    // Звук.
                    level.playSound(null, pos, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS);
                    // Частицы
                    // Убираем жертву, даём награду
                    altarInventory.setStackInSlot(0, ItemStack.EMPTY);
                    altar.activate();
                    altar.setChanged();
                    level.sendBlockUpdated(pos, state, state, 3);
                    ItemEntity reward =  god.getAltarReward(level, pos);
                    level.addFreshEntity(reward);
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
            }
            //
            // Все остальные случаи, когда в руках нет огнива
            //
            if (handStack.isEmpty()) {
                if (altarInventory.getStackInSlot(0).isEmpty()) {
                    // И слот и рука пустые
                    return InteractionResult.CONSUME;
                } else {
                    ItemStack extracted = altarInventory.extractItem(0, altarInventory.getSlotLimit(0), false);
                    // Вынимаем из алтаря
                    player.setItemInHand(InteractionHand.MAIN_HAND, extracted);
                    level.sendBlockUpdated(pos, state, state, 3);
                    return InteractionResult.SUCCESS;
                }
            } else {
                if (altarInventory.getStackInSlot(0).isEmpty()) {
                    // Кладём в алтарь
                    ItemStack toInsert = handStack.copy();
                    altarInventory.insertItem(0, toInsert, false);
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
