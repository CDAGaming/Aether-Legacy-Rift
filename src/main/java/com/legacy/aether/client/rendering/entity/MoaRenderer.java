package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.render.entity.EntityMobRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

import com.legacy.aether.client.model.MoaModel;
import com.legacy.aether.client.rendering.entity.layer.MoaCustomizerLayer;
import com.legacy.aether.client.rendering.entity.layer.MoaSaddleLayer;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;

public class MoaRenderer extends LivingEntityRenderer<EntityMoa, MoaModel>
{

	public MoaRenderer(EntityRenderDispatcher renderManager)
	{
		super(renderManager, new MoaModel(0.0F), 1.0F);

		//this.addLayer(new MoaCustomizerLayer(renderManager, (MoaModel) this.method_4038()));
		this.addLayer(new MoaSaddleLayer(this));
	}

	@Override
	protected float method_4044(EntityMoa moa, float f)
	{
		float f1 = moa.prevWingRotation + (moa.wingRotation - moa.prevWingRotation) * f;
		float f2 = moa.prevDestPos + (moa.destPos - moa.prevDestPos) * f;

		return (MathHelper.sin(f1) + 1.0F) * f2;
	}

	@Override
	protected void method_4042(EntityMoa moa, float f)
	{
		float moaScale = moa.isChild() ?  1.0F : 1.8F;

		GL11.glScalef(moaScale, moaScale, moaScale);
	}

	@Override
	protected Identifier getTexture(EntityMoa entity)
	{
		EntityMoa moa = (EntityMoa)entity;
		
		if (moa.hasPassengers() && moa.getPassengerList().get(0) instanceof PlayerEntity)
		{
			PlayerAether player = ((IEntityPlayerAether)moa.getPassengerList().get(0)).getPlayerAether();

			if (player != null && !player.donationPerks.getMoaSkin().shouldUseDefualt())
			{
				return null;
			}
		}

		return moa.getMoaType().getTexture(false);
	}

}