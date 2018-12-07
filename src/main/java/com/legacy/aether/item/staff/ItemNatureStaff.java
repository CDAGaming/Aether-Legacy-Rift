package com.legacy.aether.item.staff;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemNatureStaff extends Item
{

	public ItemNatureStaff() 
	{
		super(new Properties().maxStackSize(1).defaultMaxDamage(100).group(ItemGroup.TOOLS));
	}

}