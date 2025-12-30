package com.mrbysco.snacksticks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.snacksticks.components.MobData;
import com.mrbysco.snacksticks.registry.SnackDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class SnackSpecialRenderer implements SpecialModelRenderer<MobData> {
	private final boolean isSnack;
	private static final ConcurrentHashMap<String, Entity> entities = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<Entity, EntityModel<?>> entityModels = new ConcurrentHashMap<>();
	private final CameraRenderState cameraRenderState = new CameraRenderState();
	private final EntityRenderDispatcher renderDispatcher;

	public SnackSpecialRenderer(boolean isSnack) {
		this.renderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
		this.isSnack = isSnack;
	}

	@Override
	public void submit(@Nullable MobData data, ItemDisplayContext displayContext, PoseStack poseStack,
	                   SubmitNodeCollector nodeCollector, int packedLight, int packedOverlay,
	                   boolean hasFoil, int outlineColor) {
		if (data == null) {
			return; // Never happens but IntelliJ complains
		}
		String entityType = data.toString();
		Entity entity = entities.getOrDefault(entityType, null);
		if (entity == null) {
			Optional<EntityType<?>> optionalType = BuiltInRegistries.ENTITY_TYPE.getOptional(data.entityId());
			if (optionalType.isPresent() && Minecraft.getInstance().level != null) {
				Entity newEntity = optionalType.get().create(Minecraft.getInstance().level, EntitySpawnReason.LOAD);
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

			poseStack.translate(0.6875D, 0.625D, 0.5D);
			poseStack.scale(scale, scale, scale);
			poseStack.mulPose(Axis.ZP.rotationDegrees(-45F));

			if (displayContext == ItemDisplayContext.GUI) {
				poseStack.mulPose(Axis.YP.rotationDegrees(45F));
			}

			EntityRenderState state = renderDispatcher.extractEntity(entity, 0);
			state.lightCoords = packedLight;
			renderDispatcher.submit(state, cameraRenderState, 0, 0, 0, poseStack, nodeCollector);

			poseStack.popPose();
		}
	}

	@Override
	public void getExtents(Consumer<Vector3fc> consumer) {

	}

	@Override
	public MobData extractArgument(ItemStack stack) {
		return stack.get(SnackDataComponents.MOB_DATA);
	}

	public record Unbaked(boolean isSnack) implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<SnackSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				instance -> instance.group(
								Codec.BOOL.optionalFieldOf("snack", false).forGetter(SnackSpecialRenderer.Unbaked::isSnack)
						)
						.apply(instance, SnackSpecialRenderer.Unbaked::new)
		);

		@Override
		public MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(BakingContext bakingContext) {
			return new SnackSpecialRenderer(isSnack);
		}
	}
}
