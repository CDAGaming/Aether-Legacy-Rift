package com.legacy.aether.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.client.rendering.map.AetherMap;
import com.legacy.aether.world.WorldAether;

import net.minecraft.block.MaterialColor;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.item.map.MapState;

@Mixin(targets = "net.minecraft.client.gui.MapRenderer$MapTexture")
public class MixinMapRenderer
{

	@Shadow @Final private MapState mapState;

	@Shadow @Final private NativeImageBackedTexture texture;

	@Inject(method = "updateTexture", at = @At("RETURN"))
	private void updateAetherTexture(CallbackInfo ci)
	{
		boolean isAether = this.mapState.dimension == WorldAether.THE_AETHER;

		for (int int_1 = 0; int_1 < 128; ++int_1)
		{
			for (int int_2 = 0; int_2 < 128; ++int_2)
			{
				int int_3 = int_2 + int_1 * 128;
				int int_4 = this.mapState.colorArray[int_3] & 255;

				if (int_4 / 4 == 0)
				{
					this.texture.getImage().setPixelRGBA(int_2, int_1, (int_3 + int_3 / 128 & 1) * 8 + 16 << 24);
				}
				else
				{
					int color = MaterialColor.COLORS[int_4 / 4].getRenderColor(int_4 & 3);

					if (isAether)
					{
						color = AetherMap.getColor(MaterialColor.COLORS[int_4 / 4], int_4 & 3);
					}

					this.texture.getImage().setPixelRGBA(int_2, int_1, color);
				}
			}
		}

		this.texture.upload();
	}

}