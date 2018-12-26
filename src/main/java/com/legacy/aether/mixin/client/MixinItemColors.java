package com.legacy.aether.mixin.client;

import net.minecraft.client.render.block.BlockColorMap;
import net.minecraft.client.render.item.ItemColorMap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.rendering.color.AetherColor;
import com.legacy.aether.item.ItemsAether;

@Mixin(ItemColorMap.class)
public class MixinItemColors 
{

    @Inject(method = "method_1706", at = @At("RETURN"), cancellable = true)
	private static void colorInit(CallbackInfoReturnable<ItemColorMap> info)
	{
    	ItemColorMap colors = info.getReturnValue();

    	colors.method_1708(new AetherColor(), BlocksAether.blue_aercloud, BlocksAether.golden_aercloud,
    			BlocksAether.white_dyed_aercloud, BlocksAether.orange_dyed_aercloud, BlocksAether.magenta_dyed_aercloud, BlocksAether.light_blue_dyed_aercloud,
    			BlocksAether.yellow_dyed_aercloud, BlocksAether.lime_dyed_aercloud, BlocksAether.pink_dyed_aercloud, BlocksAether.grey_dyed_aercloud, 
    			BlocksAether.light_grey_dyed_aercloud, BlocksAether.cyan_dyed_aercloud, BlocksAether.purple_dyed_aercloud, BlocksAether.blue_dyed_aercloud, 
    			BlocksAether.brown_dyed_aercloud, BlocksAether.green_dyed_aercloud, BlocksAether.red_dyed_aercloud, BlocksAether.black_dyed_aercloud,
    	    	ItemsAether.zanite_helmet, ItemsAether.zanite_chestplate, ItemsAether.zanite_leggings, ItemsAether.zanite_boots,
    	    	ItemsAether.gravitite_helmet, ItemsAether.gravitite_chestplate, ItemsAether.gravitite_leggings, ItemsAether.gravitite_boots,
    	    	ItemsAether.neptune_helmet, ItemsAether.neptune_chestplate, ItemsAether.neptune_leggings, ItemsAether.neptune_boots,
    	    	ItemsAether.obsidian_helmet, ItemsAether.obsidian_chestplate, ItemsAether.obsidian_leggings, ItemsAether.obsidian_boots);

    	info.setReturnValue(colors);
	}

}