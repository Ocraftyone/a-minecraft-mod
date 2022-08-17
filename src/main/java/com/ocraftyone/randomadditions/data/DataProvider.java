package com.ocraftyone.randomadditions.data;

import com.ocraftyone.randomadditions.Constants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber (modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataProvider {
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (event.includeServer()) {
            registerServerProviders(event);
        }
        if (event.includeClient()) {
            registerClientProviders(event);
        }
    }
    
    private static void registerServerProviders(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    
        gen.addProvider(new ModRecipeProvider(gen));
    
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(gen, Constants.MOD_ID, existingFileHelper);
        gen.addProvider(blockTagsProvider);
        gen.addProvider(new ModItemTagProvider(gen, blockTagsProvider, Constants.MOD_ID, existingFileHelper));
    }
    
    private static void registerClientProviders(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        
        gen.addProvider(new ModLanguageProvider(gen, Constants.MOD_ID, "en_us"));
    }
}