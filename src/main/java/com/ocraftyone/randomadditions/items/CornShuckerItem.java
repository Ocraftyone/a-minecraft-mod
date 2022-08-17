package com.ocraftyone.randomadditions.items;

import com.ocraftyone.randomadditions.client.renderer.ShuckerItemModelRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

public class CornShuckerItem extends Item {
    public CornShuckerItem(Properties pProperties) {
        super(pProperties);
    }
    
    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return ShuckerItemModelRenderer.INSTANCE;
            }
        });
    }
    
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.EAT;
    }
}
