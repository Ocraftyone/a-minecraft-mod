package com.ocraftyone.randomadditions;

import com.mojang.logging.LogUtils;
import com.ocraftyone.randomadditions.Client.ClientHandler;
import com.ocraftyone.randomadditions.inits.ModEntities;
import com.ocraftyone.randomadditions.inits.ModItems;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constants.MOD_ID)
public class RandomAdditions {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public RandomAdditions() {
        // Register the setup method for mod loading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    
        modEventBus.addListener(this::setupCommon);
        modEventBus.addListener(this::setupClient);
        ModItems.REGISTRY.register(modEventBus);
        ModEntities.REGISTRY.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void setupCommon(FMLCommonSetupEvent event) {
    
    }
    
    private void setupClient(FMLClientSetupEvent event) {
        ClientHandler.setupClient();
    }
    
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}
