package com.legacy.aether.blocks.container;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.legacy.aether.blocks.entity.ChestMimicBlockEntity;

public class BlockChestMimic extends BlockWithAetherEntity
{

	@Override
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, Direction direction, float hitX, float hitY, float hitZ)
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
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state)
	{
		return new ItemStack(Blocks.CHEST);
	}

	private void spawnMimic(World world, PlayerEntity player, BlockPos pos)
	{
		
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

}