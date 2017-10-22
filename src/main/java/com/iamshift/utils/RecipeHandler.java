package com.iamshift.utils;

import com.iamshift.Config;
import com.iamshift.blocks.ModBlocks;
import com.iamshift.items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler 
{

	public RecipeHandler() {}

	public static void registerRecipies() 
	{
		if(Config.buttbooster)
		{
			
			ItemStack stack = new ItemStack(ModItems.buttBooster, 3, 0).copy();
			NBTTagCompound nbtTagCompound = new NBTTagCompound();

			nbtTagCompound.setInteger("BOOST", 1);
			stack.setTagCompound(nbtTagCompound);
			GameRegistry.addShapelessRecipe(stack, new Object[] { Items.REEDS, Items.GUNPOWDER });

			stack = new ItemStack(ModItems.buttBooster, 3, 0).copy();
			nbtTagCompound = new NBTTagCompound();

			nbtTagCompound.setInteger("BOOST", 2);
			stack.setTagCompound(nbtTagCompound);
			GameRegistry.addShapelessRecipe(stack, new Object[] { Items.REEDS, Items.GUNPOWDER, Items.GUNPOWDER });

			stack = new ItemStack(ModItems.buttBooster, 3, 0).copy();
			nbtTagCompound = new NBTTagCompound();

			nbtTagCompound.setInteger("BOOST", 3);
			stack.setTagCompound(nbtTagCompound);
			GameRegistry.addShapelessRecipe(stack, new Object[] { Items.REEDS, Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER });
		}
		
		if(Config.concrete)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 0), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 15), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 1), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 14), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 2), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 13), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 3), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 12), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 4), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 11), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 5), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 10), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 6), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 9), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 7), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 8), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 8), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 7), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 9), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 6), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 10), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 5), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 11), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 4), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 12), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 3), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 13), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 2), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 14), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 1), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		    GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.concrete_powder, 8, 15), new Object[] { Blocks.SAND, Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, new ItemStack(Items.DYE, 1, 0), Blocks.GRAVEL, Blocks.SAND, Blocks.GRAVEL, Blocks.SAND });
		}

		if(Config.lavasponge)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.lavasponge, 1, 0), new Object[] { Blocks.SOUL_SAND, ModItems.cellulose, ModItems.cellulose, ModItems.cellulose, ModItems.cellulose});
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.lavasponge, 1, 0), new Object[] { new ItemStack(ModBlocks.lavasponge, 1, 1), Items.WATER_BUCKET.setContainerItem(Items.BUCKET) });
		}
		
		if(Config.sponge)
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SPONGE, 1, 0), new Object[] { Blocks.SAND, ModItems.cellulose, ModItems.cellulose, ModItems.cellulose, ModItems.cellulose});
			
		if(Config.ironnuggets)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ironnugget, 9), new Object[] { Items.IRON_INGOT});
			GameRegistry.addShapedRecipe(new ItemStack(Items.IRON_INGOT, 1), new Object[] { "NNN", "NNN", "NNN", 'N', ModItems.ironnugget});
		}
		
		if(Config.observer)
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.observer, 1), new Object[] { "SSS", "RRQ", "SSS", 'S', Blocks.COBBLESTONE, 'R', Items.REDSTONE, 'Q', Items.QUARTZ});
		
		if(Config.supernametag)
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supernametag, 1), new Object[] {Items.NAME_TAG, Items.GOLDEN_APPLE});
		
		if(Config.colorabletorch)
		{
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_white, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 0)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_orange, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 1)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_magenta, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 2)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_lightblue, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 3)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_yellow, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 4)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_lime, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 5)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_pink, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 6)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_gray, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 7)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_lightgray, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 8)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_cyan, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 9)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_purple, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 10)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_blue, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 11)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_brown, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 12)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_green, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 13)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_red, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 14)});
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.torch_black, 1), new Object[] { "T", "C", 'T', Blocks.TORCH, 'C', new ItemStack(Blocks.CARPET, 1, 15)});
		}
	}

	public static void registerSmeltings() 
	{
		if(Config.terracottablocks)
		{
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0), new ItemStack(ModBlocks.terracotta_white, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 1), new ItemStack(ModBlocks.terracotta_orange, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 2), new ItemStack(ModBlocks.terracotta_magenta, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 3), new ItemStack(ModBlocks.terracotta_lightblue, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 4), new ItemStack(ModBlocks.terracotta_yellow, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 5), new ItemStack(ModBlocks.terracotta_lime, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 6), new ItemStack(ModBlocks.terracotta_pink, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 7), new ItemStack(ModBlocks.terracotta_gray, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 8), new ItemStack(ModBlocks.terracotta_lightgray, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 9), new ItemStack(ModBlocks.terracotta_cyan, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 10), new ItemStack(ModBlocks.terracotta_purple, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 11), new ItemStack(ModBlocks.terracotta_blue, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 12), new ItemStack(ModBlocks.terracotta_brown, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 13), new ItemStack(ModBlocks.terracotta_green, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 14), new ItemStack(ModBlocks.terracotta_red, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 15), new ItemStack(ModBlocks.terracotta_black, 1), 1.0F);
		}
		
		if(Config.lavasponge || Config.sponge) 
		{
			GameRegistry.addSmelting(new ItemStack(Items.WHEAT, 1), new ItemStack(ModItems.cellulose, 1), 1.0F);
			GameRegistry.addSmelting(new ItemStack(Items.REEDS, 1), new ItemStack(ModItems.cellulose, 2), 1.0F);

			for(ItemStack stack : OreDictionary.getOres("treeLeaves"))
				GameRegistry.addSmelting(stack, new ItemStack(ModItems.cellulose, 3), 1.0F);
			
			for(ItemStack stack : OreDictionary.getOres("treeSapling"))
				GameRegistry.addSmelting(stack, new ItemStack(ModItems.cellulose, 5), 1.0F);
		}
	}

}
