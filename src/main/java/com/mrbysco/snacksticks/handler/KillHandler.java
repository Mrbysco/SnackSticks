package com.mrbysco.snacksticks.handler;

import com.mrbysco.snacksticks.components.MobData;
import com.mrbysco.snacksticks.registry.SnackDataComponents;
import com.mrbysco.snacksticks.registry.SnackRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

public class KillHandler {
	@SubscribeEvent
	public void onKilled(LivingDeathEvent event) {
		if (event.getSource().getEntity() instanceof Player player) {
			Level level = player.level();
			if (level.isClientSide()) return;

			ItemStack stack = ItemStack.EMPTY;
			if (player.getMainHandItem().is(Items.STICK)) {
				stack = player.getMainHandItem();
			} else if (player.getOffhandItem().is(Items.STICK)) {
				stack = player.getOffhandItem();
			}

			if (!stack.isEmpty()) {
				stack.consume(1, player);
				ItemStack snackStick = SnackRegistry.SNACK_STICK.toStack();
				snackStick.set(SnackDataComponents.MOB_DATA, new MobData(
						BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType()),
						Mth.ceil(event.getEntity().getMaxHealth())
				));
				if (!player.addItem(snackStick)) {
					player.spawnAtLocation((ServerLevel) level, snackStick);
				}
			}
		}
	}
}
