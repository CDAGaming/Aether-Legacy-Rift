package com.legacy.aether.entities;

import net.fabricmc.fabric.api.entity.EntityTrackingRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

import com.legacy.aether.Aether;
import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.hostile.EntityAechorPlant;
import com.legacy.aether.entities.hostile.EntityChestMimic;
import com.legacy.aether.entities.hostile.EntityCockatrice;
import com.legacy.aether.entities.passive.EntityAerbunny;
import com.legacy.aether.entities.passive.EntityFlyingCow;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.entities.passive.EntityPhyg;
import com.legacy.aether.entities.passive.EntitySheepuff;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPoisonDart;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;

public class EntityTypesAether
{

	public static final EntityType<EntityAechorPlant> AECHOR_PLANT = register("aechor_plant", EntityCategory.MONSTER, EntitySize.resizeable(1.0F, 1.0F), (entityType, world) -> new EntityAechorPlant(world));

	public static final EntityType<EntityFlyingCow> FLYING_COW = register("flying_cow", EntityCategory.CREATURE, EntitySize.resizeable(0.9F, 1.3F), (entityType, world) -> new EntityFlyingCow(world));

	public static final EntityType<EntityAerbunny> AERBUNNY = register("aerbunny", EntityCategory.CREATURE, EntitySize.resizeable(0.4F, 0.4F), (entityType, world) -> new EntityAerbunny(world));

	public static final EntityType<EntityMoa> MOA = register("moa", EntityCategory.CREATURE, EntitySize.resizeable(1.0F, 2.0F), (entityType, world) -> new EntityMoa(world));

	public static final EntityType<EntityPhyg> PHYG = register("phyg", EntityCategory.CREATURE, EntitySize.resizeable(0.9F, 1.3F), (entityType, world) -> new EntityPhyg(world));

	public static final EntityType<EntitySheepuff> SHEEPUFF = register("sheepuff", EntityCategory.CREATURE, EntitySize.resizeable(0.9F, 1.3F), (entityType, world) -> new EntitySheepuff(world));

	public static final EntityType<EntityCockatrice> COCKATRICE = register("cockatrice", EntityCategory.MONSTER, EntitySize.resizeable(1.0F, 2.0F), (entityType, world) -> new EntityCockatrice(world));

	public static final EntityType<EntityChestMimic> CHEST_MIMIC = register("chest_mimic", EntityCategory.MONSTER, EntitySize.resizeable(1.0F, 2.0F), (entityType, world) -> new EntityChestMimic(world));

	public static final EntityType<EntityFloatingBlock> FLOATING_BLOCK = register("floating_block", EntityCategory.MISC, EntitySize.resizeable(0.98F, 0.98F), (entityType, world) -> new EntityFloatingBlock(world));

	public static final EntityType<EntityGoldenDart> GOLDEN_DART = register("golden_dart", EntityCategory.MISC, EntitySize.resizeable(0.5F, 0.5F), (entityType, world) -> new EntityGoldenDart(world));

	public static final EntityType<EntityEnchantedDart> ENCHANTED_DART = register("enchanted_dart", EntityCategory.MISC, EntitySize.resizeable(0.5F, 0.5F), (entityType, world) -> new EntityEnchantedDart(world));

	public static final EntityType<EntityPoisonDart> POISON_DART = register("poison_dart", EntityCategory.MISC, EntitySize.resizeable(0.5F, 0.5F), (entityType, world) -> new EntityPoisonDart(world));

	public static final EntityType<EntityPoisonNeedle> POISON_NEEDLE = register("poison_needle", EntityCategory.MISC, EntitySize.resizeable(0.5F, 0.5F), (entityType, world) -> new EntityPoisonNeedle(world));

	//public static EntityType<EntityWhirlwind> WHIRLWIND;

	//public static EntityType<EntityAerwhale> AERWHALE;

	//public static EntityType<EntityMiniCloud> MINI_CLOUD;

	//public static EntityType<EntityFireMinion> FIRE_MINION;

	//public static EntityType<EntityCrystal> CRYSTAL;

	//public static EntityType<EntityPhoenixArrow> PHOENIX_ARROW;

