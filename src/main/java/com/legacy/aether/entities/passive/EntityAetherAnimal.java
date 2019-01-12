package com.legacy.aether.entities.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public abstract class EntityAetherAnimal extends AnimalEntity
{

	public EntityAetherAnimal(EntityType<?> entityType_1, World world_1)
	{
		super(entityType_1, world_1);

		this.spawningGround = BlocksAether.aether_grass;
	}

}