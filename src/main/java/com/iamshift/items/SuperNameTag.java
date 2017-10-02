package com.iamshift.items;

import com.iamshift.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SuperNameTag extends ItemNameTag
{
	public SuperNameTag(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(64);

		setCreativeTab(CreativeTabs.TOOLS);

		GameRegistry.register(this);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) 
	{
		if(!stack.hasDisplayName())
			return false;
		else if(target instanceof EntityLiving)
		{
			EntityLiving entity = (EntityLiving) target;
			entity.setCustomNameTag(stack.getDisplayName());
			entity.enablePersistence();
			entity.setAlwaysRenderNameTag(true);
            --stack.stackSize;
			return true;
		}
		else
			return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}

	@Override
	public boolean hasEffect(ItemStack stack) 
	{
		return true;
	}
}
