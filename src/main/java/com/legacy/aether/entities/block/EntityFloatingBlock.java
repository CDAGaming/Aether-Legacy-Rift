package com.legacy.aether.entities.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.TagHelper;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlockFloating;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.entities.EntityTypesAether;

public class EntityFloatingBlock extends Entity
{

    private static final TrackedData<BlockPos> ORIGIN = DataTracker.registerData(EntityFloatingBlock.class, TrackedDataHandlerRegistry.BLOCK_POS);

    private BlockState state = BlocksAether.gravitite_ore.getDefaultState();

    private int floatTime;

	public EntityFloatingBlock(World world)
	{
		super(EntityTypesAether.FLOATING_BLOCK, world);

		this.state = BlocksAether.gravitite_ore.getDefaultState();
	}

	public EntityFloatingBlock(World world, double x, double y, double z, BlockState state)
	{
		this(world);

		this.state = state;
		this.field_6033 = true;

		this.setPosition(x, y + (double)((1.0F - this.getHeight()) / 2.0F), z);
		this.setVelocity(Vec3d.ZERO);

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
                else if (!this.world.isClient)
                {
                    this.invalidate();
                    return;
                }
            }

            if (!this.isUnaffectedByGravity())
            {
            	this.setVelocity(this.getVelocity().add(0.0D, -0.04D, 0.0D));
            }

            this.move(MovementType.SELF, this.getVelocity());

            if (!this.world.isClient)
            {
                BlockPos blockpos1 = new BlockPos(this);
                boolean flag = this.state.getBlock() instanceof ConcretePowderBlock;
                boolean flag1 = flag && this.world.getFluidState(blockpos1).matches(FluidTags.WATER);
                double d0 = this.getVelocity().lengthSquared();

                if (flag && d0 > 1.0D)
                {
                	BlockHitResult raytraceresult = this.world.rayTrace(new RayTraceContext(new Vec3d(this.prevX, this.prevY, this.prevZ), new Vec3d(this.x, this.y, this.z), RayTraceContext.ShapeType.OUTLINE, RayTraceContext.FluidHandling.SOURCE_ONLY, this));

                    if (raytraceresult.getType() != HitResult.Type.NONE && this.world.getFluidState(raytraceresult.getBlockPos()).matches(FluidTags.WATER))
                    {
                        blockpos1 = raytraceresult.getBlockPos();
                        flag1 = true;
                    }
                }

                if (!this.onGround && !flag1)
                {
                    if (this.floatTime > 100 && !this.world.isClient && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || this.floatTime > 600)
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

                    this.setVelocity(this.getVelocity().multiply(0.7D, -0.5D, 0.7D));
                }
            }

            this.setVelocity(this.getVelocity().multiply(0.98D));

            if (this.getVelocity().y == 0.0F)
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

	@Override
	public Packet<?> createSpawnPacket()
	{
		return new EntitySpawnS2CPacket(this, Block.getRawIdFromState(this.getBlockstate()));
	}

}