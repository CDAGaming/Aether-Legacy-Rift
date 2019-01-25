package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.util.Identifier;

import com.legacy.aether.entities.passive.EntityPhyg;

public class PhygSaddleLayer extends FeatureRenderer<EntityPhyg, PigEntityModel<EntityPhyg>>
{

    private static final Identifier TEXTURE = new Identifier("textures/entity/pig/pig_saddle.png");

    private final PigEntityModel<EntityPhyg> model = new PigEntityModel<EntityPhyg>(0.5F);

    public PhygSaddleLayer(FeatureRendererContext<EntityPhyg, PigEntityModel<EntityPhyg>> context)
    {
    	super(context);
    }

	@Override
    public void render(EntityPhyg phyg, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (phyg.getSaddled())
        {
        	this.bindTexture(TEXTURE);

        	this.getModel().method_17081(this.model);
        	//this.model.method_17081(this.getModel());
        	this.model.render(phyg, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

	@Override
    public boolean method_4200()
    {
        return false;
    }

}