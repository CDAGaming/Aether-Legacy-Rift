package com.legacy.aether.item.staff;

import net.minecraft.item.Item;

import com.legacy.aether.item.AetherItemGroup;

public class ItemNatureStaff extends Item
{

	public ItemNatureStaff() 
	{
		super(new Settings().stackSize(1).durability(100).itemGroup(AetherItemGroup.AETHER_MISC));
	}

}