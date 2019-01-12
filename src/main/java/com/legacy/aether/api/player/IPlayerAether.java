package com.legacy.aether.api.player;

import com.legacy.aether.api.player.util.AccessoryInventory;

import net.minecraft.entity.player.PlayerEntity;

public interface IPlayerAether
{

	public void setInPortal();

	public void inflictCure(int ticks);

	public void inflictPoison(int ticks);

	public int getShardsUsed();

	public void increaseHealth(int amount);

	public AccessoryInventory getAccessoryInventory();

	public PlayerEntity getPlayer();

}