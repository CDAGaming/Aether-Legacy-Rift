package com.legacy.aether.item.tool;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.util.AetherTier;

public class ItemAetherAxe extends ItemAxe implements IAetherTool
{

	private AetherTier material;

	public float[] zaniteHarvestLevels = new float[] {2F, 4F, 6F, 8F, 12F};

	public ItemAetherAxe(AetherTier material, float damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Properties().group(ItemGroup.TOOLS));

		this.material = material;
	}

	public ItemAetherAxe(AetherTier material, EnumRarity rarity, float damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Properties().group(ItemGroup.TOOLS).rarity(rarity));

		this.material = material;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		float original = super.getDestroySpeed(stack, state);

		if (this.getMaterial() == AetherTier.Zanite)
		{
			return this.calculateIncrease(stack, original);
		}

		return original;
	}

	@Override
    public EnumActionResult onItemUse(ItemUseContext context)
    {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        IBlockState iblockstate = world.getBlockState(blockpos);

        if (context.isPlacerSneaking() && this.getMaterial() == AetherTier.Gravitite && this.getDestroySpeed(context.getItem(), iblockstate) == this.efficiency)
        {
        	if (world.isAirBlock(blockpos.up()))
        	{
        		EntityFloatingBlock floatingBlock = new EntityFloatingBlock(world, (double)blockpos.getX() + 0.5D, (double)blockpos.getY(), (double)blockpos.getZ() + 0.5D, world.getBlockState(blockpos));

        		if (!world.isRemote)
        		{
            		world.spawnEntity(floatingBlock);
        		}

            	return EnumActionResult.SUCCESS;
        	}
        }

        return super.onItemUse(context);
    }

	@Override
	public boolean onBlockDestroyed(ItemStack stackIn, World worldIn, IBlockState stateIn, BlockPos posIn, EntityLivingBase entityIn)
	{
		if (this.getMaterial() == AetherTier.Holystone && !worldIn.isRemote && worldIn.getRandom().nextInt(100) <= 5)
		{
			EntityItem entityItem = new EntityItem(worldIn, posIn.getX(), posIn.getY(), posIn.getZ());
			
			entityItem.setItem(new ItemStack(ItemsAether.ambrosium_shard, 1));

			worldIn.spawnEntity(entityItem);
		}

		return super.onBlockDestroyed(stackIn, worldIn, stateIn, posIn, entityIn);
	}

	@Override
	public AetherTier getMaterial() 
	{
		return this.material;
	}

    private float calculateIncrease(ItemStack tool, float original)
    {
    	boolean AllowedCalculations = !(original != 4.0F);
    	int current = tool.getDamage();

    	if (AllowedCalculations)
    	{
    		if (isBetween(tool.getMaxDamage(), current, tool.getMaxDamage() - 50))
    		{
    			return this.zaniteHarvestLevels[4];
    		}
    		else if (isBetween(tool.getMaxDamage() - 51, current, tool.getMaxDamage() - 110))
    		{
    			return this.zaniteHarvestLevels[3];
    		}
    		else if (isBetween(tool.getMaxDamage() - 111, current, tool.getMaxDamage() - 200))
    		{
    			return this.zaniteHarvestLevels[2];
    		}
    		else if (isBetween(tool.getMaxDamage() - 201, current, tool.getMaxDamage() - 239))
    		{
    			return this.zaniteHarvestLevels[1];
    		}
    		else
    		{
    			return this.zaniteHarvestLevels[0];
    		}
    	}
    	else
    	{
    		return 1.0F;
    	}
    }

    private boolean isBetween(int max, int origin, int min)
    {
    	return origin <= max && origin >= min ? true : false;
    }

}