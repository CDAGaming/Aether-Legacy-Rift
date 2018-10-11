package com.legacy.aether.item.food;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemLifeShard extends Item {

    public ItemLifeShard() {
        super(new Builder().maxStackSize(1).group(ItemGroup.MISC).rarity(ItemsAether.AETHER_LOOT));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        PlayerAether playerAether = ((IEntityPlayerAether) playerIn).getPlayerAether();
        ItemStack heldItem = playerIn.getHeldItem(handIn);

        if (worldIn.isRemote) {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, heldItem);
        }

        if (playerAether.getShardsUsed() < 10) {
            playerAether.increaseMaxHP();
            heldItem.shrink(1);

            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, heldItem);
        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, heldItem);
    }

}