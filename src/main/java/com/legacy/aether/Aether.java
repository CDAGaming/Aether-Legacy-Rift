package com.legacy.aether;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Aether implements ModInitializer
{

	public static Identifier locate(String location)
	{
		return new Identifier("aether_legacy", location);
	}

	@Override
	public void onInitialize()
	{

	}

}