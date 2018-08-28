package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.entities.block.EntityFloatingBlock;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;

@Mixin(EntityTracker.class)
public class MixinEntityTracker 
{

	@Inject(method = "track", at = @At("RETURN"))
	public void trackAetherEntity(Entity entityIn, CallbackInfo ci)
	{
		if (entityIn instanceof EntityFloatingBlock)
		{
			((EntityTracker) (Object) this).track(entityIn, 160, 20, true);
		}
	}

}