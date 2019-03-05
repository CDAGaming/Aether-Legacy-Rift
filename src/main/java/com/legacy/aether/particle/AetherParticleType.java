package com.legacy.aether.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.particle.ParticleParameters;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

public class AetherParticleType extends ParticleType<AetherParticleType> implements ParticleParameters
{

	public AetherParticleType(boolean alwaysSpawn)
	{
		super(alwaysSpawn, PARAMETER_FACTORY);
	}

	@Override
	public ParticleType<AetherParticleType> getType()
	{
		return this;
	}

	@Override
	public void write(PacketByteBuf var1)
	{
	}

	@Override
	public String asString()
	{
		return Registry.PARTICLE_TYPE.getId(this).toString();
	}

	private static final ParticleParameters.Factory<AetherParticleType> PARAMETER_FACTORY = new ParticleParameters.Factory<AetherParticleType>()
	{

		@Override
		public AetherParticleType read(ParticleType<AetherParticleType> particleType, StringReader var2) throws CommandSyntaxException
		{
			return (AetherParticleType) particleType;
		}

		@Override
		public AetherParticleType read(ParticleType<AetherParticleType> particleType, PacketByteBuf var2)
		{
			return (AetherParticleType) particleType;
		}

	};

}