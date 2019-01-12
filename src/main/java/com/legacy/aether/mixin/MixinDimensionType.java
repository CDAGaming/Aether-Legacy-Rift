package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.legacy.aether.world.dimension.AetherDimensionType;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;

@Mixin(DimensionType.class)
public class MixinDimensionType
{

	@SuppressWarnings("unused")
	private static final DimensionType THE_AETHER = Registry.set(Registry.DIMENSION, 4, "the_aether", new AetherDimensionType());

}