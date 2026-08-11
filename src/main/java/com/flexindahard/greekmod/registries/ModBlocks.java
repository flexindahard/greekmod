package com.flexindahard.greekmod.registries;

import com.flexindahard.greekmod.block.GenericModBlock;
import com.flexindahard.greekmod.block.custom.*;
import com.flexindahard.greekmod.block.geo.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.flexindahard.greekmod.Greekmod.MODID;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Статичные блоки высотой 2, которые используют Geo модельки

    public static final RegistryObject<Block> ZEUS = registerBlock("zeus",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> AFRODITA = registerBlock("afrodita",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> POSEIDON = registerBlock("poseidon",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
            ));

    public static final RegistryObject<Block> GRAY_1 = registerBlock("gray_1",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> GRAY_2 = registerBlock("gray_2",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> GRAY_3 = registerBlock("gray_3",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> ARTEMIDA = registerBlock("artemida",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> AFINA = registerBlock("afina",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> AFINA_NIKA = registerBlock("afina_nika",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    // Переделать Кариатиду - она 3 блока в высоту
    public static final RegistryObject<Block> KARIATIDA = registerBlock("kariatida",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> STELLA = registerBlock("stella",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> APOLLON = registerBlock("apollon",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> GERA = registerBlock("gera",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> KIFARA = registerBlock("kifara",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<Block> DIONYS = registerBlock("dionys",
            () -> new TwoGeoBlockTallStatueEntityBLock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
            ));



    public static final RegistryObject<Block> PIFOS = registerBlock("pifos",
            () -> new PifosEntityGeoBlock(BlockBehaviour.Properties.of()
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

    // Обычные блоки с ванильным рендером.

    public static final RegistryObject<GenericModBlock> VASE = registerBlock("vase",
            () -> new GenericModBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<GenericModBlock> AMFORA = registerBlock("amfora",
            () -> new GenericModBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            )
    );
    public static final RegistryObject<GenericModBlock> PSICTER = registerBlock("psicter",
            () -> new GenericModBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<GenericModBlock> KANFAR = registerBlock("kanfar",
            () -> new GenericModBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<Block> SCROLL_STACK = registerBlock("scroll_stack",
            () -> new GenericModBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<Block> AMFORA_NO_PATTERN = registerBlock("amfora_no_pattern",
            () -> new GenericModBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<Block> KILIK = registerBlock("kilik",
            () -> new GenericModBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
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
            ));

    public static final RegistryObject<LittlePotBlock> LITTLE_POT = registerBlock("little_pot",
            () -> new LittlePotBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<Block> CLAY_SIGN = registerBlock("clay_sign",
            () -> new ClaySignBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));

    // Регистрируем только блок для блоков, у которых не должно быть BlockItem предмета: BLOCKS.register(...)
//    public static final RegistryObject<HorizontalDirectionalRegularModBlocks> LITTLE_POT_1 = BLOCKS.register("little_pot_1",
//            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
//                    .noOcclusion()
//                    .mapColor(MapColor.TERRACOTTA_BROWN)
//                    .strength(2.0f, 4f)
//            ));
//    public static final RegistryObject<HorizontalDirectionalRegularModBlocks> LITTLE_POT_2 = BLOCKS.register("little_pot_2",
//            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
//                    .noOcclusion()
//                    .mapColor(MapColor.TERRACOTTA_BROWN)
//                    .strength(2.0f, 4f)
//            ));
    public static final RegistryObject<Block> RED_CARPET = registerBlock("red_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(1.0f, 2f)
            ));
    public static final RegistryObject<Block> BLUE_CARPET = registerBlock("blue_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(1.0f, 2f)
            ));
    public static final RegistryObject<Block> SCROLL_SHELF = registerBlock("scroll_shelf",
            () -> new GenericModBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus iEventBus) {
        BLOCKS.register(iEventBus);
    }
}



