package com.legacy.aether.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.INameable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;

public abstract class AetherTileEntity extends TileEntity implements IInteractionObject, ISidedInventory, ITickable, INameable
{

	private String name = "generic";

	private ITextComponent customTileName;

	public AetherTileEntity(String name, TileEntityType<?> tileType) 
	{
		super(tileType);

		this.name = name;
	}

	public abstract NonNullList<ItemStack> getTileInventory();

	@Override
	public String getGuiID()
	{
		return "aether_legacy:" + this.name;
	}

	@Override
    public ITextComponent getName()
    {
        return (this.hasCustomName() ? this.getCustomName() : new TextComponentTranslation("container.aether_legacy." + this.name));
    }

	@Override
	public boolean hasCustomName() 
	{
		return this.customTileName != null;
	}

	public void setCustomName(ITextComponent textIn)
	{
		this.customTileName = textIn;
	}

	@Override
	public ITextComponent getCustomName()
	{
		return this.customTileName;
	}

	@Override
	public int getSizeInventory()
	{
		return this.getTileInventory().size();
	}

	@Override
	public ItemStack getStackInSlot(int index) 
	{
		return this.getTileInventory().get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		return ItemStackHelper.getAndSplit(this.getTileInventory(), index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(this.getTileInventory(), index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		boolean isClean = !stack.isEmpty() && stack.isItemEqualIgnoreDurability(this.getStackInSlot(index)) && ItemStack.areItemStackTagsEqual(stack, this.getStackInSlot(index));

		this.getTileInventory().set(index, stack);

		if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
		{
			stack.setCount(this.getInventoryStackLimit());
		}

		if (!isClean)
		{
			this.onSlotChanged(index);

			this.markDirty();
		}
	}

	public abstract void onSlotChanged(int index);

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		ItemStackHelper.loadAllItems(compound, this.getTileInventory());

        if (compound.hasKey("name", 8))
        {
            this.customTileName = ITextComponent.Serializer.jsonToComponent(compound.getString("name"));
        }

		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		ItemStackHelper.saveAllItems(compound, this.getTileInventory());

        if (this.hasCustomName())
        {
        	compound.setString("name", ITextComponent.Serializer.componentToJson(this.customTileName));
        }

		return super.writeToNBT(compound);
	}

	@Override
	public boolean isEmpty()
	{
        for (ItemStack itemstack : this.getTileInventory())
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
	}

	@Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

	public abstract boolean isValidSlotItem(int index, ItemStack stack);

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return this.isValidSlotItem(index, stack);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
		return this.isValidSlotItem(index, stack);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction)
	{
		return true;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void clear()
	{
		this.getTileInventory().clear();
	}

	@Override
	public void openInventory(EntityPlayer player)
	{

	}

	@Override
	public void closeInventory(EntityPlayer player)
	{

	}

}