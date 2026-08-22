package com.flexindahard.greekmod.blockentity;

import com.flexindahard.greekmod.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

// Класс представляет собой собирательный BlockEntity в обёртке GeoBlockEntity.

public class SimpleStatueBlockEntity extends BlockEntity implements GeoBlockEntity {

     private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SimpleStatueBlockEntity(BlockPos pPos, BlockState pBlockState) {
            super(ModBlockEntities.GENERIC_STATICAL_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    // Оставить пустым контроллер, чтобы не использовать анимацию.
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        //controllers.add(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    // Если нужна анимация - название должно точно совпадать с названием в файле .animation.json
    private PlayState predicate(AnimationState<SimpleStatueBlockEntity> state) {

//        state.getController().setAnimation(RawAnimation.begin().thenLoop("zeus_block"));
//        return PlayState.CONTINUE;

        return null;
    }

    // Этот Кэш необходим любой модели, даже статической.
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // Увеличивает бокс, отвечающий за зону, в которой модель рендерится.
    @Override
    public AABB getRenderBoundingBox() {
        BlockPos pos = getBlockPos();
        return new AABB(pos.getX(), pos.getY(), pos.getZ(),
                pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1);
    }

}
