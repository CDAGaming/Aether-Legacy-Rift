package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.PhygWingModel;
import com.legacy.aether.entities.passive.EntityPhyg;

public class PhygWingLayer implements LayerRenderer<EntityPhyg> 
{

	private static final ResourceLocation TEXTURE_WINGS = Aether.locate("textures/entity/phyg/wings.png");

	private RenderManager renderManager;

	private PhygWingModel model;

	public PhygWingLayer(RenderManager manager)
	{
		this.renderManager = manager;
		this.model = new PhygWingModel();
	}

	@Override
	public void render(EntityPhyg phyg, float limbSwing, float prevLimbSwing, float partialTicks, float rotation, float interpolateRotation, float prevRotationPitch, float scale)
	{
		this.renderManager.textureManager.bindTexture(TEXTURE_WINGS);
		this.model.render(phyg, limbSwing, prevLimbSwing, rotation, interpolateRotation, prevRotationPitch, scale);
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

}