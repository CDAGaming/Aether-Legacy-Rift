package com.legacy.aether.item.tool;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.legacy.aether.item.util.AetherTier;

public class ItemAetherHoe extends HoeItem implements IAetherTool
{

    protected static final Map<Block, BlockState> convertableBlocks = Maps.<Block, BlockState>newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.FARMLAND.getDefaultState(), Blocks.GRASS_PATH, Blocks.FARMLAND.getDefaultState(), Blocks.DIRT, Blocks.FARMLAND.getDefaultState(), Blocks.COARSE_DIRT, Blocks.DIRT.getDefaultState()));

	private AetherTier material;

	public ItemAetherHoe(AetherTier material, float attackSpeed)
	{
		super(material.getDefaultTier(), attackSpeed, new Settings().itemGroup(ItemGroup.TOOLS));

		this.material = material;
	}

	public ItemAetherHoe(AetherTier material, Rarity rarity, float attackSpeed)
	{
		super(material.getDefaultTier(), attackSpeed, new Settings().itemGroup(ItemGroup.TOOLS).rarity(rarity));

		this.material = material;
	}

	@Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();

        if (context.getFacing() != Direction.DOWN && world.isAir(blockpos.up()))
        {
            BlockState iblockstate = convertableBlocks.get(world.getBlockState(blockpos).getBlock());

            if (iblockstate != null)
            {
                PlayerEntity entityplayer = context.getPlayer();

                world.playSound(entityplayer, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCK, 1.0F, 1.0F);

                if (!world.isRemote)
                {
                    world.setBlockState(blockpos, iblockstate, 11);

                    if (entityplayer != null)
                    {
                    	context.getItemStack().applyDamage(1, entityplayer);
                    }
                }

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

	@Override
	public AetherTier getMaterial() 
	{
		return this.material;
	}

}