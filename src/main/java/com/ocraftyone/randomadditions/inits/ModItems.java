package com.ocraftyone.randomadditions.inits;

import com.ocraftyone.randomadditions.Items.RandomAdditionsFishingRod;
import com.ocraftyone.randomadditions.Constants;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    
    //fishing rods
    public static final RegistryObject<Item> IRON_FISHING_ROD = REGISTRY.register("iron_fishing_rod", () -> new RandomAdditionsFishingRod(new Item.Properties().durability(128).tab(Constants.FishingTab), Tiers.IRON));
    public static final RegistryObject<Item> GOLD_FISHING_ROD = REGISTRY.register("gold_fishing_rod", () -> new RandomAdditionsFishingRod(new Item.Properties().durability(96).tab(Constants.FishingTab), Tiers.GOLD));
    public static final RegistryObject<Item> DIAMOND_FISHING_ROD = REGISTRY.register("diamond_fishing_rod", () -> new RandomAdditionsFishingRod(new Item.Properties().durability(256).tab(Constants.FishingTab), Tiers.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_FISHING_ROD = REGISTRY.register("netherite_fishing_rod", () -> new RandomAdditionsFishingRod(new Item.Properties().durability(1024).tab(Constants.FishingTab), Tiers.NETHERITE));
    
    //material rods
    public static final RegistryObject<Item> IRON_ROD = REGISTRY.register("iron_rod", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_ROD = REGISTRY.register("gold_rod", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_ROD = REGISTRY.register("diamond_rod", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_ROD = REGISTRY.register("netherite_rod", () -> new Item(new Item.Properties()));
    
    //material string
    public static final RegistryObject<Item> IRON_INFUSED_STRING = REGISTRY.register("iron_infused_string", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_INFUSED_STRING = REGISTRY.register("gold_infused_string", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_INFUSED_STRING = REGISTRY.register("diamond_infused_string", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_INFUSED_STRING = REGISTRY.register("netherite_infused_string", () -> new Item(new Item.Properties()));
    
    //netherite gem
    public static final RegistryObject<Item> NETHERITE_INFUSED_GEM = REGISTRY.register("netherite_infused_gem", () -> new Item(new Item.Properties()));
    
    
    
    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }
    
}
