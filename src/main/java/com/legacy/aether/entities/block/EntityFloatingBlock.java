package com.legacy.aether.entities.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.client.particle.FactoryParticle;
import net.minecraft.client.particle.RedDustParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.HitResult;
import net.minecraft.util.TagHelper;
import net.minecraft.util.crash.CrashReportElement;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.FluidRayTraceMode;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlockFloating;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.entities.EntityTypesAether;
import org.apache.logging.log4j.LogManager;

public class EntityFloatingBlock extends Entity
{
    private static final TrackedData<BlockPos> ORIGIN = DataTracker.registerData(EntityFloatingBlock.class, TrackedDataHandlerRegistry.BLOCK_POS);

    private BlockState state = BlocksAether.gravitite_ore.getDefaultState();

    private int floatTime;

	public EntityFloatingBlock(World world)
	{
		super(EntityTypesAether.FLOATING_BLOCK, world);

		this.state = BlocksAether.gravitite_ore.getDefaultState();
		this.setSize(0.98F, 0.98F);
	}

	public EntityFloatingBlock(World world, double x, double y, double z, BlockState state)
	{
		this(world);

		this.state = state;
		//this.preventEntitySpawning = true;

		this.setPosition(x, y + (double)((1.0F - this.height) / 2.0F), z);

		this.velocityX = this.velocityY = this.velocityZ = 0.0D;
		this.prevX = x;
		this.prevY = y;
		this.prevZ = z;

		this.setOrigin(new BlockPos(this));
	}

    public void setOrigin(BlockPos p_184530_1_)
    {
        this.dataTracker.set(ORIGIN, p_184530_1_);
    }

    public BlockPos getOrigin()
    {
        return (BlockPos)this.dataTracker.get(ORIGIN);
    }

	@Override
	protected void initDataTracker()
	{
        this.dataTracker.startTracking(ORIGIN, BlockPos.ORIGIN);
	}

	/*
	@Override
    public boolean canBeAttackedWithItem()
    {
        return false;
    }

	@Override
	public boolean canTriggerWalking()
    {
        return false;
    }*/

	@Override
    public boolean doesCollide()
    {
        return !this.invalid;
    }

	@Override
	public void update()
	{
		if (this.state.isAir())
		{
			this.invalidate();
		}
        else
        {
            this.prevX = this.x;
            this.prevY = this.y;
            this.prevZ = this.z;
            Block block = this.state.getBlock();

            if (this.floatTime++ == 0)
            {
                BlockPos blockpos = new BlockPos(this);

                if (this.world.getBlockState(blockpos).getBlock() == block)
                {
                	this.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                }
                else if (!this.world.isRemote)
                {
                    this.invalidate();
                    return;
                }
            }

            if (!this.isUnaffectedByGravity())
            {
                this.velocityY += 0.04D;
            }

            this.move(MovementType.SELF, x + this.velocityX, y + this.velocityY, z + this.velocityZ);

            if (!this.world.isRemote)
            {
                BlockPos blockpos1 = new BlockPos(this);
                boolean flag = this.state.getBlock() instanceof ConcretePowderBlock;
                boolean flag1 = flag && this.world.getFluidState(blockpos1).matches(FluidTags.WATER);
                double d0 = this.velocityX * this.velocityX + this.velocityY * this.velocityY + this.velocityZ * this.velocityZ;

                if (flag && d0 > 1.0D)
                {
                    HitResult raytraceresult = this.world.rayTrace(new Vec3d(this.prevX, this.prevY, this.prevZ), new Vec3d(this.x, this.y, this.z), FluidRayTraceMode.SOURCE_ONLY);

                    if (raytraceresult != null && this.world.getFluidState(raytraceresult.getBlockPos()).matches(FluidTags.WATER))
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
                            this.dropItem(block);
                        }

                        this.invalidate();
                    }
                }
                else
                {
                    if (!flag1 && BlockFloating.canFloatThrough(this.world.getBlockState(new BlockPos(this.x, this.y + 0.009999999776482582D, this.z))))
                    {
                        this.onGround = false;
                        return;
                    }

                    this.velocityX *= 0.699999988079071D;
                    this.velocityZ *= 0.699999988079071D;
                    this.velocityY *= 0.5D;
                }
            }

            this.velocityX *= 0.9800000190734863D;
            this.velocityY *= 0.9800000190734863D;
            this.velocityZ *= 0.9800000190734863D;

            if (this.velocityY == 0.0F)
            {
            	this.world.setBlockState(new BlockPos(this), this.getBlockstate());
            	this.invalidate();
            }
        }
	}

	@Override
	protected void writeCustomDataToTag(CompoundTag compound)
	{
        compound.put("state", TagHelper.serializeBlockState(this.state));
        compound.putInt("time", this.floatTime);
	}

	@Override
	protected void readCustomDataFromTag(CompoundTag compound)
	{
        this.state = TagHelper.deserializeBlockState(compound.getCompound("state"));
        this.floatTime = compound.getInt("time");

        if (this.state.isAir())
        {
            this.state = BlocksAether.gravitite_ore.getDefaultState();
        }
	}

	@Override
    public void populateCrashReport(CrashReportElement category)
    {
        super.populateCrashReport(category);

        category.add("BlockState", this.state.toString());
        category.add("Time Floated", this.floatTime);
    }

    public World getWorldObj()
    {
        return this.world;
    }

    /*
	@Override
	public boolean ignoreItemEntityData()
	{
		return true;
	}*/

	@Override
    public boolean doesRenderOnFire()
    {
        return false;
    }

    public BlockState getBlockstate()
    {
        return this.state;
    }

}