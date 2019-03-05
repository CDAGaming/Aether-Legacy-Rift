package com.legacy.aether.blocks.natural;

import java.util.Random;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ViewableWorld;
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
	public void onScheduledTick(BlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
	{
		if (!worldIn.isClient)
		{
			return;
		}

		if (!hasSufficientLight(worldIn, posIn))
		{
			boolean doubleDrop = worldIn.getBlockState(posIn).get(DOUBLE_DROP);

			worldIn.setBlockState(posIn, BlocksAether.aether_dirt.getDefaultState().with(BlockAetherDirt.DOUBLE_DROP, doubleDrop));
		}
		else
		{
			if (worldIn.getLightLevel(posIn.up()) >= 9)
			{
				for (int int_1 = 0; int_1 < 4; ++int_1)
				{
					BlockPos blockPos_2 = posIn.add(randIn.nextInt(3) - 1, randIn.nextInt(5) - 3, randIn.nextInt(3) - 1);

					if (worldIn.getBlockState(blockPos_2).getBlock() == BlocksAether.aether_dirt && canGrow(worldIn, blockPos_2))
					{
						worldIn.setBlockState(blockPos_2, this.getDefaultState());
					}
				}
			}
		}
	}

	private static boolean hasSufficientLight(ViewableWorld world, BlockPos pos)
	{
		BlockPos abovePos = pos.up();

		return world.getLightLevel(abovePos) >= 4 || world.getBlockState(abovePos).getLightSubtracted(world, abovePos) < world.getMaxLightLevel();
	}

	private static boolean canGrow(ViewableWorld world, BlockPos pos)
	{
		BlockPos abovePos = pos.up();

		return world.getLightLevel(abovePos) >= 4 && world.getBlockState(abovePos).getLightSubtracted(world, abovePos) < world.getMaxLightLevel() && !world.getFluidState(abovePos).matches(FluidTags.WATER);
	}

}