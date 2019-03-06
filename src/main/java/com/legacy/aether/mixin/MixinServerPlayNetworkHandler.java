package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.api.player.util.ServerPlayerReach;

import net.minecraft.client.network.packet.BlockUpdateS2CPacket;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.packet.PlayerActionC2SPacket;
import net.minecraft.server.network.packet.PlayerInteractBlockC2SPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler
{

	@Shadow @Final private MinecraftServer server;

	@Shadow public ServerPlayerEntity player;

	@Shadow private Vec3d requestedTeleportPos;

	@Overwrite
	public void onPlayerAction(PlayerActionC2SPacket packetIn)
	{
		NetworkThreadUtils.forceMainThread(packetIn, (ServerPlayNetworkHandler) (Object) this, (ServerWorld) this.player.getServerWorld());

		ServerWorld serverWorld_1 = this.server.getWorld(this.player.dimension);
		BlockPos blockPos_1 = packetIn.getPos();

		this.player.updateLastActionTime();

		switch (packetIn.getAction()) {
		case SWAP_HELD_ITEMS:
			if (!this.player.isSpectator())
			{
				ItemStack itemStack_1 = this.player.getStackInHand(Hand.OFF);
				this.player.setStackInHand(Hand.OFF, this.player.getStackInHand(Hand.MAIN));
				this.player.setStackInHand(Hand.MAIN, itemStack_1);
			}

			return;
		case DROP_ITEM:
			if (!this.player.isSpectator())
			{
				this.player.dropSelectedItem(false);
			}

			return;
		case DROP_ALL_ITEMS:
			if (!this.player.isSpectator())
			{
				this.player.dropSelectedItem(true);
			}

			return;
		case RELEASE_USE_ITEM:
			this.player.method_6075();

			return;
		case START_DESTROY_BLOCK:
		case ABORT_DESTROY_BLOCK:
		case STOP_DESTROY_BLOCK:
			double double_1 = this.player.x - ((double) blockPos_1.getX() + 0.5D);
			double double_2 = this.player.y - ((double) blockPos_1.getY() + 0.5D) + 1.5D;
			double double_3 = this.player.z - ((double) blockPos_1.getZ() + 0.5D);
			double double_4 = double_1 * double_1 + double_2 * double_2 + double_3 * double_3;

			double distance = ((ServerPlayerReach)this.player.interactionManager).getReachDistance() + 1.0D;

			distance *= distance;

			if (double_4 > distance)
			{
				return;
			} 
			else if (blockPos_1.getY() >= this.server.getWorldHeight())
			{
				return;
			}
			else
			{
				if (packetIn.getAction() == PlayerActionC2SPacket.Action.START_DESTROY_BLOCK)
				{
					if (!this.server.isSpawnProtected(serverWorld_1, blockPos_1, this.player) && serverWorld_1.getWorldBorder().contains(blockPos_1))
					{
						this.player.interactionManager.method_14263(blockPos_1, packetIn.getDirection());
					}
					else
					{
						this.player.networkHandler.sendPacket(new BlockUpdateS2CPacket(serverWorld_1, blockPos_1));
					}
				}
				else
				{
					if (packetIn.getAction() == PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK)
					{
						this.player.interactionManager.method_14258(blockPos_1);
					}
					else if (packetIn.getAction() == PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK)
					{
						this.player.interactionManager.method_14269();
					}

					if (!serverWorld_1.getBlockState(blockPos_1).isAir())
					{
						this.player.networkHandler.sendPacket(new BlockUpdateS2CPacket(serverWorld_1, blockPos_1));
					}
				}

				return;
			}
		default:
			throw new IllegalArgumentException("Invalid player action");
		}
	}

	@Inject(method = "onPlayerInteractBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;updateLastActionTime()V"))
	public void onPlayerAetherInteractBlock(PlayerInteractBlockC2SPacket packetIn, CallbackInfo ci)
	{
		this.player.updateLastActionTime();

		ServerWorld world = this.server.getWorld(this.player.dimension);
		Hand hand = packetIn.getHand();
		ItemStack heldItem = this.player.getStackInHand(hand);
		BlockHitResult hitResult = packetIn.getHitY();
		BlockPos pos = hitResult.getBlockPos();
		Direction direction = hitResult.getSide();

		double distance = ((ServerPlayerReach)this.player.interactionManager).getReachDistance() + 3.0D;

		distance *= distance;

		if (distance > 64.0F)
		{
			if (pos.getY() >= this.server.getWorldHeight() - 1 && (direction == Direction.UP || pos.getY() >= this.server.getWorldHeight()))
			{
				
			}
			else if (this.requestedTeleportPos == null && this.player.squaredDistanceTo((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D) < distance && !this.server.isSpawnProtected(world, pos, this.player) && world.getWorldBorder().contains(pos))
			{
				this.player.interactionManager.interactBlock(this.player, world, heldItem, hand, hitResult);
			}
		}
	}

}