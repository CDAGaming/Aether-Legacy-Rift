package com.legacy.aether.blocks.entity;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.dungeon.ItemDungeonKey;

import net.fabricmc.fabric.block.entity.ClientSerializable;
import net.minecraft.block.Block;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.client.network.packet.BlockEntityUpdateClientPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;

public class TreasureChestBlockEntity extends ChestBlockEntity implements ClientSerializable
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
	protected void method_11049()
	{
		Block block_1 = this.getCachedState().getBlock();

		this.world.addBlockAction(this.pos, block_1, 1, this.field_11928);
		this.world.updateNeighborsAlways(this.pos, block_1);
	}

	@Override
	public BlockEntityUpdateClientPacket toUpdatePacket()
	{
		return new BlockEntityUpdateClientPacket(this.getPos(), 127, this.toClientTag(new CompoundTag()));
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