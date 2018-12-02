package com.legacy.aether.entities.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public abstract class EntityDart extends EntityArrow implements IProjectile
{

    private int ticksInAir;

	public EntityDart(EntityType<? extends EntityDart> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public EntityDart(EntityType<? extends EntityDart> entityTypeIn, World worldIn, double x, double y, double z)
	{
		super(entityTypeIn, x, y, z, worldIn);
	}

	public EntityDart(EntityType<? extends EntityDart> entityTypeIn, EntityLivingBase shooter, World worldIn)
	{
		super(entityTypeIn, shooter, worldIn);
	}

	@Override
    public void tick()
    {
        super.tick();

        if (this.ticksInAir == 500)
        {
        	this.remove();
        }

        if (!this.onGround)
        {
        	++this.ticksInAir;
        }
    }

    @Override
    public void setNoGravity(boolean flight)
    {

    }

    @Override
    public boolean hasNoGravity()
    {
        return true;
    }

}