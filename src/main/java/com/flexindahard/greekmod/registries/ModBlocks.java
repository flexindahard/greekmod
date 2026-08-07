package com.flexindahard.greekmod.registries;

import com.flexindahard.greekmod.block.CarpetBlock;
import com.flexindahard.greekmod.block.ClaySignBlock;
import com.flexindahard.greekmod.block.HorizontalDirectionalRegularModBlocks;
import com.flexindahard.greekmod.block.LittlePotBlock;
import com.flexindahard.greekmod.block.staticalComplexModels.GenericStaticalBlock;
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

    public static final RegistryObject<GenericStaticalBlock> ZEUS = registerBlock("zeus",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<GenericStaticalBlock> AFRODITA = registerBlock("afrodita",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<GenericStaticalBlock> POSEIDON = registerBlock("poseidon",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(3.0f, 4f)
            ));

    public static final RegistryObject<GenericStaticalBlock> GRAY_1 = registerBlock("gray_1",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<GenericStaticalBlock> GRAY_2 = registerBlock("gray_2",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<GenericStaticalBlock> GRAY_3 = registerBlock("gray_3",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<GenericStaticalBlock> ARTEMIDA = registerBlock("artemida",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<GenericStaticalBlock> AFINA = registerBlock("afina",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<GenericStaticalBlock> AFINA_NIKA = registerBlock("afina_nika",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<GenericStaticalBlock> KARIATIDA = registerBlock("kariatida",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));
    public static final RegistryObject<GenericStaticalBlock> STELLA = registerBlock("stella",
            () -> new GenericStaticalBlock(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .strength(3.0f, 4f)
            ));

    // Обычные блоки с ванильным рендером.

    public static final RegistryObject<HorizontalDirectionalRegularModBlocks> VASE = registerBlock("vase",
            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<HorizontalDirectionalRegularModBlocks> AMFORA = registerBlock("amfora",
            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            )
    );
    public static final RegistryObject<HorizontalDirectionalRegularModBlocks> PSICTER = registerBlock("psicter",
            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<HorizontalDirectionalRegularModBlocks> KANFAR = registerBlock("kanfar",
            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<HorizontalDirectionalRegularModBlocks> AMFORA_SHELF = registerBlock("amfora_shelf",
            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(2.0f, 4f)
            ));
    public static final RegistryObject<HorizontalDirectionalRegularModBlocks> POT_TABLE = registerBlock("pot_table",
            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
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
    public static final RegistryObject<Block> SCROLL_STACK = registerBlock("scroll_stack",
            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
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
            () -> new HorizontalDirectionalRegularModBlocks(BlockBehaviour.Properties.of()
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



