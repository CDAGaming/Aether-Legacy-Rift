package com.legacy.aether.blocks.container;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.entity.IncubatorBlockEntity;

public class BlockIncubator extends BlockWithAetherEntity
{

	@Override
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult)
	{
		if (!world.isClient)
		{
			ContainerProviderRegistry.INSTANCE.openContainer(Aether.locate("incubator"), player, (packetBuffer) -> packetBuffer.writeBlockPos(pos));
		}

		return true;
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world)
	{
		return new IncubatorBlockEntity();
	}

}