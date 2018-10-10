package com.legacy.aether.mixin.client;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.rendering.color.AetherColor;
import com.legacy.aether.item.ItemsAether;

@Mixin(ItemColors.class)
public class MixinItemColors 
{

    @Inject(method = "init", at = @At("RETURN"), cancellable = true)
	private static void colorInit(BlockColors blockColors, CallbackInfoReturnable<ItemColors> info)
	{
    	ItemColors colors = info.getReturnValue();

    	colors.register(new AetherColor(), BlocksAether.blue_aercloud, BlocksAether.golden_aercloud, 
    			BlocksAether.white_dyed_aercloud, BlocksAether.orange_dyed_aercloud, BlocksAether.magenta_dyed_aercloud, BlocksAether.light_blue_dyed_aercloud,
    			BlocksAether.yellow_dyed_aercloud, BlocksAether.lime_dyed_aercloud, BlocksAether.pink_dyed_aercloud, BlocksAether.grey_dyed_aercloud, 
    			BlocksAether.light_grey_dyed_aercloud, BlocksAether.cyan_dyed_aercloud, BlocksAether.purple_dyed_aercloud, BlocksAether.blue_dyed_aercloud, 
    			BlocksAether.brown_dyed_aercloud, BlocksAether.green_dyed_aercloud, BlocksAether.red_dyed_aercloud, BlocksAether.black_dyed_aercloud,
    			ItemsAether.moa_egg, ItemsAether.leather_gloves, ItemsAether.iron_gloves, ItemsAether.golden_gloves, ItemsAether.chain_gloves, ItemsAether.diamond_gloves,
    	    	ItemsAether.zanite_gloves, ItemsAether.gravitite_gloves, ItemsAether.neptune_gloves, ItemsAether.phoenix_gloves, ItemsAether.obsidian_gloves, ItemsAether.valkyrie_gloves,
    	    	ItemsAether.iron_ring, ItemsAether.golden_ring, ItemsAether.zanite_ring, ItemsAether.ice_ring, ItemsAether.iron_pendant, ItemsAether.golden_pendant, ItemsAether.zanite_pendant,
    	    	ItemsAether.ice_pendant, ItemsAether.red_cape, ItemsAether.blue_cape, ItemsAether.yellow_cape, ItemsAether.white_cape, ItemsAether.swet_cape, ItemsAether.invisibility_cape,
    	    	ItemsAether.agility_cape, ItemsAether.golden_feather, ItemsAether.regeneration_stone, ItemsAether.iron_bubble,
    	    	ItemsAether.zanite_helmet, ItemsAether.zanite_chestplate, ItemsAether.zanite_leggings, ItemsAether.zanite_boots,
    	    	ItemsAether.gravitite_helmet, ItemsAether.gravitite_chestplate, ItemsAether.gravitite_leggings, ItemsAether.gravitite_boots,
    	    	ItemsAether.neptune_helmet, ItemsAether.neptune_chestplate, ItemsAether.neptune_leggings, ItemsAether.neptune_boots,
    	    	ItemsAether.obsidian_helmet, ItemsAether.obsidian_chestplate, ItemsAether.obsidian_leggings, ItemsAether.obsidian_boots);

    	info.setReturnValue(colors);
	}

}