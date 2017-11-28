package com.iamshift.mineaddons.utils;

import java.util.List;

import com.iamshift.mineaddons.Config;
import com.iamshift.mineaddons.fluids.ModFluids;
import com.iamshift.mineaddons.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class CreativeTab extends CreativeTabs
{
	public CreativeTab() 
	{
		super("MineAddons");
	}

	@Override
	public Item getTabIconItem() 
	{
		return ModItems.ancientessence;
	}

	@Override
	public void displayAllRelevantItems(List<ItemStack> list) 
	{
		super.displayAllRelevantItems(list);

		if(Config.sacredwater)
			list.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.sacredwater));

		if(Config.cursedwater)
			list.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.cursedwater));
	}
}
