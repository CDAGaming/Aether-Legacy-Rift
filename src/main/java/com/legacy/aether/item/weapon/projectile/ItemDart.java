package com.legacy.aether.item.weapon.projectile;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;

public class ItemDart extends Item
{

	public ItemDart() 
	{
		super(new Settings().itemGroup(ItemGroup.COMBAT));
	}

	public ItemDart(Rarity rarity)
	{
		super(new Settings().rarity(rarity).itemGroup(ItemGroup.COMBAT));
	}

}