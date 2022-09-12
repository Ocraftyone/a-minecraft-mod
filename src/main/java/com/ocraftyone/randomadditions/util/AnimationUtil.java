/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimationUtil {
    public static float getPartialTicks() {
        return Minecraft.getInstance().getFrameTime();
    }
    
    public static int getAnimationTimeElapsed(ItemStack stack) {
        return stack.getUseDuration() - getItemRemainingTicks();
    }
    
    public static float getPartialAnimationTimeElapsed(ItemStack stack) {
        return getAnimationTimeElapsed(stack) + getPartialTicks();
    }
    
    public static int getItemRemainingTicks() {
        return Minecraft.getInstance().player.getUseItemRemainingTicks();
    }
    
    public static float getPartialItemRemainingTicks() {
        return getItemRemainingTicks() - getPartialTicks();
    }
}
