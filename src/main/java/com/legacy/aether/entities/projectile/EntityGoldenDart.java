package com.legacy.aether.entities.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.item.ItemsAether;

public class EntityGoldenDart extends EntityDart 
{

	public EntityGoldenDart(World worldIn)
	{
		super(EntityTypesAether.GOLDEN_DART, worldIn);
	}

	public EntityGoldenDart(World worldIn, double x, double y, double z)
	{
		super(EntityTypesAether.GOLDEN_DART, worldIn, x, y, z);
	}

	public EntityGoldenDart(LivingEntity shooter, World worldIn)
	{
		super(EntityTypesAether.GOLDEN_DART, shooter, worldIn);
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();

		this.setDamage(6.0D);
	}

	@Override
	protected ItemStack asItemStack()
	{
		return new ItemStack(ItemsAether.enchanted_dart);
	}

}