package com.mrbysco.snacksticks.client;

import com.mrbysco.snacksticks.SnackSticksMod;
import com.mrbysco.snacksticks.client.renderer.SnackSpecialRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

@EventBusSubscriber
public class ClientHandler {

	@SubscribeEvent
	public static void onLogOut(ClientPlayerNetworkEvent.LoggingOut event) {
		SnackStickRenderer.clearCache();
	}

	@SubscribeEvent
	public static void registerSpecialModelRenderer(RegisterSpecialModelRendererEvent event) {
		event.register(SnackSticksMod.modLoc("snack"), SnackSpecialRenderer.Unbaked.MAP_CODEC);
	}
}
