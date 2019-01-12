package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import com.legacy.aether.Aether;
import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.client.model.PlayerWingModel;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.mojang.blaze3d.platform.GlStateManager;

public class AccessoryLayer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>
{

	private static final Identifier TEXTURE_VALKYRIE = Aether.locate("textures/entity/valkyrie/valkyrie.png");

	private boolean slimFit;

	public BipedEntityModel<AbstractClientPlayerEntity> modelMisc;

	public PlayerEntityModel<AbstractClientPlayerEntity> modelPlayer;

	private PlayerWingModel modelWings;

	public AccessoryLayer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, boolean slimFit)
	{
		super(context);

		this.modelPlayer = context.getModel();
		this.slimFit = slimFit;
		this.modelWings = new PlayerWingModel(1.0F);
		this.modelMisc = new BipedEntityModel<AbstractClientPlayerEntity>(1.0F);
	}

	@Override
	public void render(AbstractClientPlayerEntity player, float limbSwing, float prevLimbSwing, float partialTicks, float rotation, float interpolateRotation, float prevRotationPitch, float scale)
	{
		IPlayerAether playerAether = AetherAPI.get(player);

		GlStateManager.pushMatrix();

		this.modelMisc.setAttributes(this.modelPlayer);
		this.modelWings.setAttributes(this.modelPlayer);

		this.modelMisc.animateModel(player, prevLimbSwing, rotation, partialTicks);
		this.modelWings.animateModel(player, prevLimbSwing, rotation, partialTicks);

        GlStateManager.scalef(0.9375F, 0.9375F, 0.9375F);

        if (this.slimFit)
        {
        	GlStateManager.translated(0.0D, 0.024D, 0.0D);
        }

        GlStateManager.enableAlphaTest();

        GlStateManager.translated(0, player.isSneaking() ? 0.25D : 0, 0);

		this.modelMisc.setAngles(player, limbSwing, prevLimbSwing, rotation, interpolateRotation, prevRotationPitch, scale);
		this.modelWings.setAngles(player, limbSwing, prevLimbSwing, rotation, interpolateRotation, prevRotationPitch, scale);

		if (playerAether.getAccessoryInventory().wearingAccessory(new ItemStack(ItemsAether.invisibility_cape)))
		{
			return;
		}

		if (!playerAether.getAccessoryInventory().getInvStack(AccessoryType.PENDANT).isEmpty())
		{
			ItemAccessory pendant = (ItemAccessory) playerAether.getAccessoryInventory().getInvStack(AccessoryType.PENDANT).getItem();

			this.bindTexture(pendant.getTexture());

			int color = pendant.getColor();
			float red = ((color >> 16) & 0xff) / 255F;
			float green = ((color >> 8) & 0xff) / 255F;
			float blue = (color & 0xff) / 255F;

			if (player.hurtTime > 0)
			{
				GlStateManager.color3f(1.0F, 0.5F, 0.5F);
			}
			else
			{
				GlStateManager.color3f(red, green, blue);
			}

			this.modelMisc.body.render(scale);

			GlStateManager.color3f(1.0F, 1.0F, 1.0F);
		}

		if (!playerAether.getAccessoryInventory().getInvStack(AccessoryType.CAPE).isEmpty())
		{
			ItemAccessory cape = (ItemAccessory) playerAether.getAccessoryInventory().getInvStack(AccessoryType.CAPE).getItem();

	        if (player.method_3125() && !player.isInvisible())
	        {
	            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	            GlStateManager.pushMatrix();
	            GlStateManager.translatef(0.0F, 0.0F, 0.125F);

				double double_1 = MathHelper.lerp((double) partialTicks, player.field_7524, player.field_7500) - MathHelper.lerp((double) partialTicks, player.prevX, player.x);
				double double_2 = MathHelper.lerp((double) partialTicks, player.field_7502, player.field_7521) - MathHelper.lerp((double) partialTicks, player.prevY, player.y);
				double double_3 = MathHelper.lerp((double) partialTicks, player.field_7522, player.field_7499) - MathHelper.lerp((double) partialTicks, player.prevZ, player.z);
				float float_8 = player.field_6220 + (player.field_6283 - player.field_6220);
				double double_4 = (double) MathHelper.sin(float_8 * 0.017453292F);
				double double_5 = (double) (-MathHelper.cos(float_8 * 0.017453292F));
				float float_9 = (float) double_2 * 10.0F;
				float_9 = MathHelper.clamp(float_9, -6.0F, 32.0F);
				float float_10 = (float) (double_1 * double_4 + double_3 * double_5) * 100.0F;
				float_10 = MathHelper.clamp(float_10, 0.0F, 150.0F);
				float float_11 = (float) (double_1 * double_5 - double_3 * double_4) * 100.0F;
				float_11 = MathHelper.clamp(float_11, -20.0F, 20.0F);

				if (float_10 < 0.0F)
				{
					float_10 = 0.0F;
				}

				float float_12 = MathHelper.lerp(partialTicks, player.field_7505, player.field_7483);
				float_9 += MathHelper.sin(MathHelper.lerp(partialTicks, player.field_6039, player.field_5973) * 6.0F) * 32.0F * float_12;

				if (player.isSneaking())
				{
					float_9 += 25.0F;
				}

				int color = cape.getColor();

				float red = ((color >> 16) & 0xff) / 255F;
				float green = ((color >> 8) & 0xff) / 255F;
				float blue = (color & 0xff) / 255F;

				if (player.hurtTime > 0)
				{
					GlStateManager.color3f(1.0F, 0.5F, 0.5F);
				}
				else
				{
					GlStateManager.color3f(red, green, blue);
				}

				if (player.getUuid().toString().equals("47ec3a3b-3f41-49b6-b5a0-c39abb7b51ef"))
				{
					this.bindTexture(Aether.locate("textures/armor/accessory_swuff.png"));
				}
				else
				{
					this.bindTexture(cape.getTexture());
				}

	            this.modelPlayer.method_2823(scale);
	            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	            GlStateManager.popMatrix();
	        }
		}

		if (!playerAether.getAccessoryInventory().getInvStack(AccessoryType.GLOVES).isEmpty())
		{
			ItemAccessory gloves = (ItemAccessory) playerAether.getAccessoryInventory().getInvStack(AccessoryType.GLOVES).getItem();

			this.bindTexture(gloves.getTexture());

			int color = gloves.getColor();

			float red = ((color >> 16) & 0xff) / 255F;
			float green = ((color >> 8) & 0xff) / 255F;
			float blue = (color & 0xff) / 255F;

			if (player.hurtTime > 0)
			{
				GlStateManager.color3f(1.0F, 0.5F, 0.5F);
			}
			else
			{
				if (gloves != ItemsAether.phoenix_gloves)
				{
					GlStateManager.color3f(red, green, blue);
				}
			}

			this.modelMisc.armLeft.render(scale);
			this.modelMisc.armRight.render(scale);

			GlStateManager.color3f(1.0F, 1.0F, 1.0F);
		}

		if (!playerAether.getAccessoryInventory().getInvStack(AccessoryType.SHIELD).isEmpty())
		{
			ItemAccessory shield = (ItemAccessory) playerAether.getAccessoryInventory().getInvStack(AccessoryType.SHIELD).getItem();

			this.bindTexture(shield.getTexture());

			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.scalef(1.1F, 1.1F, 1.1F);

			if (player.hurtTime > 0)
			{
				GlStateManager.color3f(1.0F, 0.5F, 0.5F);
			}

			this.modelMisc.render(player, limbSwing, prevLimbSwing, rotation, interpolateRotation, prevRotationPitch, scale);

			GlStateManager.color3f(1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}

		if (playerAether.getAccessoryInventory().isWearingValkyrieSet())
		{
			this.bindTexture(TEXTURE_VALKYRIE);

			this.modelWings.render(player, limbSwing, prevLimbSwing, rotation, interpolateRotation, prevRotationPitch, scale);
			//this.modelWings.wingLeft.render(scale);
			//this.modelWings.wingRight.render(scale);

			if (player.hurtTime > 0)
			{
				GlStateManager.color3f(1.0F, 0.5F, 0.5F);
			}
			else
			{
				GlStateManager.color3f(1.0F, 1.0F, 1.0F);
			}
		}

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		GlStateManager.disableBlend();
		GlStateManager.disableRescaleNormal();

		GlStateManager.popMatrix();
	}

	@Override
	public boolean method_4200() 
	{
		return true;
	}

}