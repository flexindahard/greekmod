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
import static com.flexindahard.greekmod.registries.ModItems.*;

public class ModCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> greekmod_creative_tab =
            CREATIVE_MODE_TABS.register("greekmod_creative_tab", () -> CreativeModeTab.builder().title(Component.translatable("creativetab.greekmod.greekmod_creative_tab"))
                    .withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> VASE.get().asItem().getDefaultInstance())
                    .displayItems((parameters, output) -> {
        // Статуи
        output.accept(ZEUS.get());
        output.accept(AFRODITA.get());
        output.accept(POSEIDON.get());
        output.accept(ARTEMIDA.get());
        output.accept(AFINA.get());
        output.accept(AFINA_NIKA.get());
        output.accept(KARIATIDA.get());
        output.accept(DIONYS.get());
        output.accept(APOLLON.get());
        output.accept(GERA.get());
        output.accept(ASKLEPIY.get());
        output.accept(AID.get());
        output.accept(DEMETRA.get());
        output.accept(HERMES.get());
        output.accept(GEFEST.get());
        output.accept(GRAY_1.get());
        output.accept(GRAY_2.get());
        output.accept(GRAY_3.get());
        output.accept(KIFARA.get());
        output.accept(STELLA.get());
        // Горшки
        output.accept(PIFOS.get());
        output.accept(VASE.get());
        output.accept(AMFORA.get());
        output.accept(AMFORA_NO_PATTERN.get());
        output.accept(LITTLE_POT.get());
        output.accept(PSICTER.get());
        output.accept(KANFAR.get());
        output.accept(KILIK.get());
        // Предметы
        output.accept(AMFORA_SHELF.get());
        output.accept(POT_TABLE.get());
        output.accept(SCROLL_SHELF.get());
        output.accept(SCROLL_STACK.get());
        output.accept(CLAY_SIGN.get());
        output.accept(TRIPOD.get());
        output.accept(RED_CARPET.get());
        output.accept(BLUE_CARPET.get());
        output.accept(BARELIEF_RED.get());
        output.accept(BARELIEF_YELLOW.get());
        output.accept(BARELIEF_KENTAVR.get());
        output.accept(BUTTON_GOLD.get());
        output.accept(BUTTON_SHIELD.get());
        output.accept(SCROLL.get());
        // Item
        output.accept(HERMES_BOOTS.get());
            }
    ).build());

    public static void register(IEventBus iEventBus){
        CREATIVE_MODE_TABS.register(iEventBus);
    }
}
