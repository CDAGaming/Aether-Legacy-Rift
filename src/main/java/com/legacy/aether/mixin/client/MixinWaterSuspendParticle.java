package com.legacy.aether.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.WaterSuspendParticle;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.world.WorldAether;

@Mixin(WaterSuspendParticle.class)
public abstract class MixinWaterSuspendParticle extends Particle
{

	public MixinWaterSuspendParticle(World world_1, double double_1, double double_2, double double_3, double double_4, double double_5, double double_6)
	{
		super(world_1, double_1, double_2, double_3, double_4, double_5, double_6);
	}

	@Inject(method = "<init>", at = @At("RETURN"))
	public void setAetherWaterColor(CallbackInfo ci)
	{
		MinecraftClient client = MinecraftClient.getInstance();

		if (client.player != null && client.player.dimension == WorldAether.THE_AETHER)
		{
			this.colorRed = 0.463F;
			this.colorGreen = 0.675F;;
			this.colorBlue = 0.698F;
		}
	}

}