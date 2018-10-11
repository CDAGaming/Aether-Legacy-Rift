package com.legacy.aether.blocks;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockFloating extends Block {

    public static boolean floatInstantly;

    private boolean constantlyPowered;

    public BlockFloating(Builder builder, boolean constantlyPowered) {
        super(builder.needsRandomTick());

        this.constantlyPowered = constantlyPowered;
    }

    public static boolean canFloatThrough(IBlockState state) {
        return state.isAir() || state.getBlock() == Blocks.FIRE || state.getMaterial().isLiquid();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos posIn, IBlockState stateIn, @Nullable EntityLivingBase entityIn, ItemStack itemIn) {
        worldIn.getPendingBlockTicks().scheduleUpdate(posIn, this, this.tickRate(worldIn));
    }

    @Override
    public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facingIn, IBlockState neighborIn, IWorld worldIn, BlockPos posIn, BlockPos neighborPosIn) {
        worldIn.getPendingBlockTicks().scheduleUpdate(posIn, this, this.tickRate(worldIn));

        return super.updatePostPlacement(stateIn, facingIn, neighborIn, worldIn, posIn, neighborPosIn);
    }

    @Override
    public void tick(IBlockState stateIn, World worldIn, BlockPos posIn, Random randIn) {
        if (!worldIn.isRemote) {
            if (this.constantlyPowered || (!this.constantlyPowered && worldIn.isBlockPowered(posIn))) {
                this.checkFloatable(worldIn, posIn);
            }
        }
    }

    private void checkFloatable(World worldIn, BlockPos posIn) {
        if (canFloatThrough(worldIn.getBlockState(posIn.up())) && posIn.getY() <= worldIn.getActualHeight()) {
            if (!floatInstantly && worldIn.isAreaLoaded(posIn.add(-32, -32, -32), posIn.add(32, 32, 32))) {
                if (!worldIn.isRemote) {
                    EntityFloatingBlock floatingBlock = new EntityFloatingBlock(worldIn, (double) posIn.getX() + 0.5D, (double) posIn.getY(), (double) posIn.getZ() + 0.5D, worldIn.getBlockState(posIn));

                    this.onStartFloating(floatingBlock);

                    worldIn.spawnEntity(floatingBlock);
                }
            } else {
                if (worldIn.getBlockState(posIn).getBlock() == this) {
                    worldIn.isAirBlock(posIn);
                }

                BlockPos blockpos;

                for (blockpos = posIn.up(); canFloatThrough(worldIn.getBlockState(blockpos)) && blockpos.getY() <= worldIn.getActualHeight(); blockpos = blockpos.up()) {
                    ;
                }

                if (blockpos.getY() < worldIn.getActualHeight()) {
                    worldIn.setBlockState(blockpos.up(), this.getDefaultState());
                }
            }
        }
    }

    public void onStartFloating(EntityFloatingBlock entityIn) {

    }

    public void onStopFloating(World worldIn, BlockPos posIn, IBlockState stateIn, IBlockState ceilingStateIn) {

    }

    @Override
    public int tickRate(IWorldReaderBase worldIn) {
        return 2;
    }

}