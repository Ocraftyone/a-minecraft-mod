package com.example.examplemod.inits;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CreativeMenuTab extends CreativeModeTab {
    public CreativeMenuTab(String label) {
        super(label);
    }
    
    public CreativeMenuTab(int pId, String pLangId) {
        super(pId, pLangId);
    }
    
    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(ModItems.TEST_ITEM.get().asItem());
    }
    
}
