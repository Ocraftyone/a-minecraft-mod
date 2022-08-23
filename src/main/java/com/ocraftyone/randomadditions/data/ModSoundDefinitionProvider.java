package com.ocraftyone.randomadditions.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class ModSoundDefinitionProvider extends SoundDefinitionsProvider {
    /**
     * Creates a new instance of this data provider.
     *
     * @param generator The data generator instance provided by the event you are initializing this provider in.
     * @param modId     The mod ID of the current mod.
     * @param helper    The existing file helper provided by the event you are initializing this provider in.
     */
    protected ModSoundDefinitionProvider(DataGenerator generator, String modId, ExistingFileHelper helper) {
        super(generator, modId, helper);
    }
    
    @Override
    public void registerSounds() {
        //add(SOUNDEVENT, definition().ATTRIBUTES);
    }
}
