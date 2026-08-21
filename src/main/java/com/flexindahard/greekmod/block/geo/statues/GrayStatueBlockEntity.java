package com.flexindahard.greekmod.block.geo.statues;

import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GrayStatueBlockEntity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int counter;
    RandomSource random = RandomSource.create();

    public GrayStatueBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRAY_STATUE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.counter = nbt.getInt("counter");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("counter", counter);
    }

    public int getCounter(){
        return this.counter;
    }

    public void incrementCounter(){
        if (this.counter<7)
            ++this.counter;
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
    }

    public void decrementCounter(){
        if (this.counter>0)
            --this.counter;
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    public void setCounter(){
            this.counter = random.nextInt(8);
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
    }

    // Синхронизация при обновлении блока.
    @Override
    public CompoundTag getUpdateTag() {
       return saveWithoutMetadata();
    }

    // Синхронизация при обновлении блока.
    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
