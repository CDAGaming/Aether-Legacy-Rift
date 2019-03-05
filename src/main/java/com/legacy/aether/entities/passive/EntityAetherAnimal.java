package com.legacy.aether.entities.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public abstract class EntityAetherAnimal extends AnimalEntity
{

	public EntityAetherAnimal(EntityType<? extends AnimalEntity> type, World world)
	{
		super(type, world);

		this.spawningGround = BlocksAether.aether_grass;
	}


}