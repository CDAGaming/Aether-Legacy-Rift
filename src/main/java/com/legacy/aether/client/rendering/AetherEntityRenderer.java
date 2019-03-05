package com.legacy.aether.client.rendering;

import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.minecraft.entity.Entity;

import com.legacy.aether.client.rendering.entity.AechorPlantRenderer;
import com.legacy.aether.client.rendering.entity.AerbunnyRenderer;
import com.legacy.aether.client.rendering.entity.CockatriceRenderer;
import com.legacy.aether.client.rendering.entity.DartRenderer;
import com.legacy.aether.client.rendering.entity.FloatingBlockRenderer;
import com.legacy.aether.client.rendering.entity.FlyingCowRenderer;
import com.legacy.aether.client.rendering.entity.MoaRenderer;
import com.legacy.aether.client.rendering.entity.PhygRenderer;
import com.legacy.aether.client.rendering.entity.SheepuffRenderer;
import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.hostile.EntityAechorPlant;
import com.legacy.aether.entities.hostile.EntityCockatrice;
import com.legacy.aether.entities.passive.EntityAerbunny;
import com.legacy.aether.entities.passive.EntityFlyingCow;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.entities.passive.EntityPhyg;
import com.legacy.aether.entities.passive.EntitySheepuff;
import com.legacy.aether.entities.projectile.EntityDart;

public class AetherEntityRenderer
{

	public static void registerRenderers()
	{
		register(EntityMoa.class, (entityRenderDispatcher, context) -> new MoaRenderer(entityRenderDispatcher));
		register(EntityFloatingBlock.class, (entityRenderDispatcher, context) -> new FloatingBlockRenderer(entityRenderDispatcher));
		register(EntityFlyingCow.class, (entityRenderDispatcher, context) -> new FlyingCowRenderer(entityRenderDispatcher));
		register(EntitySheepuff.class, (entityRenderDispatcher, context) -> new SheepuffRenderer(entityRenderDispatcher));
		register(EntityAerbunny.class, (entityRenderDispatcher, context) -> new AerbunnyRenderer(entityRenderDispatcher));
		register(EntityAechorPlant.class, (entityRenderDispatcher, context) -> new AechorPlantRenderer(entityRenderDispatcher));
		register(EntityPhyg.class, (entityRenderDispatcher, context) -> new PhygRenderer(entityRenderDispatcher));
		register(EntityCockatrice.class, (entityRenderDispatcher, context) -> new CockatriceRenderer(entityRenderDispatcher));
		register(EntityDart.class, (entityRenderDispatcher, context) -> new DartRenderer(entityRenderDispatcher));
		/*
		entityRenderMap.put(EntityMiniCloud.class, new MiniCloudRenderer(renderManager));
		entityRenderMap.put(EntityAerwhale.class, new AerwhaleRenderer(renderManager));
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