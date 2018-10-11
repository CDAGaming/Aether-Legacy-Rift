package com.legacy.aether.mixin;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.portal.BlockAetherPortal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBucket.class)
public class MixinItemBucket {

    @Inject(method = "tryPlaceContainedLiquid", at = @At("HEAD"), cancellable = true)
    public void isAetherPortalActivated(EntityPlayer player, World world, BlockPos pos, RayTraceResult raytrace, CallbackInfoReturnable<Boolean> ci) {
        if (((BlockAetherPortal) BlocksAether.aether_portal).trySpawnPortal(world, pos)) {
            //player.setHeldItem(player.getActiveHand(), new ItemStack(Items.BUCKET));
            ci.setReturnValue(true);
            ci.cancel();
        }
    }

}