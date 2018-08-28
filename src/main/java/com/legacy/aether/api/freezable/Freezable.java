package com.legacy.aether.api.freezable;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

public class Freezable
{

	private int timeRequired;

	private IItemProvider input;

	private IItemProvider output;

	public Freezable(IItemProvider input, IItemProvider output, int timeRequired)
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
		if (obj instanceof Freezable)
		{
			Freezable freezable = (Freezable) obj;

			boolean inputCheck = this.getInput().getItem() == freezable.getInput().getItem();
			boolean outputCheck = this.getOutput().getItem() == freezable.getOutput().getItem();

			return inputCheck && outputCheck;
		}

		return false;
	}

}