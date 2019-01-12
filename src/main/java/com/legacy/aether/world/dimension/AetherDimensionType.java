package com.legacy.aether.world.dimension;

import net.minecraft.world.dimension.DimensionType;

public class AetherDimensionType extends DimensionType
{

	public AetherDimensionType()
	{
		super(4, "_aether", "Dim-Aether", TheAetherDimension::new, true);
	}

}