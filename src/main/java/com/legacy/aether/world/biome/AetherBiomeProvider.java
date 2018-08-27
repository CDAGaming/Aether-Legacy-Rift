package com.legacy.aether.world.biome;

import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;

import com.legacy.aether.world.WorldAether;

public class AetherBiomeProvider extends SingleBiomeProvider
{

	public AetherBiomeProvider()
	{
		super(new SingleBiomeProviderSettings().setBiome(WorldAether.aetherBiome));
	}

}