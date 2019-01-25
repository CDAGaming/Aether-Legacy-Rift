package com.legacy.aether.player.abilities;

import net.minecraft.item.ItemStack;

import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.api.player.util.AetherAbility;
import com.legacy.aether.item.ItemsAether;

public class StepHeightAbility implements AetherAbility
{

	private boolean update;

	private final IPlayerAether playerAether;

	public StepHeightAbility(IPlayerAether playerAether)
	{
		this.playerAether = playerAether;
	}

	@Override
	public boolean shouldExecute()
	{
		boolean execute = this.playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.agility_cape));

		if (execute)
		{
			this.update = true;
			this.playerAether.getPlayer().stepHeight = 1.0F;
		}
		else if (this.update)
		{
			this.update = false;
			this.playerAether.getPlayer().stepHeight = 0.6F;
		}

		return execute;
	}

	@Override
	public void update()
	{

	}

}