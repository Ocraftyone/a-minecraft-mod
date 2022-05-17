package com.ocraftyone.randomadditions.inits;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RandomAdditionsFishingTab extends CreativeModeTab {
    private static RandomAdditionsFishingTab tab;
    public RandomAdditionsFishingTab(String label) {
        super(label);
    }
    
    public RandomAdditionsFishingTab(int pId, String pLangId) {
        super(pId, pLangId);
    }
    
    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(ModItems.TEST_ITEM.get());
    }
    public static RandomAdditionsFishingTab getTab() {
        if (tab == null) {
            tab = new RandomAdditionsFishingTab("Mod Tab");
        }
        return tab;
    }
    
}
