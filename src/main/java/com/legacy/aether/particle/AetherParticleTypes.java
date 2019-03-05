package com.legacy.aether.particle;

import com.legacy.aether.Aether;

import net.minecraft.util.registry.Registry;

public class AetherParticleTypes
{

	public static final AetherParticleType AETHER_PORTAL = register("aether_portal", false);

	public static void register()
	{

	}

	private static AetherParticleType register(String name, boolean alwaysSpawn)
	{
		return Registry.register(Registry.PARTICLE_TYPE, Aether.locate(name), new AetherParticleType(alwaysSpawn));
	}

}