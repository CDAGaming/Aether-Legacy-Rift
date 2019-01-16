package com.legacy.aether.item.food;

import net.minecraft.item.FoodItem;

import com.legacy.aether.item.AetherItemGroup;

public class ItemAetherFood extends FoodItem
{

	public ItemAetherFood(int healAmount, float saturationAmount)
	{
		this(new Settings().itemGroup(AetherItemGroup.AETHER_FOOD), healAmount, saturationAmount);
	}

	public ItemAetherFood(Settings builder, int healAmount, float saturationAmount)
	{
		super(healAmount, saturationAmount, false, builder);
	}

}