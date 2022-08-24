/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions;

import com.mojang.logging.LogUtils;
import com.ocraftyone.randomadditions.client.ClientHandler;
import com.ocraftyone.randomadditions.inits.ModBlocks;
import com.ocraftyone.randomadditions.inits.ModEntities;
import com.ocraftyone.randomadditions.inits.ModItems;
import com.ocraftyone.randomadditions.inits.ModSounds;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Constants.MOD_ID)
public class RandomAdditions {
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public RandomAdditions() {
        // Register the setup method for mod loading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        modEventBus.addListener(this::setupClient);
        ModSounds.SOUND_REGISTRY.register(modEventBus);
        ModBlocks.BLOCK_REGISTRY.register(modEventBus);
        ModItems.ITEM_REGISTRY.register(modEventBus);
        ModEntities.ENTITY_REGISTRY.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::onClientChatReceived);
    }
    
    private void setupClient(FMLClientSetupEvent event) {
        ClientHandler.setupClient();
    }
    
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        String string = event.getMessage().getString();
        RandomAdditions.LOGGER.info(string);
    }
}
