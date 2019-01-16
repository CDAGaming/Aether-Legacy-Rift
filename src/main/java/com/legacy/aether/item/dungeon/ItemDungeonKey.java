package com.legacy.aether.item.dungeon;

import net.minecraft.item.Item;

import com.legacy.aether.item.AetherItemGroup;
import com.legacy.aether.item.ItemsAether;

public class ItemDungeonKey extends Item
{

	public ItemDungeonKey()
	{
		super(new Settings().stackSize(1).itemGroup(AetherItemGroup.AETHER_MISC).rarity(ItemsAether.AETHER_LOOT));
	}

}