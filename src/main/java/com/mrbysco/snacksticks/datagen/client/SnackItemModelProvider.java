package com.mrbysco.snacksticks.datagen.client;

import com.mrbysco.snacksticks.SnackSticksMod;
import com.mrbysco.snacksticks.client.renderer.SnackSpecialRenderer;
import com.mrbysco.snacksticks.registry.SnackRegistry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.data.PackOutput;

public class SnackItemModelProvider extends ModelProvider {
	public SnackItemModelProvider(PackOutput packOutput) {
		super(packOutput, SnackSticksMod.MOD_ID);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(SnackRegistry.SNACK_STICK.get()), new SnackSpecialRenderer.Unbaked(false));
		itemModels.itemModelOutput.accept(SnackRegistry.SNACK_STICK.get(), itemmodel$unbaked);

		ItemModel.Unbaked itemmodel$unbaked2 = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(SnackRegistry.SNACK_STICK.get()), new SnackSpecialRenderer.Unbaked(true));
		itemModels.itemModelOutput.accept(SnackRegistry.SNACK.get(), itemmodel$unbaked2);
	}
}
