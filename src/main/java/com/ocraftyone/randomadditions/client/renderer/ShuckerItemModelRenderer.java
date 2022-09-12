/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.ocraftyone.randomadditions.util.AnimationUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ShuckerItemModelRenderer extends BlockEntityWithoutLevelRenderer {
    
    private static final ResourceLocation SHUCKER_MODEL = new ResourceLocation("randomadditions", "item/corn_shucker_special");
    
    public ShuckerItemModelRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }
    
    @Override
    public void renderByItem(ItemStack pStack, ItemTransforms.TransformType pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
//        BakedModel mainModel = renderer.getItemModelShaper().getModelManager().getModel(SHUCKER_MODEL);
        BakedModel mainModel = renderer.getItemModelShaper().getItemModel(new ItemStack(Items.SHEARS));
        LocalPlayer player = Minecraft.getInstance().player;
        boolean itemInLeftHand = player.getOffhandItem() == pStack;
        boolean firstPerson = pTransformType.firstPerson();
        boolean gui = pTransformType.equals(ItemTransforms.TransformType.GUI);
        CompoundTag tag = pStack.getOrCreateTag();
        //Time
        float time = AnimationUtil.getPartialItemRemainingTicks() + 1.0F;
        float timeElapsed = AnimationUtil.getPartialAnimationTimeElapsed(pStack);
    
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.5F, 0.5F);
    
        if (tag.contains("Shucking")) {
            //Render item to be shucked
            pPoseStack.pushPose();
        
            float f1 = time / (float) pStack.getUseDuration();
            if (f1 < 0.8F) {
                float f2 = -Mth.abs(Mth.cos(time / 4.0F * (float) Math.PI) * 0.1F);
                pPoseStack.translate(0.0D, (double) f2, 0.0D);
            }
            
            renderer.renderStatic(ItemStack.of(tag.getCompound("Shucking")), pTransformType, pPackedLight, pPackedOverlay, pPoseStack, pBuffer, 0);
            pPoseStack.popPose();
            //Render shucker
            if (AnimationUtil.getItemRemainingTicks() != 0) {
                if (firstPerson) {
                    pPoseStack.translate((Math.cos(timeElapsed * (Math.PI * -0.1)) * 0.3) + 0.1, 0.0D, Math.sin(timeElapsed * (Math.PI * -0.1)) * 0.3);
                } else if (gui) {
                    pPoseStack.translate(Math.sin(timeElapsed * (Math.PI * -0.1)) * 0.2, 0, 0);
                }
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(timeElapsed * 18));
            }
        }

//        renderer.renderModelLists(mainModel, pStack, pPackedLight, pPackedOverlay, pPoseStack, ItemRenderer.getFoilBuffer(pBuffer, Sheets.translucentItemSheet(), true, pStack.hasFoil()));
        
        renderer.render(pStack, pTransformType, itemInLeftHand, pPoseStack, pBuffer, pPackedLight, pPackedOverlay, mainModel);
        pPoseStack.popPose();
    }
}
