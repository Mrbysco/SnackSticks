package com.mrbysco.snacksticks.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public record MobData(ResourceLocation entityId, int health) {
	public static final Codec<MobData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
					ResourceLocation.CODEC.fieldOf("entity_id").forGetter(MobData::entityId),
					Codec.INT.fieldOf("health").forGetter(MobData::health))
			.apply(inst, MobData::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, MobData> STREAM_CODEC = StreamCodec.composite(
			ResourceLocation.STREAM_CODEC,
			p -> p.entityId,
			ByteBufCodecs.INT,
			p -> p.health,
			MobData::new
	);
}
