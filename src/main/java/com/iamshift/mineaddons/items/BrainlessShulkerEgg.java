package com.iamshift.mineaddons.items;

import java.util.List;

import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.achievements.AchievementHandler;
import com.iamshift.mineaddons.entities.NoAiShulker;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BrainlessShulkerEgg extends Item
{
	public BrainlessShulkerEgg(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(64);

		GameRegistry.register(this);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			NoAiShulker shulker = new NoAiShulker(worldIn);
			BlockPos spawnPos = new BlockPos(pos.getX() + facing.getFrontOffsetX(),
					pos.getY() + facing.getFrontOffsetY(),
					pos.getZ() + facing.getFrontOffsetZ());


			shulker.setPositionAndUpdate(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
			shulker.setHealth(shulker.getMaxHealth());
			worldIn.spawnEntityInWorld(shulker);

			if(!playerIn.capabilities.isCreativeMode)
				--stack.stackSize;
			
			if(!playerIn.hasAchievement(AchievementHandler.achievementSpawnBrainlessShulker))
				playerIn.addStat(AchievementHandler.achievementSpawnBrainlessShulker);
		}

		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) 
	{
		super.addInformation(stack, playerIn, tooltip, advanced);
		tooltip.add(TextFormatting.DARK_AQUA + "Spawns a Brainless Shulker.");
	}
}
