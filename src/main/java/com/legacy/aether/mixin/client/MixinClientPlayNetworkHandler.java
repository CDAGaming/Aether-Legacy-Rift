package com.legacy.aether.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.util.MapDimensionData;

import net.minecraft.class_330;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.MapUpdateClientPacket;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.map.MapState;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler
{

	@Shadow private MinecraftClient client;

	@Inject(method = "onMapUpdate", at = @At("RETURN"))
	public void onMapUpdateDimension(MapUpdateClientPacket packet, CallbackInfo ci)
	{
		class_330 class_330_1 = this.client.worldRenderer.method_3194();
		String string_1 = FilledMapItem.method_17440(packet.getId());
		MapState mapState_1 = FilledMapItem.method_7997(this.client.world, string_1);

		mapState_1.dimension = ((MapDimensionData)packet).getDimension();

		class_330_1.method_1769(mapState_1);
	}

}