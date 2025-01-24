package com.mrbysco.snacksticks.datagen.client;

import com.mrbysco.snacksticks.SnackSticksMod;
import com.mrbysco.snacksticks.registry.SnackRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SnackItemModelProvider extends ItemModelProvider {
	public SnackItemModelProvider(PackOutput packOutput, ExistingFileHelper helper) {
		super(packOutput, SnackSticksMod.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		generatedItem(SnackRegistry.SNACK_STICK.getId(), mcLoc("item/stick"));
		generatedItem(SnackRegistry.SNACK.getId(), mcLoc("item/stick"));
	}

	private void generatedItem(ResourceLocation location, ResourceLocation texture) {
		singleTexture(location.getPath(), ResourceLocation.withDefaultNamespace("item/generated"),
				"layer0", texture);
	}
}
