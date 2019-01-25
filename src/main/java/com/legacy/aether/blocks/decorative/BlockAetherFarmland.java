package com.legacy.aether.blocks.decorative;

import java.util.Iterator;
import java.util.Random;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.Material;
import net.minecraft.block.StemBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAetherFarmland extends Block
{

	protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

	public BlockAetherFarmland()
	{
		super(FabricBlockSettings.of(Material.EARTH).ticksRandomly().hardness(0.2F).sounds(BlockSoundGroup.GRAVEL).build());

		this.setDefaultState(this.getDefaultState().with(FarmlandBlock.MOISTURE, 0));
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos pos, BlockPos blockPos_2)
	{
		if (direction_1 == Direction.UP && !blockState_1.canPlaceAt(iWorld_1, pos))
		{
			iWorld_1.getBlockTickScheduler().schedule(pos, this, 1);
		}

		return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, pos, blockPos_2);
	}

	@Override
	public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos pos)
	{
		BlockState blockState_2 = viewableWorld_1.getBlockState(pos.up());

		return !blockState_2.getMaterial().method_15799() || blockState_2.getBlock() instanceof FenceGateBlock;
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1)
	{
		return !this.getDefaultState().canPlaceAt(itemPlacementContext_1.getWorld(), itemPlacementContext_1.getBlockPos()) ? BlocksAether.aether_dirt.getDefaultState() : super.getPlacementState(itemPlacementContext_1);
	}

	@Override
	public boolean method_9526(BlockState state)
	{
		return true;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos pos, VerticalEntityPosition verticalEntityPosition_1)
	{
		return SHAPE;
	}

	@Override
	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random)
	{
		if (!state.canPlaceAt(world, pos))
		{
			setToDirt(state, world, pos);
		}
		else
		{
			int moisture = (Integer) state.get(FarmlandBlock.MOISTURE);

			if (!waterNearby(world, pos) && !world.hasRain(pos.up()))
			{
				if (moisture > 0)
				{
					world.setBlockState(pos, (BlockState) state.with(FarmlandBlock.MOISTURE, moisture - 1), 2);
				}
				else if (!hasCrop(world, pos))
				{
					setToDirt(state, world, pos);
				}
			}
			else if (moisture < 7)
			{
				world.setBlockState(pos, (BlockState) state.with(FarmlandBlock.MOISTURE, 7), 2);
			}
		}
	}

	@Override
	public void onLandedUpon(World world, BlockPos pos, Entity entity, float fallDistance)
	{
		if (!world.isClient && world.random.nextFloat() < fallDistance - 0.5F && entity instanceof LivingEntity && (entity instanceof PlayerEntity || world.getGameRules().getBoolean("mobGriefing")) && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F)
		{
			setToDirt(world.getBlockState(pos), world, pos);
		}

		super.onLandedUpon(world, pos, entity, fallDistance);
	}

	public static void setToDirt(BlockState state, World world, BlockPos pos)
	{
		world.setBlockState(pos, pushEntitiesUpBeforeBlockChange(state, BlocksAether.aether_dirt.getDefaultState(), world, pos));
	}

	private static boolean hasCrop(BlockView view, BlockPos pos)
	{
		Block block = view.getBlockState(pos.up()).getBlock();

		return block instanceof CropBlock || block instanceof StemBlock || block instanceof AttachedStemBlock;
	}

	private static boolean waterNearby(ViewableWorld world, BlockPos pos)
	{
		Iterator<BlockPos> positions = BlockPos.iterateBoxPositions(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();

		BlockPos blockPos_2;
		do
		{
			if (!positions.hasNext())
			{
				return false;
			}
			blockPos_2 = (BlockPos) positions.next();
		}
		while (!world.getFluidState(blockPos_2).matches(FluidTags.WATER));

		return true;
	}

	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> factory)
	{
		factory.with(FarmlandBlock.MOISTURE);
	}

	@Override
	public boolean canPlaceAtSide(BlockState state, BlockView view, BlockPos pos, BlockPlacementEnvironment placement)
	{
		return false;
	}

}