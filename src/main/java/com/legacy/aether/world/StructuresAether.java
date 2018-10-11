package com.legacy.aether.world;

import net.minecraft.world.gen.feature.structure.Structure;
import org.dimdev.rift.listener.StructureAdder;

import java.util.Map;

public class StructuresAether implements StructureAdder {

    //public static final Structure<NoFeatureConfig> COLD_AERCLOUD = new ColdAercloudStructure();

    @Override
    public void registerStructureNames() {
        //StructureIO.registerStructure(ColdAercloudStructure.Start.class, "aether_legacy:cold_aercloud_start");

        //StructureIO.registerStructureComponent(ColdAercloudPiece.class, "aether_legacy:cold_aercloud_piece");
    }

    @Override
    public void addStructuresToMap(Map<String, Structure<?>> map) {
        //map.put("aether_legacy:cold_aercloud".toLowerCase(), COLD_AERCLOUD);
    }

}