package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;

import com.legacy.aether.world.gen.chunk.IChunkGeneratorHook;

@Mixin(SurfaceChunkGenerator.class)
public class MixinSurfaceChunkGenerator implements IChunkGeneratorHook
{

	@Override
	public double hookSampleNoise(int int_1, int int_2, int int_3, double double_1, double double_2, double double_3, double double_4)
	{
		return this.sampleNoise(int_1, int_2, int_3, double_1, double_2, double_3, double_4);
	}

	@Shadow
	private double sampleNoise(int int_1, int int_2, int int_3, double double_1, double double_2, double double_3, double double_4)
	{
		return 0.0D;
	}

}