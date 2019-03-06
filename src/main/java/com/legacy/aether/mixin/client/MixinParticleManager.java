package com.legacy.aether.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.client.particle.ParticleAetherPortal;
import com.legacy.aether.client.particle.ParticleFactories;
import com.legacy.aether.particle.AetherParticleTypes;

import net.minecraft.client.particle.ParticleManager;

@Mixin(ParticleManager.class)
public class MixinParticleManager
{

	@Inject(method = "registerDefaultFactories", at = @At("TAIL"))
	private void registerAetherFactories(CallbackInfo ci)
	{
		ParticleFactories.register((ParticleManager) (Object) this, AetherParticleTypes.AETHER_PORTAL, ParticleAetherPortal.Factory::new); 
	}

}