package com.legacy.aether.mixin.client;

import com.legacy.aether.Aether;
import com.legacy.aether.inventory.InventoryAccessories;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(TextureMap.class)
public class MixinTextureMap {

    @Shadow
    @Final
    private Set<ResourceLocation> field_195427_i;

    /**
     * @author Modding Legacy
     */
    @Overwrite
    public void func_195426_a(IResourceManager resourceManager, Iterable<ResourceLocation> resourceList) {
        this.field_195427_i.clear();

        resourceList.forEach((p_195423_2_) ->
                ((TextureMap) (Object) this).registerSprite(resourceManager, p_195423_2_));

        for (int i = 0; i < InventoryAccessories.EMPTY_SLOT_NAMES.length; ++i) {
            ((TextureMap) (Object) this).registerSprite(resourceManager, Aether.locate("item/slots/" + InventoryAccessories.EMPTY_SLOT_NAMES[i]));
        }

        ((TextureMap) (Object) this).func_195421_b(resourceManager);
    }

}