package com.legacy.aether.item.util;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.item.ItemsAether;

public enum AetherTier
{

	Skyroot(new AetherItemTier(0, 59, 2.0F, 0.0F, 15, BlocksAether.skyroot_planks)),
	Holystone(new AetherItemTier(1, 131, 4.0F, 1.0F, 5, BlocksAether.holystone)),
	Zanite(new AetherItemTier(2, 250, 6.0F, 2.0F, 14, ItemsAether.zanite_gemstone)),
	Gravitite(new AetherItemTier(3, 1561, 8.0F, 3.0F, 10, BlocksAether.enchanted_gravitite)),
	Valkyrie(new AetherItemTier(4, 2164, 10.0F, 4.0F, 8, null)),
	Legendary(new AetherItemTier(4, 2164, 10.0F, 4.0F, 8, null));

	private final AetherItemTier defaultTier;

	AetherTier(AetherItemTier defaultTier)
	{
		this.defaultTier = defaultTier;
	}

	public AetherItemTier getDefaultTier()
	{
		return this.defaultTier;
	}

}