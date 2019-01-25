package com.legacy.aether.entities;

import java.util.function.Function;

import net.fabricmc.fabric.entity.EntityTrackingRegistry;
import net.fabricmc.fabric.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.network.packet.EntitySpawnClientPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.Packet;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

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
import com.legacy.aether.entities.projectile.EntityDart;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPoisonDart;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;

public class EntityTypesAether
{

	public static final EntityType<EntityAechorPlant> AECHOR_PLANT = register("aechor_plant", EntityAechorPlant.class, EntityAechorPlant::new);

	public static final EntityType<EntityFlyingCow> FLYING_COW = register("flying_cow", EntityFlyingCow.class, EntityFlyingCow::new);

	public static final EntityType<EntityAerbunny> AERBUNNY = register("aerbunny", EntityAerbunny.class, EntityAerbunny::new);

	public static final EntityType<EntityMoa> MOA = register("moa", EntityMoa.class, EntityMoa::new);

	public static final EntityType<EntityPhyg> PHYG = register("phyg", EntityPhyg.class, EntityPhyg::new);

	public static final EntityType<EntitySheepuff> SHEEPUFF = register("sheepuff", EntitySheepuff.class, EntitySheepuff::new);

	public static final EntityType<EntityCockatrice> COCKATRICE = register("cockatrice", EntityCockatrice.class, EntityCockatrice::new);

	public static final EntityType<EntityChestMimic> CHEST_MIMIC = register("chest_mimic", EntityChestMimic.class, EntityChestMimic::new);

	public static final EntityType<EntityFloatingBlock> FLOATING_BLOCK = register("floating_block", EntityFloatingBlock.class, EntityFloatingBlock::new);

	public static final EntityType<EntityGoldenDart> GOLDEN_DART = register("golden_dart", EntityGoldenDart.class, EntityGoldenDart::new);

	public static final EntityType<EntityEnchantedDart> ENCHANTED_DART = register("enchanted_dart", EntityEnchantedDart.class, EntityEnchantedDart::new);

	public static final EntityType<EntityPoisonDart> POISON_DART = register("poison_dart", EntityPoisonDart.class, EntityPoisonDart::new);

	public static final EntityType<EntityPoisonNeedle> POISON_NEEDLE = register("poison_needle", EntityPoisonNeedle.class, EntityPoisonNeedle::new);

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

		registerSpawnPacket(FLOATING_BLOCK, (entity) -> new EntitySpawnClientPacket(entity, 583, Block.getRawIdFromState(((EntityFloatingBlock)entity).getBlockstate())));
		registerSpawnPacket(GOLDEN_DART, (entity) -> new EntitySpawnClientPacket(entity, 584, 1 + (((EntityDart)entity).getOwner() == null ? entity.getEntityId() : ((EntityDart)entity).getOwner().getEntityId())));
		registerSpawnPacket(ENCHANTED_DART, (entity) -> new EntitySpawnClientPacket(entity, 585, 1 + (((EntityDart)entity).getOwner() == null ? entity.getEntityId() : ((EntityDart)entity).getOwner().getEntityId())));
		registerSpawnPacket(POISON_DART, (entity) -> new EntitySpawnClientPacket(entity, 586, 1 + (((EntityDart)entity).getOwner() == null ? entity.getEntityId() : ((EntityDart)entity).getOwner().getEntityId())));
		registerSpawnPacket(POISON_NEEDLE, (entity) -> new EntitySpawnClientPacket(entity, 587, 1 + (((EntityDart)entity).getOwner() == null ? entity.getEntityId() : ((EntityDart)entity).getOwner().getEntityId())));
		//registerSpawnPacket(FLOATING_BLOCK, (entity) -> new CustomPayloadClientPacket(Aether.locate("spawns"), EntityFloatingBlock.write((EntityFloatingBlock)entity)));
		//registerSpawnPacket(GOLDEN_DART, (entity) -> new CustomPayloadClientPacket(Aether.locate("spawns"), EntityDart.write((EntityDart) entity)));
		//registerSpawnPacket(ENCHANTED_DART, (entity) -> new CustomPayloadClientPacket(Aether.locate("spawns"), EntityDart.write((EntityDart) entity)));
		//registerSpawnPacket(POISON_DART, (entity) -> new CustomPayloadClientPacket(Aether.locate("spawns"), EntityDart.write((EntityDart) entity)));
		//registerSpawnPacket(POISON_NEEDLE, (entity) -> new CustomPayloadClientPacket(Aether.locate("spawns"), EntityDart.write((EntityDart) entity)));
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

	@SuppressWarnings("rawtypes")
	private static void registerSpawnPacket(EntityType<?> type, Function<Entity, Packet> function)
	{
		EntityTrackingRegistry.INSTANCE.registerSpawnPacketProvider(type, function);
	}

	public static void registerItems()
	{
		//Registry.register(Registry.ITEM, Aether.locate("aechor_plant_spawn_egg"), new ItemAetherSpawnEgg(AECHOR_PLANT, 0x9fc3f7, 0x29a793));
		//Registry.register(Registry.ITEM, Aether.locate("cockatrice_spawn_egg"), new ItemAetherSpawnEgg(COCKATRICE, 0x9fc3f7, 0x3d2338));
	}

	public static <X extends Entity> EntityType<X> register(String name, Class<X> clazz, Function<? super World, X> function)
	{
		return Registry.register(Registry.ENTITY_TYPE, Aether.locate(name), FabricEntityTypeBuilder.create(clazz, function).size(1.0F, 1.0F).build());
	}

}