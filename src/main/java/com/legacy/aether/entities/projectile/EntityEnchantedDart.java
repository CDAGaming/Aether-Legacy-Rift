package com.legacy.aether.entities.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.item.ItemsAether;

public class EntityEnchantedDart extends EntityDart
{

	public EntityEnchantedDart(World worldIn)
	{
		super(EntityTypesAether.ENCHANTED_DART, worldIn);
	}

	public EntityEnchantedDart(World worldIn, double x, double y, double z)
	{
		super(EntityTypesAether.ENCHANTED_DART, worldIn, x, y, z);
	}

	public EntityEnchantedDart(EntityLivingBase shooter, World worldIn)
	{
		super(EntityTypesAether.ENCHANTED_DART, shooter, worldIn);
	}

	@Override
	protected void registerData()
	{
		super.registerData();

		this.setDamage(6.0D);
	}

	@Override
	protected ItemStack getArrowStack() 
	{
		return new ItemStack(ItemsAether.enchanted_dart);
	}

}