package com.mrbysco.snacksticks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fc;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class ColoringBufferSource implements MultiBufferSource {
	public final float _r;
	public final float _g;
	public final float _b;
	public final float _a;
	private final MultiBufferSource inner;

	public ColoringBufferSource(float r, float g, float b, float a, MultiBufferSource inner) {
		this._r = r;
		this._g = g;
		this._b = b;
		this._a = a;
		this.inner = inner;
	}

	@NotNull
	@Override
	public VertexConsumer getBuffer(@NotNull RenderType renderType) {
		return new ConsumerWrapper(inner.getBuffer(renderType));
	}

	private class ConsumerWrapper implements VertexConsumer {
		private final VertexConsumer buffer;

		public ConsumerWrapper(VertexConsumer buffer) {
			this.buffer = buffer;
		}

		@NotNull
		@Override
		public VertexConsumer addVertex(float x, float y, float z) {
			return buffer.addVertex(x, y, z);
		}

		@Override
		public VertexConsumer addVertex(Vector3fc pos) {
			return buffer.addVertex(pos);
		}

		@Override
		public VertexConsumer addVertex(PoseStack.Pose pose, Vector3f pos) {
			return buffer.addVertex(pose, pos);
		}

		@Override
		public VertexConsumer addVertex(PoseStack.Pose pose, float x, float y, float z) {
			return buffer.addVertex(pose, x, y, z);
		}

		@Override
		public VertexConsumer addVertex(Matrix4fc pose, float x, float y, float z) {
			return buffer.addVertex(pose, x, y, z);
		}

		@Override
		public void addVertex(float x, float y, float z, int color, float u, float v, int packedOverlay, int packedLight, float normalX, float normalY, float normalZ) {
			buffer.addVertex(x, y, z, color, u, v, packedOverlay, packedLight, normalX, normalY, normalZ);
		}

		@Override
		public VertexConsumer addVertexWith2DPose(Matrix3x2fc pose, float x, float y) {
			return buffer.addVertexWith2DPose(pose, x, y);
		}

		@NotNull
		@Override
		public VertexConsumer setColor(int r, int g, int b, int a) {
			return buffer.setColor((int) (r * _r), (int) (g * _g), (int) (b * _b), (int) (a * _a));
		}

		@Override
		public VertexConsumer setColor(int color) {
			return buffer.setColor(color);
		}

		@NotNull
		@Override
		public VertexConsumer setUv(float u, float v) {
			return buffer.setUv(u, v);
		}

		@NotNull
		@Override
		public VertexConsumer setUv1(int u, int v) {
			return buffer.setUv1(u, v);
		}

		@NotNull
		@Override
		public VertexConsumer setUv2(int u, int v) {
			return buffer.setUv2(u, v);
		}

		@NotNull
		@Override
		public VertexConsumer setNormal(@NotNull PoseStack.Pose pose, float x, float y, float z) {
			return buffer.setNormal(pose, x, y, z);
		}

		@NotNull
		@Override
		public VertexConsumer setNormal(float x, float y, float z) {
			return buffer.setNormal(x, y, z);
		}

		@Override
		public VertexConsumer setLineWidth(float lineWidth) {
			return buffer.setLineWidth(lineWidth);
		}
	}
}