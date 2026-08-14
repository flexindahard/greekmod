package com.flexindahard.greekmod.registries;

import com.flexindahard.greekmod.Greekmod;
import com.flexindahard.greekmod.block.geo.GenericStaticalGeoBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Greekmod.MODID);

    // В BlockEntityType.Builder.of(...) можно передавать несколько блоков, для всех из них будет подгружаться одна и та же Geo моделька.
    public static final RegistryObject<BlockEntityType<GenericStaticalGeoBlockEntity>> GENERIC_STATICAL_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("generic_statical_block_entity",
                    () -> BlockEntityType.Builder.of(GenericStaticalGeoBlockEntity::new,
                            ModBlocks.ZEUS.get(),
                            ModBlocks.AFRODITA.get(),
                            ModBlocks.POSEIDON.get(),
                            ModBlocks.GRAY_1.get(),
                            ModBlocks.GRAY_2.get(),
                            ModBlocks.GRAY_3.get(),
                            ModBlocks.ARTEMIDA.get(),
                            ModBlocks.AFINA.get(),
                            ModBlocks.AFINA_NIKA.get(),
                            ModBlocks.KARIATIDA.get(),
                            ModBlocks.PIFOS.get(),
                            ModBlocks.APOLLON.get(),
                            ModBlocks.GERA.get(),
                            ModBlocks.KIFARA.get(),
                            ModBlocks.DIONYS.get(),
                            ModBlocks.ASKLEPIY.get(),
                            ModBlocks.AID.get(),
                            ModBlocks.DEMETRA.get(),
                            ModBlocks.HERMES.get(),
                            ModBlocks.GEFEST.get(),
                            ModBlocks.STELLA.get()).build(null));
    /*
    Тут была ошибка из-за сигнатуры метода в данном случае в ClayTableBockEntity: Метод Билдера .of
     BlockEntityType.Builder.of(BLockEntityType:new) ждал на вход тип с конструктором только из двух аргументов,
     Пока я не подставил ModBlockEntities.CLAY_TABLE_BLOCK_ENTITY.get() вручную, и не убрал третий аргумент, ошибка не уходила.

    public ClayTableBlockEntity(~~Здесь_был_третий_Аргумент~~, BlockPos pPos, BlockState pBlockState)
    {super(ModBlockEntities.CLAY_TABLE_BLOCK_ENTITY.get(), pPos, pBlockState);}
    */
//    public static final RegistryObject<BlockEntityType<ClayTableBlockEntity>> CLAY_TABLE_BLOCK_ENTITY = BLOCK_ENTITIES
//            .register("clay_table_block_entity", () -> BlockEntityType.Builder.of(ClayTableBlockEntity::new,
//                    ModBlocks.POT_TABLE.get()).build(null));

    public static void register(IEventBus iEventBus){
        BLOCK_ENTITIES.register(iEventBus);
    }
}
