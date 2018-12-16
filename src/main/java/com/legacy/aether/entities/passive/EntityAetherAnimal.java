package com.legacy.aether.entities.passive;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.item.ItemsAether;

public abstract class EntityAetherAnimal extends AnimalEntity
{

	protected Random random = new Random();

	public EntityAetherAnimal(EntityType<? extends Entity> type, World worldIn) 
	{
		super(type, worldIn);

		this.spawningGround = BlocksAether.aether_grass;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack)
    {
		return stack.getItem() == ItemsAether.blueberry;
    }

}