package com.legacy.aether.item.tool;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.item.AetherItemGroup;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.util.AetherTier;

public class ItemAetherAxe extends AxeItem implements IAetherTool
{

	private AetherTier material;

	public float[] zaniteHarvestLevels = new float[] {2F, 4F, 6F, 8F, 12F};

	public ItemAetherAxe(AetherTier material, float damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Settings().itemGroup(AetherItemGroup.AETHER_TOOLS));

		this.material = material;
	}

	public ItemAetherAxe(AetherTier material, Rarity rarity, float damageVsEntity, float attackSpeed)
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Settings().itemGroup(AetherItemGroup.AETHER_TOOLS).rarity(rarity));

		this.material = material;
	}

	@Override
	public float getBlockBreakingSpeed(ItemStack stack, BlockState state)
	{
		float original = super.getBlockBreakingSpeed(stack, state);

		if (this.getMaterial() == AetherTier.Zanite)
		{
			return this.calculateIncrease(stack, original);
		}

		return original;
	}

	@Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World world = context.getWorld();
        BlockPos blockpos = context.getBlockPos();
        BlockState iblockstate = world.getBlockState(blockpos);

        if (this.getMaterial() == AetherTier.Gravitite && this.getBlockBreakingSpeed(context.getItemStack(), iblockstate) == this.blockBreakingSpeed)
        {
        	if (world.isAir(blockpos.up()) && !world.isClient)
        	{
        		EntityFloatingBlock floatingBlock = new EntityFloatingBlock(world, (double)blockpos.getX() + 0.5D, (double)blockpos.getY(), (double)blockpos.getZ() + 0.5D, world.getBlockState(blockpos));

        		world.spawnEntity(floatingBlock);
        	}
        	else
        	{
            	return ActionResult.PASS;
        	}

        	return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

	@Override
	public boolean onBlockBroken(ItemStack stackIn, World worldIn, BlockState stateIn, BlockPos posIn, LivingEntity entityIn)
	{
		if (this.getMaterial() == AetherTier.Holystone && !worldIn.isClient && worldIn.getRandom().nextInt(100) <= 5)
		{
			ItemEntity entityItem = new ItemEntity(worldIn, posIn.getX(), posIn.getY(), posIn.getZ());
			
			entityItem.setStack(new ItemStack(ItemsAether.ambrosium_shard, 1));

			worldIn.spawnEntity(entityItem);
		}

		return super.onBlockBroken(stackIn, worldIn, stateIn, posIn, entityIn);
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
    		if (isBetween(tool.getDamage(), current, tool.getDamage() - 50))
    		{
    			return this.zaniteHarvestLevels[4];
    		}
    		else if (isBetween(tool.getDamage() - 51, current, tool.getDamage() - 110))
    		{
    			return this.zaniteHarvestLevels[3];
    		}
    		else if (isBetween(tool.getDamage() - 111, current, tool.getDamage() - 200))
    		{
    			return this.zaniteHarvestLevels[2];
    		}
    		else if (isBetween(tool.getDamage() - 201, current, tool.getDamage() - 239))
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