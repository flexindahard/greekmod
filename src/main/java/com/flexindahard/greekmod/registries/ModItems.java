package com.flexindahard.greekmod.registries;

import com.flexindahard.greekmod.item.HermesBootsItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.flexindahard.greekmod.Greekmod.MODID;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final  RegistryObject<Item> HERMES_BOOTS = ITEMS.register("hermes_boots",
            () -> new HermesBootsItem(ArmorMaterials.GOLD, ArmorItem.Type.BOOTS, new Item.Properties().durability(100)));


    public static void register(IEventBus iEventBus){
        ITEMS.register(iEventBus);
    }
}
