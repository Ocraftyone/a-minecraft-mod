package com.ocraftyone.randomadditions.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;

public class ShuckerItemModelRenderer extends BlockEntityWithoutLevelRenderer {
    
    public static final ShuckerItemModelRenderer INSTANCE = new ShuckerItemModelRenderer();
    
    public ShuckerItemModelRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }
    
    @Override
    public void renderByItem(ItemStack pStack, ItemTransforms.TransformType pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        BakedModel mainModel = renderer.getModel(pStack, null, null, 0);
        LocalPlayer player = Minecraft.getInstance().player;
        boolean itemInLeftHand = player.getOffhandItem() == pStack;
        
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.5F, 0.5F);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(45));
        renderer.render(pStack, pTransformType, itemInLeftHand, pPoseStack, pBuffer, pPackedLight, pPackedOverlay, mainModel);
        pPoseStack.popPose();
    }
}
