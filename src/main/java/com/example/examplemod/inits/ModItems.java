package com.example.examplemod.inits;

import com.example.examplemod.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    
    public static final RegistryObject<Item> TEST_ITEM = REGISTRY.register("test_item", () -> new Item(new Item.Properties()));
    
    
    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }
    
}
