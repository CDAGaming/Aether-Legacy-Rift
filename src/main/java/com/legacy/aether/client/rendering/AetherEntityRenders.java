package com.legacy.aether.client.rendering;

import java.util.Map;

import org.dimdev.rift.listener.client.EntityRendererAdder;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

import com.legacy.aether.client.rendering.entity.AechorPlantRenderer;
import com.legacy.aether.client.rendering.entity.AerbunnyRenderer;
import com.legacy.aether.client.rendering.entity.AerwhaleRenderer;
import com.legacy.aether.client.rendering.entity.ChestMimicRenderer;
import com.legacy.aether.client.rendering.entity.CockatriceRenderer;
import com.legacy.aether.client.rendering.entity.DartRenderer;
import com.legacy.aether.client.rendering.entity.FloatingBlockRenderer;
import com.legacy.aether.client.rendering.entity.FlyingCowRenderer;
import com.legacy.aether.client.rendering.entity.MoaRenderer;
import com.legacy.aether.client.rendering.entity.PhygRenderer;
import com.legacy.aether.client.rendering.entity.SheepuffRenderer;
import com.legacy.aether.client.rendering.entity.WhirlwindRenderer;
import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.hostile.EntityAechorPlant;
import com.legacy.aether.entities.hostile.EntityChestMimic;
import com.legacy.aether.entities.hostile.EntityCockatrice;
import com.legacy.aether.entities.hostile.EntityWhirlwind;
import com.legacy.aether.entities.passive.EntityAerbunny;
import com.legacy.aether.entities.passive.EntityAerwhale;
import com.legacy.aether.entities.passive.EntityFlyingCow;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.entities.passive.EntityPhyg;
import com.legacy.aether.entities.passive.EntitySheepuff;
import com.legacy.aether.entities.projectile.EntityDart;

public class AetherEntityRenders implements EntityRendererAdder
{

	@Override
	public void addEntityRenderers(Map<Class<? extends Entity>, Render<? extends Entity>> entityRenderMap, RenderManager renderManager) 
	{
		entityRenderMap.put(EntityDart.class, new DartRenderer(renderManager));
		entityRenderMap.put(EntityAerwhale.class, new AerwhaleRenderer(renderManager));
		entityRenderMap.put(EntityAerbunny.class, new AerbunnyRenderer(renderManager));
		entityRenderMap.put(EntityMoa.class, new MoaRenderer(renderManager));
		entityRenderMap.put(EntityAechorPlant.class, new AechorPlantRenderer(renderManager));
		entityRenderMap.put(EntityFlyingCow.class, new FlyingCowRenderer(renderManager));
		entityRenderMap.put(EntityPhyg.class, new PhygRenderer(renderManager));
		entityRenderMap.put(EntitySheepuff.class, new SheepuffRenderer(renderManager));
		entityRenderMap.put(EntityCockatrice.class, new CockatriceRenderer(renderManager));
		entityRenderMap.put(EntityChestMimic.class, new ChestMimicRenderer(renderManager));
		entityRenderMap.put(EntityFloatingBlock.class, new FloatingBlockRenderer(renderManager));
		entityRenderMap.put(EntityWhirlwind.class, new WhirlwindRenderer(renderManager));
	}

}
