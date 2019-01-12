package com.legacy.aether.blocks.natural;

import java.util.Random;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAetherGrass extends Block
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockAetherGrass() 
	{
		super(FabricBlockSettings.of(Material.ORGANIC).ticksRandomly().strength(0.2F, -1.0F).sounds(BlockSoundGroup.GRASS).build());

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true));
	}

	@Override
	public void appendProperties(StateFactory.Builder<Block, BlockState> propertyBuilderIn)
	{
		propertyBuilderIn.with(DOUBLE_DROP);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context)
	{
		return super.getPlacementState(context).with(DOUBLE_DROP, false);
	}
 
	@Override
	public void scheduledTick(BlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
	{
		if (worldIn.isClient)
		{
			return;
		}

		if (worldIn.method_8602(posIn.up()) < 4)
		{
			for (int i = 0; i < 4; ++i)
			{
                BlockPos blockpos = posIn.add(randIn.nextInt(3) - 1, randIn.nextInt(5) - 3, randIn.nextInt(3) - 1);
                BlockState iblockstate1 = worldIn.getBlockState(blockpos);

                if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
                {
                    return;
                }

                if (iblockstate1.getBlock() == BlocksAether.aether_dirt && worldIn.getLightLevel(LightType.SKY, blockpos.up()) >= 4)
                {
                    boolean shouldContainDoubleDrop = iblockstate1.get(BlockAetherDirt.DOUBLE_DROP);
                    worldIn.setBlockState(blockpos, BlocksAether.aether_grass.getDefaultState().with(DOUBLE_DROP, shouldContainDoubleDrop));
                	return;
                }
			}
		}
	}

}