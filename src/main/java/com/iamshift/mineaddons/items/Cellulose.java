package com.iamshift.mineaddons.items;

import java.util.List;

import com.iamshift.mineaddons.References;

import javafx.scene.control.TextFormatter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Cellulose extends Item
{
	public Cellulose(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(64);
		
		GameRegistry.register(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) 
	{
		super.addInformation(stack, playerIn, tooltip, advanced);
		tooltip.add(TextFormatting.DARK_AQUA + "Used in Sponge / Lava Sponge craft.");
	}
}
