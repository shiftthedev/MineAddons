package com.iamshift.mineaddons.blocks;

import com.iamshift.mineaddons.Config;
import com.iamshift.mineaddons.MineAddons;
import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.blocks.colorabletorch.BlackTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.BlueTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.BrownTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.CyanTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.GrayTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.GreenTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.LightBlueTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.LightGrayTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.LimeTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.MagentaTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.OrangeTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.PinkTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.PurpleTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.RedTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.WhiteTorch;
import com.iamshift.mineaddons.blocks.colorabletorch.YellowTorch;
import com.iamshift.mineaddons.blocks.concrete.Concrete;
import com.iamshift.mineaddons.blocks.concrete.ConcretePowder;
import com.iamshift.mineaddons.blocks.lavasponge.ItemLavaSponge;
import com.iamshift.mineaddons.blocks.lavasponge.LavaSponge;
import com.iamshift.mineaddons.blocks.observer.Observer;
import com.iamshift.mineaddons.blocks.terracotta.BlackTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.BlueTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.BrownTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.CyanTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.GrayTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.GreenTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.LightBlueTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.LightGrayTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.LimeTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.MagentaTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.OrangeTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.PinkTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.PurpleTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.RedTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.WhiteTerracotta;
import com.iamshift.mineaddons.blocks.terracotta.YellowTerracotta;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks 
{
	public static Block concrete;
	public static Block concrete_powder;

	public static Block terracotta_white;
	public static Block terracotta_orange;
	public static Block terracotta_magenta;
	public static Block terracotta_lightblue;
	public static Block terracotta_yellow;
	public static Block terracotta_lime;
	public static Block terracotta_pink;
	public static Block terracotta_gray;
	public static Block terracotta_lightgray;
	public static Block terracotta_cyan;
	public static Block terracotta_purple;
	public static Block terracotta_blue;
	public static Block terracotta_brown;
	public static Block terracotta_green;
	public static Block terracotta_red;
	public static Block terracotta_black;

	public static Block lavasponge;

	public static Block observer;

	public static Block torch_white;
	public static Block torch_orange;
	public static Block torch_magenta;
	public static Block torch_lightblue;
	public static Block torch_yellow;
	public static Block torch_lime;
	public static Block torch_pink;
	public static Block torch_gray;
	public static Block torch_lightgray;
	public static Block torch_cyan;
	public static Block torch_purple;
	public static Block torch_blue;
	public static Block torch_brown;
	public static Block torch_green;
	public static Block torch_red;
	public static Block torch_black;
	
	public static void init()
	{
		if(Config.concrete)
		{
			concrete = new Concrete("concrete");
			concrete_powder = new ConcretePowder("concrete_powder");
		}

		if(Config.terracottablocks)
		{
			terracotta_white = new WhiteTerracotta("terracotta_white");
			terracotta_orange = new OrangeTerracotta("terracotta_orange");
			terracotta_magenta = new MagentaTerracotta("terracotta_magenta");
			terracotta_lightblue = new LightBlueTerracotta("terracotta_lightblue");
			terracotta_yellow = new YellowTerracotta("terracotta_yellow");
			terracotta_lime = new LimeTerracotta("terracotta_lime");
			terracotta_pink = new PinkTerracotta("terracotta_pink");
			terracotta_gray = new GrayTerracotta("terracotta_gray");
			terracotta_lightgray = new LightGrayTerracotta("terracotta_lightgray");
			terracotta_cyan = new CyanTerracotta("terracotta_cyan");
			terracotta_purple = new PurpleTerracotta("terracotta_purple");
			terracotta_blue = new BlueTerracotta("terracotta_blue");
			terracotta_brown = new BrownTerracotta("terracotta_brown");
			terracotta_green = new GreenTerracotta("terracotta_green");
			terracotta_red = new RedTerracotta("terracotta_red");
			terracotta_black = new BlackTerracotta("terracotta_black");
		}

		if(Config.lavasponge)
			lavasponge = new LavaSponge("lavasponge");

		if(Config.observer)
			observer = new Observer("observer");

		if(Config.colorabletorch)
		{
			torch_white = new WhiteTorch("torch_white");
			torch_orange = new OrangeTorch("torch_orange");
			torch_magenta = new MagentaTorch("torch_magenta");
			torch_lightblue = new LightBlueTorch("torch_lightblue");
			torch_yellow = new YellowTorch("torch_yellow");
			torch_lime = new LimeTorch("torch_lime");
			torch_pink = new PinkTorch("torch_pink");
			torch_gray = new GrayTorch("torch_gray");
			torch_lightgray = new LightGrayTorch("torch_lightgray");
			torch_cyan = new CyanTorch("torch_cyan");
			torch_purple = new PurpleTorch("torch_purple");
			torch_blue = new BlueTorch("torch_blue");
			torch_brown = new BrownTorch("torch_brown");
			torch_green = new GreenTorch("torch_green");
			torch_red = new RedTorch("torch_red");
			torch_black = new BlackTorch("torch_black");
		}
	}

	public static void register()
	{
		if(Config.concrete)
		{
			registerBlock(concrete_powder, new ItemBlockMeta(concrete_powder));
			registerBlock(concrete, new ItemBlockMeta(concrete));
		}

		if(Config.terracottablocks)
		{
			registerBlock(terracotta_white, new ItemBlock(terracotta_white));
			registerBlock(terracotta_orange, new ItemBlock(terracotta_orange));
			registerBlock(terracotta_magenta, new ItemBlock(terracotta_magenta));
			registerBlock(terracotta_lightblue, new ItemBlock(terracotta_lightblue));
			registerBlock(terracotta_yellow, new ItemBlock(terracotta_yellow));
			registerBlock(terracotta_lime, new ItemBlock(terracotta_lime));
			registerBlock(terracotta_pink, new ItemBlock(terracotta_pink));
			registerBlock(terracotta_gray, new ItemBlock(terracotta_gray));
			registerBlock(terracotta_lightgray, new ItemBlock(terracotta_lightgray));
			registerBlock(terracotta_cyan, new ItemBlock(terracotta_cyan));
			registerBlock(terracotta_purple, new ItemBlock(terracotta_purple));
			registerBlock(terracotta_blue, new ItemBlock(terracotta_blue));
			registerBlock(terracotta_brown, new ItemBlock(terracotta_brown));
			registerBlock(terracotta_green, new ItemBlock(terracotta_green));
			registerBlock(terracotta_red, new ItemBlock(terracotta_red));
			registerBlock(terracotta_black, new ItemBlock(terracotta_black));
		}

		if(Config.lavasponge)
			registerBlock(lavasponge, new ItemLavaSponge(lavasponge));

		if(Config.observer)
			registerBlock(observer, new ItemBlock(observer));

		if(Config.colorabletorch)
		{
			registerBlock(torch_white, new ItemBlock(torch_white));
			registerBlock(torch_orange, new ItemBlock(torch_orange));
			registerBlock(torch_magenta, new ItemBlock(torch_magenta));
			registerBlock(torch_lightblue, new ItemBlock(torch_lightblue));
			registerBlock(torch_yellow, new ItemBlock(torch_yellow));
			registerBlock(torch_lime, new ItemBlock(torch_lime));
			registerBlock(torch_pink, new ItemBlock(torch_pink));
			registerBlock(torch_gray, new ItemBlock(torch_gray));
			registerBlock(torch_lightgray, new ItemBlock(torch_lightgray));
			registerBlock(torch_cyan, new ItemBlock(torch_cyan));
			registerBlock(torch_purple, new ItemBlock(torch_purple));
			registerBlock(torch_blue, new ItemBlock(torch_blue));
			registerBlock(torch_brown, new ItemBlock(torch_brown));
			registerBlock(torch_green, new ItemBlock(torch_green));
			registerBlock(torch_red, new ItemBlock(torch_red));
			registerBlock(torch_black, new ItemBlock(torch_black));
		}
	}

	public static void registerRenders()
	{
		if(Config.concrete)
		{
			for(EnumDyeColor type : EnumDyeColor.values())
			{
				registerRender(concrete_powder, type.getMetadata(), "concrete_powder_" + type.getName());
				registerRender(concrete, type.getMetadata(), "concrete_" + type.getName());
			}
		}

		if(Config.terracottablocks)
		{
			registerRender(terracotta_white);
			registerRender(terracotta_orange);
			registerRender(terracotta_magenta);
			registerRender(terracotta_lightblue);
			registerRender(terracotta_yellow);
			registerRender(terracotta_lime);
			registerRender(terracotta_pink);
			registerRender(terracotta_gray);
			registerRender(terracotta_lightgray);
			registerRender(terracotta_cyan);
			registerRender(terracotta_purple);
			registerRender(terracotta_blue);
			registerRender(terracotta_brown);
			registerRender(terracotta_green);
			registerRender(terracotta_red);
			registerRender(terracotta_black);
		}

		if(Config.lavasponge)
		{
			registerRender(lavasponge, 0, "lavasponge_dry");
			registerRender(lavasponge, 0, "lavasponge_wet");
		}

		if(Config.observer)
			registerRender(observer);
		
		if(Config.colorabletorch)
		{
			registerRender(torch_white);
			registerRender(torch_orange);
			registerRender(torch_magenta);
			registerRender(torch_lightblue);
			registerRender(torch_yellow);
			registerRender(torch_lime);
			registerRender(torch_pink);
			registerRender(torch_gray);
			registerRender(torch_lightgray);
			registerRender(torch_cyan);
			registerRender(torch_purple);
			registerRender(torch_blue);
			registerRender(torch_brown);
			registerRender(torch_green);
			registerRender(torch_red);
			registerRender(torch_black);
		}
	}

	private static void registerBlock(Block block, ItemBlock itemBlock)
	{
		GameRegistry.register(block);
		GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
		block.setCreativeTab(MineAddons.mineaddonstab);
	}

	private static void registerRender(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	private static void registerRender(Block block, int meta, String fileName)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(References.MODID, fileName), "inventory"));
	}
}
