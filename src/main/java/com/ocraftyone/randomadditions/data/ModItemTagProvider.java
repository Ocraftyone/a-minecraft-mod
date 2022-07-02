package com.ocraftyone.randomadditions.data;

import com.ocraftyone.randomadditions.inits.ModItems;
import com.ocraftyone.randomadditions.inits.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ModItemTagProvider extends ItemTagsProvider {
    
    public ModItemTagProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, modId, existingFileHelper);
    }
    
    @Override
    protected void addTags() {
        for (RegistryObject<Item> item :
                ModItems.CUPCAKE_PANS_BATTER) {
            this.tag(ModTags.Items.BATTER_PANS).add(item.get());
        }
        for (RegistryObject<Item> item :
                ModItems.CUPCAKE_PANS_CUPCAKES) {
            this.tag(ModTags.Items.CUPCAKE_PANS).add(item.get());
        }
    }
    
    @Override
    public String getName() {
        return "RandomAdditions: Item Tags";
    }
}
