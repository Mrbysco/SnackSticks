package com.mrbysco.snacksticks.registry;

import com.mrbysco.snacksticks.SnackSticksMod;
import com.mrbysco.snacksticks.components.MobData;
import com.mrbysco.snacksticks.item.SnackItem;
import com.mrbysco.snacksticks.item.SnackStickItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SnackRegistry {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SnackSticksMod.MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SnackSticksMod.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SnackSticksMod.MOD_ID);

	public static final DeferredItem<SnackStickItem> SNACK_STICK = ITEMS.registerItem("snack_stick", SnackStickItem::new);
	public static final DeferredItem<SnackItem> SNACK = ITEMS.registerItem("snack", SnackItem::new);

	public static final Supplier<CreativeModeTab> SNACK_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> {
				ItemStack stack = new ItemStack(SnackRegistry.SNACK_STICK.get());
				stack.set(SnackDataComponents.MOB_DATA, new MobData(
						BuiltInRegistries.ENTITY_TYPE.getKey(EntityType.PIG),
						10
				));
				return stack;
			})
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
			.title(Component.translatable("itemGroup.snacksticks"))
			.displayItems((displayParameters, output) -> {
				Map<EntityType<?>, Integer> entityMap = getVanillaEntities();
				for (Map.Entry<EntityType<?>, Integer> entry : entityMap.entrySet()) {
					ResourceLocation entityID = BuiltInRegistries.ENTITY_TYPE.getKey(entry.getKey());
					int health = entry.getValue();
					MobData data = new MobData(
							entityID,
							health
					);

					ItemStack snackStick = new ItemStack(SnackRegistry.SNACK_STICK.get());
					snackStick.set(SnackDataComponents.MOB_DATA, data);
					output.accept(snackStick);

					ItemStack snack = new ItemStack(SnackRegistry.SNACK.get());
					snack.set(SnackDataComponents.MOB_DATA, data);
					output.accept(snack);
				}
			}).build());

	private static Map<EntityType<?>, Integer> getVanillaEntities() {
		Map<EntityType<?>, Integer> entityMap = new HashMap<>();
		entityMap.put(EntityType.ALLAY, 20);
		entityMap.put(EntityType.ARMADILLO, 12);
		entityMap.put(EntityType.AXOLOTL, 14);
		entityMap.put(EntityType.BAT, 6);
		entityMap.put(EntityType.BEE, 10);
		entityMap.put(EntityType.BLAZE, 20);
		entityMap.put(EntityType.CAMEL, 32);
		entityMap.put(EntityType.CAT, 10);
		entityMap.put(EntityType.CAVE_SPIDER, 12);
		entityMap.put(EntityType.CHICKEN, 4);
		entityMap.put(EntityType.COD, 3);
		entityMap.put(EntityType.COW, 10);
		entityMap.put(EntityType.CREEPER, 20);
		entityMap.put(EntityType.DOLPHIN, 10);
		entityMap.put(EntityType.DONKEY, 15);
		entityMap.put(EntityType.DROWNED, 20);
		entityMap.put(EntityType.ELDER_GUARDIAN, 80);
		entityMap.put(EntityType.ENDER_DRAGON, 200);
		entityMap.put(EntityType.ENDERMAN, 40);
		entityMap.put(EntityType.ENDERMITE, 8);
		entityMap.put(EntityType.EVOKER, 24);
		entityMap.put(EntityType.FOX, 10);
		entityMap.put(EntityType.FROG, 10);
		entityMap.put(EntityType.GHAST, 10);
		entityMap.put(EntityType.GIANT, 100);
		entityMap.put(EntityType.GLOW_SQUID, 10);
		entityMap.put(EntityType.GOAT, 10);
		entityMap.put(EntityType.GUARDIAN, 30);
		entityMap.put(EntityType.HOGLIN, 40);
		entityMap.put(EntityType.HORSE, 15); // Base health; can vary
		entityMap.put(EntityType.HUSK, 20);
		entityMap.put(EntityType.ILLUSIONER, 32);
		entityMap.put(EntityType.IRON_GOLEM, 100);
		entityMap.put(EntityType.LLAMA, 15); // Base health; can vary
		entityMap.put(EntityType.MAGMA_CUBE, 4); // Small; sizes scale health
		entityMap.put(EntityType.MOOSHROOM, 10);
		entityMap.put(EntityType.MULE, 15);
		entityMap.put(EntityType.OCELOT, 10);
		entityMap.put(EntityType.PANDA, 20);
		entityMap.put(EntityType.PARROT, 6);
		entityMap.put(EntityType.PHANTOM, 20);
		entityMap.put(EntityType.PIG, 10);
		entityMap.put(EntityType.PIGLIN, 16);
		entityMap.put(EntityType.PIGLIN_BRUTE, 50);
		entityMap.put(EntityType.PILLAGER, 24);
		entityMap.put(EntityType.POLAR_BEAR, 30);
		entityMap.put(EntityType.PUFFERFISH, 3);
		entityMap.put(EntityType.RABBIT, 3);
		entityMap.put(EntityType.RAVAGER, 100);
		entityMap.put(EntityType.SALMON, 3);
		entityMap.put(EntityType.SHEEP, 8);
		entityMap.put(EntityType.SHULKER, 30);
		entityMap.put(EntityType.SILVERFISH, 8);
		entityMap.put(EntityType.SKELETON, 20);
		entityMap.put(EntityType.SKELETON_HORSE, 15);
		entityMap.put(EntityType.SLIME, 4); // Small; sizes scale health
		entityMap.put(EntityType.SNIFFER, 14);
		entityMap.put(EntityType.SNOW_GOLEM, 4);
		entityMap.put(EntityType.SPIDER, 16);
		entityMap.put(EntityType.SQUID, 10);
		entityMap.put(EntityType.STRAY, 20);
		entityMap.put(EntityType.STRIDER, 20);
		entityMap.put(EntityType.TADPOLE, 6);
		entityMap.put(EntityType.TRADER_LLAMA, 15); // Base health; can vary
		entityMap.put(EntityType.TROPICAL_FISH, 3);
		entityMap.put(EntityType.TURTLE, 30);
		entityMap.put(EntityType.VEX, 14);
		entityMap.put(EntityType.VILLAGER, 20);
		entityMap.put(EntityType.VINDICATOR, 24);
		entityMap.put(EntityType.WARDEN, 500);
		entityMap.put(EntityType.WITCH, 26);
		entityMap.put(EntityType.WITHER, 300);
		entityMap.put(EntityType.WITHER_SKELETON, 20);
		entityMap.put(EntityType.WOLF, 8); // Base health; tamed wolves can vary
		entityMap.put(EntityType.ZOGLIN, 40);
		entityMap.put(EntityType.ZOMBIE, 20);
		entityMap.put(EntityType.ZOMBIE_HORSE, 15);
		entityMap.put(EntityType.ZOMBIE_VILLAGER, 20);
		entityMap.put(EntityType.ZOMBIFIED_PIGLIN, 20);
		return entityMap;
	}
}
