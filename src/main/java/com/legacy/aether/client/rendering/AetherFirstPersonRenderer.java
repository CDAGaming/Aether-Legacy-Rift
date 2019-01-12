package com.legacy.aether.client.rendering;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;

import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.mojang.blaze3d.platform.GlStateManager;

public class AetherFirstPersonRenderer
{

	private static BipedEntityModel<AbstractClientPlayerEntity> gloveModel = new BipedEntityModel<AbstractClientPlayerEntity>(0.01F);

	private static BipedEntityModel<AbstractClientPlayerEntity> slimGloveModel = new PlayerEntityModel<AbstractClientPlayerEntity>(0.01F, true);

	public static void renderRightGlove(IPlayerAether player, ItemAccessory gloves)
    {
		boolean isSlim = ((AbstractClientPlayerEntity)player.getPlayer()).method_3121().equals("slim");
		MinecraftClient.getInstance().getEntityRenderManager().textureManager.bindTexture(gloves.getTexture(isSlim));

		int colour = gloves.getColor();
		float red = ((colour >> 16) & 0xff) / 255F;
		float green = ((colour >> 8) & 0xff) / 255F;
		float blue = (colour & 0xff) / 255F;

		if (gloves != ItemsAether.phoenix_gloves)
		{
			GlStateManager.color4f(red, green, blue, 1.0F);
		}

        GlStateManager.enableBlend();
        getModel(isSlim).swingProgress = 0.0F;
        getModel(isSlim).isSneaking = false;
        getModel(isSlim).setAngles((AbstractClientPlayerEntity) player.getPlayer(), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        getModel(isSlim).armRight.pitch = 0.0F;
        getModel(isSlim).armRight.render(0.0625F);
        GlStateManager.disableBlend();

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

	public static void renderLeftGlove(IPlayerAether player, ItemAccessory gloves)
    {
		boolean isSlim = ((AbstractClientPlayerEntity)player.getPlayer()).method_3121().equals("slim");
		MinecraftClient.getInstance().getEntityRenderManager().textureManager.bindTexture(gloves.getTexture(isSlim));

		int colour = gloves.getColor();
		float red = ((colour >> 16) & 0xff) / 255F;
		float green = ((colour >> 8) & 0xff) / 255F;
		float blue = (colour & 0xff) / 255F;

		if (gloves != ItemsAether.phoenix_gloves)
		{
			GlStateManager.color3f(red, green, blue);
		}

        GlStateManager.enableBlend();
        getModel(isSlim).swingProgress = 0.0F;
        getModel(isSlim).isSneaking = false;
        getModel(isSlim).setAngles((AbstractClientPlayerEntity) player.getPlayer(), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        getModel(isSlim).armLeft.pitch = 0.0F;
        getModel(isSlim).armLeft.render(0.0625F);
        GlStateManager.disableBlend();

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

	private static BipedEntityModel<AbstractClientPlayerEntity> getModel(boolean isSlim)
	{
		return isSlim ? slimGloveModel : gloveModel;
	}

}