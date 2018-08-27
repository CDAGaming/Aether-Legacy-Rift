package com.legacy.aether.mixin.client;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Minecraft.class)
public class MixinMinecraft
{

	@Overwrite
	public String getVersionType()
	{
		return "Aether Legacy Rift Edition";
	}

}