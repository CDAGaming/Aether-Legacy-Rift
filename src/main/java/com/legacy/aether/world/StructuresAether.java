package com.legacy.aether.world;

import java.util.Map;

import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureIO;

import org.dimdev.rift.listener.StructureAdder;

import com.legacy.aether.world.biome.structure.SilverDungeonStructure;
import com.legacy.aether.world.biome.structure.components.SilverDungeonPiece;
import com.legacy.aether.world.biome.structure.config.SilverDungeonConfig;

public class StructuresAether implements StructureAdder
{

	public static final Structure<SilverDungeonConfig> SILVER_DUNGEON = new SilverDungeonStructure();

	//public static final Structure<NoFeatureConfig> COLD_AERCLOUD = new ColdAercloudStructure();

	@Override
	public void registerStructureNames() 
	{
		StructureIO.registerStructure(SilverDungeonStructure.Start.class, "aether_legacy:silver_dungeon_start");
		//StructureIO.registerStructure(ColdAercloudStructure.Start.class, "aether_legacy:cold_aercloud_start");

		StructureIO.registerStructureComponent(SilverDungeonPiece.class, "aether_legacy:silver_dungeon_piece");
		//StructureIO.registerStructureComponent(ColdAercloudPiece.class, "aether_legacy:cold_aercloud_piece");
	}

	@Override
	public void addStructuresToMap(Map<String, Structure<?>> map) 
	{
		map.put("aether_legacy:silver_dungeon".toLowerCase(), SILVER_DUNGEON);
		//map.put("aether_legacy:cold_aercloud".toLowerCase(), COLD_AERCLOUD);
	}

}