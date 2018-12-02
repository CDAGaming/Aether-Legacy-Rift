package com.legacy.aether.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;

public class ItemAetherSpawnEgg extends ItemSpawnEgg
{

	public ItemAetherSpawnEgg(EntityType<?> entityType, int primaryEggColor, int seconaryEggColor)
	{
		super(entityType, primaryEggColor, seconaryEggColor, new Item.Properties().group(ItemGroup.MISC));
	}

}