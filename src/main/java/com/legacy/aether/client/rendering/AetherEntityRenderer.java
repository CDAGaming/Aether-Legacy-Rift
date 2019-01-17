package com.legacy.aether.client.rendering;

import net.fabricmc.fabric.client.render.EntityRendererRegistry;
import net.minecraft.entity.Entity;

import com.legacy.aether.client.rendering.entity.FloatingBlockRenderer;
import com.legacy.aether.client.rendering.entity.FlyingCowRenderer;
import com.legacy.aether.client.rendering.entity.MoaRenderer;
import com.legacy.aether.client.rendering.entity.SheepuffRenderer;
import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.passive.EntityFlyingCow;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.entities.passive.EntitySheepuff;

public class AetherEntityRenderer
{

	public static void registerRenderers()
	{
		register(EntityMoa.class, (entityRenderDispatcher, context) -> new MoaRenderer(entityRenderDispatcher));
		register(EntityFloatingBlock.class, (entityRenderDispatcher, context) -> new FloatingBlockRenderer(entityRenderDispatcher));
		register(EntityFlyingCow.class, (entityRenderDispatcher, context) -> new FlyingCowRenderer(entityRenderDispatcher));
		register(EntitySheepuff.class, (entityRenderDispatcher, context) -> new SheepuffRenderer(entityRenderDispatcher));
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

	private static void register(Class<? extends Entity> clazz, EntityRendererRegistry.Factory factory)
	{
		EntityRendererRegistry.INSTANCE.register(clazz, factory);
	}

}