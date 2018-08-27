package com.legacy.aether.entities;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import org.dimdev.rift.listener.EntityTypeAdder;
import org.dimdev.rift.listener.ItemAdder;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.hostile.EntityAechorPlant;
import com.legacy.aether.item.ItemAetherSpawnEgg;

public class EntityTypesAether implements EntityTypeAdder, ItemAdder
{

	public static EntityType<EntityAechorPlant> AECHOR_PLANT;

	public static EntityType<EntityFloatingBlock> FLOATING_BLOCK;

	public static EntityType<Entity> AERWHALE; // TEST

	public static final ArrayList<EntityType<?>> SUMMONABLE_ENTITIES = new ArrayList<EntityType<?>>();

	@Override
	@SuppressWarnings("unchecked")
	public void registerEntityTypes() 
	{
		AECHOR_PLANT = (EntityType<EntityAechorPlant>) register("aechor_plant", true, Builder.create(EntityAechorPlant.class, EntityAechorPlant::new));
		FLOATING_BLOCK = (EntityType<EntityFloatingBlock>) register("floating_block", false, Builder.create(EntityFloatingBlock.class, EntityFloatingBlock::new));

		//AECHOR_PLANT = EntityType.registerEntityType("aether_legacy:aechor_plant", EntityType.Builder.create(EntityAechorPlant.class, EntityAechorPlant::new));
		//FLOATING_BLOCK = EntityType.registerEntityType("aether_legacy:floating_block", EntityType.Builder.create(EntityFloatingBlock.class, EntityFloatingBlock::new));
	}

	@Override
	public void registerItems()
	{
		Item.registerItem("aether_legacy:aechor_plant_spawn_egg", new ItemAetherSpawnEgg(AECHOR_PLANT, 0x9fc3f7, 0x29a793, new Item.Builder().group(ItemGroup.MISC)));
	}

	public EntityType<? extends Entity> register(String name, boolean summonable, Builder<? extends Entity> entityBuilder)
	{
		EntityType<? extends Entity> entityType = EntityType.registerEntityType("aether_legacy:" + name, entityBuilder.func_200706_c());

		if (summonable)
		{
			SUMMONABLE_ENTITIES.add(entityType);
		}

		return entityType;
	}

}