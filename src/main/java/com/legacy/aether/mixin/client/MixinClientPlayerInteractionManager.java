package com.legacy.aether.mixin.client;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.world.GameMode;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.legacy.aether.api.player.util.PlayerReach;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientPlayerInteractionManager implements PlayerReach
{

	private float survivalReachDistance = 4.5F;

	private float creativeReachDistance = 5.0F;

	@Shadow private GameMode gameMode;

	@Override
	public void setReachDistance(float survivalReach, float creativeReach)
	{
		this.survivalReachDistance = survivalReach;
		this.creativeReachDistance = creativeReach;
	}

	@Overwrite
	public float getReachDistance()
	{
		return this.gameMode.isCreative() ? this.creativeReachDistance : this.survivalReachDistance;
	}

}