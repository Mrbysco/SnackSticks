package com.mrbysco.snacksticks.mixin;

import net.minecraft.client.renderer.entity.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
//	@SuppressWarnings("DiscouragedShift")
//	@Inject(method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
//			at = @At(
//					value = "INVOKE",
//					target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V",
//					shift = Shift.BEFORE,
//					ordinal = 0))
//	private void snacksticks$render(ItemStack itemStack,
//	                                ItemDisplayContext displayContext,
//	                                boolean leftHand,
//	                                PoseStack poseStack,
//	                                MultiBufferSource bufferSource,
//	                                int combinedLight,
//	                                int combinedOverlay,
//	                                BakedModel p_model,
//	                                CallbackInfo ci) {
//		if (itemStack.is(SnackRegistry.SNACK_STICK) || itemStack.is(SnackRegistry.SNACK)) {
//			SnackStickRenderer.INSTANCE.renderSnack(itemStack, displayContext, poseStack, bufferSource, combinedLight, combinedOverlay);
//		}
//	}

}
