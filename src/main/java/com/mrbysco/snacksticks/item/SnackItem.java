package com.mrbysco.snacksticks.item;

import com.mrbysco.snacksticks.components.MobData;
import com.mrbysco.snacksticks.registry.SnackDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Util;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SnackItem extends Item {
	public SnackItem(Properties properties) {
		super(properties);
	}

//	@Override
//	public FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
//		if (!stack.has(DataComponents.FOOD)) {
//			if (stack.has(SnackDataComponents.MOB_DATA)) {
//				MobData mobData = stack.get(SnackDataComponents.MOB_DATA);
//				int health = mobData.health();
//				int saturation = Math.max(1, Mth.ceil(health / 3F));
//				float saturationModifier = saturation * 0.05F;
//				SnackSticksMod.LOGGER.info("{} {} {}", mobData.entityId(), saturation, saturationModifier);
//				stack.set(DataComponents.FOOD, new FoodProperties.Builder().nutrition(saturation).saturationModifier(saturationModifier).build());
//			} else {
//				stack.set(DataComponents.FOOD, new FoodProperties.Builder().nutrition(0).saturationModifier(0).build());
//			}
//		}
//		return super.getFoodProperties(stack, entity);
//	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		ItemStack resultStack = super.finishUsingItem(stack, level, livingEntity);
		if (!level.isClientSide() && livingEntity instanceof Player player) {
			ItemStack stick = Items.STICK.getDefaultInstance();
			if (!player.addItem(stick)) {
				player.spawnAtLocation((ServerLevel) level, stick);
			}
		}
		return resultStack;
	}


	@Override
	public Component getName(ItemStack stack) {
		MobData content = stack.get(SnackDataComponents.MOB_DATA);
		if (content != null) {
			String mobTranslation = Util.makeDescriptionId("entity", content.entityId());
			return Component.translatable(stack.getItem().getDescriptionId(), Component.translatable(mobTranslation));
		}

		return Component.translatable(stack.getItem().getDescriptionId(), "");
	}
}
