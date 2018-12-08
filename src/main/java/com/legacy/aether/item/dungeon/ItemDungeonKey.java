package com.legacy.aether.item.dungeon;

import com.legacy.aether.item.ItemsAether;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemDungeonKey extends Item
{

	public ItemDungeonKey()
	{
		super(new Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(ItemsAether.AETHER_LOOT));
	}

}