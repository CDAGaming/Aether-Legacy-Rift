package com.legacy.aether.entities.movement;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class AetherPoisonMovement 
{

    public int poisonTime = 0;

    public double rotD, motD;

	private EntityLivingBase entity;

	public AetherPoisonMovement(EntityLivingBase entity)
	{
		this.entity = entity;
	}

	public void tick()
	{
        int timeUntilHit = this.poisonTime % 50;

        if (this.entity.removed)
        {
        	this.poisonTime = 0;

        	return;
        }

        if (this.poisonTime < 0)
        {
        	this.poisonTime++;

            return;
        }

        if (this.poisonTime == 0)
        {
            return;
        }

        this.distractEntity();

        if (timeUntilHit == 0) 
        {
            this.entity.attackEntityFrom(DamageSource.GENERIC, 1);
        }

        this.poisonTime--;
	}

    public boolean afflictPoison(int amount) 
    {
        this.poisonTime = amount;

        return true;
    }

    public boolean curePoison(int amount) 
    {
        this.poisonTime = amount;

        return true;
    }

    public void distractEntity()
    {
    	double gaussian = this.entity.world.rand.nextGaussian();
        double newMotD = 0.1D * gaussian;
        double newRotD = (Math.PI / 4D) * gaussian;

        this.motD = 0.2D * newMotD + (0.8D) * this.motD;
        this.entity.motionX += this.motD;
        this.entity.motionZ += this.motD;
        this.rotD = 0.125D * newRotD + (1.0D - 0.125D) * this.rotD;

        this.entity.rotationYaw = (float)((double)this.entity.rotationYaw + rotD);
        this.entity.rotationPitch = (float)((double)this.entity.rotationPitch + rotD);
    }

}