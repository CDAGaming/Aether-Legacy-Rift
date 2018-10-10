package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelPig;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.client.rendering.entity.layer.PhygSaddleLayer;
import com.legacy.aether.client.rendering.entity.layer.PhygWingLayer;
import com.legacy.aether.entities.passive.EntityPhyg;

public class PhygRenderer extends RenderLiving<EntityPhyg>
{

	private static final ResourceLocation TEXTURE = Aether.locate("textures/entity/phyg/phyg.png");

	public PhygRenderer(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, new ModelPig(), 0.7F);

		this.addLayer(new PhygSaddleLayer(this));
		this.addLayer(new PhygWingLayer(rendermanagerIn));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPhyg entity)
	{
		return TEXTURE;
	}

}