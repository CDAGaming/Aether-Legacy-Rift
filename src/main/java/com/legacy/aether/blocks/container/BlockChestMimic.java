package com.legacy.aether.blocks.container;

import com.legacy.aether.blocks.entity.ChestMimicBlockEntity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockChestMimic extends BlockWithAetherEntity implements Waterloggable
{

	protected static final VoxelShape SINGLE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

	public BlockChestMimic()
	{
		super();

		this.setDefaultState(this.getDefaultState().with(POWERED, false).with(HorizontalFacingBlock.field_11177, Direction.NORTH).with(Properties.WATERLOGGED, false));
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1)
	{
		Direction direction_1 = itemPlacementContext_1.getPlayerHorizontalFacing().getOpposite();
		FluidState fluidState_1 = itemPlacementContext_1.getWorld().getFluidState(itemPlacementContext_1.getBlockPos());
		boolean boolean_1 = itemPlacementContext_1.isPlayerSneaking();
		Direction direction_2 = itemPlacementContext_1.getFacing();

		if (direction_2.getAxis().isHorizontal() && boolean_1)
		{
			Direction direction_3 = itemPlacementContext_1.getWorld().getBlockState(itemPlacementContext_1.getBlockPos().offset(direction_2.getOpposite())).get(HorizontalFacingBlock.field_11177);

			if (direction_3 != null && direction_3.getAxis() != direction_2.getAxis())
			{
				direction_1 = direction_3;
			}
		}

		return (BlockState) ((BlockState) ((BlockState) this.getDefaultState().with(HorizontalFacingBlock.field_11177, direction_1)).with(Properties.WATERLOGGED, fluidState_1.getFluid() == Fluids.WATER));
	}

	@Override
	public boolean isSimpleFullBlock(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
	{
		return false;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, VerticalEntityPosition position)
	{
		return SINGLE_SHAPE;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if ((Boolean) state.get(Properties.WATERLOGGED))
		{
			world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult)
	{
		this.spawnMimic(world, player, pos);

		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		double d1 = (double) i + 0.5D;
		double d2 = (double) k + 0.5D;

		world.playSound(null, d1, (double) j + 0.5D, d2, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCK, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);

		return true;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean hasBlockEntityBreakingRender(BlockState state)
	{
		return true;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state)
	{
		return new ItemStack(Blocks.CHEST);
	}

	private void spawnMimic(World world, PlayerEntity player, BlockPos pos)
	{
		world.setBlockState(pos, Blocks.AIR.getDefaultState());
	}

	@Override
	public FluidState getFluidState(BlockState state)
	{
		return (Boolean) state.get(Properties.WATERLOGGED) ? Fluids.WATER.getState(false) : super.getFluidState(state);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world)
	{
		return new ChestMimicBlockEntity();
	}

	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder)
	{
		builder.with(POWERED, HorizontalFacingBlock.field_11177, Properties.WATERLOGGED);
	}

}