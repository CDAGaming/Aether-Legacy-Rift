package com.legacy.aether.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_4002;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleParameters;
import net.minecraft.particle.ParticleType;

@Mixin(ParticleManager.class)
public class MixinParticleManager
{

	@Inject(method = "registerDefaultFactories", at = @At("RETURN"))
	private void registerAetherFactories(CallbackInfo ci)
	{
		//this.method_18834(AetherParticleTypes.AETHER_PORTAL, ParticleAetherPortal.Factory::new);
	}

	@Shadow
	private <T extends ParticleParameters> void method_18834(ParticleType<T> particleType_1, @Coerce Object simpleFactory)
	{
		
	}

	@FunctionalInterface
	@Environment(EnvType.CLIENT)
	interface class_4091<T extends ParticleParameters>
	{
		ParticleFactory<T> create(class_4002 var1);
	}

}