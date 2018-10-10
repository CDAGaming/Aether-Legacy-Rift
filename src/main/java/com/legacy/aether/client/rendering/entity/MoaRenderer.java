package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

import com.legacy.aether.client.model.MoaModel;
import com.legacy.aether.client.rendering.entity.layer.MoaCustomizerLayer;
import com.legacy.aether.client.rendering.entity.layer.MoaSaddleLayer;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;

public class MoaRenderer extends RenderLiving<EntityMoa>
{

	public MoaRenderer(RenderManager renderManager)
	{
		super(renderManager, new MoaModel(0.0F), 1.0F);

		this.addLayer(new MoaCustomizerLayer(renderManager, (MoaModel) this.getMainModel()));
		this.addLayer(new MoaSaddleLayer(this));
	}

	@Override
	protected float handleRotationFloat(EntityMoa moa, float f)
	{
		float f1 = moa.prevWingRotation + (moa.wingRotation - moa.prevWingRotation) * f;
		float f2 = moa.prevDestPos + (moa.destPos - moa.prevDestPos) * f;

		return (MathHelper.sin(f1) + 1.0F) * f2;
	}

	@Override
	protected void preRenderCallback(EntityMoa moa, float f)
	{
		float moaScale = moa.isChild() ?  1.0F : 1.8F;

		GL11.glScalef(moaScale, moaScale, moaScale);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMoa entity)
	{
		EntityMoa moa = (EntityMoa)entity;
		
		if (moa.isBeingRidden() && moa.getPassengers().get(0) instanceof EntityPlayer)
		{
			PlayerAether player = ((IEntityPlayerAether)moa.getPassengers().get(0)).getPlayerAether();

			if (player != null && !player.donationPerks.getMoaSkin().shouldUseDefualt())
			{
				return null;
			}
		}

		return moa.getMoaType().getTexture(false);
	}

}