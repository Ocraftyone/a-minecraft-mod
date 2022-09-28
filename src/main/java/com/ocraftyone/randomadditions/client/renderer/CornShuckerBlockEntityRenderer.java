/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.ocraftyone.randomadditions.blocks.entity.CornShuckerBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;

public class CornShuckerBlockEntityRenderer implements BlockEntityRenderer<CornShuckerBlockEntity> {
    
    private static final ResourceLocation SHUCKER_MODEL = new ResourceLocation("randomadditions", "block/auto_corn_shucker_spin");
    private BlockEntityRendererProvider.Context context;
    
    public CornShuckerBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
        this.context = pContext;
    }
    
    @Override
    public void render(CornShuckerBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        Minecraft instance = Minecraft.getInstance();
        BakedModel model = instance.getModelManager().getModel(SHUCKER_MODEL);
        ModelBlockRenderer modelRenderer = instance.getBlockRenderer().getModelRenderer();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5D, -0.0625D, 0.5D);
//        pPoseStack.mulPose(Vector3f.YP.rotationDegrees((instance.isPaused() ? AnimationUtil.getTicks() : AnimationUtil.getRenderTicks()) * 15F));
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(pBlockEntity.getRotation()));
        pPoseStack.translate(-0.5D, 0.0D, -0.5D);
        modelRenderer.renderModel(pPoseStack.last(), pBufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(pBlockEntity.getBlockState(), false)), pBlockEntity.getBlockState(), model, 1.0F, 1.0F, 1.0F, pPackedLight, pPackedOverlay);
        pPoseStack.popPose();
    }
}
