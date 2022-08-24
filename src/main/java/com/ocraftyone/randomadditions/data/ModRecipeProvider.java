/*
 * Copyright (c) 2022 Ocraftyone
 *
 * View license here: https://gist.github.com/Ocraftyone/06f367618c202a79bc6309ee59250260
 */

package com.ocraftyone.randomadditions.data;

import com.ocraftyone.randomadditions.Constants;
import com.ocraftyone.randomadditions.inits.ModItems;
import com.ocraftyone.randomadditions.inits.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }
    
    @Override
    public String getName() {
        return "RandomAdditions: Recipes";
    }
    
    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        craftingRecipes(consumer);
    }
    
    
    public static void craftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        int i = 0;
        for (RegistryObject<Item> item :
                ModItems.CUPCAKE_PANS_BATTER) {
            i++;
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(item.get()), ModItems.CUPCAKE_PANS_CUPCAKES.get(i - 1).get(), 0.75F, 600 * i).unlockedBy("has_batter_pan", has(ModTags.Items.BATTER_PANS)).save(consumer);
        }
        i = 0;
        for (RegistryObject<Item> item :
                ModItems.CUPCAKE_PANS_CUPCAKES) {
            i++;
            ShapelessRecipeBuilder.shapeless(ModItems.CUPCAKE.get(), i).requires(item.get()).unlockedBy("has_cupcake_pan", has(ModTags.Items.CUPCAKE_PANS)).save(consumer, new ResourceLocation(Constants.MOD_ID, "cupcake_" + i));
        }
    }
}
