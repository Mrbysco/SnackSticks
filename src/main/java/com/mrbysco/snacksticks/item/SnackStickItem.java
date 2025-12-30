package com.mrbysco.snacksticks.item;

import com.mrbysco.snacksticks.components.MobData;
import com.mrbysco.snacksticks.registry.SnackDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.Optional;
import java.util.function.Consumer;

public class SnackStickItem extends Item {
	public SnackStickItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
		if (stack.has(SnackDataComponents.MOB_DATA)) {
			MobData mobData = stack.get(SnackDataComponents.MOB_DATA);
			Optional<EntityType<?>> optionalType = BuiltInRegistries.ENTITY_TYPE.getOptional(mobData.entityId());
			if (optionalType.isPresent()) {
				int health = mobData.health();
				ChatFormatting color = ChatFormatting.GRAY;
				if (health < 5) {
					color = ChatFormatting.GRAY;
				} else if (health < 15) {
					color = ChatFormatting.GREEN;
				} else if (health < 30) {
					color = ChatFormatting.YELLOW;
				} else if (health < 50) {
					color = ChatFormatting.GOLD;
				} else if (health >= 50) {
					color = ChatFormatting.RED;
				}
				MutableComponent entityDescription = optionalType.get().getDescription().copy().withStyle(color);
				tooltipAdder.accept(Component.translatable("snacksticks.snack_stick.tooltip", entityDescription));
				tooltipAdder.accept(Component.translatable("snacksticks.snack_stick.tooltip.health", Component.literal(String.valueOf(health)).withStyle(color)));
			}
		}
	}
}
