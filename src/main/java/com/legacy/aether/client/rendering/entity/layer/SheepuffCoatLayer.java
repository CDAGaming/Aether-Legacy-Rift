package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.SheepuffModel;
import com.legacy.aether.client.model.SheepuffWoolModel;
import com.legacy.aether.client.model.SheepuffedModel;
import com.legacy.aether.entities.passive.EntitySheepuff;
import com.mojang.blaze3d.platform.GlStateManager;

public class SheepuffCoatLayer extends FeatureRenderer<EntitySheepuff, SheepuffWoolModel>
{

    private static final Identifier TEXTURE_FUR = Aether.locate("textures/entity/sheepuff/fur.png");

	private SheepuffModel woolModel;

	private SheepuffedModel puffedModel;

	public SheepuffCoatLayer(FeatureRendererContext<EntitySheepuff, SheepuffWoolModel> context)
	{
		super(context);

		this.woolModel = new SheepuffModel();
		this.puffedModel = new SheepuffedModel();
	}

	@Override
	public void render(EntitySheepuff sheepuff, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) 
	{
        if (!sheepuff.isSheared() && !sheepuff.isInvisible())
        {
        	GlStateManager.pushMatrix();

            this.bindTexture(TEXTURE_FUR);

            if (sheepuff.hasCustomName() && "jeb_".equals(sheepuff.getName().getText()))
            {
                int i = sheepuff.age / 25 + sheepuff.getEntityId();
                int j1 = DyeColor.values().length;
                int k = i % j1;
                int l = (i + 1) % j1;
                float f = ((float)(sheepuff.age % 25) + partialTicks) / 25.0F;
                float[] afloat1 = EntitySheepuff.getRgbColor(DyeColor.byId(k));
                float[] afloat2 = EntitySheepuff.getRgbColor(DyeColor.byId(l));
                GlStateManager.color3f(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
            }
            else
            {
                float[] dye = EntitySheepuff.getRgbColor(sheepuff.getColor());
                GlStateManager.color3f(dye[0], dye[1], dye[2]);
            }

            if (sheepuff.isPuffed())
            {
                this.puffedModel.method_17081(this.getModel());
                this.puffedModel.animateModel(sheepuff, p_177141_2_, p_177141_3_, partialTicks);
                this.puffedModel.render(sheepuff, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, scale);
            }
            else
            {
                this.woolModel.method_17081(this.getModel());
                this.woolModel.animateModel(sheepuff, p_177141_2_, p_177141_3_, partialTicks);
                this.woolModel.render(sheepuff, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, scale);
            }

            GlStateManager.popMatrix();
        }
	}

	@Override
	public boolean method_4200()
	{
		return false;
	}

}