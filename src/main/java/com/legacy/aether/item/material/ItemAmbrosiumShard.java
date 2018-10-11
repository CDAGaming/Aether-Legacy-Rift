package com.legacy.aether.item.material;

import com.legacy.aether.blocks.BlocksAether;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemAmbrosiumShard extends Item {

    public ItemAmbrosiumShard() {
        super(new Builder().group(ItemGroup.MISC));
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
        if (context.getWorld().getBlockState(context.getPos()).getBlock() == BlocksAether.aether_grass) {
            if (!context.getPlayer().isCreative()) {
                context.getItem().shrink(1);
            }

            context.getWorld().setBlockState(context.getPos(), BlocksAether.enchanted_aether_grass.getDefaultState());

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);

        if (playerIn.shouldHeal()) {
            if (!playerIn.isCreative()) {
                heldItem.shrink(1);
            }

            playerIn.heal(2.0F);

            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, heldItem);
        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, heldItem);
    }

}