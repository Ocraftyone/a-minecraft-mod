package com.ocraftyone.randomadditions;

import com.mojang.logging.LogUtils;
import com.ocraftyone.randomadditions.client.ClientHandler;
import com.ocraftyone.randomadditions.inits.ModBlocks;
import com.ocraftyone.randomadditions.inits.ModEntities;
import com.ocraftyone.randomadditions.inits.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Constants.MOD_ID)
public class RandomAdditions {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public RandomAdditions() {
        // Register the setup method for mod loading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        modEventBus.addListener(this::setupClient);
        ModItems.ITEM_REGISTRY.register(modEventBus);
        ModEntities.ENTITY_REGISTRY.register(modEventBus);
        ModBlocks.BLOCK_REGISTRY.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void setupClient(FMLClientSetupEvent event) {
        ClientHandler.setupClient();
    }
}
