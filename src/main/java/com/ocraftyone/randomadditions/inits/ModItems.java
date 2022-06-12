package com.ocraftyone.randomadditions.inits;

import com.ocraftyone.randomadditions.Constants;
import com.ocraftyone.randomadditions.items.HomeItem;
import com.ocraftyone.randomadditions.items.ModifiedFishingRod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModItems {
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    
    //material rods
    public static final RegistryObject<Item> IRON_ROD = register("iron_rod", () -> new Item(new Item.Properties().tab(Constants.GeneralTab)));
    public static final RegistryObject<Item> GOLD_ROD = register("gold_rod", () -> new Item(new Item.Properties().tab(Constants.GeneralTab)));
    public static final RegistryObject<Item> DIAMOND_ROD = register("diamond_rod", () -> new Item(new Item.Properties().tab(Constants.GeneralTab)));
    public static final RegistryObject<Item> NETHERITE_ROD = register("netherite_rod", () -> new Item(new Item.Properties().tab(Constants.GeneralTab)));
    
    //material string
    public static final RegistryObject<Item> IRON_INFUSED_STRING = register("iron_infused_string", () -> new Item(new Item.Properties().tab(Constants.GeneralTab)));
    public static final RegistryObject<Item> GOLD_INFUSED_STRING = register("gold_infused_string", () -> new Item(new Item.Properties().tab(Constants.GeneralTab)));
    public static final RegistryObject<Item> DIAMOND_INFUSED_STRING = register("diamond_infused_string", () -> new Item(new Item.Properties().tab(Constants.GeneralTab)));
    public static final RegistryObject<Item> NETHERITE_INFUSED_STRING = register("netherite_infused_string", () -> new Item(new Item.Properties().tab(Constants.GeneralTab)));
    
    //netherite gem
    public static final RegistryObject<Item> NETHERITE_INFUSED_GEM = register("netherite_infused_gem", () -> new Item(new Item.Properties().tab(Constants.GeneralTab)));
    
    //fishing rods
    public static final RegistryObject<Item> IRON_FISHING_ROD = register("iron_fishing_rod", () -> new ModifiedFishingRod(new Item.Properties().durability(128).tab(Constants.FishingTab), Tiers.IRON));
    public static final RegistryObject<Item> GOLD_FISHING_ROD = register("gold_fishing_rod", () -> new ModifiedFishingRod(new Item.Properties().durability(96).tab(Constants.FishingTab), Tiers.GOLD));
    public static final RegistryObject<Item> DIAMOND_FISHING_ROD = register("diamond_fishing_rod", () -> new ModifiedFishingRod(new Item.Properties().durability(256).tab(Constants.FishingTab), Tiers.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_FISHING_ROD = register("netherite_fishing_rod", () -> new ModifiedFishingRod(new Item.Properties().durability(1024).tab(Constants.FishingTab), Tiers.NETHERITE));
    
    //corn items
    public static final RegistryObject<Item> CORN_KERNEL = register("corn_kernel", () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties()));
    
    //home item
    public static final RegistryObject<Item> HOME = register("home", () -> new HomeItem(new Item.Properties().stacksTo(1)));
    
    private static RegistryObject<Item> register(String name, Supplier<? extends Item> supplier) {
        return ITEM_REGISTRY.register(name, supplier);
    }
    
}
