package com.mrbysco.snacksticks.registry;

import com.mrbysco.snacksticks.SnackSticksMod;
import com.mrbysco.snacksticks.recipe.SnackCampfireRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SnackRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, SnackSticksMod.MOD_ID);

	public static final Supplier<SnackCampfireRecipe.Serializer> SNACK_CAMPFIRE_SERIALIZER = RECIPE_SERIALIZERS.register("snack_campfire_cooking", SnackCampfireRecipe.Serializer::new);
}
