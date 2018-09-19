package com.legacy.aether.tileentity;

import java.util.Map;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.freezable.Freezable;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.container.BlockAetherContainer;
import com.legacy.aether.inventory.container.ContainerFreezer;

public class TileEntityFreezer extends AetherTileEntity
{

	public int progress, ticksRequired, powerRemaining;

	private NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);

	private Freezable freezable;

	public TileEntityFreezer() 
	{
		super("freezer", TileEntityTypesAether.FREEZER);
	}

	@Override
	public Container createContainer(InventoryPlayer inventoryIn, EntityPlayer playerIn)
	{
		return new ContainerFreezer(inventoryIn, this);
	}

	@Override
	public void tick()
	{
		boolean isActive = this.isFreezing();

		if (isActive)
		{
			--this.powerRemaining;

			if (this.world.getBlockState(this.getPos().down()).getBlock() == BlocksAether.icestone)
			{
				this.progress += 2.5F;
			}
			else
			{
				this.progress++;
			}
		}

		if (this.freezable != null)
		{
			if (this.progress >= this.ticksRequired && !this.world.isRemote)
			{
				ItemStack result = new ItemStack(this.freezable.getOutput());

				EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(this.getStackInSlot(0)), result);

				if (!this.getStackInSlot(2).isEmpty())
				{
					result.setCount(this.getStackInSlot(2).getCount() + 1);

					this.setInventorySlotContents(2, result);
				}
				else
				{
					this.setInventorySlotContents(2, result);
				}

				if (this.getStackInSlot(0).getItem().hasContainerItem())
				{
					this.setInventorySlotContents(0, new ItemStack(this.getStackInSlot(0).getItem().getContainerItem()));
				}
				else
				{
					this.decrStackSize(0, 1);
				}

				this.progress = 0;
			}

			if (this.getStackInSlot(0).isEmpty() || (!this.getStackInSlot(0).isEmpty() && AetherAPI.instance().getFreezable(this.getStackInSlot(0)) != this.freezable))
			{
				this.freezable = null;
				this.progress = 0;
			}

			if (this.powerRemaining <= 0)
			{
				if (!this.getStackInSlot(1).isEmpty() && AetherAPI.instance().isFreezerFuel(this.getStackInSlot(1)))
				{
					this.powerRemaining += AetherAPI.instance().getFreezableFuel(this.getStackInSlot(1)).getTimeGiven();

					if (!this.world.isRemote)
					{
						this.decrStackSize(1, 1);
					}
				}
				else
				{
					this.freezable = null;
					this.progress = 0;
				}
			}
		}
		else if (!this.getStackInSlot(0).isEmpty())
		{
			ItemStack itemstack = this.getStackInSlot(0);
			Freezable freezable = AetherAPI.instance().getFreezable(itemstack);

			if (freezable != null)
			{
				if (this.getStackInSlot(2).isEmpty() || (freezable.getOutput().asItem() == this.getStackInSlot(2).getItem()))
				{
					this.freezable = freezable;
					this.ticksRequired = this.freezable.getTimeRequired();
					this.addEnchantmentWeight(itemstack);
				}
			}
		}

		if (isActive != this.isFreezing())
		{
			this.markDirty();
			BlockAetherContainer.setState(this.world, this.pos, this.isFreezing());
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		this.progress = compound.getInteger("progress");
		this.powerRemaining = compound.getInteger("powerRemaining");
		this.ticksRequired = compound.getInteger("ticksRequired");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("progress", this.progress);
		compound.setInteger("powerRemaining", this.powerRemaining);
		compound.setInteger("ticksRequired", this.ticksRequired);

		return super.writeToNBT(compound);
	}

	@Override
	public void onSlotChanged(int index)
	{

	}

	public void addEnchantmentWeight(ItemStack stack)
	{
		Map<net.minecraft.enchantment.Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);

		if (!enchantments.isEmpty())
		{
			for (int levels : enchantments.values())
			{
				this.ticksRequired += (levels * 1250);
			}
		}
	}

	public boolean isFreezing()
	{
		return this.getField(1) > 0;
	}

	@Override
	public boolean isValidSlotItem(int index, ItemStack stack)
	{
		if (!stack.isEmpty())
		{
			if (AetherAPI.instance().isFreezable(stack))
			{
				return true;
			}
			else return index == 1 && AetherAPI.instance().isFreezerFuel(stack);
		}

		return false;
	}

	@Override
	public int getField(int id)
	{
		if (id == 0)
		{
			return this.progress;
		}
		else if (id == 1)
		{
			return this.powerRemaining;
		}
		else if (id == 2)
		{
			return this.ticksRequired;
		}

		return 0; 
	}

	@Override
	public void setField(int id, int value) 
	{ 
		if (id == 0)
		{
			this.progress = value;
		}
		else if (id == 1)
		{
			this.powerRemaining = value;
		}
		else if (id == 2)
		{
			this.ticksRequired = value;
		}
	}

	@Override
	public int getFieldCount() 
	{
		return 3; 
	}

	@Override
	public NonNullList<ItemStack> getTileInventory() 
	{
		return this.stacks;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? new int[] {2} : new int[] {0, 1};
	}

}