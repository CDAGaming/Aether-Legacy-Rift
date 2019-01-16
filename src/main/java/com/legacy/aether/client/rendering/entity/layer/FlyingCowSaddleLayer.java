package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.FlyingCowModel;
import com.legacy.aether.entities.passive.EntityFlyingCow;

public class FlyingCowSaddleLayer extends FeatureRenderer<EntityFlyingCow, FlyingCowModel> 
{

    private static final Identifier TEXTURE = Aether.locate("textures/entity/flying_cow/saddle.png");

    private final FlyingCowModel cowModel = new FlyingCowModel(0.5F);

    public FlyingCowSaddleLayer(FeatureRendererContext<EntityFlyingCow, FlyingCowModel> context)
    {
    	super(context);
    }

    @Override
    public void render(EntityFlyingCow entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.getSaddled())
        {
            this.bindTexture(TEXTURE);
            this.cowModel.method_17081(this.getModel());
            this.cowModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean method_4200()
    {
        return false;
    }

}