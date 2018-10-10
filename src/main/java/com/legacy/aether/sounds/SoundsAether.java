package com.legacy.aether.sounds;

import net.minecraft.util.SoundEvent;

import org.dimdev.rift.listener.SoundAdder;

import com.legacy.aether.Aether;

public class SoundsAether implements SoundAdder
{

	public static SoundEvent moa_say, moa_flap;
	
	public static SoundEvent sheepuff_hurt, sheepuff_say, sheepuff_death;
	
	public static SoundEvent flyingcow_hurt, flyingcow_death, flyingcow_say;
	
	public static SoundEvent phyg_hurt, phyg_death, phyg_say;

	public static SoundEvent aerbunny_hurt, aerbunny_death, aerbunny_lift;

	public static SoundEvent aerwhale_call, aerwhale_death;

	public static SoundEvent zephyr_call, zephyr_shoot;

	public static SoundEvent slider_collide, slider_move, slider_awaken, slider_death;

	public static SoundEvent sun_spirit_shoot;

	public static SoundEvent aether_tune, ascending_dawn, welcoming_skies;

	public static SoundEvent achievement_gen, achievement_bronze, achievement_silver;

	public static SoundEvent projectile_shoot, dart_shooter_shoot;

	public static SoundEvent aether1, aether2, aether3, aether4, aether_menu;

	@Override
	public void registerSounds()
	{

	}

	public SoundEvent register(String name)
	{
		SoundEvent.registerSound("aether_legacy:" + name);

		return SoundEvent.REGISTRY.get(Aether.locate(name));
	}

}