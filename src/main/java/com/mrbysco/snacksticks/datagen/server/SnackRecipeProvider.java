package com.mrbysco.snacksticks.datagen.server;

import com.mrbysco.snacksticks.SnackSticksMod;
import com.mrbysco.snacksticks.datagen.server.builder.SnackCampfireCookingRecipeBuilder;
import com.mrbysco.snacksticks.recipe.SnackCampfireRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class SnackRecipeProvider extends RecipeProvider {
	public SnackRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void buildRecipes(RecipeOutput output, HolderLookup.Provider provider) {
		SnackCampfireCookingRecipeBuilder.cooking(SnackCampfireRecipe::new)
				.save(output, SnackSticksMod.modLoc("stick_cooking"));
	}
}
