package com.legacy.aether.player.abilities;

import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.api.player.util.AetherAbility;

public class JumpBoostAbility implements AetherAbility
{

	private boolean boosted;

	private final IPlayerAether playerAether;

	public JumpBoostAbility(IPlayerAether playerAether)
	{
		this.playerAether = playerAether;
	}

	@Override
	public boolean shouldExecute()
	{
		return this.playerAether.getAccessoryInventory().isWearingGravititeSet();
	}

	@Override
	public void update()
	{
		if (this.playerAether.isJumping() && !this.boosted)
		{
			this.boosted = true;

			this.playerAether.getPlayer().setVelocity(this.playerAether.getPlayer().getVelocity().add(0.0D, 1.0D, 0.0D));
		}
		else if (this.playerAether.getPlayer().onGround)
		{
			this.boosted = false;
		}
	}

	@Override
	public boolean disableFallDamage()
	{
		return true;
	}

}