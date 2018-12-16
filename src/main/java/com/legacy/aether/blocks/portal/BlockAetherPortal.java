package com.legacy.aether.blocks.portal;

import java.util.Random;

import com.google.common.cache.LoadingCache;
import com.legacy.aether.player.IEntityPlayerAether;

import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockProxy;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import static net.minecraft.block.PillarBlock.AXIS;

public class BlockAetherPortal extends PortalBlock
{

	public BlockAetherPortal()
	{
		super(Settings.of(Material.GLASS).strength(-1.0F, 900000F));
	}

	@Override
	public VoxelShape getBoundingShape(BlockState blockstateIn, BlockView blockReaderIn, BlockPos posIn)
	{
		return VoxelShapes.cube(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	}

	// TODO: method_10352 maybe for 1.14?
	@Override
	public boolean trySpawnPortal(IWorld worldIn, BlockPos pos)
	{
		AetherPortalSize aetherportal$size = new AetherPortalSize(worldIn, pos, Direction.Axis.X);

        if (aetherportal$size.isValid() && aetherportal$size.portalBlockCount == 0)
        {
        	aetherportal$size.placePortalBlocks();

            return true;
        }
        else
        {
        	AetherPortalSize aetherportal$size1 = new AetherPortalSize(worldIn, pos, Direction.Axis.Z);

            if (aetherportal$size1.isValid() && aetherportal$size1.portalBlockCount == 0)
            {
            	aetherportal$size1.placePortalBlocks();

                return true;
            }
            else
            {
                return false;
            }
        }
	}

	@Override
    public void neighborUpdate(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        Direction.Axis enumfacing$axis = state.get(AXIS);

        if (enumfacing$axis == Direction.Axis.X)
        {
        	AetherPortalSize blockportal$size = new AetherPortalSize(worldIn, pos, Direction.Axis.X);

            if (!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height)
            {
            	worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
        else if (enumfacing$axis == Direction.Axis.Z)
        {
            AetherPortalSize blockportal$size1 = new AetherPortalSize(worldIn, pos, Direction.Axis.Z);

            if (!blockportal$size1.isValid() || blockportal$size1.portalBlockCount < blockportal$size1.width * blockportal$size1.height)
            {
            	worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

	@Override
    public BlockPattern.Result method_10350(IWorld worldIn, BlockPos p_181089_2_)
    {
        Direction.Axis enumfacing$axis = Direction.Axis.Z;
        AetherPortalSize blockportal$size = new AetherPortalSize(worldIn, p_181089_2_, Direction.Axis.X);
        LoadingCache<BlockPos, BlockProxy> loadingcache = BlockPattern.makeCache(worldIn, true);

        if (!blockportal$size.isValid())
        {
            enumfacing$axis = Direction.Axis.X;
            blockportal$size = new AetherPortalSize(worldIn, p_181089_2_, Direction.Axis.Z);
        }

        if (!blockportal$size.isValid())
        {
            return new BlockPattern.Result(p_181089_2_, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
        }
        else
        {
            int[] aint = new int[Direction.AxisDirection.values().length];
            Direction enumfacing = blockportal$size.rightDir.rotateYCounterclockwise();
            BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.getHeight() - 1);

            for (Direction.AxisDirection enumfacing$axisdirection : Direction.AxisDirection.values())
            {
                BlockPattern.Result blockpattern$patternhelper = new BlockPattern.Result(enumfacing.getDirection() == enumfacing$axisdirection ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), Direction.get(enumfacing$axisdirection, enumfacing$axis), Direction.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);

                for (int i = 0; i < blockportal$size.getWidth(); ++i)
                {
                    for (int j = 0; j < blockportal$size.getHeight(); ++j)
                    {
                        BlockProxy blockworldstate = blockpattern$patternhelper.translate(i, j, 1);

                        if (blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != Material.AIR)
                        {
                            ++aint[enumfacing$axisdirection.ordinal()];
                        }
                    }
                }
            }

            Direction.AxisDirection enumfacing$axisdirection1 = Direction.AxisDirection.POSITIVE;

            for (Direction.AxisDirection enumfacing$axisdirection2 : Direction.AxisDirection.values())
            {
                if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()])
                {
                    enumfacing$axisdirection1 = enumfacing$axisdirection2;
                }
            }

            return new BlockPattern.Result(enumfacing.getDirection() == enumfacing$axisdirection1 ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), Direction.get(enumfacing$axisdirection1, enumfacing$axis), Direction.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
        }
    }

	@Override
	public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
	{

	}

	@Override
	public void onEntityCollision(BlockState stateIn, World worldIn, BlockPos posIn, Entity entityIn)
	{
		if (entityIn instanceof IEntityPlayerAether)
		{
			((IEntityPlayerAether) entityIn).getPlayerAether().setInPortal();
		}
	}

}