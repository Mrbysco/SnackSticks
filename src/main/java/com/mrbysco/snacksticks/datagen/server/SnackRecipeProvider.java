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
	public SnackRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
		super(provider, recipeOutput);
	}

	@Override
	protected void buildRecipes() {
		SnackCampfireCookingRecipeBuilder.cooking(SnackCampfireRecipe::new)
				.save(output, SnackSticksMod.modLoc("stick_cooking"));
	}

	public static class Runner extends RecipeProvider.Runner {
		public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
			super(output, completableFuture);
		}

		@Override
		protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
			return new SnackRecipeProvider(provider, recipeOutput);
		}

		@Override
		public String getName() {
			return "SnackSticks Recipes";
		}
	}
}
