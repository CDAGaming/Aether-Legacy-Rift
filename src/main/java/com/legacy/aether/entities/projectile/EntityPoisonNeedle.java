package com.legacy.aether.entities.projectile;

import com.legacy.aether.entities.EntityTypesAether;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityPoisonNeedle extends EntityPoisonDart
{

    public EntityPoisonNeedle(World world)
    {
        super(EntityTypesAether.POISON_NEEDLE, world);
    }

	public EntityPoisonNeedle(World worldIn, double x, double y, double z)
	{
		super(EntityTypesAether.POISON_NEEDLE, worldIn, x, y, z);
	}

    public EntityPoisonNeedle(EntityLiving entity, World world)
    {
        super(EntityTypesAether.POISON_NEEDLE, entity, world);
    }

	@Override
	protected void registerData()
	{
		super.registerData();

        this.setDamage(0.5D);
    }

    @Override
    public boolean hasNoGravity()
    {
        return false;
    }

}