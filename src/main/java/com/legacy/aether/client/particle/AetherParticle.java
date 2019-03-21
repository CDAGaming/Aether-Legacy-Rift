package com.legacy.aether.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import net.minecraft.world.World;

public class AetherParticle extends Particle
{

	public AetherParticle(World worldIn, double posXIn, double posYIn, double posZIn) 
	{
		super(worldIn, posXIn, posYIn, posZIn);
	}

    public AetherParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    }

    public void setMotionX(double motionX)
    {
    	this.velocityX = motionX;
    }

    public void setMotionY(double motionY)
    {
    	this.velocityY = motionY;
    }

    public void setMotionZ(double motionZ)
    {
    	this.velocityZ = motionZ;
    }

    public double getX()
    {
    	return this.posX;
    }

    public double getY()
    {
    	return this.posY;
    }

    public double getZ()
    {
    	return this.posZ;
    }

    public double getMotionX()
    {
    	return this.velocityX;
    }

    public double getMotionY()
    {
    	return this.velocityY;
    }

    public double getMotionZ()
    {
    	return this.velocityZ;
    }

	@Override
	public void buildGeometry(BufferBuilder builder, Camera camera, float var3, float var4, float var5, float var6, float var7, float var8)
	{

	}

	@Override
	public ParticleTextureSheet getTextureSheet()
	{
		return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
	}

}