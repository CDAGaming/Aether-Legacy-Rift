package com.legacy.aether.api.freezable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Freezable
{

	public int timeRequired;

	public ItemStack input, output;

	public Freezable(ItemStack input, Block output, int timeRequired)
	{
		this(input, new ItemStack(output), timeRequired);
	}

	public Freezable(Block input, ItemStack output, int timeRequired)
	{
		this(new ItemStack(input), output, timeRequired);
	}

	public Freezable(Block input, Block output, int timeRequired)
	{
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public Freezable(ItemStack input, Item output, int timeRequired)
	{
		this(input, new ItemStack(output), timeRequired);
	}

	public Freezable(Item input, ItemStack output, int timeRequired)
	{
		this(new ItemStack(input), output, timeRequired);
	}

	public Freezable(Item input, Item output, int timeRequired)
	{
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public Freezable(Block input, Item output, int timeRequired)
	{
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public Freezable(Item input, Block output, int timeRequired)
	{
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public Freezable(Item result, int timeRequired)
	{
		this(new ItemStack(result), new ItemStack(result), timeRequired);
	}

	public Freezable(ItemStack input, ItemStack output, int timeRequired)
	{
		this.input = input;
		this.output = output;
		this.timeRequired = timeRequired;
	}

	public int getTimeRequired()
	{
		return this.timeRequired;
	}

	public ItemStack getInput()
	{
		return this.input;
	}

	public ItemStack getOutput()
	{
		return this.output;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Freezable)
		{
			Freezable freezable = (Freezable) obj;

			boolean inputCheck = this.getInput().getItem() == freezable.getInput().getItem() && this.getInput().getItemDamage() == freezable.getInput().getItemDamage();
			boolean outputCheck = this.getOutput().getItem() == freezable.getOutput().getItem() && this.getOutput().getItemDamage() == freezable.getOutput().getItemDamage();

			return inputCheck && outputCheck;
		}

		return false;
	}

}