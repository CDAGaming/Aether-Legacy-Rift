package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.SheepuffWoolModel;
import com.legacy.aether.client.rendering.entity.layer.SheepuffCoatLayer;
import com.legacy.aether.entities.passive.EntitySheepuff;

public class SheepuffRenderer extends MobEntityRenderer<EntitySheepuff, SheepuffWoolModel>
{

	public SheepuffRenderer(EntityRenderDispatcher renderManager) 
	{
		super(renderManager, new SheepuffWoolModel(), 0.7F);

		this.addFeature(new SheepuffCoatLayer(this));
	}

	@Override
	protected Identifier getTexture(EntitySheepuff entity)
	{
		return Aether.locate("textures/entity/sheepuff/sheepuff.png");
	}

}