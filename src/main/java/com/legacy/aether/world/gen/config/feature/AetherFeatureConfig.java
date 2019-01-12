package com.legacy.aether.world.gen.config.feature;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.world.gen.config.feature.FeatureConfig;

public interface AetherFeatureConfig extends FeatureConfig
{

	@Override
	public default <T> Dynamic<T> serialize(DynamicOps<T> var1)
	{
		return new Dynamic<T>(var1, var1.emptyMap());
	}

}