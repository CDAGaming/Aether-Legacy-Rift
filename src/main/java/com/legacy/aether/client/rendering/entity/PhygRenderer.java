package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.rendering.entity.layer.PhygSaddleLayer;
import com.legacy.aether.client.rendering.entity.layer.PhygWingLayer;
import com.legacy.aether.entities.passive.EntityPhyg;

public class PhygRenderer extends MobEntityRenderer<EntityPhyg, PigEntityModel<EntityPhyg>>
{

	private static final Identifier TEXTURE = Aether.locate("textures/entity/phyg/phyg.png");

	public PhygRenderer(EntityRenderDispatcher rendermanagerIn)
	{
		super(rendermanagerIn, new PigEntityModel<EntityPhyg>(), 0.7F);

		this.addFeature(new PhygWingLayer(this));
		this.addFeature(new PhygSaddleLayer(this));
	}

	@Override
	protected Identifier getTexture(EntityPhyg entity)
	{
		return TEXTURE;
	}

}