package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.client.renderer.entity.model.ModelPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.AetherWingsModel;
import com.legacy.aether.inventory.InventoryAccessories;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;

public class AccessoriesLayer implements LayerRenderer<AbstractClientPlayer>
{

	private static final ResourceLocation TEXTURE_VALKYRIE = Aether.locate("textures/entity/valkyrie/valkyrie.png");

	private boolean slimFit;

	public ModelBiped modelMisc;

	public ModelPlayer modelPlayer;

	private AetherWingsModel modelWings;

	public AccessoriesLayer(ModelPlayer modelPlayer, boolean slimFit)
	{
		this.modelPlayer = modelPlayer;
		this.slimFit = slimFit;
		this.modelWings = new AetherWingsModel(1.0F);
		this.modelMisc = new ModelBiped(1.0F);
	}

	@Override
	public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float prevLimbSwing, float partialTicks, float rotation, float interpolateRotation, float prevRotationPitch, float scale)
	{
		PlayerAether playerAether = ((IEntityPlayerAether)player).getPlayerAether();
		InventoryAccessories accessories = playerAether.getAccessories();

		if (accessories == null)
		{
			return;
		}

		GlStateManager.pushMatrix();

		this.modelMisc.setModelAttributes(this.modelPlayer);
		this.modelWings.setModelAttributes(this.modelPlayer);

		this.modelMisc.setLivingAnimations(player, prevLimbSwing, rotation, partialTicks);
		this.modelWings.setLivingAnimations(player, prevLimbSwing, rotation, partialTicks);

        GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);

        if (this.slimFit)
        {
        	GlStateManager.translate(0.0D, 0.024D, 0.0D);
        }

        GlStateManager.enableAlpha();

        GlStateManager.translate(0, player.isSneaking() ? 0.25D : 0, 0);

		this.modelMisc.setRotationAngles(limbSwing, prevLimbSwing, rotation, interpolateRotation, prevRotationPitch, scale, player);
		this.modelWings.setRotationAngles(limbSwing, prevLimbSwing, rotation, interpolateRotation, prevRotationPitch, scale, player);

		if (accessories.stacks.get(0).getItem() instanceof ItemAccessory)
		{
			ItemAccessory pendant = ((ItemAccessory) (accessories.stacks.get(0).getItem()));
			Minecraft.getMinecraft().getTextureManager().bindTexture(pendant.getAccessoryTexture());
			int color = pendant.getColor();
			float red = ((color >> 16) & 0xff) / 255F;
			float green = ((color >> 8) & 0xff) / 255F;
			float blue = (color & 0xff) / 255F;

			if (player.hurtTime > 0)
			{
				GlStateManager.color(1.0F, 0.5F, 0.5F);
			}
			else
			{
				GlStateManager.color(red, green, blue);
			}

			this.modelMisc.bipedBody.render(scale);

			GlStateManager.color(1.0F, 1.0F, 1.0F);
		}

		if (accessories.stacks.get(1).getItem() instanceof ItemAccessory && accessories.stacks.get(1).getItem() != ItemsAether.invisibility_cape)
		{
			ItemAccessory cape = ((ItemAccessory) (accessories.stacks.get(1).getItem()));

	        if (player.hasPlayerInfo() && !player.isInvisible())
	        {
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            GlStateManager.pushMatrix();
	            GlStateManager.translate(0.0F, 0.0F, 0.125F);
	            double d0 = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * (double)partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks);
	            double d1 = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * (double)partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks);
	            double d2 = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * (double)partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks);
	            float f = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
	            double d3 = (double)MathHelper.sin(f * (float)Math.PI / 180.0F);
	            double d4 = (double)(-MathHelper.cos(f * (float)Math.PI / 180.0F));
	            float f1 = (float)d1 * 10.0F;
	            f1 = MathHelper.clamp(f1, -6.0F, 32.0F);
	            float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
	            float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;

	            if (f2 < 0.0F)
	            {
	                f2 = 0.0F;
	            }

	            float f4 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
	            f1 = f1 + MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;

	            if (player.isSneaking())
	            {
	                f1 += 25.0F;
	            }

	            GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
	            GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
	            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

				int color = cape.getColor();

				float red = ((color >> 16) & 0xff) / 255F;
				float green = ((color >> 8) & 0xff) / 255F;
				float blue = (color & 0xff) / 255F;

				if (player.hurtTime > 0)
				{
					GlStateManager.color(1.0F, 0.5F, 0.5F);
				}
				else
				{
					GlStateManager.color(red, green, blue);
				}

				if (player.getUniqueID().toString().equals("47ec3a3b-3f41-49b6-b5a0-c39abb7b51ef"))
				{
					Minecraft.getMinecraft().getTextureManager().bindTexture(Aether.locate("textures/armor/accessory_swuff.png"));
				}
				else
				{
					Minecraft.getMinecraft().getTextureManager().bindTexture(cape.getAccessoryTexture());
				}

	            this.modelPlayer.renderCape(scale);
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            GlStateManager.popMatrix();
	        }
		}

		if (accessories.stacks.get(6).getItem() instanceof ItemAccessory)
		{
			ItemAccessory gloves = (ItemAccessory) accessories.stacks.get(6).getItem();
			Minecraft.getMinecraft().getTextureManager().bindTexture(gloves.getAccessoryTexture());

			int color = gloves.getColor();

			float red = ((color >> 16) & 0xff) / 255F;
			float green = ((color >> 8) & 0xff) / 255F;
			float blue = (color & 0xff) / 255F;

			if (player.hurtTime > 0)
			{
				GlStateManager.color(1.0F, 0.5F, 0.5F);
			}
			else
			{
				if (gloves != ItemsAether.phoenix_gloves)
				{
					GlStateManager.color(red, green, blue);
				}
			}

			this.modelMisc.bipedLeftArm.render(scale);
			this.modelMisc.bipedRightArm.render(scale);

			GlStateManager.color(1.0F, 1.0F, 1.0F);
		}

		if (accessories.stacks.get(2).getItem() instanceof ItemAccessory)
		{
			ItemAccessory shield = (ItemAccessory) accessories.stacks.get(2).getItem();

			Minecraft.getMinecraft().getTextureManager().bindTexture(shield.getAccessoryTexture());

			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.scale(1.1F, 1.1F, 1.1F);

			if (player.hurtTime > 0)
			{
				GlStateManager.color(1.0F, 0.5F, 0.5F);
			}

			this.modelMisc.bipedHead.render(scale);
			this.modelMisc.bipedBody.render(scale);
			this.modelMisc.bipedLeftArm.render(scale);
			this.modelMisc.bipedRightArm.render(scale);
			this.modelMisc.bipedLeftLeg.render(scale);
			this.modelMisc.bipedRightLeg.render(scale);

			GlStateManager.color(1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}

		if (playerAether.isWearingValkyrieSet())
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE_VALKYRIE);

			this.modelWings.wingLeft.render(scale);
			this.modelWings.wingRight.render(scale);

			if (player.hurtResistantTime > 0)
			{
				GlStateManager.color(1.0F, 0.5F, 0.5F);
			}
			else
			{
				GlStateManager.color(1.0F, 1.0F, 1.0F);
			}
		}

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();

		GlStateManager.popMatrix();
	}

	@Override
	public boolean shouldCombineTextures() 
	{
		return true;
	}

}