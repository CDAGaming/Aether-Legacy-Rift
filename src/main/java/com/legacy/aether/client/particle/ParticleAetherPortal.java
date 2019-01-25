package com.legacy.aether.client.particle;

import net.minecraft.client.particle.PortalParticle;
import net.minecraft.world.World;

public class ParticleAetherPortal extends PortalParticle
{

	public ParticleAetherPortal(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed)
	{
		super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);

		float f = this.random.nextFloat() * 0.6F + 0.4F;

		this.colorRed = this.colorGreen = this.colorBlue = 1.0F * f;
		this.colorRed *= 0.2F;
		this.colorGreen *= 0.2F;
	}

}