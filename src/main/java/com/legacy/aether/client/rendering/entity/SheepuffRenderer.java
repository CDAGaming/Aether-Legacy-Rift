package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.SheepuffWoolModel;
import com.legacy.aether.client.rendering.entity.layer.SheepuffCoatLayer;
import com.legacy.aether.entities.passive.EntitySheepuff;

public class SheepuffRenderer extends RenderLiving<EntitySheepuff>
{

	public SheepuffRenderer(RenderManager renderManager) 
	{
		super(renderManager, new SheepuffWoolModel(), 0.7F);

		this.addLayer(new SheepuffCoatLayer(renderManager, this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySheepuff entity) 
	{
		return Aether.locate("textures/entity/sheepuff/sheepuff.png");
	}

}