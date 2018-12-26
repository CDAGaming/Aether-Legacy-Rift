package com.legacy.aether.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.natural.BlockAetherLog;

@Mixin(ItemAxe.class)
public class MixinItemAxe
{

    @Inject(method = "onItemUse", at = @At("INVOKE"), cancellable = true)
    public void onCreateStripAxe(ItemUseContext context, CallbackInfoReturnable<EnumActionResult> ci)
    {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        IBlockState iblockstate = world.getBlockState(blockpos);
        Block block = iblockstate.getBlock() == BlocksAether.skyroot_log ? BlocksAether.stripped_skyroot_log : iblockstate.getBlock() == BlocksAether.golden_oak_log ? BlocksAether.stripped_golden_oak_log : null;

        if (block != null)
        {
            EntityPlayer entityplayer = context.getPlayer();
            world.playSound(entityplayer, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);

            if (!world.isRemote)
            {
            	IBlockState stripState = block.getDefaultState().with(BlockRotatedPillar.AXIS, iblockstate.get(BlockRotatedPillar.AXIS));

            	if (block instanceof BlockAetherLog)
            	{
            		stripState = stripState.with(BlockAetherLog.DOUBLE_DROP, iblockstate.get(BlockAetherLog.DOUBLE_DROP));
            	}

                world.setBlockState(blockpos, stripState, 11);

                if (entityplayer != null)
                {
                    context.getItem().damageItem(1, entityplayer);
                }
            }

            ci.setReturnValue(EnumActionResult.SUCCESS);
        }
    }

}
