package com.legacy.aether.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class ItemAetherDisc extends ItemRecord
{

	public ItemAetherDisc(int comparatorValueIn, SoundEvent soundIn) 
	{
		super(comparatorValueIn, soundIn, new Properties().defaultMaxDamage(1).group(ItemGroup.MISC).rarity(EnumRarity.RARE));
	}

}