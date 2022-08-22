package com.ocraftyone.randomadditions.inits;

import com.ocraftyone.randomadditions.Constants;
import com.ocraftyone.randomadditions.items.CornShuckerItem;
import com.ocraftyone.randomadditions.items.HomeItem;
import com.ocraftyone.randomadditions.items.MeasuringCupItem;
import com.ocraftyone.randomadditions.items.ModifiedFishingRod;
import com.ocraftyone.randomadditions.util.ItemTypeSupplier;
import com.ocraftyone.randomadditions.util.PropertySupplier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;


public class ModItems {
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    
    /*
     * GENERAL ITEMS
     * */
    
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
    public static final RegistryObject<Item> IRON_FISHING_ROD = register("iron_fishing_rod", () -> new ModifiedFishingRod(new Item.Properties().durability(128).tab(Constants.GeneralTab), Tiers.IRON));
    public static final RegistryObject<Item> GOLD_FISHING_ROD = register("gold_fishing_rod", () -> new ModifiedFishingRod(new Item.Properties().durability(96).tab(Constants.GeneralTab), Tiers.GOLD));
    public static final RegistryObject<Item> DIAMOND_FISHING_ROD = register("diamond_fishing_rod", () -> new ModifiedFishingRod(new Item.Properties().durability(256).tab(Constants.GeneralTab), Tiers.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_FISHING_ROD = register("netherite_fishing_rod", () -> new ModifiedFishingRod(new Item.Properties().durability(1024).tab(Constants.GeneralTab), Tiers.NETHERITE));
    
    //home item
    public static final RegistryObject<Item> HOME = register("home", () -> new HomeItem(new Item.Properties().tab(Constants.GeneralTab).stacksTo(1)));
    
    /*
     * FOOD ITEMS
     * */
    
    //corn items
    public static final RegistryObject<Item> CORN_KERNEL = register("corn_kernel", () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties().tab(Constants.FoodTab)));
    public static final RegistryObject<Item> CORN_COB = register("corn_cob", () -> new Item(new Item.Properties().tab(Constants.FoodTab)));
    public static final RegistryObject<Item> SHUCKED_CORN_COB = register("shucked_corn_cob", () -> new Item(new Item.Properties().tab(Constants.FoodTab).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 300, 0), 0.5F).build())));
    public static final RegistryObject<Item> CORN_SHUCKER = register("corn_shucker", Constants.FoodTab, CornShuckerItem::new, (p) -> p.durability(120));
    //cupcake items
    public static final RegistryObject<Item> CUPCAKE = register("cupcake", () -> new Item(new Item.Properties().tab(Constants.FoodTab)));
    public static final RegistryObject<Item> CUPCAKE_LINER = register("cupcake_liner", () -> new Item(new Item.Properties().tab(Constants.FoodTab)));
    public static final RegistryObject<Item> CUPCAKE_PAN = register("cupcake_pan", () -> new Item(new Item.Properties().tab(Constants.FoodTab)));
    
    //batter pans
    public static final RegistryObject<Item> CUPCAKE_PAN_BATTER_ONE = register("cupcake_pan_batter_one", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1)));
    public static final RegistryObject<Item> CUPCAKE_PAN_BATTER_TWO = register("cupcake_pan_batter_two", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1)));
    public static final RegistryObject<Item> CUPCAKE_PAN_BATTER_THREE = register("cupcake_pan_batter_three", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1)));
    public static final RegistryObject<Item> CUPCAKE_PAN_BATTER_FOUR = register("cupcake_pan_batter_four", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1)));
    public static final RegistryObject<Item> CUPCAKE_PAN_BATTER_FIVE = register("cupcake_pan_batter_five", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1)));
    public static final RegistryObject<Item> CUPCAKE_PAN_BATTER_SIX = register("cupcake_pan_batter_six", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1)));
    public static final List<RegistryObject<Item>> CUPCAKE_PANS_BATTER = Arrays.asList(
            CUPCAKE_PAN_BATTER_ONE,
            CUPCAKE_PAN_BATTER_TWO,
            CUPCAKE_PAN_BATTER_THREE,
            CUPCAKE_PAN_BATTER_FOUR,
            CUPCAKE_PAN_BATTER_FIVE,
            CUPCAKE_PAN_BATTER_SIX
    );
    
    //cupcake pans
    public static RegistryObject<Item> CUPCAKE_PAN_CUPCAKES_ONE = register("cupcake_pan_cupcakes_one", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1).craftRemainder(ModItems.CUPCAKE_PAN.get())));
    public static RegistryObject<Item> CUPCAKE_PAN_CUPCAKES_TWO = register("cupcake_pan_cupcakes_two", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1).craftRemainder(ModItems.CUPCAKE_PAN.get())));
    public static RegistryObject<Item> CUPCAKE_PAN_CUPCAKES_THREE = register("cupcake_pan_cupcakes_three", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1).craftRemainder(ModItems.CUPCAKE_PAN.get())));
    public static RegistryObject<Item> CUPCAKE_PAN_CUPCAKES_FOUR = register("cupcake_pan_cupcakes_four", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1).craftRemainder(ModItems.CUPCAKE_PAN.get())));
    public static RegistryObject<Item> CUPCAKE_PAN_CUPCAKES_FIVE = register("cupcake_pan_cupcakes_five", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1).craftRemainder(ModItems.CUPCAKE_PAN.get())));
    public static RegistryObject<Item> CUPCAKE_PAN_CUPCAKES_SIX = register("cupcake_pan_cupcakes_six", () -> new Item(new Item.Properties().tab(Constants.FoodTab).stacksTo(1).craftRemainder(ModItems.CUPCAKE_PAN.get())));
    public static final List<RegistryObject<Item>> CUPCAKE_PANS_CUPCAKES = Arrays.asList(
            CUPCAKE_PAN_CUPCAKES_ONE,
            CUPCAKE_PAN_CUPCAKES_TWO,
            CUPCAKE_PAN_CUPCAKES_THREE,
            CUPCAKE_PAN_CUPCAKES_FOUR,
            CUPCAKE_PAN_CUPCAKES_FIVE,
            CUPCAKE_PAN_CUPCAKES_SIX
    );
    
    //measuring cups
    public static final RegistryObject<Item> MEASURING_CUP = register("measuring_cup", () -> new MeasuringCupItem(new Item.Properties().tab(Constants.FoodTab)));
    public static final RegistryObject<Item> WATER_MEASURING_CUP = register("water_measuring_cup", () -> new MeasuringCupItem(new Item.Properties().tab(Constants.FoodTab)));
    public static final RegistryObject<Item> BATTER_MEASURING_CUP = register("batter_measuring_cup", () -> new MeasuringCupItem(new Item.Properties().tab(Constants.FoodTab).craftRemainder(MEASURING_CUP.get())));
    
    //dough
    public static final RegistryObject<Item> DOUGH = register("dough", () -> new Item(new Item.Properties().tab(Constants.FoodTab)));
    
    private static RegistryObject<Item> register(String name, Supplier<? extends Item> supplier) {
        return ITEM_REGISTRY.register(name, supplier);
    }
    
    private static <T extends Item> RegistryObject<Item> register(String name, CreativeModeTab tab, ItemTypeSupplier<T> itemTypeSupplier, @Nullable PropertySupplier<Item.Properties> properties) {
        Item.Properties finalProperties = properties == null ? new Item.Properties() : properties.apply(new Item.Properties());
        if (tab != null) finalProperties.tab(tab);
        return register(name, () -> itemTypeSupplier.create(finalProperties));
    }
    
    private static <T extends Item> RegistryObject<Item> register(String name, CreativeModeTab tab, ItemTypeSupplier<T> itemTypeSupplier) {
        return register(name, tab, itemTypeSupplier, null);
    }
}
