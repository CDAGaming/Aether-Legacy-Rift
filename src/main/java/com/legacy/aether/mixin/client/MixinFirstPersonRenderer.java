package com.legacy.aether.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.FirstPersonRenderer;
import net.minecraft.sortme.OptionMainHand;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.client.rendering.AetherFirstPersonRenderer;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.mojang.blaze3d.platform.GlStateManager;

@Mixin(FirstPersonRenderer.class)
public class MixinFirstPersonRenderer
{

	@Shadow @Final private MinecraftClient client;

	@Inject(method = "renderArm", at = @At("RETURN"))
	private void renderGlove(OptionMainHand handIn, CallbackInfo ci)
	{
		IPlayerAether playerAether = AetherAPI.get(this.client.player);

		if (!playerAether.getAccessoryInventory().getInvStack(AccessoryType.GLOVES).isEmpty())
		{
			GlStateManager.pushMatrix();

			float float_1 = handIn == OptionMainHand.RIGHT ? 1.0F : -1.0F;

			GlStateManager.rotatef(92.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotatef(45.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotatef(float_1 * -41.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.translatef(float_1 * 0.3F, -1.1F, 0.45F);

			if (handIn == OptionMainHand.RIGHT)
			{
				AetherFirstPersonRenderer.renderRightGlove(playerAether, (ItemAccessory) playerAether.getAccessoryInventory().getInvStack(AccessoryType.GLOVES).getItem());
			}
			else
			{
				AetherFirstPersonRenderer.renderLeftGlove(playerAether, (ItemAccessory) playerAether.getAccessoryInventory().getInvStack(AccessoryType.GLOVES).getItem());
			}

			GlStateManager.popMatrix();
		}
	}

	@Inject(method = "method_3219", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;method_4220(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)V"))
	private void renderRightGlove(float float_1, float float_2, OptionMainHand handIn, CallbackInfo ci)
	{
		IPlayerAether playerAether = AetherAPI.get(this.client.player);

		if (!playerAether.getAccessoryInventory().getInvStack(AccessoryType.GLOVES).isEmpty())
		{
			AetherFirstPersonRenderer.renderRightGlove(playerAether, (ItemAccessory) playerAether.getAccessoryInventory().getInvStack(AccessoryType.GLOVES).getItem());

			this.client.getTextureManager().bindTexture(this.client.player.method_3117());
		}
	}

	@Inject(method = "method_3219", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;method_4221(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)V"))
	private void renderLeftGlove(float float_1, float float_2, OptionMainHand handIn, CallbackInfo ci)
	{
		IPlayerAether playerAether = AetherAPI.get(this.client.player);

		if (!playerAether.getAccessoryInventory().getInvStack(AccessoryType.GLOVES).isEmpty())
		{
			AetherFirstPersonRenderer.renderLeftGlove(playerAether, (ItemAccessory) playerAether.getAccessoryInventory().getInvStack(AccessoryType.GLOVES).getItem());

			this.client.getTextureManager().bindTexture(this.client.player.method_3117());
		}
	}

}