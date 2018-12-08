package com.legacy.aether.mixin;

import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.legacy.aether.player.IEntityHook;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase implements IEntityHook
{

	@Shadow
    protected boolean isJumping;

    public boolean checkIsJumping()
    {
    	return this.isJumping;
    }

}