package com.legacy.aether.util;

import net.minecraft.class_4002;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.particle.ParticleParameters;

public interface AetherParticleFactory<T extends ParticleParameters>
{

	ParticleFactory<T> create(class_4002 spriteHandler);

}