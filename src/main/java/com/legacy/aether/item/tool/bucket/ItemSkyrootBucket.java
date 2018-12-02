package com.legacy.aether.item.tool.bucket;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import com.legacy.aether.item.ItemsAether;

public class ItemSkyrootBucket extends Item
{

	public ItemSkyrootBucket()
	{
		super(new Properties().maxStackSize(16).group(ItemGroup.TOOLS));
	}

	public ItemSkyrootBucket(Item containerIn)
	{
		super(new Properties().maxStackSize(1).group(ItemGroup.TOOLS).containerItem(containerIn));
	}

	@Override
    public EnumRarity getRarity(ItemStack stack)
    {
    	return stack.getItem() == ItemsAether.skyroot_bucket ? EnumRarity.RARE : super.getRarity(stack);
    }

}