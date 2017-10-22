package com.iamshift.items;

import java.util.List;

import com.iamshift.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AncientEssence extends Item
{
	public AncientEssence(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(64);

		setCreativeTab(CreativeTabs.MISC);

		GameRegistry.register(this);
	}

	@Override
	public boolean hasEffect(ItemStack stack) 
	{
		return true;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) 
	{
		if(!target.worldObj.isRemote)
		{
			if(target instanceof EntityShulker)
			{
				target.setDropItemsWhenDead(false);
				target.setDead();
				target.dropItem(ModItems.noaishulkerspawnegg, 1);

				if(!playerIn.capabilities.isCreativeMode)
					--stack.stackSize;

				return true;
			}
		}

		return false;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) 
	{
		super.addInformation(stack, playerIn, tooltip, advanced);
		tooltip.add(TextFormatting.GREEN + "Right click on a Shulker to use.");
	}
}
