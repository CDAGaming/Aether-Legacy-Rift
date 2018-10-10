package com.legacy.aether.blocks.decorative;

import net.minecraft.item.EnumDyeColor;

import com.legacy.aether.blocks.natural.aercloud.BlockAercloud;

public class BlockColoredAercloud extends BlockAercloud
{

	private EnumDyeColor color;

	public BlockColoredAercloud(EnumDyeColor color)
	{
		this.color = color;
	}

	public int getColor()
	{
		return this.color.func_196060_f();
	}

}