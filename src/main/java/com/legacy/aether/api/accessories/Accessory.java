package com.legacy.aether.api.accessories;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Accessory 
{

	private ItemStack accessoryStack;

	private AccessoryType accessoryType;

	public Accessory(Block item, AccessoryType type)
	{
		this(new ItemStack(item), type);
	}

	public Accessory(Item item, AccessoryType type)
	{
		this(new ItemStack(item), type);
	}

	public Accessory(ItemStack stack, AccessoryType type)
	{
		this.accessoryType = type;
		this.accessoryStack = stack;
	}

	public AccessoryType getType()
	{
		return this.accessoryType;
	}

	public ItemStack getStack()
	{
		return this.accessoryStack;
	}

}