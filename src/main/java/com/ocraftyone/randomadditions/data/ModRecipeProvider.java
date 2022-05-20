package com.ocraftyone.randomadditions.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
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
    
    }
}
