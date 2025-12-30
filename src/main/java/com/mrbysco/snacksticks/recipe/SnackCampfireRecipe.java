package com.mrbysco.snacksticks.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.snacksticks.components.MobData;
import com.mrbysco.snacksticks.registry.SnackDataComponents;
import com.mrbysco.snacksticks.registry.SnackRecipes;
import com.mrbysco.snacksticks.registry.SnackRegistry;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleRecipeInput;

public class SnackCampfireRecipe extends CampfireCookingRecipe {
	public SnackCampfireRecipe(CookingBookCategory category) {
		super("", category, Ingredient.of(SnackRegistry.SNACK_STICK.get()), ItemStack.EMPTY, 1.0F, 600);
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public RecipeSerializer getSerializer() {
		return SnackRecipes.SNACK_CAMPFIRE_SERIALIZER.get();
	}

	@Override
	public ItemStack assemble(SingleRecipeInput input, Provider registries) {
		ItemStack snack = SnackRegistry.SNACK.toStack();

		ItemStack stick = input.item();
		if (stick.has(SnackDataComponents.MOB_DATA)) {
			MobData mobData = stick.get(SnackDataComponents.MOB_DATA);
			snack.set(SnackDataComponents.MOB_DATA, mobData);
		} else {
			snack.set(DataComponents.FOOD, new FoodProperties.Builder().nutrition(1).saturationModifier(0).build());
		}

		return snack;
	}

	public static class Serializer implements RecipeSerializer<SnackCampfireRecipe> {
		public static final MapCodec<SnackCampfireRecipe> CODEC = RecordCodecBuilder.mapCodec(
				instance -> instance.group(
								CookingBookCategory.CODEC.fieldOf("category").orElse(CookingBookCategory.FOOD).
										forGetter(recipe -> recipe.category)
						)
						.apply(instance, SnackCampfireRecipe::new)
		);
		public static final StreamCodec<RegistryFriendlyByteBuf, SnackCampfireRecipe> STREAM_CODEC = StreamCodec.of(
				SnackCampfireRecipe.Serializer::toNetwork, SnackCampfireRecipe.Serializer::fromNetwork
		);

		@Override
		public MapCodec<SnackCampfireRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, SnackCampfireRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		public static SnackCampfireRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			CookingBookCategory cookingbookcategory = buffer.readEnum(CookingBookCategory.class);
			return new SnackCampfireRecipe(cookingbookcategory);
		}

		public static void toNetwork(RegistryFriendlyByteBuf buffer, SnackCampfireRecipe recipe) {
			buffer.writeEnum(recipe.category());
		}
	}
}
