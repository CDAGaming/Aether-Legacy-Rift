package com.legacy.aether.api.player.util;

public interface AetherAbility
{

	public boolean shouldExecute();

	public void update();

	public default boolean disableFallDamage()
	{
		return false;
	}

}