package com.legacy.aether.mixin;

import java.util.Map;
import java.util.concurrent.ExecutorService;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.SecondaryServerWorld;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ServerWorldListener;
import net.minecraft.util.profiler.DisableableProfiler;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.world.WorldAether;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer
{

	@Shadow @Final private DisableableProfiler profiler;

	@Shadow @Final private Map<DimensionType, ServerWorld> worlds;

	@Shadow @Final private ExecutorService field_17200;

	@SuppressWarnings("resource")
    @Inject(method = "createWorlds", at = @At("RETURN"))
	protected void registerAetherDimension(WorldSaveHandler saveHandler, PersistentStateManager stateManager, LevelProperties properties, LevelInfo info, WorldGenerationProgressListener listener, CallbackInfo ci)
	{
	    ServerWorld serverWorld_1 = ((MinecraftServer) (Object) this).getWorld(DimensionType.OVERWORLD);

	    SecondaryServerWorld theAether = (new SecondaryServerWorld((MinecraftServer) (Object) this, this.field_17200, saveHandler, WorldAether.THE_AETHER, serverWorld_1, this.profiler, listener)).initializeAsSecondaryWorld();

		this.worlds.put(WorldAether.THE_AETHER, theAether);
		theAether.registerListener(new ServerWorldListener((MinecraftServer) (Object) this, theAether));

		if (!((MinecraftServer) (Object) this).isSinglePlayer())
		{
			theAether.getLevelProperties().setGameMode(((MinecraftServer) (Object) this).getDefaultGameMode());
		}
	}
}