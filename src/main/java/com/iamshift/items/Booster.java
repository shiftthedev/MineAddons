package com.iamshift.items;

import java.util.List;

import com.iamshift.Config;
import com.iamshift.References;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Booster extends Item
{
	public Booster(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(64);
		
		setCreativeTab(CreativeTabs.TRANSPORTATION);
		
		GameRegistry.register(this);
	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{

		if ((playerIn.isPotionActive(ModItems.potionBooster)) && (playerIn.getActivePotionEffect(ModItems.potionBooster).getDuration() > 0)) 
			return new ActionResult(EnumActionResult.FAIL, itemStackIn);

		if ((itemStackIn.getTagCompound() != null) && (itemStackIn.getTagCompound().hasKey("BOOST")))
		{
			int boostTier = itemStackIn.getTagCompound().getInteger("BOOST");
			float boost = Config.GetBoosterTier(boostTier);

			double velX = playerIn.getLookVec().xCoord * boost;
			double velY = playerIn.getLookVec().yCoord * boost;
			double velZ = playerIn.getLookVec().zCoord * boost;

			if (playerIn.isElytraFlying())
			{
				if (worldIn.isRemote)
					playerIn.addVelocity(velX, velY, velZ);

				playerIn.addPotionEffect(new PotionEffect(ModItems.potionBooster, Config.GetBoosterDuration(itemStackIn.getTagCompound().getInteger("BOOST"))));
				playerIn.playSound(SoundEvents.ENTITY_FIREWORK_LAUNCH, 1.0F, 1.0F);

				if ((!playerIn.capabilities.isCreativeMode) && (!worldIn.isRemote)) 
					if(itemStackIn.getTagCompound().getInteger("BOOST") != 4)
						itemStackIn.stackSize -= 1;
					

			}
			else if ((playerIn.isSneaking()) && (!playerIn.isElytraFlying()))
			{
				if (worldIn.isRemote)
					playerIn.addVelocity(0.0D, boost * 2.0F, 0.0D);

				playerIn.addPotionEffect(new PotionEffect(ModItems.potionBooster, Config.GetBoosterDuration(itemStackIn.getTagCompound().getInteger("BOOST"))));
				playerIn.playSound(SoundEvents.ENTITY_FIREWORK_LAUNCH, 1.0F, 1.0F);

				if ((!playerIn.capabilities.isCreativeMode) && (!worldIn.isRemote)) 
					if(itemStackIn.getTagCompound().getInteger("BOOST") != 4)
						itemStackIn.stackSize -= 1;
			}
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}

	public void getSubItems(Item item, CreativeTabs tab, List subItems)
	{
		for (int i = 1; i < 5; i++)
		{
			ItemStack stack = new ItemStack(this).copy();
			NBTTagCompound nbtTagCompound = new NBTTagCompound();

			nbtTagCompound.setInteger("BOOST", i);
			
			stack.setTagCompound(nbtTagCompound);
			subItems.add(stack);
		}
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		super.addInformation(stack, player, tooltip, advanced);

		if ((stack.getTagCompound() != null) && (stack.getTagCompound().hasKey("BOOST")))
		{
			if(stack.getTagCompound().getInteger("BOOST") == 4)
				tooltip.add("Creative");
			else
				tooltip.add(I18n.format("booster.tooltip.charge", new Object[0]) + ": " + stack.getTagCompound().getInteger("BOOST"));
		}
	}

	public boolean isDamageable()
	{
		return false;
	}
}
