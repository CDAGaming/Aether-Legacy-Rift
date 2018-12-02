package com.legacy.aether.blocks.container;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.tileentity.AetherTileEntity;

public abstract class BlockAetherContainer extends BlockContainer
{

	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	protected BlockAetherContainer(Properties propertiesIn)
	{
		super(propertiesIn.needsRandomTick());

		this.setDefaultState(this.getDefaultState().with(POWERED, false));
	}

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, IBlockState> propertyBuilderIn)
	{
		propertyBuilderIn.add(POWERED);
	}

	public static void setState(World worldIn, BlockPos posIn, boolean isActive)
	{
        IBlockState iblockstate = worldIn.getBlockState(posIn);
        TileEntity tileentity = worldIn.getTileEntity(posIn);

        worldIn.setBlockState(posIn, iblockstate.with(POWERED, isActive), 3);

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(posIn, tileentity);
        }
	}

	@Override
    public void onBlockPlacedBy(World worldIn, BlockPos posIn, IBlockState stateIn, EntityLivingBase entityIn, ItemStack stackIn)
    {
    	if (stackIn.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(posIn);

            if (tileentity instanceof AetherTileEntity)
            {
                ((AetherTileEntity)tileentity).setCustomName(stackIn.getDisplayName());
            }
        }
    }

	@Override
	public EnumBlockRenderType getRenderType(IBlockState stateIn)
	{
		return EnumBlockRenderType.MODEL;
	}

}