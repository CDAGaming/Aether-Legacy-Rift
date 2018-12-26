package com.legacy.aether.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;

@Mixin(ItemSpade.class)
public class MixinItemSpade
{

    @Inject(method = "onItemUse", at = @At("INVOKE"), cancellable = true)
    public void onCreateAetherPath(ItemUseContext context, CallbackInfoReturnable<EnumActionResult> ci)
    {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();

        if (context.getFace() != EnumFacing.DOWN && world.getBlockState(blockpos.up()).isAir())
        {
        	Block block = world.getBlockState(blockpos).getBlock();
            IBlockState iblockstate = BlocksAether.aether_grass_path.getDefaultState();

            if (block == BlocksAether.aether_grass && iblockstate != null)
            {
                EntityPlayer entityplayer = context.getPlayer();
                world.playSound(entityplayer, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (!world.isRemote)
                {
                    world.setBlockState(blockpos, iblockstate, 11);

                    if (entityplayer != null)
                    {
                        context.getItem().damageItem(1, entityplayer);
                    }
                }

                ci.setReturnValue(EnumActionResult.SUCCESS);
            }
        }

    }

}