	public static void register()
	{
		Aether.log("Registering Aether Entities");

		trackEntity(FLOATING_BLOCK, 160, 20, true);
	}

	@SuppressWarnings("unused")
	private static void registerEntityTypes()
	{
		//FLYING_COW = (EntityType<EntityFlyingCow>) register("flying_cow", FabricEntityTypeBuilder.create(EntityFlyingCow.class, EntityFlyingCow::new).build());
		/*AERWHALE = (EntityType<EntityAerwhale>) register("aerwhale", Builder.create(EntityAerwhale.class, EntityAerwhale::new));
		AECHOR_PLANT = (EntityType<EntityAechorPlant>) register("aechor_plant", Builder.create(EntityAechorPlant.class, EntityAechorPlant::new));
		PHYG = (EntityType<EntityPhyg>) register("phyg", Builder.create(EntityPhyg.class, EntityPhyg::new));
		SHEEPUFF = (EntityType<EntitySheepuff>) register("sheepuff", Builder.create(EntitySheepuff.class, EntitySheepuff::new));
		AERBUNNY = (EntityType<EntityAerbunny>) register("aerbunny", Builder.create(EntityAerbunny.class, EntityAerbunny::new));
		COCKATRICE = (EntityType<EntityCockatrice>) register("cockatrice", Builder.create(EntityCockatrice.class, EntityCockatrice::new));
		CHEST_MIMIC = (EntityType<EntityChestMimic>) register("chest_mimic", Builder.create(EntityChestMimic.class, EntityChestMimic::new));
		GOLDEN_DART = (EntityType<EntityGoldenDart>) register("golden_dart", Builder.create(EntityGoldenDart.class, EntityGoldenDart::new));
		ENCHANTED_DART = (EntityType<EntityEnchantedDart>) register("enchanted_dart", Builder.create(EntityEnchantedDart.class, EntityEnchantedDart::new));
		POISON_DART = (EntityType<EntityPoisonDart>) register("poison_dart", Builder.create(EntityPoisonDart.class, EntityPoisonDart::new));
		POISON_NEEDLE = (EntityType<EntityPoisonNeedle>) register("poison_needle", Builder.create(EntityPoisonNeedle.class, EntityPoisonNeedle::new));*/
		/*WHIRLWIND = (EntityType<EntityWhirlwind>) register("whirlwind", Builder.create(EntityWhirlwind.class, EntityWhirlwind::new));
		MINI_CLOUD = (EntityType<EntityMiniCloud>) register("mini_cloud", Builder.create(EntityMiniCloud.class, EntityMiniCloud::new));
		FIRE_MINION = (EntityType<EntityFireMinion>) register("fire_minion", Builder.create(EntityFireMinion.class, EntityFireMinion::new));
		CRYSTAL = (EntityType<EntityCrystal>) register("crystal", Builder.create(EntityCrystal.class, EntityCrystal::new));
		PHOENIX_ARROW = (EntityType<EntityPhoenixArrow>) register("phoenix_arrow", Builder.create(EntityPhoenixArrow.class, EntityPhoenixArrow::new));

		AetherMoaTypes.initialization();*/
	}

	private static void trackEntity(EntityType<?> type, int trackingDistance, int updateIntervalTicks, boolean alwaysUpdateVelocity)
	{
		EntityTrackingRegistry.INSTANCE.register(type, trackingDistance, updateIntervalTicks, alwaysUpdateVelocity);
	}

	public static void registerItems()
	{
		//Registry.register(Registry.ITEM, Aether.locate("aechor_plant_spawn_egg"), new ItemAetherSpawnEgg(AECHOR_PLANT, 0x9fc3f7, 0x29a793));
		//Registry.register(Registry.ITEM, Aether.locate("cockatrice_spawn_egg"), new ItemAetherSpawnEgg(COCKATRICE, 0x9fc3f7, 0x3d2338));
	}

	public static <X extends Entity> EntityType<X> register(String name, EntityCategory category, EntitySize size, EntityType.class_4049<X> factory)
	{
		return Registry.register(Registry.ENTITY_TYPE, Aether.locate(name), FabricEntityTypeBuilder.<X>create(category, factory).size(size).disableSaving().build());
	}

}