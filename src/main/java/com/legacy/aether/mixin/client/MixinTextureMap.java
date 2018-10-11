package com.legacy.aether.mixin.client;

import java.util.Set;

import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.legacy.aether.Aether;
import com.legacy.aether.inventory.InventoryAccessories;

@Mixin(TextureMap.class)
public class MixinTextureMap 
{

	@Final private Set<ResourceLocation> field_195427_i;

	/**
     * @author Modding Legacy
     */
    @Overwrite
    public void func_195426_a(IResourceManager resourceManager, Iterable<ResourceLocation> resourceList)
    {
        this.field_195427_i.clear();

        resourceList.forEach((p_195423_2_) ->
        {
        	((TextureMap) (ITextureObject) this).registerSprite(resourceManager, p_195423_2_);
        });

		for (int i = 0; i < InventoryAccessories.EMPTY_SLOT_NAMES.length; ++i)
		{
			((TextureMap) (ITextureObject) this).registerSprite(resourceManager, Aether.locate("item/slots/" + InventoryAccessories.EMPTY_SLOT_NAMES[i]));
		}

        ((TextureMap) (ITextureObject) this).func_195421_b(resourceManager);
    }

}