package com.mrbysco.snacksticks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.snacksticks.components.MobData;
import com.mrbysco.snacksticks.registry.SnackDataComponents;
import com.mrbysco.snacksticks.registry.SnackRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SnackStickRenderer {
	private static final ConcurrentHashMap<String, Entity> entities = new ConcurrentHashMap<>();
	public static final SnackStickRenderer INSTANCE = new SnackStickRenderer();

	private final EntityRenderDispatcher renderDispatcher;

	public SnackStickRenderer() {
		this.renderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
	}

	protected static void clearCache() {
		entities.clear();
	}

	public void renderSnack(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		if (stack.has(SnackDataComponents.MOB_DATA)) {
			MobData data = stack.get(SnackDataComponents.MOB_DATA);
			if (data == null) {
				return; // Never happens but IntelliJ complains
			}
			String entityType = data.toString();
			Entity entity = entities.getOrDefault(entityType, null);
			if (entity == null) {
				Optional<EntityType<?>> optionalType = BuiltInRegistries.ENTITY_TYPE.getOptional(data.entityId());
				if (optionalType.isPresent() && Minecraft.getInstance().level != null) {
					Entity newEntity = optionalType.get().create(Minecraft.getInstance().level);
					if (newEntity != null) {
						entities.put(entityType, newEntity);
						entity = entities.getOrDefault(entityType, null);
					}
				}
			}

			if (entity != null) {
				poseStack.pushPose();

				float entityWidth = entity.getBbWidth();
				float entityHeight = entity.getBbHeight();
				float scale = 0.5F / Math.max(entityWidth, entityHeight);

				poseStack.translate(0.625D, 0.625D, 0.5D);
				poseStack.scale(scale, scale, scale);
				poseStack.mulPose(Axis.ZP.rotationDegrees(-45F));

				if (displayContext == ItemDisplayContext.GUI) {
					poseStack.mulPose(Axis.YP.rotationDegrees(45F));
				}

				MultiBufferSource source = bufferSource;
				if (stack.is(SnackRegistry.SNACK)) {
					source = new ColoringBufferSource(0.58823529411F, 0.29411764705F, 0, 0.5F, bufferSource);
				}
				renderDispatcher.setRenderShadow(false);
				renderDispatcher.render(entity, 0, 0, 0, 0, 0, poseStack, source, packedLight);

				poseStack.popPose();
			}
		}
	}
}
