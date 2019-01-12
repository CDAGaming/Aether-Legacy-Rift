package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.client.model.MoaModel;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.player.PlayerAether;
import com.legacy.aether.player.perks.CustomizedMoaSkin;
import com.mojang.blaze3d.platform.GlStateManager;

public class MoaCustomizerLayer extends FeatureRenderer<EntityMoa, MoaModel>
{

	private static final Identifier TEXTURE_OUTSIDE = Aether.locate("textures/entity/moa/canvas/moa_outside.png");

	private static final Identifier TEXTURE_EYE = Aether.locate("textures/entity/moa/canvas/moa_eye.png");

	private static final Identifier TEXTURE_BODY = Aether.locate("textures/entity/moa/canvas/moa_body.png");

	private static final Identifier TEXTURE_MARKINGS = Aether.locate("textures/entity/moa/canvas/moa_markings.png");

	private static final Identifier TEXTURE_WING = Aether.locate("textures/entity/moa/canvas/moa_wing.png");

	private static final Identifier TEXTURE_WING_MARKINGS = Aether.locate("textures/entity/moa/canvas/moa_wing_markings.png");

	private static final Identifier TEXTURE_UNCHANGED = Aether.locate("textures/entity/moa/canvas/moa_unchanged.png");

	private EntityRenderDispatcher renderManager;

	private MoaModel model;

	public MoaCustomizerLayer(FeatureRendererContext<EntityMoa, MoaModel> class_3883)
	{
		super(class_3883);

		model = class_3883.getModel();
		renderManager = MinecraftClient.getInstance().getEntityRenderManager();
	}

	@Override
	public void render(EntityMoa moa, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		if (!moa.getPassengerList().isEmpty() && moa.getPassengerList().get(0) instanceof PlayerEntity)
		{
			IPlayerAether player = AetherAPI.get((PlayerEntity) moa.getPassengerList().get(0));

			if (player instanceof PlayerAether)
			{
				CustomizedMoaSkin moaSkin = ((PlayerAether)player).donationPerks.getMoaSkin();

				if (moaSkin != null && !moaSkin.shouldUseDefualt())
				{
					GlStateManager.pushMatrix();
					this.renderManager.textureManager.bindTexture(TEXTURE_UNCHANGED);
					this.model.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
					GlStateManager.popMatrix();

					GlStateManager.pushMatrix();
					float red = ((moaSkin.getWingMarkingColor() >> 16) & 0xff) / 255F;
					float green = ((moaSkin.getWingMarkingColor() >> 8) & 0xff) / 255F;
					float blue = (moaSkin.getWingMarkingColor() & 0xff) / 255F;

					this.renderManager.textureManager.bindTexture(TEXTURE_WING_MARKINGS);

					GlStateManager.color3f(red, green, blue);

					this.model.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
					GlStateManager.popMatrix();

					GlStateManager.pushMatrix();
					red = ((moaSkin.getWingColor() >> 16) & 0xff) / 255F;
					green = ((moaSkin.getWingColor() >> 8) & 0xff) / 255F;
					blue = (moaSkin.getWingColor() & 0xff) / 255F;

					this.renderManager.textureManager.bindTexture(TEXTURE_WING);

					GlStateManager.color3f(red, green, blue);

					this.model.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
					GlStateManager.popMatrix();

					GlStateManager.pushMatrix();
					red = ((moaSkin.getMarkingColor() >> 16) & 0xff) / 255F;
					green = ((moaSkin.getMarkingColor() >> 8) & 0xff) / 255F;
					blue = (moaSkin.getMarkingColor() & 0xff) / 255F;

					this.renderManager.textureManager.bindTexture(TEXTURE_MARKINGS);

					GlStateManager.color3f(red, green, blue);

					this.model.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
					GlStateManager.popMatrix();
					
					GlStateManager.pushMatrix();
					red = ((moaSkin.getBodyColor() >> 16) & 0xff) / 255F;
					green = ((moaSkin.getBodyColor() >> 8) & 0xff) / 255F;
					blue = (moaSkin.getBodyColor() & 0xff) / 255F;

					this.renderManager.textureManager.bindTexture(TEXTURE_BODY);

					GlStateManager.color3f(red, green, blue);

					this.model.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
					GlStateManager.popMatrix();

					GlStateManager.pushMatrix();
					red = ((moaSkin.getEyeColor() >> 16) & 0xff) / 255F;
					green = ((moaSkin.getEyeColor() >> 8) & 0xff) / 255F;
					blue = (moaSkin.getEyeColor() & 0xff) / 255F;

					this.renderManager.textureManager.bindTexture(TEXTURE_EYE);

					GlStateManager.color3f(red, green, blue);

					this.model.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
					GlStateManager.popMatrix();

					GlStateManager.pushMatrix();
					red = ((moaSkin.getOutsideColor() >> 16) & 0xff) / 255F;
					green = ((moaSkin.getOutsideColor() >> 8) & 0xff) / 255F;
					blue = (moaSkin.getOutsideColor() & 0xff) / 255F;

					this.renderManager.textureManager.bindTexture(TEXTURE_OUTSIDE);

					GlStateManager.color3f(red, green, blue);

					this.model.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
					
					GlStateManager.popMatrix();
				}
			}
		}
	}

	@Override
	public boolean method_4200()
	{
		return false;
	}

}