package com.legacy.aether.entities.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockConcretePowder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlockFloating;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.entities.EntityTypesAether;

public class EntityFloatingBlock extends Entity
{

    private static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(EntityFloatingBlock.class, DataSerializers.BLOCK_POS);

    private IBlockState state = BlocksAether.gravitite_ore.getDefaultState();

    private int floatTime;

	public EntityFloatingBlock(World world)
	{
		super(EntityTypesAether.FLOATING_BLOCK, world);

		this.state = BlocksAether.gravitite_ore.getDefaultState();
		this.setSize(0.98F, 0.98F);
	}

	public EntityFloatingBlock(World world, double x, double y, double z, IBlockState state)
	{
		this(world);

		this.state = state;
		this.preventEntitySpawning = true;

		this.setPosition(x, y + (double)((1.0F - this.height) / 2.0F), z);

		this.motionX = this.motionY = this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;

		this.setOrigin(new BlockPos(this));
	}

    public void setOrigin(BlockPos p_184530_1_)
    {
        this.dataManager.set(ORIGIN, p_184530_1_);
    }

    public BlockPos getOrigin()
    {
        return (BlockPos)this.dataManager.get(ORIGIN);
    }

	@Override
	protected void entityInit() 
	{
        this.dataManager.register(ORIGIN, BlockPos.ORIGIN);
	}

	@Override
    public boolean canBeAttackedWithItem()
    {
        return false;
    }

	@Override
	public boolean canTriggerWalking()
    {
        return false;
    }

	@Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

	@Override
	public void onUpdate()
	{
		if (this.state.isAir())
		{
			this.setDead();
		}
        else
        {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            Block block = this.state.getBlock();

            if (this.floatTime++ == 0)
            {
                BlockPos blockpos = new BlockPos(this);

                if (this.world.getBlockState(blockpos).getBlock() == block)
                {
                    this.world.isAirBlock(blockpos);
                }
                else if (!this.world.isRemote)
                {
                    this.setDead();
                    return;
                }
            }

            if (!this.hasNoGravity())
            {
                this.motionY += 0.04D;
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

            if (!this.world.isRemote)
            {
                BlockPos blockpos1 = new BlockPos(this);
                boolean flag = this.state.getBlock() instanceof BlockConcretePowder;
                boolean flag1 = flag && this.world.getFluidState(blockpos1).isTagged(FluidTags.WATER);
                double d0 = this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ;

                if (flag && d0 > 1.0D)
                {
                    RayTraceResult raytraceresult = this.world.rayTraceBlocks(new Vec3d(this.prevPosX, this.prevPosY, this.prevPosZ), new Vec3d(this.posX, this.posY, this.posZ), RayTraceFluidMode.SOURCE_ONLY);

                    if (raytraceresult != null && this.world.getFluidState(raytraceresult.getBlockPos()).isTagged(FluidTags.WATER))
                    {
                        blockpos1 = raytraceresult.getBlockPos();
                        flag1 = true;
                    }
                }

                if (!this.onGround && !flag1)
                {
                    if (this.floatTime > 100 && !this.world.isRemote && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || this.floatTime > 600)
                    {
                        if (this.world.getGameRules().getBoolean("doEntityDrops"))
                        {
                            this.entityDropItem(block);
                        }

                        this.setDead();
                    }
                }
                else
                {
                    if (!flag1 && BlockFloating.canFloatThrough(this.world.getBlockState(new BlockPos(this.posX, this.posY + 0.009999999776482582D, this.posZ))))
                    {
                        this.onGround = false;
                        return;
                    }

                    this.motionX *= 0.699999988079071D;
                    this.motionZ *= 0.699999988079071D;
                    this.motionY *= 0.5D;
                }
            }

            this.motionX *= 0.9800000190734863D;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= 0.9800000190734863D;

            if (this.motionY == 0.0F)
            {
            	this.world.setBlockState(new BlockPos(this), this.getBlockstate());
            	this.setDead();
            }
        }
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
        compound.setTag("state", NBTUtil.writeBlockState(this.state));
        compound.setInteger("time", this.floatTime);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
        this.state = NBTUtil.readBlockState(compound.getCompoundTag("state"));
        this.floatTime = compound.getInteger("time");

        if (this.state.isAir())
        {
            this.state = BlocksAether.gravitite_ore.getDefaultState();
        }
	}

	@Override
    public void addEntityCrashInfo(CrashReportCategory category)
    {
        super.addEntityCrashInfo(category);

        category.addCrashSection("BlockState", this.state.toString());
        category.addCrashSection("Time Floated", this.floatTime);
    }

    public World getWorldObj()
    {
        return this.world;
    }

	@Override
	public boolean ignoreItemEntityData()
	{
		return true;
	}

	@Override
    public boolean canRenderOnFire()
    {
        return false;
    }

    public IBlockState getBlockstate()
    {
        return this.state;
    }

}