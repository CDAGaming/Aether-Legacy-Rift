package com.legacy.aether.util;

import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.particle.ParticleParameters;

public interface AetherParticleFactory<T extends ParticleParameters>
{

	ParticleFactory<T> create(SpriteProvider provider);

}