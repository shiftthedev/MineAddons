package com.iamshift.mineaddons.items;

import java.util.List;

import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.utils.TimeSkipHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TimeSkipClock extends Item
{
	private int timeskip = 1;
	private String time = "0:00";

	public TimeSkipClock(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(1);

		GameRegistry.register(this);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) 
	{
		if(worldIn.isRemote)
			return new ActionResult(EnumActionResult.FAIL, itemStackIn);

		if(playerIn.isSneaking())
		{
			this.timeskip++;

			if(this.timeskip > 24)
				this.timeskip = 1;

			playerIn.addChatMessage(new TextComponentString("Skiping " + timeskip + " hours."));
		}
		else
		{
			TimeSkipHandler.startTimeSkip(this.timeskip, worldIn, playerIn);
		}

		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public boolean hasEffect(ItemStack stack) 
	{
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) 
	{
		super.addInformation(stack, playerIn, tooltip, advanced);
		tooltip.add(TextFormatting.DARK_GREEN + "Sneak + Right click to increase time skip.");
		tooltip.add(TextFormatting.GREEN + "Right click to start time skip.");
	}
	
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		
		if(!worldIn.isRemote)
			return;
		
		time = convertTime(worldIn.getWorldTime());
	}
	
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) 
	{
		return super.getItemStackDisplayName(stack) + " [" + time + "]";
	}
	
	private String convertTime(long time)
	{
		long worldTime = time % 24000L;
		long fixedTime = worldTime + 6000L;
		if(fixedTime >= 24000L) fixedTime -= 24000L;
		long allSecs = fixedTime / 20L;
		
		return String.format("%d:%02d", (long)Math.floor(allSecs / 50L), (long)Math.ceil(allSecs % 50L));
	}
}
