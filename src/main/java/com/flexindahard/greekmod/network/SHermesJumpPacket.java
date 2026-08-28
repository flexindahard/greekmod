package com.flexindahard.greekmod.network;

import com.flexindahard.greekmod.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SHermesJumpPacket {
    //private final BlockPos position;

    public SHermesJumpPacket(BlockPos pos){
        //this.position = pos;
    }

    public SHermesJumpPacket(FriendlyByteBuf buffer) {
       // this(buffer.readBlockPos());
    }

    public SHermesJumpPacket() {}

    public void encode(FriendlyByteBuf buffer) {
       // buffer.writeBlockPos(this.position);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(
                () -> {
                    ServerPlayer player = context.getSender();
                    if (player != null) {
                        if (player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HERMES_BOOTS.get().getDefaultInstance().getItem())
                        {
                            player.setJumping(false);
                        }
                    }
                });
        context.setPacketHandled(true);
    }
}
