package com.legacy.aether.client.particle;

import net.minecraft.client.particle.Particle;
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

}