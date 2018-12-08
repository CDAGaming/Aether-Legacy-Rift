package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.MiniCloudModel;
import com.legacy.aether.entities.passive.EntityMiniCloud;

public class MiniCloudRenderer extends RenderLiving<EntityMiniCloud>
{

	public MiniCloudRenderer(RenderManager rendermanagerIn) 
	{
		super(rendermanagerIn, new MiniCloudModel(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMiniCloud entity) 
	{
		return Aether.locate("textures/entity/mini_cloud/mini_cloud.png");
	}

}