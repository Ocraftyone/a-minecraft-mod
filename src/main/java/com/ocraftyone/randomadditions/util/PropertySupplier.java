package com.ocraftyone.randomadditions.util;

import net.minecraft.world.item.Item;

@FunctionalInterface
public interface PropertySupplier<T extends Item.Properties> {
    Item.Properties apply(Item.Properties properties);
}
