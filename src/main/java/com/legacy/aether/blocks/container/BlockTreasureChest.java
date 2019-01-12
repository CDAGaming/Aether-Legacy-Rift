package com.legacy.aether.blocks.container;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.entity.TreasureChestBlockEntity;

public class BlockTreasureChest extends ChestBlock
{

	public BlockTreasureChest()
	{
		super(FabricBlockSettings.of(Material.STONE).strength(-1.0F, -1.0F).build());

		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(field_10770, ChestType.SINGLE).with(field_10772, false));
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockViewIn)
	{
		return new TreasureChestBlockEntity();
	}

	@Override
	public boolean activate(BlockState stateIn, World worldIn, BlockPos posIn, PlayerEntity playerIn, Hand handIn, Direction facingIn, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isClient)
		{
			return true;
		}

		BlockEntity entity = worldIn.getBlockEntity(posIn);

		if (entity instanceof TreasureChestBlockEntity)
		{
			TreasureChestBlockEntity chest = (TreasureChestBlockEntity) entity;

			if (chest.isTreasureLocked())
			{
				return chest.unlockTreasure(playerIn, handIn);
			}

			ContainerProviderRegistry.INSTANCE.openContainer(Aether.locate("treasure_chest"), playerIn, (byteBuf) -> byteBuf.writeBlockPos(posIn));

			return true;
		}

		return false;
	}

}