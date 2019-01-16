package com.legacy.aether.blocks.container;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sortme.ItemScatterer;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.entity.AetherBlockEntity;

public abstract class BlockWithAetherEntity extends BlockWithEntity
{

	public static final BooleanProperty POWERED = Properties.POWERED;

	public BlockWithAetherEntity()
	{
		super(FabricBlockSettings.of(Material.STONE).strength(2.0F, 2.0F).build());

		this.setDefaultState(this.getDefaultState().with(POWERED, false));
	}

	public static void setState(World worldIn, BlockPos pos, boolean isActive)
	{
		BlockState state = worldIn.getBlockState(pos);
		BlockEntity entity = worldIn.getBlockEntity(pos);

		worldIn.setBlockState(pos, state.with(POWERED, isActive), 3);

		if (entity != null)
		{
			entity.validate();
			worldIn.setBlockEntity(pos, entity);
		}
	}

	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack)
	{
		if (stack.hasDisplayName())
		{
			BlockEntity blockEntity = world.getBlockEntity(pos);

			if (blockEntity instanceof AetherBlockEntity)
			{
				((AetherBlockEntity) blockEntity).setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState replacedState, boolean updateNeighbors)
	{
		if (state.getBlock() != replacedState.getBlock())
		{
			BlockEntity entity = world.getBlockEntity(pos);

			if (entity instanceof AetherBlockEntity)
			{
				ItemScatterer.spawn(world, pos, (AetherBlockEntity) entity);
				world.updateHorizontalAdjacent(pos, this);
			}
		}

		super.onBlockRemoved(state, world, pos, replacedState, updateNeighbors);
	}

	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder)
	{
		builder.with(POWERED);
	}

	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos)
	{
		return Container.calculateComparatorOutput(world.getBlockEntity(pos));
	}

	@Override
	public boolean hasComparatorOutput(BlockState state)
	{
		return true;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
	}

}