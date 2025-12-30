package com.mrbysco.snacksticks.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

public record MobData(Identifier entityId, int health) {
	public static final Codec<MobData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
					Identifier.CODEC.fieldOf("entity_id").forGetter(MobData::entityId),
					Codec.INT.fieldOf("health").forGetter(MobData::health))
			.apply(inst, MobData::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, MobData> STREAM_CODEC = StreamCodec.composite(
			Identifier.STREAM_CODEC,
			p -> p.entityId,
			ByteBufCodecs.INT,
			p -> p.health,
			MobData::new
	);
}
