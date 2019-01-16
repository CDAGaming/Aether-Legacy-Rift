package com.legacy.aether.item;

import net.minecraft.item.RecordItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;

public class ItemAetherDisc extends RecordItem
{

	public ItemAetherDisc(int comparatorValueIn, SoundEvent soundIn)
	{
		super(comparatorValueIn, soundIn, new Settings().stackSize(1).itemGroup(AetherItemGroup.AETHER_MISC).rarity(Rarity.RARE));
	}

}