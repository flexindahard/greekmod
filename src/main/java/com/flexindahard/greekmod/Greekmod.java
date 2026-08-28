package com.flexindahard.greekmod;


import com.flexindahard.greekmod.client.renderer.AltarRenderer;
import com.flexindahard.greekmod.client.renderer.GeoBlockEntityRenderer;
import com.flexindahard.greekmod.client.renderer.GrayStatueRenderer;
import com.flexindahard.greekmod.item.HermesBootsItem;
import com.flexindahard.greekmod.network.PacketHandler;
import com.flexindahard.greekmod.network.SHermesJumpPacket;
import com.flexindahard.greekmod.registries.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Greekmod.MODID)
public class Greekmod {

    public static final String MODID = "greekmod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public Greekmod() {
        // Меняет поведение логгера.
        Configurator.setLevel(
                "net.minecraft.client.renderer.texture.TextureAtlas",
                Level.WARN
        );

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so tabs get registered
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTab.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }

        // GEO RENDER SUBSCRIBE
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.GENERIC_STATICAL_BLOCK_ENTITY.get(),
                    GeoBlockEntityRenderer::new);

            event.registerBlockEntityRenderer(ModBlockEntities.GRAY_STATUE_BLOCK_ENTITY.get(),
                    GrayStatueRenderer::new);

            event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_BLOCK_ENTITY.get(),
                    AltarRenderer::new);
        }

        // Регистрируем кнопки мода.
        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event){
            event.register(ModKeybindings.INSTANCE.hermesJumpKey);
            event.register(ModKeybindings.INSTANCE.noobKey);
        }
    }

        @Mod.EventBusSubscriber(modid = Greekmod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
        public class ClientForgeHandler{
            @SubscribeEvent
            public static void clientTick(TickEvent.ClientTickEvent event){
                Minecraft minecraft = Minecraft.getInstance();
                if (ModKeybindings.INSTANCE.noobKey.consumeClick()) {
                    minecraft.player.displayClientMessage(Component.literal("Нуб... Боже бот.."), false);
                    minecraft.player.playSound(SoundEvents.PIG_AMBIENT, 0.7f, 1f);
                }

                if(ModKeybindings.INSTANCE.hermesJumpKey.consumeClick()) {
                    PacketHandler.sendToServer(new SHermesJumpPacket());
                }
            }
        }

        @Mod.EventBusSubscriber(modid = Greekmod.MODID)
        public class HermesJumpEvent {
        @SubscribeEvent
            public static void onJump(LivingEvent.LivingJumpEvent event) {
            if (event.getEntity().level().isClientSide) return; // работаем только с серверной, авторитетной стороной
            if (!(event.getEntity() instanceof ServerPlayer player)) return;
            if (!player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.HERMES_BOOTS.get())) return;
            HermesBootsItem.hermesJump(event.getEntity().level(), player);
        }
        }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PacketHandler.register();
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
