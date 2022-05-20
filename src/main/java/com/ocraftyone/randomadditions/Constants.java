package com.ocraftyone.randomadditions;

import com.ocraftyone.randomadditions.inits.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Constants {
    public static final String MOD_ID = "randomadditions";
    
    
    public static final CreativeModeTab GeneralTab = new CreativeModeTab("random_additions_general") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.NETHERITE_INFUSED_GEM.get());
        }
    };
    
    public static final CreativeModeTab FishingTab = new CreativeModeTab("random_additions_fishing") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.NETHERITE_FISHING_ROD.get());
        }
    };
    
}
