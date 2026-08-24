package com.flexindahard.greekmod.registries;

import com.flexindahard.greekmod.block.*;
import com.flexindahard.greekmod.block.geo.*;
import com.flexindahard.greekmod.block.geo.statues.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.flexindahard.greekmod.Greekmod.MODID;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
            ModItems.ITEMS.register(name, () -> {
                Rarity rarity = block.get() instanceof GenericStatueBlock ? Rarity.EPIC : Rarity.COMMON;
                   return new BlockItem(block.get(), new Item.Properties().rarity(rarity));
            });
    }

    // Статичные блоки высотой 2, которые используют Geo модельки

    public static final RegistryObject<Block> ZEUS = registerBlock("zeus",
            () -> new ZeusStatue(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> AFRODITA = registerBlock("afrodita",
            () -> new GenericStatueBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> POSEIDON = registerBlock("poseidon",
            () -> new GenericStatueBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> ARTEMIDA = registerBlock("artemida",
            () -> new ArtemidaStatue(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> AFINA = registerBlock("afina",
            () -> new GenericStatueBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> NIKA = registerBlock("nika",
            () -> new GenericStatueBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    // Переделать Кариатиду - она 3 блока в высоту
    public static final RegistryObject<Block> KARIATIDA = registerBlock("kariatida",
            () -> new KariatidaStatue(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> STELLA = registerBlock("stella",
            () -> new GenericStatueBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 5)
            ));
    public static final RegistryObject<Block> APOLLON = registerBlock("apollon",
            () -> new ApollonStatue(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> GERA = registerBlock("gera",
            () -> new GenericStatueBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> KIFARA = registerBlock("kifara",
            () -> new KifaraBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> DIONYS = registerBlock("dionys",
            () -> new GenericStatueBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> ASKLEPIY = registerBlock("asklepiy",
            () -> new GenericStatueBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> AID = registerBlock("aid",
            () -> new AidStatue(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BLACK)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> DEMETRA = registerBlock("demetra",
            () -> new GenericStatueBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> HERMES = registerBlock("hermes",
            () -> new HermesStatue(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));
    public static final RegistryObject<Block> GEFEST = registerBlock("gefest",
            () -> new GefestStatue(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
                    .lightLevel(value -> 8)
            ));

    public static final RegistryObject<Block> PIFOS = registerBlock("pifos",
            () -> new PifosBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> POT_TABLE = registerBlock("pot_table",
            () -> new ClayTableBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<Block> GRAY_STATUE = registerBlock("gray_statue",
            () -> new GrayStatue(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));

    // Обычные блоки с ванильным рендером.

    public static final RegistryObject<GenericModBlock> VASE = registerBlock("vase",
            () -> new SimplePotBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<GenericModBlock> AMFORA = registerBlock("amfora",
            () -> new SimplePotBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            )
    );
    public static final RegistryObject<GenericModBlock> PSICTER = registerBlock("psicter",
            () -> new SimplePotBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));
    public static final RegistryObject<GenericModBlock> KANFAR = registerBlock("kanfar",
            () -> new SimplePotBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));
    public static final RegistryObject<Block> SCROLL_STACK = registerBlock("scroll_stack",
            () -> new GenericModBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .sound(SoundType.CANDLE)
            ));
    public static final RegistryObject<Block> AMFORA_NO_PATTERN = registerBlock("amfora_no_pattern",
            () -> new SimplePotBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<Block> KILIK = registerBlock("kilik",
            () -> new SimplePotBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));

    // Кастомные блоки

    public static final RegistryObject<Block> TRIPOD = registerBlock("tripod",
            () -> new TripodBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<Block> AMFORA_SHELF = registerBlock("amfora_shelf",
            () -> new AmforaShelfBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .sound(SoundType.CHISELED_BOOKSHELF)
            ));

    public static final RegistryObject<LittlePotBlock> LITTLE_POT = registerBlock("little_pot",
            () -> new LittlePotBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));
    public static final RegistryObject<Block> CLAY_SIGN = registerBlock("clay_sign",
            () -> new ClaySignBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));
    public static final RegistryObject<Block> SCROLL_SHELF = registerBlock("scroll_shelf",
            () -> new ScrollShelfBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .sound(SoundType.WOOD)
            ));
    public static final RegistryObject<Block> BARELIEF_RED = registerBlock("barelief_red",
            () -> new VerticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));
    public static final RegistryObject<Block> BARELIEF_YELLOW = registerBlock("barelief_yellow",
            () -> new VerticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));
    public static final RegistryObject<Block> BARELIEF_KENTAVR = registerBlock("barelief_kentavr",
            () -> new VerticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));
    public static final RegistryObject<Block> BUTTON_GOLD = registerBlock("button_gold",
            () -> new VerticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.GOLD)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));
    public static final RegistryObject<Block> BUTTON_SHIELD = registerBlock("button_shield",
            () -> new VerticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.GOLD)
                    .strength(2.0f, 4f)
                    .noCollission()
            ));
    public static final RegistryObject<Block> SCROLL = registerBlock("scroll",
            () -> new ScrollBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.WOOL)
                    .strength(2.0f, 4f)
            ));

    // Ковры

    public static final  CarpetVariantProfile RED = new CarpetVariantProfile(
            () -> ModBlocks.RED_CARPET.get(),
            () -> ModBlocks.RED_CARPET_WALL.get(),
            CarpetEnumProperty.NORTH_WEST,
            CarpetEnumProperty.NORTH_EAST
    );
    public static final  CarpetVariantProfile BLUE = new CarpetVariantProfile(
            () -> ModBlocks.BLUE_CARPET.get(),
            () -> ModBlocks.BLUE_CARPET_WALL.get(),
            CarpetEnumProperty.SOUTH_WEST,
            CarpetEnumProperty.SOUTH_EAST
    );

    public static final RegistryObject<Block> RED_CARPET = registerBlock("red_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(0.0f, 1f), RED, false
            ));
    public static final RegistryObject<Block> RED_CARPET_WALL = BLOCKS.register("red_carpet_wall",
            () -> new CarpetBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(0.0f, 1f), RED, true
            ));
    public static final RegistryObject<Block> BLUE_CARPET = registerBlock("blue_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(0.0f, 1f), BLUE, false
            ));
    public static final RegistryObject<Block> BLUE_CARPET_WALL = BLOCKS.register("blue_carpet_wall",
            () -> new CarpetBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(0.0f, 1f), BLUE, true
            ));

    public static final RegistryObject<Block> LIRA = registerBlock("lira",
            () -> new VerticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.WOOL)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<Block> ALTAR = registerBlock("altar",
            () -> new AltarBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.SAND)
                    .strength(2.0f, 4f)
                    .sound(SoundType.STONE)
            ));



    public static void register(IEventBus iEventBus) {
        BLOCKS.register(iEventBus);
    }
}



