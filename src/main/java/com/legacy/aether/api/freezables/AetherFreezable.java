package com.legacy.aether.api.freezables;

import net.minecraft.item.ItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AetherFreezable
{

	private Identifier registryName;

	public int timeRequired;

	public ItemStack input, output;

	public AetherFreezable(ItemStack input, ItemProvider output, int timeRequired)
	{
		this(input, new ItemStack(output), timeRequired);
	}

	public AetherFreezable(ItemProvider input, ItemStack output, int timeRequired)
	{
		this(new ItemStack(input), output, timeRequired);
	}

	public AetherFreezable(ItemProvider input, ItemProvider output, int timeRequired)
	{
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public AetherFreezable(ItemStack input, ItemStack output, int timeRequired)
	{
		this.input = input;
		this.output = output;
		this.timeRequired = timeRequired;

		this.registryName = Registry.ITEM.getId(input.getItem());
	}

	public Identifier getRegistryName()
	{
		return this.registryName;
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
		if (obj instanceof AetherFreezable)
		{
			AetherFreezable freezable = (AetherFreezable) obj;

			boolean inputCheck = this.getInput().getItem() == freezable.getInput().getItem();
			boolean outputCheck = this.getOutput().getItem() == freezable.getOutput().getItem();

			return inputCheck && outputCheck;
		}

		return false;
	}

}