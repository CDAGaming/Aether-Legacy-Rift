package com.legacy.aether.mixin.client;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.level.LevelGeneratorType;
import net.minecraft.world.level.LevelProperties;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.legacy.aether.world.WorldAether;

@Mixin(World.class)
public class MixinWorld
{

	@Shadow @Final public Dimension dimension;

	@Shadow @Final protected LevelProperties properties;

	/*@Inject(method = "method_8540", at = @At("RETURN"), cancellable = true)
	public void getAetherColorCutoff(CallbackInfoReturnable<?> distance)
	{
		System.out.println(this.dimension);

		if (this.dimension.getType() == WorldAether.THE_AETHER)
		{
			double newDistance = 0.0D;

			distance.setReturnValue(newDistance);
		}
	}*/

	@Overwrite
	public double method_8540()
	{
		if (this.dimension.getType() == WorldAether.THE_AETHER)
		{
			return -256.0D;
		}

		return this.properties.getGeneratorType() == LevelGeneratorType.FLAT ? 0.0D : 63.0D;
	}
}