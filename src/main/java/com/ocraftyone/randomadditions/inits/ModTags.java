/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.inits;

import com.ocraftyone.randomadditions.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Constants.MOD_ID, name));
        }
        
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
        
    }
    
    public static class Items {
        
        public static final TagKey<Item> BATTER_PANS = tag("batter_pans");
        public static final TagKey<Item> CUPCAKE_PANS = tag("cupcake_pans");
        
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Constants.MOD_ID, name));
        }
        
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
