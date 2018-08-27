package com.legacy.aether.mixin.client;

import net.minecraft.client.renderer.color.BlockColors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.rendering.color.AetherColor;

@Mixin(BlockColors.class)
public class MixinBlockColors 
{

    @Inject(method = "init", at = @At("RETURN"), cancellable = true)
	private static void colorInit(CallbackInfoReturnable<BlockColors> info)
	{
    	BlockColors colors = info.getReturnValue();

    	colors.registerBlockColorHandler(new AetherColor(), BlocksAether.blue_aercloud, BlocksAether.golden_aercloud);

    	info.setReturnValue(colors);
	}

}