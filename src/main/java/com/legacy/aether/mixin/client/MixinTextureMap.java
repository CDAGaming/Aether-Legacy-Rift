package com.legacy.aether.mixin.client;

import java.util.Set;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.legacy.aether.Aether;
import com.legacy.aether.inventory.InventoryAccessories;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextureMap.class)
public class MixinTextureMap 
{

	@Shadow
    @Final private Set<ResourceLocation> sprites;

	/**
     * @author Modding Legacy
     */
    @Overwrite
    public void stitch(IResourceManager resourceManager, Iterable<ResourceLocation> resourceList)
    {
        this.sprites.clear();

        resourceList.forEach((p_195423_2_) ->
                ((TextureMap) (Object) this).registerSprite(resourceManager, p_195423_2_));

		for (int i = 0; i < InventoryAccessories.EMPTY_SLOT_NAMES.length; ++i)
		{
			((TextureMap) (Object) this).registerSprite(resourceManager, Aether.locate("item/slots/" + InventoryAccessories.EMPTY_SLOT_NAMES[i]));
		}

        ((TextureMap) (Object) this).stitch(resourceManager);
    }

}