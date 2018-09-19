package com.legacy.aether.blocks.container;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import com.legacy.aether.tileentity.TileEntityFreezer;

public class BlockFreezer extends BlockAetherContainer
{

	public BlockFreezer()
	{
		super(Builder.create(Material.ROCK).hardnessAndResistance(2.0F, -1.0F));
	}

	@Override
    public boolean onBlockActivated(IBlockState stateIn, World worldIn, BlockPos posIn, EntityPlayer playerIn, EnumHand handIn, EnumFacing facingIn, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tile = worldIn.getTileEntity(posIn);

            if (tile instanceof TileEntityFreezer)
            {
            	playerIn.displayGUIChest((TileEntityFreezer)tile);
            }

            return true;
        }
    }

	@Override
	public void animateTick(IBlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
    {
		if(stateIn.get(POWERED))
		{
			float f = (float)posIn.getX() + 0.5F;
			float f1 = (float)posIn.getY() + 1.0F + (randIn.nextFloat() * 6F) / 16F;
			float f2 = (float)posIn.getZ() + 0.5F;

			worldIn.addParticle(Particles.SMOKE, true, f, f1, f2, 0.0D, 0.0D, 0.0D);
			worldIn.addParticle(Particles.CLOUD, true, f, f1, f2, 0.0D, 0.0D, 0.0D);
			
			if (randIn.nextDouble() < 0.1D)
            {
				worldIn.playSound((double)posIn.getX() + 0.5D, (double)posIn.getY(), (double)posIn.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
		}
    }

	@Override
    public void onReplaced(IBlockState previousStateIn, World worldIn, BlockPos posIn, IBlockState newStateIn, boolean isDirty)
    {
        if (previousStateIn.getBlock() != newStateIn.getBlock())
        {
            TileEntity tileentity = worldIn.getTileEntity(posIn);

            if (tileentity instanceof TileEntityFreezer)
            {
                InventoryHelper.dropInventoryItems(worldIn, posIn, (TileEntityFreezer)tileentity);
                worldIn.updateComparatorOutputLevel(posIn, this);
            }

            super.onReplaced(previousStateIn, worldIn, posIn, newStateIn, isDirty);
        }
    }

	@Override
	public TileEntity createNewTileEntity(IBlockReader readerIn)
	{
		return new TileEntityFreezer();
	}

}