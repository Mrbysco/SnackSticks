package com.mrbysco.snacksticks.registry;

import com.mrbysco.snacksticks.SnackSticksMod;
import com.mrbysco.snacksticks.components.MobData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SnackDataComponents {
	public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, SnackSticksMod.MOD_ID);

	public static final Supplier<DataComponentType<MobData>> MOB_DATA = DATA_COMPONENT_TYPES.register("mob_data", () ->
			DataComponentType.<MobData>builder()
					.persistent(MobData.CODEC)
					.networkSynchronized(MobData.STREAM_CODEC)
					.build());
}
