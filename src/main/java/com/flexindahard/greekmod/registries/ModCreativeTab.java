package com.flexindahard.greekmod.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.flexindahard.greekmod.Greekmod.MODID;
import static com.flexindahard.greekmod.registries.ModBlocks.*;

public class ModCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> greekmod_creative_tab =
            CREATIVE_MODE_TABS.register("greekmod_creative_tab", () -> CreativeModeTab.builder().title(Component.translatable("creativetab.greekmod.greekmod_creative_tab"))
                    .withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> VASE.get().asItem().getDefaultInstance())
                    .displayItems((parameters, output) -> {
        output.accept(ZEUS.get());
        output.accept(AFRODITA.get());
        output.accept(POSEIDON.get());
        output.accept(ARTEMIDA.get());
        output.accept(AFINA.get());
        output.accept(AFINA_NIKA.get());
        output.accept(KARIATIDA.get());
                        output.accept(GRAY_1.get());
                        output.accept(GRAY_2.get());
                        output.accept(GRAY_3.get());
        output.accept(LITTLE_POT.get());
                        output.accept(VASE.get());
                        output.accept(AMFORA.get());
                        output.accept(AMFORA_SHELF.get());
                        output.accept(PSICTER.get());
                        output.accept(KANFAR.get());
        output.accept(POT_TABLE.get());
        output.accept(RED_CARPET.get());
        output.accept(BLUE_CARPET.get());
        output.accept(CLAY_SIGN.get());
        output.accept(SCROLL_STACK.get());
        output.accept(SCROLL_SHELF.get());
        output.accept(STELLA.get());
            }
    ).build());

    public static void register(IEventBus iEventBus){
        CREATIVE_MODE_TABS.register(iEventBus);
    }
}
