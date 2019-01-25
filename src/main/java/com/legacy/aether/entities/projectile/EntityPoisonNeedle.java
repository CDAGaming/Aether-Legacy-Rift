package com.legacy.aether.entities.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;

public class EntityPoisonNeedle extends EntityPoisonDart
{

	public EntityPoisonNeedle(double x, double y, double z, World world)
	{
		super(EntityTypesAether.POISON_NEEDLE, x, y, z, world);

		this.setUnaffectedByGravity(false);
	}

	public EntityPoisonNeedle(LivingEntity owner, World world)
	{
		super(EntityTypesAether.POISON_NEEDLE, owner, world);

		this.setUnaffectedByGravity(false);
	}

	public EntityPoisonNeedle(World world)
	{
		super(EntityTypesAether.POISON_NEEDLE, world);

		this.setUnaffectedByGravity(false);
	}

	@Override
	protected ItemStack asItemStack()
	{
		return ItemStack.EMPTY;
	}

	@Override
	public int getSpawnID()
	{
		return 4;
	}

}