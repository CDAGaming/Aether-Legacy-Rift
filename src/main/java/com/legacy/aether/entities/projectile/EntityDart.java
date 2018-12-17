package com.legacy.aether.entities.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.sortme.Projectile;
import net.minecraft.world.World;

public abstract class EntityDart extends ArrowEntity implements Projectile
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

	public EntityDart(EntityType<? extends EntityDart> entityTypeIn, LivingEntity shooter, World worldIn)
	{
		super(entityTypeIn, shooter, worldIn);
	}

	@Override
    public void update()
    {
        super.update();

        if (this.ticksInAir == 500)
        {
        	this.invalidate();
        }

        if (!this.onGround)
        {
        	++this.ticksInAir;
        }
    }

    @Override
    public void setUnaffectedByGravity(boolean flight)
    {

    }

    @Override
    public boolean isUnaffectedByGravity()
    {
        return true;
    }

}