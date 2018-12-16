package com.legacy.aether.blocks.container;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.legacy.aether.tileentity.TileEntityFreezer;

public class BlockFreezer extends BlockAetherContainer
{

	public BlockFreezer()
	{
		super(Settings.of(Material.EARTH).strength(2.0F, -1.0F));
	}

	@Override
    public boolean activate(BlockState stateIn, World worldIn, BlockPos posIn, PlayerEntity playerIn, Hand handIn, Direction facingIn, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            BlockEntity tile = worldIn.getBlockEntity(posIn);

            if (tile instanceof TileEntityFreezer)
            {
            	playerIn.displayGUIChest((TileEntityFreezer)tile);
            }

            return true;
        }
    }

	@Override
	public void randomTick(BlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
    {
		if(stateIn.get(POWERED))
		{
			float f = (float)posIn.getX() + 0.5F;
			float f1 = (float)posIn.getY() + 1.0F + (randIn.nextFloat() * 6F) / 16F;
			float f2 = (float)posIn.getZ() + 0.5F;

			// TODO: Verify in 1.14
			worldIn.method_8406(ParticleTypes.SMOKE, f, f1, f2, 0.0D, 0.0D, 0.0D);
			worldIn.method_8406(ParticleTypes.CLOUD, f, f1, f2, 0.0D, 0.0D, 0.0D);
			
			if (randIn.nextDouble() < 0.1D)
            {
				worldIn.playSound((double)posIn.getX() + 0.5D, (double)posIn.getY(), (double)posIn.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCK, 1.0F, 1.0F, false);
            }
		}
    }

	@Override
    public void onBlockRemoved(BlockState previousStateIn, World worldIn, BlockPos posIn, BlockState newStateIn, boolean isDirty)
    {
        if (previousStateIn.getBlock() != newStateIn.getBlock())
        {
            BlockEntity tileentity = worldIn.getBlockEntity(posIn);

            if (tileentity instanceof TileEntityFreezer)
            {
                InventoryHelper.dropInventoryItems(worldIn, posIn, (TileEntityFreezer)tileentity);
                worldIn.updateComparatorOutputLevel(posIn, this);
            }

            super.onBlockRemoved(previousStateIn, worldIn, posIn, newStateIn, isDirty);
        }
    }

	@Override
	public BlockEntity createBlockEntity(BlockView readerIn)
	{
		return new TileEntityFreezer();
	}

}