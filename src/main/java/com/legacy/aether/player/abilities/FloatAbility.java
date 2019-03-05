package com.legacy.aether.player.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.api.player.util.AetherAbility;
import com.legacy.aether.item.ItemsAether;

public class FloatAbility implements AetherAbility
{

	private final IPlayerAether playerAether;

	public FloatAbility(IPlayerAether playerAether)
	{
		this.playerAether = playerAether;
	}

	@Override
	public boolean shouldExecute()
	{
		PlayerEntity player = this.playerAether.getPlayer();

		return this.playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.golden_feather)) && !player.isFallFlying() && player.getVelocity().y < 0.0D && !player.onGround && !player.isInsideWater() && !player.isSneaking();
	}

	@Override
	public void update()
	{
		this.playerAether.getPlayer().setVelocity(this.playerAether.getPlayer().getVelocity().multiply(1.0D, 0.59D, 1.0D));
	}

	@Override
	public boolean disableFallDamage()
	{
		return true;
	}

}