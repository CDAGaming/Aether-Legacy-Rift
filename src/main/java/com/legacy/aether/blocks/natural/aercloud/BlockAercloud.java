package com.legacy.aether.blocks.natural.aercloud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.client.render.block.BlockRenderLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAercloud extends Block
{

	public BlockAercloud() 
	{
		super(Settings.of(Material.ICE).strength(0.2F, -1.0F).sound(SoundType.CLOTH));
	}

	@Override
	public void onEntityCollision(BlockState stateIn, World worldIn, BlockPos posIn, Entity entityIn)
	{
		entityIn.fallDistance = 0.0F;

		if (this == BlocksAether.blue_aercloud)
		{
			for (int count = 0; count < 50; count++)
			{
				double xOffset = posIn.getX() + worldIn.random.nextDouble();
				double yOffset = posIn.getY() + worldIn.random.nextDouble();
				double zOffset = posIn.getZ() + worldIn.random.nextDouble();

				worldIn.addParticle(Particles.SPLASH, xOffset, yOffset, zOffset, 0.0D, 0.0D, 0.0D);
			}

			if (entityIn instanceof PlayerEntity && entityIn.isSneaking() && entityIn.velocityY < 0.0F)
			{
				entityIn.velocityY *= 0.005D;

    			return;
			}

			entityIn.velocityY = 2.0D;
		}
		else
		{
			if (this == BlocksAether.pink_aercloud && entityIn.ticksExisted % 20 == 0 && entityIn instanceof LivingEntity)
			{
				((LivingEntity)entityIn).heal(1.0F);
			}

    		if (entityIn.velocityY <= 0.0F)
    		{
    			entityIn.velocityY *= 0.005D;
    		}
		}
	}

	@Override
    public BlockRenderLayer getRenderLayer()
    {
    	return BlockRenderLayer.TRANSLUCENT;
    }

	@Override
    public boolean isFullCube(BlockState state)
    {
        return false;
    }

	@Override
	public VoxelShape getCollisionShape(BlockState blockstateIn, BlockReader blockReaderIn, BlockPos posIn)
	{
		return this == BlocksAether.blue_aercloud ? VoxelShapes.cube(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D) : VoxelShapes.cube(0.0D, 0.0D, 0.0D, 1.0D, 0.01D, 1.0D);
	}

}