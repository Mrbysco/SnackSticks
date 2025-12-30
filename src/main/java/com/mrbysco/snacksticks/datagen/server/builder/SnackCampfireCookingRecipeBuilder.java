package com.mrbysco.snacksticks.datagen.server.builder;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Recipe;

import java.util.function.Function;

public class SnackCampfireCookingRecipeBuilder {
	private final Function<CookingBookCategory, Recipe<?>> factory;

	public SnackCampfireCookingRecipeBuilder(Function<CookingBookCategory, Recipe<?>> factory) {
		this.factory = factory;
	}

	public static SnackCampfireCookingRecipeBuilder cooking(Function<CookingBookCategory, Recipe<?>> factory) {
		return new SnackCampfireCookingRecipeBuilder(factory);
	}

	public void save(RecipeOutput recipeOutput, Identifier recipeId) {
		this.save(recipeOutput, ResourceKey.create(Registries.RECIPE, recipeId));
	}

	public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> recipeId) {
		recipeOutput.accept(recipeId, this.factory.apply(CookingBookCategory.FOOD), null);
	}
}
