package com.legacy.aether.blocks.natural.aercloud;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ShapeUtils;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAercloud extends Block
{

	public BlockAercloud() 
	{
		super(Builder.create(Material.ICE).hardnessAndResistance(0.2F, -1.0F).sound(SoundType.CLOTH));
	}

	@Override
	public void onEntityCollision(IBlockState stateIn, World worldIn, BlockPos posIn, Entity entityIn)
	{
		entityIn.fallDistance = 0.0F;

		if (this == BlocksAether.blue_aercloud)
		{
			for (int count = 0; count < 50; count++)
			{
				double xOffset = posIn.getX() + worldIn.rand.nextDouble();
				double yOffset = posIn.getY() + worldIn.rand.nextDouble();
				double zOffset = posIn.getZ() + worldIn.rand.nextDouble();

				worldIn.addParticle(Particles.SPLASH, true, xOffset, yOffset, zOffset, 0.0D, 0.0D, 0.0D);
			}

			if (entityIn instanceof EntityPlayer && entityIn.isSneaking() && entityIn.motionY < 0.0F)
			{
				entityIn.motionY *= 0.005D;

    			return;
			}

			entityIn.motionY = 2.0D;
		}
		else
		{
			if (this == BlocksAether.pink_aercloud && entityIn.ticksExisted % 20 == 0 && entityIn instanceof EntityLivingBase)
			{
				((EntityLivingBase)entityIn).heal(1.0F);
			}

    		if (entityIn.motionY <= 0.0F)
    		{
    			entityIn.motionY *= 0.005D;
    		}
		}
	}

	@Override
    public BlockRenderLayer getRenderLayer()
    {
    	return BlockRenderLayer.TRANSLUCENT;
    }

	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

	@Override
	public VoxelShape getCollisionShape(IBlockState blockstateIn, IBlockReader blockReaderIn, BlockPos posIn)
	{
		return this == BlocksAether.blue_aercloud ? ShapeUtils.create(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D) : ShapeUtils.create(0.0D, 0.0D, 0.0D, 1.0D, 0.01D, 1.0D);
	}

}