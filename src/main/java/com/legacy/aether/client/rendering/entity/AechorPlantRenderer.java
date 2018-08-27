package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.AechorPlantModel;
import com.legacy.aether.entities.hostile.EntityAechorPlant;

public class AechorPlantRenderer extends RenderLiving<EntityAechorPlant>
{

    private static final ResourceLocation TEXTURE = Aether.locate("textures/entity/aechor_plant/aechor_plant.png");

    private AechorPlantModel model;

    public AechorPlantRenderer(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new AechorPlantModel(), 0.3F);

        this.model = (AechorPlantModel) this.mainModel;
    }

    @Override
    protected void preRenderCallback(EntityAechorPlant entityIn, float partialTicks)
    {
        float f1 = (float)Math.sin((double)entityIn.sinage);
        float f3;

        if (entityIn.hurtTime > 0)
        {
            f1 *= 0.45F;
            f1 -= 0.125F;
            f3 = 1.75F + (float)Math.sin((double)(entityIn.sinage + 2.0F)) * 1.5F;
        }
        else
        {
            f1 *= 0.125F;
            f3 = 1.75F;
        }

        this.model.sinage = f1;
        this.model.sinage2 = f3;

        float f2 = 0.625F + (float)entityIn.size / 6.0F;

        this.model.size = f2;
        this.shadowSize = f2 - 0.25F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAechorPlant entityIn)
    {
        return TEXTURE;
    }

}