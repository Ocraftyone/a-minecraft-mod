/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ModItemStackHandler extends ItemStackHandler {
    public ModItemStackHandler(int size) {
        super(size);
    }
    
    public NonNullList<ItemStack> getAsList() {
        return NonNullList.of(null, stacks.toArray(new ItemStack[0]));
    }
}
