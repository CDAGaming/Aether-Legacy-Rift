package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.PhygWingModel;
import com.legacy.aether.entities.passive.EntityPhyg;

public class PhygWingLayer extends FeatureRenderer<EntityPhyg, PigEntityModel<EntityPhyg>>
{

	private static final Identifier TEXTURE_WINGS = Aether.locate("textures/entity/phyg/wings.png");

	private final PhygWingModel model = new PhygWingModel();

	public PhygWingLayer(FeatureRendererContext<EntityPhyg, PigEntityModel<EntityPhyg>> context)
	{
		super(context);
	}

	@Override
	public void render(EntityPhyg phyg, float limbSwing, float prevLimbSwing, float partialTicks, float rotation, float interpolateRotation, float prevRotationPitch, float scale) 
	{
		this.bindTexture(TEXTURE_WINGS);

		this.model.render(phyg, limbSwing, prevLimbSwing, rotation, interpolateRotation, prevRotationPitch, scale);
	}

	@Override
	public boolean method_4200()
	{
		return false;
	}

}