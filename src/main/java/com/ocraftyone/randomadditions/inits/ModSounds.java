package com.ocraftyone.randomadditions.inits;

import com.ocraftyone.randomadditions.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Constants.MOD_ID);
    
    public static RegistryObject<SoundEvent> HOME_TELEPORT = register("home_teleport");
    
    public static RegistryObject<SoundEvent> register(String name) {
        return SOUND_REGISTRY.register(name, () -> new SoundEvent(new ResourceLocation(Constants.MOD_ID, name)));
    }
}
