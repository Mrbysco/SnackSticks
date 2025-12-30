package com.mrbysco.snacksticks;

import com.mojang.logging.LogUtils;
import com.mrbysco.snacksticks.handler.KillHandler;
import com.mrbysco.snacksticks.registry.SnackDataComponents;
import com.mrbysco.snacksticks.registry.SnackRecipes;
import com.mrbysco.snacksticks.registry.SnackRegistry;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(SnackSticksMod.MOD_ID)
public class SnackSticksMod {
	public static final String MOD_ID = "snacksticks";
	public static final Logger LOGGER = LogUtils.getLogger();

	public SnackSticksMod(IEventBus eventBus, Dist dist, ModContainer container) {
		SnackDataComponents.DATA_COMPONENT_TYPES.register(eventBus);
		SnackRegistry.BLOCKS.register(eventBus);
		SnackRegistry.ITEMS.register(eventBus);
		SnackRegistry.CREATIVE_MODE_TABS.register(eventBus);
		SnackRecipes.RECIPE_SERIALIZERS.register(eventBus);

		NeoForge.EVENT_BUS.register(new KillHandler());
	}

	public static Identifier modLoc(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
