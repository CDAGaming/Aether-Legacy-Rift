package com.legacy.aether.player;

import com.legacy.aether.inventory.InventoryAccessories;
import com.legacy.aether.util.AetherTeleportation;
import com.legacy.aether.world.TeleporterAether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class PlayerAether
{

	private EntityPlayer player;

	private boolean isJumping;

	private InventoryAccessories accessories;

	public PlayerAether(EntityPlayer player)
	{
		this.player = player;
		this.accessories = new InventoryAccessories((IPlayerAether) player);
	}

	public void onUpdate()
	{
		boolean shouldTeleport = false;//this.player.isSneaking();

		if (shouldTeleport)
		{
			if (!this.player.world.isRemote)
			{
				MinecraftServer server = this.player.getServer();
				int dimensionToTravel = this.player.dimension == 12 ? 0 : 12;

				if (server != null && server.getPlayerList() != null)
				{
					AetherTeleportation.travelDimension((EntityPlayerMP) this.player, dimensionToTravel, new TeleporterAether(true, server.getWorld(dimensionToTravel)));
				}
			}

			this.player.setSneaking(false);
		}
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		
	}

	public void damageAccessories(float accessoryDamage)
	{
		
	}

	public void setJumping(boolean isJumping)
	{
		this.isJumping = isJumping;
	}

	public boolean isJumping()
	{
		return this.isJumping;
	}

	public InventoryAccessories getAccessories()
	{
		return this.accessories;
	}

	public EntityPlayer getPlayer()
	{
		return this.player;
	}

}