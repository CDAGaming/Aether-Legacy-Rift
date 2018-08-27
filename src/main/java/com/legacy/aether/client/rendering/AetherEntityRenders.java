package com.legacy.aether.client.rendering;

import java.util.Map;

import org.dimdev.rift.listener.client.EntityRendererAdder;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

import com.legacy.aether.client.rendering.entity.AechorPlantRenderer;
import com.legacy.aether.client.rendering.entity.FloatingBlockRenderer;
import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.hostile.EntityAechorPlant;

public class AetherEntityRenders implements EntityRendererAdder
{

	@Override
	public void addEntityRenderers(Map<Class<? extends Entity>, Render<? extends Entity>> entityRenderMap, RenderManager renderManager) 
	{
		entityRenderMap.put(EntityAechorPlant.class, new AechorPlantRenderer(renderManager));
		entityRenderMap.put(EntityFloatingBlock.class, new FloatingBlockRenderer(renderManager));
	}

}
