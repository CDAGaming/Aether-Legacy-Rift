package com.legacy.aether.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.item.Item;

import org.dimdev.rift.listener.EntityTypeAdder;
import org.dimdev.rift.listener.ItemAdder;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.hostile.EntityAechorPlant;
import com.legacy.aether.entities.hostile.EntityChestMimic;
import com.legacy.aether.entities.hostile.EntityCockatrice;
//import com.legacy.aether.entities.hostile.EntityWhirlwind;
import com.legacy.aether.entities.passive.EntityAerbunny;
import com.legacy.aether.entities.passive.EntityFlyingCow;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.entities.passive.EntityPhyg;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPoisonDart;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;
import com.legacy.aether.entities.util.AetherMoaTypes;
import com.legacy.aether.item.ItemAetherSpawnEgg;

public class EntityTypesAether implements EntityTypeAdder, ItemAdder
{

	public static EntityType<EntityAechorPlant> AECHOR_PLANT;

	public static EntityType<EntityFlyingCow> FLYING_COW;

	public static EntityType<EntityAerbunny> AERBUNNY;

	public static EntityType<EntityMoa> MOA;

	public static EntityType<EntityPhyg> PHYG;

	public static EntityType<EntityCockatrice> COCKATRICE;

	public static EntityType<EntityChestMimic> CHEST_MIMIC;

	public static EntityType<EntityFloatingBlock> FLOATING_BLOCK;

	public static EntityType<EntityGoldenDart> GOLDEN_DART;

	public static EntityType<EntityEnchantedDart> ENCHANTED_DART;

	public static EntityType<EntityPoisonDart> POISON_DART;

	public static EntityType<EntityPoisonNeedle> POISON_NEEDLE;

	//public static EntityType<EntityWhirlwind> WHIRLWIND;

	public static EntityType<Entity> AERWHALE; // TEST

	@Override
	@SuppressWarnings("unchecked")
	public void registerEntityTypes() 
	{
		AECHOR_PLANT = (EntityType<EntityAechorPlant>) register("aechor_plant", Builder.create(EntityAechorPlant.class, EntityAechorPlant::new));
		FLYING_COW = (EntityType<EntityFlyingCow>) register("flying_cow", Builder.create(EntityFlyingCow.class, EntityFlyingCow::new));
		MOA = (EntityType<EntityMoa>) register("moa", Builder.create(EntityMoa.class, EntityMoa::new));
		PHYG = (EntityType<EntityPhyg>) register("phyg", Builder.create(EntityPhyg.class, EntityPhyg::new));
		AERBUNNY = (EntityType<EntityAerbunny>) register("aerbunny", Builder.create(EntityAerbunny.class, EntityAerbunny::new));
		COCKATRICE = (EntityType<EntityCockatrice>) register("cockatrice", Builder.create(EntityCockatrice.class, EntityCockatrice::new));
		CHEST_MIMIC = (EntityType<EntityChestMimic>) register("chest_mimic", Builder.create(EntityChestMimic.class, EntityChestMimic::new));
		GOLDEN_DART = (EntityType<EntityGoldenDart>) register("golden_dart", Builder.create(EntityGoldenDart.class, EntityGoldenDart::new));
		ENCHANTED_DART = (EntityType<EntityEnchantedDart>) register("enchanted_dart", Builder.create(EntityEnchantedDart.class, EntityEnchantedDart::new));
		POISON_DART = (EntityType<EntityPoisonDart>) register("poison_dart", Builder.create(EntityPoisonDart.class, EntityPoisonDart::new));
		POISON_NEEDLE = (EntityType<EntityPoisonNeedle>) register("poison_needle", Builder.create(EntityPoisonNeedle.class, EntityPoisonNeedle::new));
		FLOATING_BLOCK = (EntityType<EntityFloatingBlock>) register("floating_block", Builder.create(EntityFloatingBlock.class, EntityFloatingBlock::new));
		//WHIRLWIND = (EntityType<EntityWhirlwind>) register("whirlwind", Builder.create(EntityWhirlwind.class, EntityWhirlwind::new));

		AetherMoaTypes.initialization();
	}

	@Override
	public void registerItems()
	{
		Item.registerItem("aether_legacy:aechor_plant_spawn_egg", new ItemAetherSpawnEgg(AECHOR_PLANT, 0x9fc3f7, 0x29a793));
		Item.registerItem("aether_legacy:cockatrice_spawn_egg", new ItemAetherSpawnEgg(COCKATRICE, 0x9fc3f7, 0x3d2338));
	}

	public EntityType<? extends Entity> register(String name, Builder<? extends Entity> entityBuilder)
	{
		EntityType<? extends Entity> entityType = EntityType.register("aether_legacy:" + name, entityBuilder.disableSerialization());

		return entityType;
	}

}