package com.flexindahard.greekmod.registries;

import com.flexindahard.greekmod.item.HermesBootsItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.flexindahard.greekmod.Greekmod.MODID;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final  RegistryObject<Item> HERMES_BOOTS = ITEMS.register("hermes_boots",
            () -> new HermesBootsItem(ArmorMaterials.GOLD, ArmorItem.Type.BOOTS, new Item.Properties().durability(100)));

    public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register("bronze_sword",
            () -> new SwordItem(ModTiers.BRONZE, 2, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> BRONZE_SHOVEL = ITEMS.register("bronze_shovel",
            () ->  new ShovelItem(ModTiers.BRONZE, 1, -3.0F, new Item.Properties()));

    public static final RegistryObject<Item> BRONZE_PICKAXE = ITEMS.register("bronze_pickaxe",
            () ->  new PickaxeItem(ModTiers.BRONZE, 1, -2.8F, new Item.Properties()));

    public static final RegistryObject<Item> BRONZE_AXE = ITEMS.register("bronze_axe",
            () ->  new AxeItem(ModTiers.BRONZE, 5.0F, -3.1F, new Item.Properties()));

    public static final RegistryObject<ArmorItem> BRONZE_HELMET = ITEMS.register("bronze_helmet",
            () ->  new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> BRONZE_CHESTPLATE = ITEMS.register("bronze_chestplate",
            () ->  new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> BRONZE_LEGGINGS = ITEMS.register("bronze_leggings",
            () ->  new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> BRONZE_BOOTS = ITEMS.register("bronze_boots",
            () ->  new ArmorItem(ModArmorMaterial.BRONZE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static void register(IEventBus iEventBus){
        ITEMS.register(iEventBus);
    }
}
