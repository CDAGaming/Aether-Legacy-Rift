package com.legacy.aether.mixin.client;

import java.util.Map;

import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.item.armor.ItemAetherArmor;

@Mixin(value = LayerArmorBase.class)
public abstract class MixinLayerArmorBase
{

	@Shadow private float alpha;

	@Shadow private float colorR;

	@Shadow private float colorG;

	@Shadow private float colorB;

	@Shadow @Final private static Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP;

    @Inject(method = "getArmorResource(Lnet/minecraft/item/ItemArmor;ZLjava/lang/String;)Lnet/minecraft/util/ResourceLocation;", at = @At("RETURN"), cancellable = true)
	private void getAetherArmorResource(ItemArmor armorIn, boolean isLeggings, String extraIn, CallbackInfoReturnable<ResourceLocation> info)
	{
    	if (armorIn instanceof ItemAetherArmor)
    	{
    		ItemAetherArmor aetherArmor = (ItemAetherArmor) armorIn;

            this.colorR = (float)(aetherArmor.getColor() >> 16 & 255) / 255.0F;
            this.colorG = (float)(aetherArmor.getColor() >> 8 & 255) / 255.0F;
            this.colorB = (float)(aetherArmor.getColor() & 255) / 255.0F;

        	String s = aetherArmor.getArmorName().isEmpty() ? (isLeggings ? "textures/models/armor/iron_layer_2.png" : "textures/models/armor/iron_layer_1.png") : "aether_legacy:textures/armor/" + aetherArmor.getArmorName() + "_" + (isLeggings ? "layer_2" : "layer_1") + ".png";

    	    ResourceLocation resourcelocation = ARMOR_TEXTURE_RES_MAP.get(s);

    	    if (resourcelocation == null)
    	    {
    	    	resourcelocation = new ResourceLocation(s);
    	    	ARMOR_TEXTURE_RES_MAP.put(s, resourcelocation);
    	    }

    	    info.setReturnValue(resourcelocation);
    	}
    	else
    	{
    		this.colorR = 1.0F;
    		this.colorG = 1.0F;
    		this.colorB = 1.0F;
    		this.alpha = 1.0F;
    	}
	}

}