package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.FlyingCowModel;
import com.legacy.aether.client.model.FlyingCowWingModel;
import com.legacy.aether.entities.passive.EntityFlyingCow;

public class FlyingCowWingLayer extends FeatureRenderer<EntityFlyingCow, FlyingCowModel> 
{

	private static final Identifier TEXTURE = Aether.locate("textures/entity/flying_cow/wings.png");

	private FlyingCowWingModel model;

	public FlyingCowWingLayer(FeatureRendererContext<EntityFlyingCow, FlyingCowModel> context)
	{
		super(context);

		this.model = new FlyingCowWingModel();
	}

	@Override
	public void render(EntityFlyingCow cow, float limbSwing, float prevLimbSwing, float partialTicks, float rotation, float interpolateRotation, float prevRotationPitch, float scale)
	{
		this.bindTexture(TEXTURE);
		this.model.render(cow, limbSwing, prevLimbSwing, rotation, interpolateRotation, prevRotationPitch, scale);
	}

	@Override
	public boolean method_4200()
	{
		return false;
	}

}