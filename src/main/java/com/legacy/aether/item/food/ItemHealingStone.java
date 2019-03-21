package com.legacy.aether.item.food;

import com.legacy.aether.item.AetherItemGroup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ItemHealingStone extends Item
{

	public ItemHealingStone() 
	{
		super(new Settings().itemGroup(AetherItemGroup.AETHER_FOOD).rarity(Rarity.RARE).food(AetherFood.HEALING_STONE));
	}

	@Override
    public boolean hasEnchantmentGlint(ItemStack stackIn)
    {
    	return true;
    }

}