package com.legacy.aether.client.rendering;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.legacy.aether.player.IEntityPlayerAether;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.client.renderer.entity.model.ModelPlayer;

public class AetherFirstPersonRenderer {

    private static ModelBiped gloveModel = new ModelBiped(0.01F);

    private static ModelBiped slimGloveModel = new ModelPlayer(0.01F, true);

    public static void renderRightGlove(IEntityPlayerAether hook, ItemAccessory gloves) {
        boolean isSlim = ((AbstractClientPlayer) hook.getInstance()).getSkinType().equals("slim");
        Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(gloves.getAccessoryTexture(isSlim));

        int colour = gloves.getColor();
        float red = ((colour >> 16) & 0xff) / 255F;
        float green = ((colour >> 8) & 0xff) / 255F;
        float blue = (colour & 0xff) / 255F;

        if (gloves != ItemsAether.phoenix_gloves) {
            GlStateManager.color(red, green, blue, 1.0F);
        }

        GlStateManager.enableBlend();
        getModel(isSlim).swingProgress = 0.0F;
        getModel(isSlim).isSneak = false;
        getModel(isSlim).setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, hook.getInstance());
        getModel(isSlim).bipedRightArm.rotateAngleX = 0.0F;
        getModel(isSlim).bipedRightArm.render(0.0625F);
        GlStateManager.disableBlend();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void renderLeftGlove(IEntityPlayerAether hook, ItemAccessory gloves) {
        boolean isSlim = ((AbstractClientPlayer) hook.getInstance()).getSkinType().equals("slim");
        Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(gloves.getAccessoryTexture(isSlim));

        int colour = gloves.getColor();
        float red = ((colour >> 16) & 0xff) / 255F;
        float green = ((colour >> 8) & 0xff) / 255F;
        float blue = (colour & 0xff) / 255F;

        if (gloves != ItemsAether.phoenix_gloves) {
            GlStateManager.color(red, green, blue);
        }

        GlStateManager.enableBlend();
        getModel(isSlim).isSneak = false;
        getModel(isSlim).swingProgress = 0.0F;
        getModel(isSlim).setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, hook.getInstance());
        getModel(isSlim).bipedLeftArm.rotateAngleX = 0.0F;
        getModel(isSlim).bipedLeftArm.render(0.0625F);
        GlStateManager.disableBlend();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private static ModelBiped getModel(boolean isSlim) {
        return isSlim ? slimGloveModel : gloveModel;
    }

}