package com.legacy.aether.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemSpawnEgg;

public class ItemAetherSpawnEgg extends ItemSpawnEgg
{

	private EntityType<?> entityType;

	public ItemAetherSpawnEgg(EntityType<?> entityType, int primaryEggColor, int seconaryEggColor, Builder builder)
	{
		super(entityType, primaryEggColor, seconaryEggColor, builder);

		this.entityType = entityType;
	}

	public EntityType<?> getEntityType()
	{
		return this.entityType;
	}

}