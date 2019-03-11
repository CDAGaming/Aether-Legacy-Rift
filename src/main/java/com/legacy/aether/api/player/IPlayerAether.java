package com.legacy.aether.api.player;

import com.legacy.aether.api.player.util.AccessoryInventory;

import net.minecraft.entity.player.PlayerEntity;

public interface IPlayerAether
{

	public boolean disableFallDamage();

	public float setReachDistance(float distance);

	public void setInPortal();

	public void inflictCure(int ticks);

	public void inflictPoison(int ticks);

	public int getShardsUsed();

	public void increaseHealth(int amount);

	public boolean isJumping();

	public AccessoryInventory getAccessoryInventory();

	public PlayerEntity getPlayer();

}