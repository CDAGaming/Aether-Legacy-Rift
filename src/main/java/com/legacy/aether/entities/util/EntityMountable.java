package com.legacy.aether.entities.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.world.World;

import com.legacy.aether.entities.passive.EntityAetherAnimal;

public abstract class EntityMountable extends EntityAetherAnimal
{

	private static final TrackedData<Boolean> RIDER_SNEAKING = DataTracker.registerData(EntityMountable.class, TrackedDataHandlerRegistry.BOOLEAN);

	public EntityMountable(EntityType<?> entityType_1, World world_1)
	{
		super(entityType_1, world_1);
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();

		this.dataTracker.startTracking(RIDER_SNEAKING, false);
	}

}
