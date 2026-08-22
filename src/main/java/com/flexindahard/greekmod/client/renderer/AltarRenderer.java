package com.flexindahard.greekmod.client.renderer;

import com.flexindahard.greekmod.blockentity.AltarBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import static com.flexindahard.greekmod.block.geo.GenericStaticalEntityBlock.FACING;

public class AltarRenderer implements BlockEntityRenderer<AltarBlockEntity> {

    private final BlockEntityRendererProvider.Context context;

    public AltarRenderer(BlockEntityRendererProvider.Context context){
        this.context = context;
    }

    @Override
    public void render(AltarBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        Level level = pBlockEntity.getLevel();
        ItemStack stack = Items.BEEF.getDefaultInstance();
        Direction blockDirection = pBlockEntity.getBlockState().getValue(FACING);
        double gameTime = level.getGameTime() + pPartialTick;
        double offsetY = Math.sin(gameTime / 12) /24;
        pPoseStack.translate(0.5f, 0.3f + offsetY, 0.5f);
        pPoseStack.scale(1, 1, 1);
        float yRotation = switch (blockDirection) {
            case NORTH -> 180f;
            case SOUTH -> 0f;
            case WEST -> 90f + 180f;
            case EAST -> 270f + 180f;
            default -> 0f; // UP/DOWN не актуальны для горизонтального FACING
        };
        pPoseStack.mulPose(Axis.YP.rotationDegrees(yRotation));
        pPoseStack.mulPose(Axis.XN.rotationDegrees(90));
        Minecraft.getInstance().getItemRenderer().renderStatic
                (stack, ItemDisplayContext.GROUND, pPackedLight, pPackedOverlay, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();
    }
}
