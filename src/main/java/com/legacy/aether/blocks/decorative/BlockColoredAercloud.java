package com.legacy.aether.blocks.decorative;

import net.minecraft.util.DyeColor;

import com.legacy.aether.blocks.natural.aercloud.BlockAercloud;

public class BlockColoredAercloud extends BlockAercloud
{
	private DyeColor color;

	public BlockColoredAercloud(DyeColor color)
	{
		super(color);
		this.color = color;
	}

	public int getColor()
	{
		return this.color.method_16357();
	}

}