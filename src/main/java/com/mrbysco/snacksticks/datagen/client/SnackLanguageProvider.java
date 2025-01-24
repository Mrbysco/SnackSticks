package com.mrbysco.snacksticks.datagen.client;

import com.mrbysco.snacksticks.SnackSticksMod;
import com.mrbysco.snacksticks.registry.SnackRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class SnackLanguageProvider extends LanguageProvider {

	public SnackLanguageProvider(PackOutput packOutput) {
		super(packOutput, SnackSticksMod.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("itemGroup." + SnackSticksMod.MOD_ID, "Snack Sticks");

		addItem(SnackRegistry.SNACK_STICK, "Snack Stick");
		addItem(SnackRegistry.SNACK, "%s Snack");

		add("snacksticks.snack_stick.tooltip", "Snack Type: %s");
		add("snacksticks.snack_stick.tooltip.health", "Health: %s");
	}
}
