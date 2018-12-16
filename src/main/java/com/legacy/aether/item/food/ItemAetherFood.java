package com.legacy.aether.item.food;

import net.minecraft.item.FoodItem;
import net.minecraft.item.ItemGroup;

public class ItemAetherFood extends FoodItem
{

	public ItemAetherFood(int healAmount, float saturationAmount)
	{
		this(new Settings().itemGroup(ItemGroup.FOOD), healAmount, saturationAmount);
	}

	public ItemAetherFood(Settings builder, int healAmount, float saturationAmount)
	{
		super(healAmount, saturationAmount, false, builder);
	}

}