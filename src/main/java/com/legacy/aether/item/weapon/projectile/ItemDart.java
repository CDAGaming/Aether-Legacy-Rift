package com.legacy.aether.item.weapon.projectile;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemDart extends Item
{

	public ItemDart() 
	{
		super(new Properties().group(ItemGroup.COMBAT));
	}

	public ItemDart(EnumRarity rarity) 
	{
		super(new Properties().rarity(rarity).group(ItemGroup.COMBAT));
	}

}