package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.player.IEntityPlayerAether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

@Mixin(EntityPlayerMP.class)
public class MixinEntityPlayerMP 
{

    @Inject(method = "copyFrom", at = @At("RETURN"))
	public void copyAetherDataFrom(EntityPlayerMP that, boolean keepEverything, CallbackInfo ci)
	{
		EntityPlayer entity = (EntityPlayerMP) (Object) this;

		if (entity instanceof IEntityPlayerAether && that instanceof IEntityPlayerAether)
		{
			((IEntityPlayerAether) entity).getPlayerAether().copyFrom(((IEntityPlayerAether) that).getPlayerAether(), keepEverything);
		}
	}
}