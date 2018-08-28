package com.legacy.aether.api.accessories;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

public class Accessory 
{

	private IItemProvider item;

	private AccessoryType type;

	public Accessory(AccessoryType type, IItemProvider item)
	{
		this.type = type;
		this.item = item;
	}

	public AccessoryType getType()
	{
		return this.type;
	}

	public Item getItem()
	{
		return this.item.getItem();
	}

}