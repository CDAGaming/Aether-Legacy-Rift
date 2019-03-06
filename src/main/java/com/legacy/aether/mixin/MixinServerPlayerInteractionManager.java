package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.player.util.ServerPlayerReach;

import net.minecraft.server.network.ServerPlayerInteractionManager;

@Mixin(ServerPlayerInteractionManager.class)
public class MixinServerPlayerInteractionManager implements ServerPlayerReach
{

	@Override
	public float getReachDistance()
	{
		return AetherAPI.get(((ServerPlayerInteractionManager)(Object)this).player).setReachDistance(4.5F);
	}

}