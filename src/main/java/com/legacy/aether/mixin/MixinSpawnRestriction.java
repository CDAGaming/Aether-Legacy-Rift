package com.legacy.aether.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.sortme.SpawnRestriction;
import net.minecraft.world.gen.Heightmap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.legacy.aether.entities.EntityTypesAether;

@Mixin(SpawnRestriction.class)
public class MixinSpawnRestriction
{

	@Shadow
	private static void register(EntityType<?> entityType_1, SpawnRestriction.Location spawnRestriction$Location_1, Heightmap.Type heightmap$Type_1) {
		
	}

	static
	{
		register(EntityTypesAether.SHEEPUFF, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES);
		register(EntityTypesAether.PHYG, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES);
		register(EntityTypesAether.AERBUNNY, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES);
		register(EntityTypesAether.FLYING_COW, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES);
		register(EntityTypesAether.COCKATRICE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES);
		register(EntityTypesAether.AECHOR_PLANT, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES);
	}
}