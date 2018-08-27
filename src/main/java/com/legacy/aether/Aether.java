package com.legacy.aether;

import org.dimdev.riftloader.listener.InitializationListener;

import net.minecraft.util.ResourceLocation;

public class Aether implements InitializationListener
{

	public static ResourceLocation locate(String location)
	{
		return new ResourceLocation("aether_legacy", location);
	}

	@Override
	public void onInitialization() 
	{

	}

}