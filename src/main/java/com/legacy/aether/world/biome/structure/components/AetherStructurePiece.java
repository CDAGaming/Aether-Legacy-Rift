package com.legacy.aether.world.biome.structure.components;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public abstract class AetherStructurePiece extends StructurePiece {

    public int chance;

    public IBlockState airState = Blocks.AIR.getDefaultState(), blockState, extraBlockState;

    public boolean replaceAir, replaceSolid;

    public IWorld world;

    public Random random;

    public MutableBoundingBox structureBoundingBox;

    public ChunkPos chunkPos;

    private int startX, startY, startZ;

    public void setBlocks(IBlockState blockState) {
        this.blockState = blockState;
        this.extraBlockState = null;
        this.chance = 0;
    }

    public void setBlocks(IBlockState blockState, IBlockState extraBlockState, int chance) {
        this.blockState = blockState;
        this.extraBlockState = extraBlockState;
        this.chance = chance;

        if (this.chance < 1) {
            this.chance = 1;
        }
    }

    public void setStructureOffset(int x, int y, int z) {
        this.startX = x;
        this.startY = y;
        this.startZ = z;
    }

    public void addLineX(int x, int y, int z, int xRange) {
        for (int lineX = x; lineX < x + xRange; lineX++) {
            Block block = this.getBlockState(lineX + this.startX, y + this.startY, z + this.startZ).getBlock();

            if ((this.replaceAir || block != Blocks.AIR) && (this.replaceSolid || block == Blocks.AIR)) {
                this.setBlock(lineX + this.startX, y + this.startY, z + this.startZ);
            }
        }
    }

    public void addLineY(int x, int y, int z, int yRange) {
        for (int lineY = y; lineY < y + yRange; lineY++) {
            Block block = this.getBlockState(x + this.startX, lineY + this.startY, z + this.startZ).getBlock();

            if ((this.replaceAir || block != Blocks.AIR) && (this.replaceSolid || block == Blocks.AIR)) {
                this.setBlock(x + this.startX, lineY + this.startY, z + this.startZ);
            }
        }
    }

    public void addLineZ(int x, int y, int z, int zRange) {
        for (int lineZ = z; lineZ < z + zRange; lineZ++) {
            Block block = this.getBlockState(x + this.startX, y + this.startY, lineZ + this.startZ).getBlock();

            if ((this.replaceAir || block != Blocks.AIR) && (this.replaceSolid || block == Blocks.AIR)) {
                this.setBlock(x + this.startX, y + this.startY, lineZ + this.startZ);
            }
        }
    }

    public void addPlaneX(int x, int y, int z, int yRange, int zRange) {
        for (int lineY = y; lineY < y + yRange; lineY++) {
            for (int lineZ = z; lineZ < z + zRange; lineZ++) {
                Block block = this.getBlockState(x + this.startX, lineY + this.startY, lineZ + this.startZ).getBlock();

                if ((this.replaceAir || block != Blocks.AIR) && (this.replaceSolid || block == Blocks.AIR)) {
                    this.setBlock(x + this.startX, lineY + this.startY, lineZ + this.startZ);
                }
            }
        }
    }

    public void addPlaneY(int x, int y, int z, int xRange, int zRange) {
        for (int lineX = x; lineX < x + xRange; lineX++) {
            for (int lineZ = z; lineZ < z + zRange; lineZ++) {
                Block block = this.getBlockState(lineX + this.startX, y + this.startY, lineZ + this.startZ).getBlock();

                if ((this.replaceAir || block != Blocks.AIR) && (this.replaceSolid || block == Blocks.AIR)) {
                    this.setBlock(lineX + this.startX, y + this.startY, lineZ + this.startZ);
                }
            }
        }
    }

    public void addPlaneZ(int x, int y, int z, int xRange, int yRange) {
        for (int lineX = x; lineX < x + xRange; lineX++) {
            for (int lineY = y; lineY < y + yRange; lineY++) {
                Block block = this.getBlockState(lineX + this.startX, lineY + this.startY, z + this.startZ).getBlock();

                if ((this.replaceAir || block != Blocks.AIR) && (this.replaceSolid || block == Blocks.AIR)) {
                    this.setBlock(lineX + this.startX, lineY + this.startY, z + this.startZ);
                }
            }
        }
    }

    public void addHollowBox(int x, int y, int z, int xRange, int yRange, int zRange) {
        IBlockState temp1 = this.blockState;
        IBlockState temp2 = this.extraBlockState;

        this.setBlocks(this.airState, this.airState, this.chance);
        this.addSolidBox(x, y, z, xRange, yRange, zRange);
        this.setBlocks(temp1, temp2, this.chance);
        this.addPlaneY(x, y, z, xRange, zRange);
        this.addPlaneY(x, y + (yRange - 1), z, xRange, zRange);
        this.addPlaneX(x, y, z, yRange, zRange);
        this.addPlaneX(x + (xRange - 1), y, z, yRange, zRange);
        this.addPlaneZ(x, y, z, xRange, yRange);
        this.addPlaneZ(x, y, z + (zRange - 1), xRange, yRange);
    }

    public void addSquareTube(int x, int y, int z, int xRange, int yRange, int zRange, int angel) {
        IBlockState temp1 = this.blockState;
        IBlockState temp2 = this.extraBlockState;

        this.setBlocks(this.airState, this.airState, this.chance);
        this.addSolidBox(x, y, z, xRange, yRange, zRange);
        this.setBlocks(temp1, temp2, this.chance);

        if (angel == 0 || angel == 2) {
            this.addPlaneY(x, y, z, xRange, zRange);
            this.addPlaneY(x, y + (yRange - 1), z, xRange, zRange);
        }

        if (angel == 1 || angel == 2) {
            this.addPlaneX(x, y, z, yRange, zRange);
            this.addPlaneX(x + (xRange - 1), y, z, yRange, zRange);
        }

        if (angel == 0 || angel == 1) {
            this.addPlaneZ(x, y, z, xRange, yRange);
            this.addPlaneZ(x, y, z + (zRange - 1), xRange, yRange);
        }
    }

    public void addSolidBox(int x, int y, int z, int xRange, int yRange, int zRange) {
        for (int lineX = x; lineX < x + xRange; lineX++) {
            for (int lineY = y; lineY < y + yRange; lineY++) {
                for (int lineZ = z; lineZ < z + zRange; lineZ++) {
                    Block block = this.getBlockState(lineX + this.startX, lineY + this.startY, lineZ + this.startZ).getBlock();

                    if ((this.replaceAir || block != Blocks.AIR) && (this.replaceSolid || block == Blocks.AIR)) {
                        this.setBlock(lineX + this.startX, lineY + this.startY, lineZ + this.startZ);
                    }
                }
            }
        }
    }

    public boolean isBoxSolid(int x, int y, int z, int xRange, int yRange, int zRange) {
        boolean flag = true;

        for (int lineX = x; lineX < x + xRange; lineX++) {
            for (int lineY = y; lineY < y + yRange; lineY++) {
                for (int lineZ = z; lineZ < z + zRange; lineZ++) {
                    if (this.getBlockState(lineX + this.startX, lineY + this.startY, lineZ + this.startZ).getBlock() == Blocks.AIR) {
                        flag = false;
                    }
                }
            }
        }

        return flag;
    }

    public boolean isBoxEmpty(int x, int y, int z, int xRange, int yRange, int zRange) {
        boolean flag = true;

        for (int lineX = x; lineX < x + xRange; lineX++) {
            for (int lineY = y; lineY < y + yRange; lineY++) {
                for (int lineZ = z; lineZ < z + zRange; lineZ++) {
                    if (this.getBlockState(lineX + this.startX, lineY + this.startY, lineZ + this.startZ).getBlock() != Blocks.AIR) {
                        flag = false;
                    }
                }
            }
        }

        return flag;
    }

    public TileEntity getTileEntityFromPosWithOffset(int x, int y, int z) {
        BlockPos blockpos = new BlockPos(this.getActualX(x, z), this.getActualY(y), this.getActualZ(x, z));

        return !this.structureBoundingBox.isVecInside(blockpos) ? null : this.world.getTileEntity(blockpos);
    }

    public IBlockState getBlockStateWithOffset(int x, int y, int z) {
        return this.getBlockStateFromPos(this.world, x + this.startX, y + this.startY, z + this.startZ, this.structureBoundingBox);
    }

    public IBlockState getBlockState(int x, int y, int z) {
        return this.getBlockStateFromPos(this.world, x, y, z, this.structureBoundingBox);
    }

    public void setBlockWithOffset(int x, int y, int z, IBlockState state) {
        this.setBlockState(this.world, state, x + this.startX, y + this.startY, z + this.startZ, this.structureBoundingBox);
    }

    public void setBlock(int x, int y, int z, IBlockState state) {
        this.setBlockState(this.world, state, x, y, z, this.structureBoundingBox);
    }

    public void setBlockWithOffset(int x, int y, int z) {
        if (this.chance == 0) {
            this.setBlock(x + this.startX, y + this.startY, z + this.startZ, this.blockState);
            return;
        }

        if (this.random.nextInt(this.chance) == 0) {
            this.setBlockState(this.world, this.extraBlockState, x + this.startX, y + this.startY, z + this.startZ, this.structureBoundingBox);
        } else {
            this.setBlockState(this.world, this.blockState, x + this.startX, y + this.startY, z + this.startZ, this.structureBoundingBox);
        }
    }

    public void setBlock(int x, int y, int z) {
        if (this.chance == 0) {
            this.setBlock(x, y, z, this.blockState);
            return;
        }

        if (this.random.nextInt(this.chance) == 0) {
            this.setBlockState(this.world, this.extraBlockState, x, y, z, this.structureBoundingBox);
        } else {
            this.setBlockState(this.world, this.blockState, x, y, z, this.structureBoundingBox);
        }
    }

    public boolean spawnEntity(Entity entity, int structureX, int structureY, int structureZ) {
        int posX = this.getActualX(structureX, structureZ);
        int posY = this.getActualY(structureY);
        int posZ = this.getActualZ(structureX, structureZ);

        if (this.structureBoundingBox.isVecInside(new BlockPos(posX, posY, posZ))) {
            entity.setLocationAndAngles((double) posX + 0.5D, (double) posY + 0.5D, (double) posZ + 0.5D, 0.0F, 0.0F);

            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntity = ((EntityLivingBase) entity);

                livingEntity.heal(livingEntity.getMaxHealth());
            }

            if (entity instanceof EntityLiving) {
                ((EntityLiving) entity).onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entity)), null, null);
            }

            if (!this.world.isRemote()) //Not taking chances ~Kino
            {
                this.world.spawnEntity(entity);
            }

            return true;
        }

        return false;
    }

    public int getActualX(int structureX, int structureZ) {
        return this.getXWithOffset(structureX + this.startX, structureZ + this.startZ);
    }

    public int getActualY(int structureY) {
        return this.getYWithOffset(structureY + this.startY);
    }

    public int getActualZ(int structureX, int structureZ) {
        return this.getZWithOffset(structureX + this.startX, structureZ + this.startZ);
    }

    public abstract boolean generate();

    @Override
    public boolean addComponentParts(IWorld worldIn, Random randIn, MutableBoundingBox boundingBoxIn, ChunkPos chunkPosIn) {
        this.world = worldIn;
        this.random = randIn;
        this.structureBoundingBox = boundingBoxIn;
        this.chunkPos = chunkPosIn;

        return this.generate();
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound compound, TemplateManager templateIn) {

    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound compound) {

    }

}