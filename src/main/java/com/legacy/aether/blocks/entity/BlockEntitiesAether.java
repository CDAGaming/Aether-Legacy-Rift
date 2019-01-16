package com.legacy.aether.blocks.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import com.legacy.aether.Aether;

public class BlockEntitiesAether
{

	public static final BlockEntityType<EnchanterBlockEntity> ENCHANTER;

	public static final BlockEntityType<FreezerBlockEntity> FREEZER;

	public static final BlockEntityType<IncubatorBlockEntity> INCUBATOR;

	static
	{
		ENCHANTER = register("enchanter", BlockEntityType.Builder.create(EnchanterBlockEntity::new));
		FREEZER = register("freezer", BlockEntityType.Builder.create(FreezerBlockEntity::new));
		INCUBATOR = register("incubator", BlockEntityType.Builder.create(IncubatorBlockEntity::new));

		register("chest_mimic", BlockEntityType.Builder.create(ChestMimicBlockEntity::new));
		register("treasure_chest", BlockEntityType.Builder.create(TreasureChestBlockEntity::new));
	}

	private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> blockEntityType$Builder_1)
	{
		return Registry.register(Registry.BLOCK_ENTITY, Aether.locate(name), blockEntityType$Builder_1.method_11034(null));
	}

}