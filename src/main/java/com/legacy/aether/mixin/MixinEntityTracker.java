package com.legacy.aether.mixin;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTracker.class)
public class MixinEntityTracker {

    @Inject(method = "track", at = @At("RETURN"))
    public void trackAetherEntity(Entity entityIn, CallbackInfo ci) {
        if (entityIn instanceof EntityFloatingBlock) {
            ((EntityTracker) (Object) this).track(entityIn, 160, 20, true);
        }
    }

}