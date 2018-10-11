package com.legacy.aether.mixin.client;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.rendering.color.AetherColor;
import net.minecraft.client.renderer.color.BlockColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockColors.class)
public class MixinBlockColors {

    @Inject(method = "init", at = @At("RETURN"), cancellable = true)
    private static void colorInit(CallbackInfoReturnable<BlockColors> info) {
        BlockColors colors = info.getReturnValue();

        colors.register(new AetherColor(), BlocksAether.blue_aercloud, BlocksAether.golden_aercloud,
                BlocksAether.white_dyed_aercloud, BlocksAether.orange_dyed_aercloud, BlocksAether.magenta_dyed_aercloud, BlocksAether.light_blue_dyed_aercloud,
                BlocksAether.yellow_dyed_aercloud, BlocksAether.lime_dyed_aercloud, BlocksAether.pink_dyed_aercloud, BlocksAether.grey_dyed_aercloud,
                BlocksAether.light_grey_dyed_aercloud, BlocksAether.cyan_dyed_aercloud, BlocksAether.purple_dyed_aercloud, BlocksAether.blue_dyed_aercloud,
                BlocksAether.brown_dyed_aercloud, BlocksAether.green_dyed_aercloud, BlocksAether.red_dyed_aercloud, BlocksAether.black_dyed_aercloud);

        info.setReturnValue(colors);
    }

}