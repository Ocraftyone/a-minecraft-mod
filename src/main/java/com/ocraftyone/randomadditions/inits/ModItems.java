package com.ocraftyone.randomadditions.inits;

import com.ocraftyone.randomadditions.Items.*;
import com.ocraftyone.randomadditions.Reference;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    
    public static final RegistryObject<Item> TEST_ITEM = REGISTRY.register("test_item", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_FISHING_ROD = REGISTRY.register("iron_fishing_rod", () -> new RandomAdditionsFishingRod(new Item.Properties().durability(128).tab(RandomAdditionsFishingTab.getTab()), Tiers.IRON));
    public static final RegistryObject<Item> GOLD_FISHING_ROD = REGISTRY.register("gold_fishing_rod", () -> new RandomAdditionsFishingRod(new Item.Properties().durability(96).tab(RandomAdditionsFishingTab.getTab()), Tiers.GOLD));
    public static final RegistryObject<Item> DIAMOND_FISHING_ROD = REGISTRY.register("diamond_fishing_rod", () -> new RandomAdditionsFishingRod(new Item.Properties().durability(256).tab(RandomAdditionsFishingTab.getTab()), Tiers.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_FISHING_ROD = REGISTRY.register("netherite_fishing_rod", () -> new RandomAdditionsFishingRod(new Item.Properties().durability(1024).tab(RandomAdditionsFishingTab.getTab()), Tiers.NETHERITE));
    
    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }
    
}
