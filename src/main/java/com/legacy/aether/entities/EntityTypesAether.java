package com.legacy.aether.entities;

import net.fabricmc.fabric.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

import com.legacy.aether.Aether;
import com.legacy.aether.client.rendering.entity.FloatingBlockRenderer;
import com.legacy.aether.client.rendering.entity.MoaRenderer;
import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.passive.EntityMoa;

public class EntityTypesAether
{

	//public static EntityType<EntityAechorPlant> AECHOR_PLANT;

	//public static EntityType<EntityFlyingCow> FLYING_COW;

	//public static EntityType<EntityAerbunny> AERBUNNY;

	public static EntityType<EntityMoa> MOA;

	//public static EntityType<EntityPhyg> PHYG;

	//public static EntityType<EntitySheepuff> SHEEPUFF;

	//public static EntityType<EntityCockatrice> COCKATRICE;

	//public static EntityType<EntityChestMimic> CHEST_MIMIC;

	public static EntityType<EntityFloatingBlock> FLOATING_BLOCK;

	//public static EntityType<EntityGoldenDart> GOLDEN_DART;

	//public static EntityType<EntityEnchantedDart> ENCHANTED_DART;

	//public static EntityType<EntityPoisonDart> POISON_DART;

	//public static EntityType<EntityPoisonNeedle> POISON_NEEDLE;

	//public static EntityType<EntityWhirlwind> WHIRLWIND;

	//public static EntityType<EntityAerwhale> AERWHALE;

	//public static EntityType<EntityMiniCloud> MINI_CLOUD;

	//public static EntityType<EntityFireMinion> FIRE_MINION;

	//public static EntityType<EntityCrystal> CRYSTAL;

	//public static EntityType<EntityPhoenixArrow> PHOENIX_ARROW;

	@SuppressWarnings("unchecked")
	public static void registerEntityTypes()
	{
		/*AERWHALE = (EntityType<EntityAerwhale>) register("aerwhale", Builder.create(EntityAerwhale.class, EntityAerwhale::new));
		AECHOR_PLANT = (EntityType<EntityAechorPlant>) register("aechor_plant", Builder.create(EntityAechorPlant.class, EntityAechorPlant::new));
		FLYING_COW = (EntityType<EntityFlyingCow>) register("flying_cow", Builder.create(EntityFlyingCow.class, EntityFlyingCow::new));
		PHYG = (EntityType<EntityPhyg>) register("phyg", Builder.create(EntityPhyg.class, EntityPhyg::new));
		SHEEPUFF = (EntityType<EntitySheepuff>) register("sheepuff", Builder.create(EntitySheepuff.class, EntitySheepuff::new));
		AERBUNNY = (EntityType<EntityAerbunny>) register("aerbunny", Builder.create(EntityAerbunny.class, EntityAerbunny::new));
		COCKATRICE = (EntityType<EntityCockatrice>) register("cockatrice", Builder.create(EntityCockatrice.class, EntityCockatrice::new));
		CHEST_MIMIC = (EntityType<EntityChestMimic>) register("chest_mimic", Builder.create(EntityChestMimic.class, EntityChestMimic::new));
		GOLDEN_DART = (EntityType<EntityGoldenDart>) register("golden_dart", Builder.create(EntityGoldenDart.class, EntityGoldenDart::new));
		ENCHANTED_DART = (EntityType<EntityEnchantedDart>) register("enchanted_dart", Builder.create(EntityEnchantedDart.class, EntityEnchantedDart::new));
		POISON_DART = (EntityType<EntityPoisonDart>) register("poison_dart", Builder.create(EntityPoisonDart.class, EntityPoisonDart::new));
		POISON_NEEDLE = (EntityType<EntityPoisonNeedle>) register("poison_needle", Builder.create(EntityPoisonNeedle.class, EntityPoisonNeedle::new));*/
		MOA = (EntityType<EntityMoa>) register("moa", FabricEntityTypeBuilder.create(EntityMoa.class, EntityMoa::new).build());
		FLOATING_BLOCK = (EntityType<EntityFloatingBlock>) register("floating_block", FabricEntityTypeBuilder.create(EntityFloatingBlock.class, EntityFloatingBlock::new).build());
		/*WHIRLWIND = (EntityType<EntityWhirlwind>) register("whirlwind", Builder.create(EntityWhirlwind.class, EntityWhirlwind::new));
		MINI_CLOUD = (EntityType<EntityMiniCloud>) register("mini_cloud", Builder.create(EntityMiniCloud.class, EntityMiniCloud::new));
		FIRE_MINION = (EntityType<EntityFireMinion>) register("fire_minion", Builder.create(EntityFireMinion.class, EntityFireMinion::new));
		CRYSTAL = (EntityType<EntityCrystal>) register("crystal", Builder.create(EntityCrystal.class, EntityCrystal::new));
		PHOENIX_ARROW = (EntityType<EntityPhoenixArrow>) register("phoenix_arrow", Builder.create(EntityPhoenixArrow.class, EntityPhoenixArrow::new));

		AetherMoaTypes.initialization();*/
	}

	public static void registerRenderers() {
		EntityRendererRegistry.INSTANCE.register(EntityMoa.class, (entityRenderDispatcher, context) -> new MoaRenderer(entityRenderDispatcher));
		EntityRendererRegistry.INSTANCE.register(EntityFloatingBlock.class, (entityRenderDispatcher, context) -> new FloatingBlockRenderer(entityRenderDispatcher));
		/*
		entityRenderMap.put(EntityDart.class, new DartRenderer(renderManager));
		entityRenderMap.put(EntityMiniCloud.class, new MiniCloudRenderer(renderManager));
		entityRenderMap.put(EntityAerwhale.class, new AerwhaleRenderer(renderManager));
		entityRenderMap.put(EntityAerbunny.class, new AerbunnyRenderer(renderManager));
		entityRenderMap.put(EntityMoa.class, new MoaRenderer(renderManager));
		entityRenderMap.put(EntityAechorPlant.class, new AechorPlantRenderer(renderManager));
		entityRenderMap.put(EntityFlyingCow.class, new FlyingCowRenderer(renderManager));
		entityRenderMap.put(EntityPhyg.class, new PhygRenderer(renderManager));
		entityRenderMap.put(EntitySheepuff.class, new SheepuffRenderer(renderManager));
		entityRenderMap.put(EntityCockatrice.class, new CockatriceRenderer(renderManager));
		entityRenderMap.put(EntityChestMimic.class, new ChestMimicRenderer(renderManager));
		entityRenderMap.put(EntityWhirlwind.class, new WhirlwindRenderer(renderManager));
		entityRenderMap.put(EntityPhoenixArrow.class, new PhoenixArrowRenderer(renderManager));
		 */
	}

	public static void registerItems()
	{
		//Registry.register(Registry.ITEM, Aether.locate("aechor_plant_spawn_egg"), new ItemAetherSpawnEgg(AECHOR_PLANT, 0x9fc3f7, 0x29a793));
		//Registry.register(Registry.ITEM, Aether.locate("cockatrice_spawn_egg"), new ItemAetherSpawnEgg(COCKATRICE, 0x9fc3f7, 0x3d2338));
	}

	public static EntityType<? extends Entity> register(String name, EntityType<?> entityBuilder)
	{
		Registry.register(Registry.ENTITY_TYPE, Aether.locate(name), entityBuilder);

		return entityBuilder;
	}

}