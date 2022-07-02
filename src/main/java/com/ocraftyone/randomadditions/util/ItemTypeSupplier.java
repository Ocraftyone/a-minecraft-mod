package com.ocraftyone.randomadditions.util;

import net.minecraft.world.item.Item;

@FunctionalInterface
public interface ItemTypeSupplier<T extends Item> {
    T create(Item.Properties properties);
}
