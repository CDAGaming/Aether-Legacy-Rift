package com.legacy.aether.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntityType.Builder;

import org.dimdev.rift.listener.TileEntityTypeAdder;

public class TileEntityTypesAether implements TileEntityTypeAdder
{

	public static TileEntityType<TileEntityEnchanter> ENCHANTER;

	public static TileEntityType<TileEntityFreezer> FREEZER;

	public static TileEntityType<TileEntityTreasureChest> TREASURE_CHEST;

	public static TileEntityType<TileEntityChestMimic> CHEST_MIMIC;

	@Override
	@SuppressWarnings("unchecked")
	public void registerTileEntityTypes()
	{
		ENCHANTER = (TileEntityType<TileEntityEnchanter>) register("enchanter", Builder.create(TileEntityEnchanter::new));
		FREEZER = (TileEntityType<TileEntityFreezer>) register("freezer", Builder.create(TileEntityFreezer::new));
		TREASURE_CHEST = (TileEntityType<TileEntityTreasureChest>) register("treasure_chest", Builder.create(TileEntityTreasureChest::new));
		CHEST_MIMIC = (TileEntityType<TileEntityChestMimic>) register("chest_mimic", Builder.create(TileEntityChestMimic::new));
	}

	public TileEntityType<? extends TileEntity> register(String name, Builder<? extends TileEntity> builder)
	{
		return TileEntityType.register("aether_legacy:" + name, builder);
	}
}