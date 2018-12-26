package com.legacy.aether.mixin.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MinecraftClient.class)
public class MixinMinecraft
{

	/**
	 * @author Modding Legacy
	 */
	@Overwrite
	public String getVersionType()
	{
		return "Aether Legacy Fabric Edition";
	}

}