package com.flexindahard.greekmod.network;

import com.flexindahard.greekmod.Greekmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1";

    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.tryBuild(Greekmod.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
            INSTANCE.registerMessage(5,
                    SHermesJumpPacket.class,
                    SHermesJumpPacket::encode,
                    SHermesJumpPacket::new,
                    SHermesJumpPacket::handle
            );}

    public static void sendToServer(Object msg) {
        INSTANCE.send(PacketDistributor.SERVER.noArg(), msg);
    }

    public static void sendToClient(Object msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }
}
