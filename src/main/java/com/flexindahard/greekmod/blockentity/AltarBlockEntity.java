package com.flexindahard.greekmod.blockentity;

import com.flexindahard.greekmod.Greekmod;
import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AltarBlockEntity extends BlockEntity {

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
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        // var greekModData = new CompoundTag();
        CompoundTag greekModData = nbt.getCompound(Greekmod.MODID);
        greekModData.put("Inventory", this.inventory.serializeNBT());
        nbt.put(Greekmod.MODID, greekModData);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
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
}
