package com.iamshift.proxy;

import com.iamshift.Config;
import com.iamshift.References;
import com.iamshift.blocks.ModBlocks;
import com.iamshift.fluids.ModFluids;
import com.iamshift.items.ModItems;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy
{
	private static ModelResourceLocation fluid_location;

	@Override
	public void registerRenders() 
	{
		if(Config.buttbooster)
			ModelLoader.setCustomModelResourceLocation(ModItems.buttBooster, 0, new ModelResourceLocation(ModItems.buttBooster.getRegistryName(), "inventory"));

		if(Config.lavasponge || Config.sponge)
			ModelLoader.setCustomModelResourceLocation(ModItems.cellulose, 0, new ModelResourceLocation(ModItems.cellulose.getRegistryName(), "inventory"));
		
		ModBlocks.registerRenders();

		if(Config.lavasponge)
			ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.lavasponge), new ResourceLocation[] { new ResourceLocation(References.MODID, "lavasponge"), new ResourceLocation(References.MODID, "lavasponge_wet") });

		if(Config.concrete)
		{
			ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.concrete), new ResourceLocation[] 
					{ new ResourceLocation(References.MODID, "concrete_white"), 
							new ResourceLocation(References.MODID, "concrete_orange"), 
							new ResourceLocation(References.MODID, "concrete_magenta"),
							new ResourceLocation(References.MODID, "concrete_light_blue"), 
							new ResourceLocation(References.MODID, "concrete_yellow"), 
							new ResourceLocation(References.MODID, "concrete_lime"), 
							new ResourceLocation(References.MODID, "concrete_pink"), 
							new ResourceLocation(References.MODID, "concrete_gray"), 
							new ResourceLocation(References.MODID, "concrete_light_gray"), 
							new ResourceLocation(References.MODID, "concrete_cyan"), 
							new ResourceLocation(References.MODID, "concrete_purple"), 
							new ResourceLocation(References.MODID, "concrete_blue"), 
							new ResourceLocation(References.MODID, "concrete_brown"), 
							new ResourceLocation(References.MODID, "concrete_green"), 
							new ResourceLocation(References.MODID, "concrete_red"), 
							new ResourceLocation(References.MODID, "concrete_black") });

			ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.concrete_powder), new ResourceLocation[] 
					{ new ResourceLocation(References.MODID, "concrete_powder_white"), 
							new ResourceLocation(References.MODID, "concrete_powder_orange"), 
							new ResourceLocation(References.MODID, "concrete_powder_magenta"), 
							new ResourceLocation(References.MODID, "concrete_powder_light_blue"), 
							new ResourceLocation(References.MODID, "concrete_powder_yellow"), 
							new ResourceLocation(References.MODID, "concrete_powder_lime"), 
							new ResourceLocation(References.MODID, "concrete_powder_pink"), 
							new ResourceLocation(References.MODID, "concrete_powder_gray"), 
							new ResourceLocation(References.MODID, "concrete_powder_light_gray"), 
							new ResourceLocation(References.MODID, "concrete_powder_cyan"), 
							new ResourceLocation(References.MODID, "concrete_powder_purple"), 
							new ResourceLocation(References.MODID, "concrete_powder_blue"), 
							new ResourceLocation(References.MODID, "concrete_powder_brown"), 
							new ResourceLocation(References.MODID, "concrete_powder_green"), 
							new ResourceLocation(References.MODID, "concrete_powder_red"), 
							new ResourceLocation(References.MODID, "concrete_powder_black") });
		}

		if(Config.sacredwater || Config.cursedwater)
		{
			ModFluids.renderFluids();
			
			if(Config.sacredwater)
				ModelLoader.setCustomModelResourceLocation(ModItems.rainbowBottle, 0, new ModelResourceLocation(ModItems.rainbowBottle.getRegistryName(), "inventory"));
				
			if(Config.cursedwater)
				ModelLoader.setCustomModelResourceLocation(ModItems.witherDust, 0, new ModelResourceLocation(ModItems.witherDust.getRegistryName(), "inventory"));
		}
	}
}
