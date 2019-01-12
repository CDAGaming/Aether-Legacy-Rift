package com.legacy.aether.mixin;

import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.world.WorldAether;

import net.minecraft.class_3202;
import net.minecraft.class_3689;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ServerWorldListener;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer
{

	@Shadow @Final private class_3689 profiler;

	@Shadow @Final private Map<DimensionType, ServerWorld> worlds;

	@Shadow @Final private ExecutorService field_17200;

	@SuppressWarnings("resource")
    @Inject(method = "method_3786", at = @At("RETURN"))
	protected void registerAetherDimension(WorldSaveHandler worldSaveHandler_1, PersistentStateManager persistentStateManager_1, LevelProperties levelProperties_1, LevelInfo levelInfo_1, CallbackInfo ci)
	{
	    ServerWorld serverWorld_1 = ((MinecraftServer) (Object) this).getWorld(DimensionType.OVERWORLD);

		class_3202 theAether = (new class_3202((MinecraftServer) (Object) this, this.field_17200, worldSaveHandler_1, WorldAether.THE_AETHER, serverWorld_1, this.profiler)).method_14033();

		this.worlds.put(WorldAether.THE_AETHER, theAether);
		theAether.registerListener(new ServerWorldListener((MinecraftServer) (Object) this, theAether));

		if (!((MinecraftServer) (Object) this).isSinglePlayer())
		{
			theAether.getLevelProperties().setGameMode(((MinecraftServer) (Object) this).getDefaultGameMode());
		}
	}
}