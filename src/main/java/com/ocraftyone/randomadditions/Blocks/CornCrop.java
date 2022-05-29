package com.ocraftyone.randomadditions.Blocks;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CornCrop extends CropBlock  {
    private final Supplier<? extends ItemLike> seedItem;
    
    public CornCrop(Properties properties, Supplier<? extends ItemLike> seed) {
        super(properties);
        this.seedItem = seed;
        
    }
    
    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return this.seedItem.get();
    }
    
}
