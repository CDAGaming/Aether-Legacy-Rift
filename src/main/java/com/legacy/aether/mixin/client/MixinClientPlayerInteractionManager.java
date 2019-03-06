package com.legacy.aether.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.api.AetherAPI;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientPlayerInteractionManager
{

	@Shadow @Final private MinecraftClient client;

	@Inject(method = "getReachDistance", at = @At("RETURN"), cancellable = true)
	public void setAetherReachDistance(CallbackInfoReturnable<Float> ci)
	{
		if (this.client.player != null)
		{
			float original = ci.getReturnValue();

			ci.setReturnValue(AetherAPI.get(this.client.player).setReachDistance(original));
		}
	}

}