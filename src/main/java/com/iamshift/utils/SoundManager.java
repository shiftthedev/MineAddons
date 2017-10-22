package com.iamshift.utils;

import com.iamshift.References;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundManager 
{
	public static SoundEvent carp_ambient1;
	public static SoundEvent carp_ambient2;
	public static SoundEvent carp_ambient3;
	public static SoundEvent carp_ambient4;
	
	public static SoundEvent carp_hurt;

	private static int size = 0;
	
	public static void init()
	{
		size = SoundEvent.REGISTRY.getKeys().size();
		
		carp_ambient1 = register("carpambient1");
		carp_ambient2 = register("carpambient2");
		carp_ambient3 = register("carpambient3");
		carp_ambient4 = register("carpambient4");
		
		carp_hurt = register("carphurt");
	}
	
	private static SoundEvent register(String name)
	{
		ResourceLocation loc = new ResourceLocation(References.MODID, name);
		
		return GameRegistry.register(new SoundEvent(loc).setRegistryName(loc));
	}
	
}

