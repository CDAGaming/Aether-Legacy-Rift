package com.legacy.aether.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntityType.Builder;
import org.dimdev.rift.listener.TileEntityTypeAdder;

public class TileEntityTypesAether implements TileEntityTypeAdder {

    public static TileEntityType<TileEntityEnchanter> ENCHANTER;

    public static TileEntityType<TileEntityFreezer> FREEZER;

    @Override
    @SuppressWarnings("unchecked")
    public void registerTileEntityTypes() {
        ENCHANTER = (TileEntityType<TileEntityEnchanter>) register("enchanter", Builder.create(TileEntityEnchanter::new));
        FREEZER = (TileEntityType<TileEntityFreezer>) register("freezer", Builder.create(TileEntityFreezer::new));
    }

    public TileEntityType<? extends TileEntity> register(String name, Builder<? extends AetherTileEntity> builder) {
        return TileEntityType.registerTileEntityType("aether_legacy:" + name, builder);
    }
}