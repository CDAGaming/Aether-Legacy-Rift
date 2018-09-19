package com.legacy.aether.mixin.client;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Minecraft.class)
public class MixinMinecraft
{

	/**
	 * @author Modding Legacy
	 */
	@Overwrite
	public String getVersionType()
	{
		return "Aether Legacy Rift Edition";
	}

}