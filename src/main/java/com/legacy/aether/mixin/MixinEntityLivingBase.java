package com.legacy.aether.mixin;

import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.player.IEntityPlayerAether;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase
{

    @Inject(method = "setJumping", at = @At("RETURN"))
	public void checkJumping(boolean isJumping, CallbackInfo ci)
	{
		EntityLivingBase entity = (EntityLivingBase) (Object) this;

		if (entity instanceof IEntityPlayerAether)
		{
			((IEntityPlayerAether)entity).getPlayerAether().setJumping(isJumping);
		}
	}

}