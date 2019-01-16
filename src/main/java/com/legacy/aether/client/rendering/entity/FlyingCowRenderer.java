package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.FlyingCowModel;
import com.legacy.aether.client.rendering.entity.layer.FlyingCowSaddleLayer;
import com.legacy.aether.client.rendering.entity.layer.FlyingCowWingLayer;
import com.legacy.aether.entities.passive.EntityFlyingCow;

public class FlyingCowRenderer extends MobEntityRenderer<EntityFlyingCow, FlyingCowModel>
{

	private static final Identifier TEXTURE = Aether.locate("textures/entity/flying_cow/flying_cow.png");

	public FlyingCowRenderer(EntityRenderDispatcher renderManager)
	{
		super(renderManager, new FlyingCowModel(0.0F), 0.7F);

		this.addFeature(new FlyingCowWingLayer(this));
		this.addFeature(new FlyingCowSaddleLayer(this));
	}

	@Override
	protected Identifier getTexture(EntityFlyingCow entity) 
	{
		return TEXTURE;
	}

}