package com.legacy.aether.mixin.client;

import net.minecraft.block.MaterialColor;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.item.map.MapState;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.legacy.aether.client.rendering.map.AetherMap;
import com.legacy.aether.world.WorldAether;

@Mixin(targets = "net.minecraft.class_330$class_331")
public class MixinMapRenderer
{

	@Shadow @Final private MapState field_2046;

	@Shadow @Final private NativeImageBackedTexture field_2048;

	@Overwrite
	private void method_1776()
	{
		boolean isAether = this.field_2046.dimension == WorldAether.THE_AETHER;

		for (int int_1 = 0; int_1 < 128; ++int_1)
		{
			for (int int_2 = 0; int_2 < 128; ++int_2)
			{
				int int_3 = int_2 + int_1 * 128;
				int int_4 = this.field_2046.colorArray[int_3] & 255;

				if (int_4 / 4 == 0)
				{
					this.field_2048.getImage().setPixelRGBA(int_2, int_1, (int_3 + int_3 / 128 & 1) * 8 + 16 << 24);
				}
				else
				{
					int color = MaterialColor.COLORS[int_4 / 4].getRenderColor(int_4 & 3);

					if (isAether)
					{
						color = AetherMap.getColor(MaterialColor.COLORS[int_4 / 4], int_4 & 3);
					}

					this.field_2048.getImage().setPixelRGBA(int_2, int_1, color);
				}
			}
		}

		this.field_2048.upload();
	}

}