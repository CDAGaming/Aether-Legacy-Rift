package com.legacy.aether.blocks.dungeon;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDungeonTrap extends BlockDungeon
{

	private IBlockState placeState;

	public BlockDungeonTrap(IBlockState state)
	{
		super(true);

		this.placeState = state;
	}

	@Override
    public void onEntityWalk(World world, BlockPos pos, Entity entityIn)
    {
    	if (entityIn instanceof EntityPlayer)
    	{
    		Block block = this.placeState.getBlock();
 
        	world.setBlockState(pos, this.placeState);
        	world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FENCE_GATE_CLOSE, SoundCategory.PLAYERS, 1.0F, 1.5F);

        	if (world.isRemote)
        	{
        		return;
        	}

        	if (block == BlocksAether.carved_stone || block == BlocksAether.sentry_stone)
        	{
        		//EntitySentry sentry = new EntitySentry(world, pos.getX() + 2D, pos.getY() + 1D, pos.getZ() + 2D);

        		//world.spawnEntity(sentry);
        	}
        	else if (block == BlocksAether.angelic_stone || block == BlocksAether.light_angelic_stone)
        	{
        		//EntityValkyrie valkyrie = new EntityValkyrie(world);
        		//valkyrie.setPosition(pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D);

        		//world.spawnEntity(valkyrie);
        	}
        	else if (block == BlocksAether.hellfire_stone || block == BlocksAether.light_hellfire_stone)
        	{
        		//EntityFireMinion minion = new EntityFireMinion(world);
        		//minion.setPosition(pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D);

        		//world.spawnEntity(minion);
        	}
    	}
    }

}