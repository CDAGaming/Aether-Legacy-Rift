package com.legacy.aether.mixin.client;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.level.LevelProperties;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.world.WorldAether;

@Mixin(World.class)
public class MixinWorld
{

	@Shadow @Final public Dimension dimension;

	@Shadow @Final protected LevelProperties properties;

	@Inject(method = "getHorizonHeight", at = @At("RETURN"), cancellable = true)
	public void getAetherColorCutoff(CallbackInfoReturnable<Double> distance)
	{
		if (this.dimension.getType() == WorldAether.THE_AETHER)
		{
			double newDistance = -256.0D;

			distance.setReturnValue(newDistance);
		}
	}

}