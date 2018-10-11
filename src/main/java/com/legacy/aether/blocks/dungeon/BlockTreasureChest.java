package com.legacy.aether.blocks.dungeon;

import com.legacy.aether.tileentity.TileEntityTreasureChest;
import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockTreasureChest extends BlockChest {

    public BlockTreasureChest() {
        super(Builder.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(-1.0F, -1.0F));

        this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, ChestType.SINGLE).withProperty(WATERLOGGED, false));
    }

    @Override
    public boolean onBlockActivated(IBlockState stateIn, World worldIn, BlockPos posIn, EntityPlayer playerIn, EnumHand handIn, EnumFacing facingIn, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = worldIn.getTileEntity(posIn);

        if (tileEntity instanceof TileEntityTreasureChest) {
            TileEntityTreasureChest chest = (TileEntityTreasureChest) tileEntity;

            if (chest.isLocked()) {
                chest.unlock(playerIn);

                return true;
            }

            return super.onBlockActivated(stateIn, worldIn, posIn, playerIn, handIn, facingIn, hitX, hitY, hitZ);
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader blockreader) {
        return new TileEntityTreasureChest();
    }

}