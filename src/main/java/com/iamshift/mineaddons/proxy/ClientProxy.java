package com.iamshift.mineaddons.proxy;

import com.iamshift.mineaddons.Config;
import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.blocks.ModBlocks;
import com.iamshift.mineaddons.entities.AncientCarp;
import com.iamshift.mineaddons.entities.EnderCarp;
import com.iamshift.mineaddons.entities.PeaceCreeper;
import com.iamshift.mineaddons.entities.TrueCreeper;
import com.iamshift.mineaddons.entities.VoidCreeper;
import com.iamshift.mineaddons.entities.renders.RenderAncientCarp;
import com.iamshift.mineaddons.entities.renders.RenderEnderCarp;
import com.iamshift.mineaddons.entities.renders.RenderPeaceCreeper;
import com.iamshift.mineaddons.entities.renders.RenderTrueCreeper;
import com.iamshift.mineaddons.entities.renders.RenderVoidCreeper;
import com.iamshift.mineaddons.fluids.ModFluids;
import com.iamshift.mineaddons.items.ModItems;
import com.iamshift.mineaddons.utils.SoundManager;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	private static ModelResourceLocation fluid_location;
	
	@Override
	public void registerRenders() 
	{
		ModBlocks.registerRenders();
		
		if(Config.buttbooster)
			ModelLoader.setCustomModelResourceLocation(ModItems.buttBooster, 0, new ModelResourceLocation(ModItems.buttBooster.getRegistryName(), "inventory"));

		if(Config.lavasponge || Config.sponge)
			ModelLoader.setCustomModelResourceLocation(ModItems.cellulose, 0, new ModelResourceLocation(ModItems.cellulose.getRegistryName(), "inventory"));

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
							new ResourceLocation(References.MODID, "concrete_silver"), 
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
							new ResourceLocation(References.MODID, "concrete_powder_silver"), 
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

		if(Config.truecreeper)
		{
			RenderingRegistry.registerEntityRenderingHandler(TrueCreeper.class, new IRenderFactory() 
			{
				@Override
				public Render createRenderFor(RenderManager manager) 
				{
					return new RenderTrueCreeper(manager);
				}

			});
		}
		
		if(Config.peacecreeper)
		{
			RenderingRegistry.registerEntityRenderingHandler(PeaceCreeper.class, new IRenderFactory() 
			{
				@Override
				public Render createRenderFor(RenderManager manager) 
				{
					return new RenderPeaceCreeper(manager);
				}

			});
		}
		
		if(Config.ironnuggets)
			ModelLoader.setCustomModelResourceLocation(ModItems.ironnugget, 0, new ModelResourceLocation(ModItems.ironnugget.getRegistryName(), "inventory"));
		
		if(Config.supernametag)
			ModelLoader.setCustomModelResourceLocation(ModItems.supernametag, 0, new ModelResourceLocation("minecraft:name_tag", "inventory"));
		
		if(Config.endexpansion)
		{
			if(Config.endercarp || Config.ancientcarp)
				ModelLoader.setCustomModelResourceLocation(ModItems.sushi, 0, new ModelResourceLocation(ModItems.sushi.getRegistryName(), "inventory"));
			
			if(Config.endercarp)
			{
				RenderingRegistry.registerEntityRenderingHandler(EnderCarp.class, new IRenderFactory() 
				{
					@Override
					public Render createRenderFor(RenderManager manager) 
					{
						return new RenderEnderCarp(manager);
					}

				});
			}
			
			if(Config.ancientcarp)
			{
				ModelLoader.setCustomModelResourceLocation(ModItems.ancientessence, 0, new ModelResourceLocation(ModItems.ancientessence.getRegistryName(), "inventory"));
				
				RenderingRegistry.registerEntityRenderingHandler(AncientCarp.class, new IRenderFactory() 
				{
					@Override
					public Render createRenderFor(RenderManager manager) 
					{
						return new RenderAncientCarp(manager);
					}

				});
			}
			
			if(Config.noaishulker)
				ModelLoader.setCustomModelResourceLocation(ModItems.noaishulkerspawnegg, 0, new ModelResourceLocation(ModItems.noaishulkerspawnegg.getRegistryName(), "inventory"));
			
			if(Config.voidcreeper)
			{
				RenderingRegistry.registerEntityRenderingHandler(VoidCreeper.class, new IRenderFactory() 
				{
					@Override
					public Render createRenderFor(RenderManager manager) 
					{
						return new RenderVoidCreeper(manager);
					}

				});
			}
			
			if(Config.timeskipclock)
				ModelLoader.setCustomModelResourceLocation(ModItems.timeskipclock, 0, new ModelResourceLocation(ModItems.timeskipclock.getRegistryName(), "inventory"));
		}
	}

	@Override
	public void registerSounds()
	{
		SoundManager.init();
	}
}
