package com.legacy.aether.world;

import java.util.Map;

import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureIO;

import org.dimdev.rift.listener.StructureAdder;

import com.legacy.aether.world.biome.structure.BlueAercloudStructure;
import com.legacy.aether.world.biome.structure.ColdAercloudStructure;
import com.legacy.aether.world.biome.structure.GoldenAercloudStructure;
import com.legacy.aether.world.biome.structure.QuicksoilStructure;
import com.legacy.aether.world.biome.structure.components.BlueAercloudPiece;
import com.legacy.aether.world.biome.structure.components.ColdAercloudPiece;
import com.legacy.aether.world.biome.structure.components.GoldenAercloudPiece;
import com.legacy.aether.world.biome.structure.components.QuicksoilPiece;

public class StructuresAether implements StructureAdder
{

	public static final Structure<NoFeatureConfig> COLD_AERCLOUD = new ColdAercloudStructure();

	public static final Structure<NoFeatureConfig> BLUE_AERCLOUD = new BlueAercloudStructure();

	public static final Structure<NoFeatureConfig> GOLDEN_AERCLOUD = new GoldenAercloudStructure();

	public static final Structure<NoFeatureConfig> QUICKSOIL = new QuicksoilStructure();

	@Override
	public void registerStructureNames() 
	{
		StructureIO.registerStructure(ColdAercloudStructure.Start.class, "aether_legacy:cold_aercloud_start");
		StructureIO.registerStructure(BlueAercloudStructure.Start.class, "aether_legacy:blue_aercloud_start");
		StructureIO.registerStructure(GoldenAercloudStructure.Start.class, "aether_legacy:golden_aercloud_start");
		StructureIO.registerStructure(QuicksoilStructure.Start.class, "aether_legacy:quicksoil_start");

		StructureIO.registerStructureComponent(ColdAercloudPiece.class, "aether_legacy:cold_aercloud_piece");
		StructureIO.registerStructureComponent(BlueAercloudPiece.class, "aether_legacy:blue_aercloud_piece");
		StructureIO.registerStructureComponent(GoldenAercloudPiece.class, "aether_legacy:golden_aercloud_piece");
		StructureIO.registerStructureComponent(QuicksoilPiece.class, "aether_legacy:quicksoil_piece");
	}

	@Override
	public void addStructuresToMap(Map<String, Structure<?>> map) 
	{
		map.put("aether_legacy:cold_aercloud".toLowerCase(), COLD_AERCLOUD);
		map.put("aether_legacy:blue_aercloud".toLowerCase(), BLUE_AERCLOUD);
		map.put("aether_legacy:golden_aercloud".toLowerCase(), GOLDEN_AERCLOUD);
		map.put("aether_legacy:quicksoil".toLowerCase(), QUICKSOIL);
	}

}