package com.legacy.aether.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.MapRenderer;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.MapUpdateClientPacket;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.map.MapState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.util.MapDimensionData;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler
{

	@Shadow private MinecraftClient client;

	@Inject(method = "onMapUpdate", at = @At("RETURN"))
	public void onMapUpdateDimension(MapUpdateClientPacket packet, CallbackInfo ci)
	{
		MapRenderer mapRenderer = this.client.gameRenderer.getMapRenderer();
		String string_1 = FilledMapItem.method_17440(packet.getId());
		MapState mapState = FilledMapItem.method_7997(this.client.world, string_1);

		mapState.dimension = ((MapDimensionData)packet).getDimension();

		mapRenderer.updateTexture(mapState);
	}

}