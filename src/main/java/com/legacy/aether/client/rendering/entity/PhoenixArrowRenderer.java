package com.legacy.aether.client.rendering.entity;

import com.legacy.aether.Aether;
import com.legacy.aether.entities.projectile.EntityPhoenixArrow;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class PhoenixArrowRenderer extends RenderArrow<EntityPhoenixArrow>
{

	public PhoenixArrowRenderer(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPhoenixArrow entity)
	{
        return Aether.locate("textures/entity/projectile/flaming_arrow.png");
	}

}