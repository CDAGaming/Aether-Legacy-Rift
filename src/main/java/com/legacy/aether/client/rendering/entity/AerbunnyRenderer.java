package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import org.lwjgl.opengl.GL11;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.AerbunnyModel;
import com.legacy.aether.entities.passive.EntityAerbunny;
import com.mojang.blaze3d.platform.GlStateManager;

public class AerbunnyRenderer extends MobEntityRenderer<EntityAerbunny, AerbunnyModel>
{

	private static final Identifier TEXTURE = Aether.locate("textures/entity/aerbunny/aerbunny.png");

	public AerbunnyRenderer(EntityRenderDispatcher renderManager)
	{
		super(renderManager, new AerbunnyModel(), 0.3F);
	}

	protected void rotateAerbunny(EntityAerbunny bunny)
	{
		if (bunny.getRiddenEntity() != null)
		{
			GlStateManager.translatef(0.0F, -0.2F, 0.0F);
		}

		if (!bunny.onGround)
		{
			if (bunny.getVelocity().y > 0.5D)
			{
				GL11.glRotatef(15.0F, -1.0F, 0.0F, 0.0F);
			}
			else if (bunny.getVelocity().y < -0.5D)
			{
				GL11.glRotatef(-15.0F, -1.0F, 0.0F, 0.0F);
			}
			else
			{
				GL11.glRotatef((float) (bunny.getVelocity().y * 30.0D), -1.0F, 0.0F, 0.0F);
			}
		}
	}

	@Override
	protected void method_4042(EntityAerbunny entitybunny, float f)
	{
		this.rotateAerbunny(entitybunny);
	}

	@Override
	protected Identifier getTexture(EntityAerbunny entity)
	{
		return TEXTURE;
	}
}
