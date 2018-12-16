package com.legacy.aether.item.staff;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemNatureStaff extends Item
{

	public ItemNatureStaff() 
	{
		super(new Settings().stackSize(1).durability(100).itemGroup(ItemGroup.TOOLS));
	}

}