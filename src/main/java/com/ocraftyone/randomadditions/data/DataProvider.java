package com.ocraftyone.randomadditions.data;

import com.ocraftyone.randomadditions.Constants;
import net.minecraft.data.DataGenerator;
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
    }
    
    private static void registerServerProviders(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        
        gen.addProvider(new ModRecipeProvider(gen));
    }
}
