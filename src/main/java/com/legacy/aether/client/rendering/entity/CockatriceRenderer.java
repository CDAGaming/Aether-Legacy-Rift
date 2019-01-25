package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.CockatriceModel;
import com.legacy.aether.entities.hostile.EntityCockatrice;

public class CockatriceRenderer extends MobEntityRenderer<EntityCockatrice, CockatriceModel>
{

    private static final Identifier TEXTURE = Aether.locate("textures/entity/cockatrice/cockatrice.png");

	public CockatriceRenderer(EntityRenderDispatcher rendermanagerIn) 
	{
		super(rendermanagerIn, new CockatriceModel(), 1.0F);
	}

	@Override
    protected float method_4045(EntityCockatrice cockatrice, float f)
    {
        float f1 = cockatrice.prevWingRotation + (cockatrice.wingRotation - cockatrice.prevWingRotation) * f;
        float f2 = cockatrice.prevDestPos + (cockatrice.destPos - cockatrice.prevDestPos) * f;

        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

	@Override
    protected void method_4042(EntityCockatrice cockatrice, float f)
    {
        GL11.glScalef(1.8F, 1.8F, 1.8F);
    }

	@Override
	protected Identifier getTexture(EntityCockatrice cockatrice)
	{
		return TEXTURE;
	}

}