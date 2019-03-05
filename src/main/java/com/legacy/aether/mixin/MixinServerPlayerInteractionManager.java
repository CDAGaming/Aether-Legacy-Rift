package com.legacy.aether.mixin;

import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.world.GameMode;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.legacy.aether.api.player.util.PlayerReach;

@Mixin(ServerPlayerInteractionManager.class)
public class MixinServerPlayerInteractionManager implements PlayerReach
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

	@Override
	public float getReachDistance()
	{
		return this.gameMode.isCreative() ? this.creativeReachDistance : this.survivalReachDistance;
	}

}