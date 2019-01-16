package com.legacy.aether.blocks.entity;

import java.util.UUID;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.Direction;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.container.BlockWithAetherEntity;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.item.ItemMoaEgg;
import com.legacy.aether.item.ItemsAether;

public class IncubatorBlockEntity extends AetherBlockEntity
{

	public String ownerName;

	public UUID ownerUUID;

	public int progress;

	public int powerRemaining;

	public int ticksRequired = 5700;

	private DefaultedList<ItemStack> incubatorItemStacks = DefaultedList.create(2, ItemStack.EMPTY);

	public IncubatorBlockEntity()
	{
		super("incubator", BlockEntitiesAether.INCUBATOR);
	}

	@Override
	public void tick()
	{
		boolean flag = this.isIncubating();

		if (this.powerRemaining > 0)
		{
			this.powerRemaining--;

			if (!this.getInvStack(1).isEmpty())
			{
				if (this.world.getBlockState(this.getPos().down()).getBlock() == Blocks.MAGMA_BLOCK)
				{
					this.progress += 2F;
				}
				else
				{
					this.progress++;
				}
			}
		}

		if (this.progress >= this.ticksRequired)
		{
			if (this.getInvStack(1).getItem() instanceof ItemMoaEgg)
			{
				ItemMoaEgg moaEgg = (ItemMoaEgg) this.getInvStack(1).getItem();

				if (!this.world.isClient)
				{
					EntityMoa moa = new EntityMoa(this.world);

					moa.setPlayerGrown(true);
					moa.setBreedingAge(-24000);
					moa.setMoaType(moaEgg.getMoaType(this.getInvStack(1)));

					for (int safeY = 0; !this.world.isAir(this.pos.up(safeY)); safeY++)
					{
						moa.setPositionAnglesAndUpdate(this.pos.getX() + 0.5D, this.pos.getY() + safeY + 1.5D, this.pos.getZ() + 0.5D, 0.0F, 0.0F);
					}

					this.world.spawnEntity(moa);
				}
			}

			if (!this.world.isClient)
			{
				this.takeInvStack(1, 1);
			}

			this.progress = 0;
		}

		if (this.powerRemaining <= 0)
		{
			if (this.getInvStack(1).getItem() == ItemsAether.moa_egg && this.getInvStack(0).getItem() == Item.getItemFromBlock(BlocksAether.ambrosium_torch))
			{
				this.powerRemaining += 1000;

				if (!this.world.isClient)
				{
					this.takeInvStack(0, 1);
				}
			}
			else
			{
				this.powerRemaining = 0;
				this.progress = 0;
			}
		}

		if (flag != this.isIncubating())
		{
			this.markDirty();
			BlockWithAetherEntity.setState(this.world, this.pos, this.isIncubating());
		}
	}

	@Override
	public void onInvOpen(PlayerEntity playerIn)
	{
		if (this.ownerUUID == null)
		{
			this.ownerUUID = playerIn.getUuid();
			this.ownerName = playerIn.getName().getFormattedText();
		}
	}

	@Override
	public void onSlotChanged(int index) 
	{
		if (index == 1)
		{
			this.progress = 0;
		}
	}

	@Override
	public void fromTag(CompoundTag compound)
	{
		super.fromTag(compound);

		this.progress = compound.getInt("progress");
		this.powerRemaining = compound.getInt("powerRemaining");

		if (compound.containsKey("ownerUUID"))
		{
			this.ownerUUID = compound.getUuid("ownerUUID");
			this.ownerName = compound.getString("ownerName");
		}
	}

	@Override
	public CompoundTag toTag(CompoundTag compound)
	{
		compound.putInt("progress", this.progress);
		compound.putInt("powerRemaining", this.powerRemaining);

		if (this.ownerUUID != null)
		{
			compound.putUuid("ownerUUID", this.ownerUUID);
			compound.putString("ownerName", this.ownerName);
		}

		return super.toTag(compound);
	}

	public boolean isIncubating()
	{
		return this.getProperty(1) > 0;
	}

	@Override
	public boolean isValidInvStack(int index, ItemStack itemstack)
	{
		return (index == 0 && itemstack.getItem() == Item.getItemFromBlock(BlocksAether.ambrosium_torch) ? true : (index == 1 && itemstack.getItem() == ItemsAether.moa_egg));
	}

	@Override
	public int getProperty(int id)
	{
		if (id == 0)
		{
			return this.progress;
		}
		else if (id == 1)
		{
			return this.powerRemaining;
		}

		return 0; 
	}

	@Override
	public void setProperty(int id, int value) 
	{ 
		if (id == 0)
		{
			this.progress = value;
		}
		else if (id == 1)
		{
			this.powerRemaining = value;
		}
	}

	@Override
	public int getPropertySize() 
	{
		return 2; 
	}

	@Override
	public DefaultedList<ItemStack> getInventory()
	{
		return this.incubatorItemStacks;
	}

	@Override
	public int[] getInvAvailableSlots(Direction direction)
	{
		return direction == Direction.DOWN ? new int[] {} : new int[] {0, 1};
	}

}