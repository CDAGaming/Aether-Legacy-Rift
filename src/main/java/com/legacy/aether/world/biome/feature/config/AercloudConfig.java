package com.legacy.aether.world.biome.feature.config;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.config.feature.FeatureConfig;

public class AercloudConfig implements FeatureConfig
{

	private BlockState state;

	private boolean isFlat;

	private int amount;

	private int y;

	public AercloudConfig(BlockState state, boolean isFlat, int amount, int y)
	{
		this.state = state;
		this.isFlat = isFlat;
		this.amount = amount;
		this.y = y;
	}

	public BlockState getCloudState()
	{
		return this.state;
	}

	public int getY()
	{
		return this.y;
	}

	public int cloudModifier()
	{
		return this.isFlat ? 3 : 1;
	}

	public int cloudAmount()
	{
		return this.amount;
	}

	public boolean isFlat()
	{
		return this.isFlat;
	}

	@Override
	public <T> Dynamic<T> serialize(DynamicOps<T> dynamicOps) {
		return null; // TODO? (1.14 Stub)
	}
}