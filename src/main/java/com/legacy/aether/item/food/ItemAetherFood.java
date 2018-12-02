package com.legacy.aether.item.food;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;

public class ItemAetherFood extends ItemFood
{

	public ItemAetherFood(int healAmount, float saturationAmount)
	{
		this(new Properties().group(ItemGroup.FOOD), healAmount, saturationAmount);
	}

	public ItemAetherFood(Properties builder, int healAmount, float saturationAmount)
	{
		super(healAmount, saturationAmount, false, builder);
	}

}