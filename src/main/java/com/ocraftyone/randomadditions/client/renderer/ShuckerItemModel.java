package com.ocraftyone.randomadditions.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraftforge.client.model.BakedModelWrapper;

public class ShuckerItemModel extends BakedModelWrapper<BakedModel> {
    public ShuckerItemModel(BakedModel originalModel) {
        super(originalModel);
    }
    
    @Override
    public boolean isCustomRenderer() {
        return true;
    }
    
    @Override
    public BakedModel handlePerspective(ItemTransforms.TransformType cameraTransformType, PoseStack poseStack) {
        super.handlePerspective(cameraTransformType, poseStack);
        return this;
    }
}
