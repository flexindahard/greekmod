package com.flexindahard.greekmod.blockentity;

import com.flexindahard.greekmod.Greekmod;
import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class AltarBlockEntity extends BlockEntity {

    // Спросить почему поле ticks реально влияет на тики, хотя даже в BlockEntity классе родителе нет ничего про тики.
    private int ticks;
    private Random randomSource = new Random(128);
    private long activatedTick = -1;
    public static final int smokeTime = 3*20;

    public void activate (){
        this.activatedTick = level.getGameTime();
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
    }

    public boolean isActive(){
        return activatedTick != -1
                && level.getGameTime() - activatedTick < smokeTime;
    }

    // Анонимный класс, который говорит сущности сохраняться после изменений в инвентаре.
    private final ItemStackHandler inventory = new ItemStackHandler(1)
    {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            AltarBlockEntity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);

    // Сообщаем форджу, что может наш блок.
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return this.optional.cast();
        }
        return super.getCapability(cap);
    }



    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.optional.invalidate();
    }

    public AltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ALTAR_BLOCK_ENTITY.get(), pPos, pBlockState);
    }


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        CompoundTag greekModData = nbt.getCompound(Greekmod.MODID);
        this.inventory.deserializeNBT(greekModData.getCompound("Inventory"));
        this.activatedTick = greekModData.getLong("activatedTick");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        // var greekModData = new CompoundTag();
        CompoundTag greekModData = nbt.getCompound(Greekmod.MODID);
        greekModData.put("Inventory", this.inventory.serializeNBT());
        greekModData.putLong("activatedTick", activatedTick);
        nbt.put(Greekmod.MODID, greekModData);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = new CompoundTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundTag nbt) {
        load(nbt);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public ItemStack getSlot() {
        return inventory.getStackInSlot(0);
    }

    public void setSlot(ItemStack itemStack) {
        this.inventory.setStackInSlot(0, itemStack);
    }

    public void tick(BlockPos pos, AltarBlockEntity blockEntity){
        if (this.level == null) return;
        if (blockEntity.isActive())
        {
            if (this.ticks++ % 3 == 0)
            {
                float xSpeed = randomSource.nextFloat(-0.01f, 0.02f );
                float ySpeed = randomSource.nextFloat(0.05f, 0.11f );
                float zSpeed = randomSource.nextFloat(-0.01f, 0.02f );

                float xOffset = randomSource.nextFloat(-0.2f, 0.21f);
                float yOffset = randomSource.nextFloat(-0.1f, 0.21f);
                float zOffset = randomSource.nextFloat(-0.2f, 0.21f);

                level.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, true,
                        pos.getX() + 0.5 + xOffset, pos.getY() + 0.2 + yOffset, pos.getZ() + 0.5 + zOffset, xSpeed, ySpeed, zSpeed);

                    level.addParticle(ParticleTypes.FLAME, true,
                            pos.getX() + 0.5 + xOffset, pos.getY() + 0.2 + yOffset, pos.getZ() + 0.5 + zOffset, xSpeed, ySpeed, zSpeed);
            }
        }
    }
}
