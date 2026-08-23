package com.flexindahard.greekmod;


import com.flexindahard.greekmod.client.renderer.AltarRenderer;
import com.flexindahard.greekmod.client.renderer.GeoBlockEntityRenderer;
import com.flexindahard.greekmod.client.renderer.GrayStatueRenderer;
import com.flexindahard.greekmod.registries.ModBlockEntities;
import com.flexindahard.greekmod.registries.ModBlocks;
import com.flexindahard.greekmod.registries.ModCreativeTab;
import com.flexindahard.greekmod.registries.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Greekmod.MODID)
public class Greekmod {

    public static final String MODID = "greekmod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public Greekmod() {
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
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
