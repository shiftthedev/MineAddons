package com.iamshift.items;

import com.iamshift.MineAddons;
import com.iamshift.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WitherDust extends Item
{
	private String thrower;
	
	public WitherDust(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(64);

		setCreativeTab(CreativeTabs.MATERIALS);

		GameRegistry.register(this);
	}
	
	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) 
	{
		return Integer.MAX_VALUE;
	}
	
	@Override
	public boolean isDamageable() 
	{
		return false;
	}
	
	@Override
	public boolean isDamaged(ItemStack stack) 
	{
		return false;
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) 
	{
		return true;
	}
	
	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) 
	{
		EntityWaterChanger entity = new EntityWaterChanger(world, location.posX, location.posY, location.posZ, itemstack);
		
		entity.motionX = location.motionX;
		entity.motionY = location.motionY;
		entity.motionZ = location.motionZ;
		
		entity.setPickupDelay(40);
		
		entity.setThrower(thrower);
		
		thrower = null;
		
		return entity;
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) 
	{
		thrower = player.getName();
		return true;
	}
}
