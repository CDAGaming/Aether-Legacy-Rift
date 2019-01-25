package com.legacy.aether.mixin;

import net.minecraft.client.network.packet.BlockUpdateClientPacket;
import net.minecraft.client.network.packet.ChatMessageClientPacket;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.packet.PlayerActionServerPacket;
import net.minecraft.server.network.packet.PlayerInteractBlockServerPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sortme.ChatMessageType;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.legacy.aether.api.player.util.PlayerReach;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler
{

	@Shadow @Final private MinecraftServer server;

	@Shadow public ServerPlayerEntity player;

	@Shadow private Vec3d field_14119;

	@Overwrite
	public void onPlayerAction(PlayerActionServerPacket packetIn)
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

			double distance = ((PlayerReach)this.player.interactionManager).getReachDistance() + 1.0D;

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
				if (packetIn.getAction() == PlayerActionServerPacket.Action.START_DESTROY_BLOCK)
				{
					if (!this.server.isSpawnProtected(serverWorld_1, blockPos_1, this.player) && serverWorld_1.getWorldBorder().contains(blockPos_1))
					{
						this.player.interactionManager.method_14263(blockPos_1, packetIn.getDirection());
					}
					else
					{
						this.player.networkHandler.sendPacket(new BlockUpdateClientPacket(serverWorld_1, blockPos_1));
					}
				}
				else
				{
					if (packetIn.getAction() == PlayerActionServerPacket.Action.STOP_DESTROY_BLOCK)
					{
						this.player.interactionManager.method_14258(blockPos_1);
					}
					else if (packetIn.getAction() == PlayerActionServerPacket.Action.ABORT_DESTROY_BLOCK)
					{
						this.player.interactionManager.method_14269();
					}

					if (!serverWorld_1.getBlockState(blockPos_1).isAir())
					{
						this.player.networkHandler.sendPacket(new BlockUpdateClientPacket(serverWorld_1, blockPos_1));
					}
				}

				return;
			}
		default:
			throw new IllegalArgumentException("Invalid player action");
		}
	}

	@Overwrite
	public void onPlayerInteractBlock(PlayerInteractBlockServerPacket packetIn)
	{
		NetworkThreadUtils.forceMainThread(packetIn, (ServerPlayNetworkHandler) (Object) this, (ServerWorld) this.player.getServerWorld());

		ServerWorld world = this.server.getWorld(this.player.dimension);
		Hand hand = packetIn.getHand();
		ItemStack heldItem = this.player.getStackInHand(hand);
		BlockHitResult class_3965_1 = packetIn.getHitY();
		BlockPos pos = class_3965_1.getBlockPos();
		Direction direction = class_3965_1.getSide();

		this.player.updateLastActionTime();

		double distance = ((PlayerReach)this.player.interactionManager).getReachDistance() + 3.0D;

		distance *= distance;

		if (pos.getY() >= this.server.getWorldHeight() - 1 && (direction == Direction.UP || pos.getY() >= this.server.getWorldHeight()))
		{
			TextComponent textComponent_1 = (new TranslatableTextComponent("build.tooHigh", new Object[]{this.server.getWorldHeight()})).applyFormat(TextFormat.RED);

			this.player.networkHandler.sendPacket(new ChatMessageClientPacket(textComponent_1, ChatMessageType.GAME_INFO));
		} 
		else if (this.field_14119 == null && this.player.squaredDistanceTo((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D) < distance && !this.server.isSpawnProtected(world, pos, this.player) && world.getWorldBorder().contains(pos))
		{
			this.player.interactionManager.interactBlock(this.player, world, heldItem, hand, class_3965_1);
		}

		this.player.networkHandler.sendPacket(new BlockUpdateClientPacket(world, pos));
		this.player.networkHandler.sendPacket(new BlockUpdateClientPacket(world, pos.offset(direction)));
	}

}