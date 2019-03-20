package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.legacy.aether.util.PlayerTeleportationData;

import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity implements PlayerTeleportationData
{

	@Shadow private float field_13997;

	@Shadow private int field_13978;

	@Shadow private int field_13979;

	@Shadow private boolean inTeleportationState;

	@Override
	public void setPlayerTeleporting()
	{
		this.inTeleportationState = true;
	}

	@Override
	public void afterTeleportation()
	{
		this.field_13978 = -1;
		this.field_13997 = -1.0F;
		this.field_13979 = -1;
	}

}