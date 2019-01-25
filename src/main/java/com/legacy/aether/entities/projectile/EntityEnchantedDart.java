package com.legacy.aether.entities.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.item.ItemsAether;

public class EntityEnchantedDart extends EntityDart
{

	public EntityEnchantedDart(double x, double y, double z, World world)
	{
		super(EntityTypesAether.ENCHANTED_DART, x, y, z, world);

		this.setDamage(6);
	}

	public EntityEnchantedDart(LivingEntity owner, World world)
	{
		super(EntityTypesAether.ENCHANTED_DART, owner, world);

		this.setDamage(6);
	}

	public EntityEnchantedDart(World world)
	{
		super(EntityTypesAether.ENCHANTED_DART, world);

		this.setDamage(6);
	}

	@Override
	protected ItemStack asItemStack()
	{
		return new ItemStack(ItemsAether.enchanted_dart);
	}

	@Override
	public int getSpawnID()
	{
		return 2;
	}

}