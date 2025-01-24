package com.mrbysco.snacksticks.client;

import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

public class ClientHandler {

	public static void onLogOut(ClientPlayerNetworkEvent.LoggingOut event) {
		SnackStickRenderer.clearCache();
	}
}
