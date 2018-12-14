package com.legacy.aether.world.biome;

import com.legacy.aether.world.WorldAether;

public class AetherBiomeProvider extends SingleBiomeProvider
{

	public AetherBiomeProvider()
	{
		super(new SingleBiomeProviderSettings().setBiome(WorldAether.aetherBiome));
	}

}