package com.legacy.aether.blocks.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import com.legacy.aether.Aether;

public class BlockEntitiesAether
{

	static
	{
		register("chest_mimic", BlockEntityType.Builder.create(ChestMimicBlockEntity::new));
		register("treasure_chest", BlockEntityType.Builder.create(TreasureChestBlockEntity::new));
	}

	private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> blockEntityType$Builder_1)
	{
		return Registry.register(Registry.BLOCK_ENTITY, Aether.locate(name), blockEntityType$Builder_1.method_11034(null));
	}

}