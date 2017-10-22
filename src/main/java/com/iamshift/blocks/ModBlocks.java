package com.iamshift.blocks;

import com.iamshift.Config;
import com.iamshift.References;
import com.iamshift.blocks.colorabletorch.BlackTorch;
import com.iamshift.blocks.colorabletorch.BlueTorch;
import com.iamshift.blocks.colorabletorch.BrownTorch;
import com.iamshift.blocks.colorabletorch.CyanTorch;
import com.iamshift.blocks.colorabletorch.GrayTorch;
import com.iamshift.blocks.colorabletorch.GreenTorch;
import com.iamshift.blocks.colorabletorch.LightBlueTorch;
import com.iamshift.blocks.colorabletorch.LightGrayTorch;
import com.iamshift.blocks.colorabletorch.LimeTorch;
import com.iamshift.blocks.colorabletorch.MagentaTorch;
import com.iamshift.blocks.colorabletorch.OrangeTorch;
import com.iamshift.blocks.colorabletorch.PinkTorch;
import com.iamshift.blocks.colorabletorch.PurpleTorch;
import com.iamshift.blocks.colorabletorch.RedTorch;
import com.iamshift.blocks.colorabletorch.WhiteTorch;
import com.iamshift.blocks.colorabletorch.YellowTorch;
import com.iamshift.blocks.concrete.Concrete;
import com.iamshift.blocks.concrete.ConcretePowder;
import com.iamshift.blocks.lavasponge.LavaSponge;
import com.iamshift.blocks.lavasponge.LavaSpongeEnum;
import com.iamshift.blocks.observer.Observer;
import com.iamshift.blocks.terracotta.BlackTerracotta;
import com.iamshift.blocks.terracotta.BlueTerracotta;
import com.iamshift.blocks.terracotta.BrownTerracotta;
import com.iamshift.blocks.terracotta.CyanTerracotta;
import com.iamshift.blocks.terracotta.GrayTerracotta;
import com.iamshift.blocks.terracotta.GreenTerracotta;
import com.iamshift.blocks.terracotta.LightBlueTerracotta;
import com.iamshift.blocks.terracotta.LightGrayTerracotta;
import com.iamshift.blocks.terracotta.LimeTerracotta;
import com.iamshift.blocks.terracotta.MagentaTerracotta;
import com.iamshift.blocks.terracotta.OrangeTerracotta;
import com.iamshift.blocks.terracotta.PinkTerracotta;
import com.iamshift.blocks.terracotta.PurpleTerracotta;
import com.iamshift.blocks.terracotta.RedTerracotta;
import com.iamshift.blocks.terracotta.WhiteTerracotta;
import com.iamshift.blocks.terracotta.YellowTerracotta;

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
			registerBlock(terracotta_white);
			registerBlock(terracotta_orange);
			registerBlock(terracotta_magenta);
			registerBlock(terracotta_lightblue);
			registerBlock(terracotta_yellow);
			registerBlock(terracotta_lime);
			registerBlock(terracotta_pink);
			registerBlock(terracotta_gray);
			registerBlock(terracotta_lightgray);
			registerBlock(terracotta_cyan);
			registerBlock(terracotta_purple);
			registerBlock(terracotta_blue);
			registerBlock(terracotta_brown);
			registerBlock(terracotta_green);
			registerBlock(terracotta_red);
			registerBlock(terracotta_black);
		}

		if(Config.lavasponge)
			registerBlock(lavasponge, new ItemBlockMeta(lavasponge));

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
			for(LavaSpongeEnum.Type type : LavaSpongeEnum.Type.values())
				registerRender(lavasponge, type.getID(), "lavasponge_" + type.getName());
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

	private static void registerBlock(Block block)
	{
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	private static void registerBlock(Block block, ItemBlock itemBlock)
	{
		GameRegistry.register(block);
		GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
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
