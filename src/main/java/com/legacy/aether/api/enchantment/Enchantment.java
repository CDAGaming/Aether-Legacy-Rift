package com.legacy.aether.api.enchantment;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

public class Enchantment 
{

	private int timeRequired;

	private IItemProvider input;

	private IItemProvider output;

	public Enchantment(IItemProvider input, IItemProvider output, int timeRequired)
	{
		this.input = input;
		this.output = output;
		this.timeRequired = timeRequired;
	}

	public int getTimeRequired()
	{
		return this.timeRequired;
	}

	public Item getInput()
	{
		return this.input.getItem();
	}

	public Item getOutput()
	{
		return this.output.getItem();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Enchantment)
		{
			Enchantment enchantment = (Enchantment) obj;

			boolean inputCheck = this.getInput().getItem() == enchantment.getInput().getItem();
			boolean outputCheck = this.getOutput().getItem() == enchantment.getOutput().getItem();

			return inputCheck && outputCheck;
		}

		return false;
	}

}