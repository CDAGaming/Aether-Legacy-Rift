package com.legacy.aether.blocks.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.Block;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.client.network.packet.BlockEntityUpdateS2CPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.dungeon.ItemDungeonKey;

public class TreasureChestBlockEntity extends ChestBlockEntity implements BlockEntityClientSerializable
{

	private boolean locked = true;

	private int type = 0;

	public boolean unlockTreasure(PlayerEntity player, Hand hand)
	{
		ItemStack heldItem = player.getStackInHand(hand);

		if (heldItem.getItem() instanceof ItemDungeonKey)
		{
			this.locked = false;
			this.type = heldItem.getItem() == ItemsAether.bronze_key ? 0 : heldItem.getItem() == ItemsAether.silver_key ? 1 : heldItem.getItem() == ItemsAether.golden_key ? 2 : 3;
		}

		return !this.locked;
	}

	public int getDungeonType()
	{
		return this.type;
	}

	public boolean isTreasureLocked()
	{
		return this.locked;
	}

	@Override
	public void onInvOpen(PlayerEntity playerIn)
	{
		super.onInvOpen(playerIn);

		if (playerIn instanceof ServerPlayerEntity)
		{
			((ServerPlayerEntity)playerIn).networkHandler.sendPacket(this.toUpdatePacket());
		}
	}

	@Override
	protected void onInvOpenOrClose()
	{
		Block block_1 = this.getCachedState().getBlock();

		this.world.addBlockAction(this.pos, block_1, 1, this.viewerCount);
		this.world.updateNeighborsAlways(this.pos, block_1);
	}

	@Override
	public BlockEntityUpdateS2CPacket toUpdatePacket()
	{
		return new BlockEntityUpdateS2CPacket(this.getPos(), 127, this.toClientTag(new CompoundTag()));
	}

	@Override
	public CompoundTag toClientTag(CompoundTag compound)
	{
		return this.toTag(compound);
	}

	@Override
	public void fromClientTag(CompoundTag compound)
	{
		this.fromTag(compound);
	}

	@Override
	public CompoundTag toTag(CompoundTag compound)
	{
		compound.putInt("dungeonType", this.type);
		compound.putBoolean("locked", this.locked);

		return super.toTag(compound);
	}

	@Override
	public void fromTag(CompoundTag compound)
	{
		super.fromTag(compound);

		this.type = compound.getInt("dungeonType");
		this.locked = compound.getBoolean("locked");
	}

}