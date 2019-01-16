package com.legacy.aether.api.enchantments;

import net.minecraft.item.ItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AetherEnchantment
{

	private Identifier registryName;

	public int timeRequired;

	public ItemStack input, output;

	public AetherEnchantment(ItemProvider input, int timeRequired)
	{
		this(input, new ItemStack(input), timeRequired);
	}

	public AetherEnchantment(ItemStack input, ItemProvider output, int timeRequired)
	{
		this(input, new ItemStack(output), timeRequired);
	}

	public AetherEnchantment(ItemProvider input, ItemStack output, int timeRequired)
	{
		this(new ItemStack(input), output, timeRequired);
	}

	public AetherEnchantment(ItemProvider input, ItemProvider output, int timeRequired)
	{
		this(new ItemStack(input), new ItemStack(output), timeRequired);
	}

	public AetherEnchantment(ItemStack input, ItemStack output, int timeRequired)
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
		if (obj instanceof AetherEnchantment)
		{
			AetherEnchantment freezable = (AetherEnchantment) obj;

			boolean inputCheck = this.getInput().getItem() == freezable.getInput().getItem();
			boolean outputCheck = this.getOutput().getItem() == freezable.getOutput().getItem();

			return inputCheck && outputCheck;
		}

		return false;
	}

}