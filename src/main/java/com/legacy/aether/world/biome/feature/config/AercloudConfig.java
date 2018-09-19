package com.legacy.aether.world.biome.feature.config;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class AercloudConfig implements IFeatureConfig
{

	private IBlockState state;

	private boolean isFlat;

	private int amount;

	private int y;

	public AercloudConfig(IBlockState state, boolean isFlat, int amount, int y)
	{
		this.state = state;
		this.isFlat = isFlat;
		this.amount = amount;
		this.y = y;
	}

	public IBlockState getCloudState()
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

}