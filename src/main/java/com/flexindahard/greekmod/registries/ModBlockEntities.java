package com.flexindahard.greekmod.registries;

import com.flexindahard.greekmod.Greekmod;
import com.flexindahard.greekmod.block.staticalComplexModels.GenericStaticalGeoBlockEntity;
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
                            ModBlocks.STELLA.get()
                    ).build(null));

    public static void register(IEventBus iEventBus){
        BLOCK_ENTITIES.register(iEventBus);
    }
}
