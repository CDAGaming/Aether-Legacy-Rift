package com.legacy.aether.mixin.client;

import com.legacy.aether.client.rendering.AetherFirstPersonRenderer;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FirstPersonRenderer.class)
public class MixinFirstPersonRenderer {

    @Shadow
    @Final
    private Minecraft mc;

    @Shadow
    @Final
    private RenderManager renderManager;

    @Inject(method = "renderArm", at = @At("RETURN"))
    private void renderGlove(EnumHandSide handIn, CallbackInfo ci) {
        PlayerAether playerAether = ((IEntityPlayerAether) this.mc.player).getPlayerAether();
        ItemStack stack = playerAether.getAccessories().getStackInSlot(6);

        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();
            float f = handIn == EnumHandSide.RIGHT ? 1.0F : -1.0F;
            GlStateManager.rotate(92.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(f * -41.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(f * 0.3F, -1.1F, 0.45F);

            if (handIn == EnumHandSide.RIGHT) {
                AetherFirstPersonRenderer.renderRightGlove((IEntityPlayerAether) this.mc.player, (ItemAccessory) stack.getItem());
            } else {
                AetherFirstPersonRenderer.renderLeftGlove((IEntityPlayerAether) this.mc.player, (ItemAccessory) stack.getItem());
            }

            GlStateManager.popMatrix();
        }
    }

    @Inject(method = "renderArmFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderPlayer;renderRightArm(Lnet/minecraft/client/entity/AbstractClientPlayer;)V"))
    private void renderRightGlove(float p_187456_1_, float p_187456_2_, EnumHandSide p_187456_3_, CallbackInfo ci) {
        PlayerAether playerAether = ((IEntityPlayerAether) this.mc.player).getPlayerAether();
        ItemStack stack = playerAether.getAccessories().getStackInSlot(6);

        if (!stack.isEmpty()) {
            AetherFirstPersonRenderer.renderRightGlove((IEntityPlayerAether) this.mc.player, (ItemAccessory) stack.getItem());

            this.mc.getTextureManager().bindTexture(this.mc.player.getLocationSkin());
        }
    }

    @Inject(method = "renderArmFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderPlayer;renderLeftArm(Lnet/minecraft/client/entity/AbstractClientPlayer;)V"))
    private void renderLeftGlove(float p_187456_1_, float p_187456_2_, EnumHandSide p_187456_3_, CallbackInfo ci) {
        PlayerAether playerAether = ((IEntityPlayerAether) this.mc.player).getPlayerAether();
        ItemStack stack = playerAether.getAccessories().getStackInSlot(6);

        if (!stack.isEmpty()) {
            AetherFirstPersonRenderer.renderLeftGlove((IEntityPlayerAether) this.mc.player, (ItemAccessory) stack.getItem());

            this.mc.getTextureManager().bindTexture(this.mc.player.getLocationSkin());
        }
    }

}