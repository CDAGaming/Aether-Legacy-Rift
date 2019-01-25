package com.legacy.aether.entities.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.item.ItemsAether;

public class EntityGoldenDart extends EntityDart
{

	public EntityGoldenDart(double x, double y, double z, World world)
	{
		super(EntityTypesAether.GOLDEN_DART, x, y, z, world);

		this.setDamage(4);
	}

	public EntityGoldenDart(LivingEntity owner, World world)
	{
		super(EntityTypesAether.GOLDEN_DART, owner, world);

		this.setDamage(4);
	}

	public EntityGoldenDart(World world)
	{
		super(EntityTypesAether.GOLDEN_DART, world);

		this.setDamage(4);
	}

	@Override
	protected ItemStack asItemStack()
	{
		return new ItemStack(ItemsAether.golden_dart);
	}

	@Override
	public int getSpawnID()
	{
		return 1;
	}

}