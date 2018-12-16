package com.legacy.aether.blocks.container;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.ContainerProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.tileentity.AetherTileEntity;

public abstract class BlockAetherContainer extends BlockWithEntity
{

	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	protected BlockAetherContainer(Block.Settings propertiesIn)
	{
		super(propertiesIn.needsRandomTick());

		this.setDefaultState(this.getDefaultState().with(POWERED, false));
	}

	@Override
	public void appendProperties(StateFactory.Builder<Block, BlockState> propertyBuilderIn)
	{
		propertyBuilderIn.with(POWERED);
	}

	public static void setState(World worldIn, BlockPos posIn, boolean isActive)
	{
        BlockState iblockstate = worldIn.getBlockState(posIn);
        BlockEntity tileentity = worldIn.getBlockEntity(posIn);

        worldIn.setBlockState(posIn, iblockstate.with(POWERED, isActive), 3);

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setBlockEntity(posIn, tileentity);
        }
	}

	@Override
    public void onPlaced(World worldIn, BlockPos posIn, BlockState stateIn, LivingEntity entityIn, ItemStack stackIn)
    {
    	if (stackIn.hasDisplayName())
        {
            BlockEntity tileentity = worldIn.getBlockEntity(posIn);

            if (tileentity instanceof AetherTileEntity)
            {
                ((AetherTileEntity)tileentity).setCustomName(stackIn.getDisplayName());
            }
        }
    }

	@Override
	public RenderTypeBlock getRenderType(BlockState stateIn)
	{
		return RenderTypeBlock.MODEL;
	}

}