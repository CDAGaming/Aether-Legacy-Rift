package com.legacy.aether.item.tool;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAetherHoe extends ItemHoe implements IAetherTool
{

    protected static final Map<Block, IBlockState> convertableBlocks = Maps.<Block, IBlockState>newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.FARMLAND.getDefaultState(), Blocks.GRASS_PATH, Blocks.FARMLAND.getDefaultState(), Blocks.DIRT, Blocks.FARMLAND.getDefaultState(), Blocks.COARSE_DIRT, Blocks.DIRT.getDefaultState()));

	private AetherMaterialType material;

	public ItemAetherHoe(AetherMaterialType material, IItemTier itemTier, float attackSpeed)
	{
		super(itemTier, attackSpeed, new Builder().group(ItemGroup.TOOLS));

		this.material = material;
	}

	public ItemAetherHoe(AetherMaterialType material, EnumRarity rarity, IItemTier itemTier, float attackSpeed)
	{
		super(itemTier, attackSpeed, new Builder().group(ItemGroup.TOOLS).rarity(rarity));

		this.material = material;
	}

	@Override
    public EnumActionResult func_195939_a(ItemUseContext context)
    {
        World world = context.func_195991_k();
        BlockPos blockpos = context.func_195995_a();

        if (context.func_196000_l() != EnumFacing.DOWN && world.isAirBlock(blockpos.up()))
        {
            IBlockState iblockstate = convertableBlocks.get(world.getBlockState(blockpos).getBlock());

            if (iblockstate != null)
            {
                EntityPlayer entityplayer = context.func_195999_j();

                world.playSound(entityplayer, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (!world.isRemote)
                {
                    world.setBlockState(blockpos, iblockstate, 11);

                    if (entityplayer != null)
                    {
                    	context.func_195996_i().damageItem(1, entityplayer);
                    }
                }

                return EnumActionResult.SUCCESS;
            }
        }

        return EnumActionResult.PASS;
    }

	@Override
	public AetherMaterialType getMaterial() 
	{
		return this.material;
	}

}