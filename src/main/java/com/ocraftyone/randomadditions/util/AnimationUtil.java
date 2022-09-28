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
    private static int ticks;
    private static int pausedTicks;
    
    public static void reset() {
        ticks = 0;
        pausedTicks = 0;
    }
    
    public static void tick() {
        if (!Minecraft.getInstance().isPaused()) {
            ticks = (ticks + 1) % 1_728_000;
        } else {
            pausedTicks = (pausedTicks + 1) % 1_728_000;
        }
    }
    
    public static int getTicks() {
        return getTicks(false);
    }
    
    public static int getTicks(boolean includePaused) {
        return includePaused ? ticks + pausedTicks : ticks;
    }
    
    public static float getRenderTicks() {
        return getTicks() + getPartialTicks();
    }
    
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
