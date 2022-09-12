/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ocraftyone.randomadditions.blocks.entity.CornShuckerBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class CornShuckerBlockEntityRenderer implements BlockEntityRenderer<CornShuckerBlockEntity> {
    public CornShuckerBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
    }
    
    @Override
    public void render(CornShuckerBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
    
    
    }
}
