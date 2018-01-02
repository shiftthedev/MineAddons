package com.iamshift.mineaddons.items.entities;

import com.iamshift.mineaddons.Config;
import com.iamshift.mineaddons.achievements.AchievementHandler;
import com.iamshift.mineaddons.fluids.ModFluids;
import com.iamshift.mineaddons.items.RainbowBottle;
import com.iamshift.mineaddons.items.WitherDust;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class EntityWaterChanger extends EntityItem
{
	public EntityWaterChanger(World worldIn) 
	{
		super(worldIn);
	}

	public EntityWaterChanger(World worldIn, double x, double y, double z, ItemStack stack) 
	{
		super(worldIn, x, y, z, stack);
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		Item item = this.getEntityItem().getItem();

		if(this.getThrower() == null)
			return;

		EntityPlayer player = this.worldObj.getPlayerEntityByName(this.getThrower());

		int x = MathHelper.floor_double(this.posX);
		int y = MathHelper.floor_double((this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0D);
		int z = MathHelper.floor_double(this.posZ);

		BlockPos pos = new BlockPos(x, y, z);

		IBlockState state = this.worldObj.getBlockState(pos);
		Material mat = state.getMaterial();
		Biome biome = this.worldObj.getBiome(pos);

		IBlockState stateAbove = this.worldObj.getBlockState(new BlockPos(x, y+1, z));
		if(stateAbove != Blocks.AIR.getDefaultState())
			return;

		if(mat.isLiquid() 
				&& mat == Material.WATER 
				&& state.getBlock().getUnlocalizedName().equals("tile.water") 
				&& state.getBlock().getMetaFromState(state) == 0 
				&& biome != Biomes.OCEAN 
				&& biome != Biomes.DEEP_OCEAN)
		{
			if(Config.sacredwater && item instanceof RainbowBottle)
			{
				this.worldObj.setBlockState(pos, ModFluids.sacredwater_block.getDefaultState());

				--this.getEntityItem().stackSize;

				if(this.getEntityItem().stackSize <= 0)
					this.setDead();

				if(!player.hasAchievement(AchievementHandler.achievementSacredWater))
					player.addStat(AchievementHandler.achievementSacredWater);

				return;
			}

			if(Config.cursedwater && item instanceof WitherDust)
			{
				this.worldObj.setBlockState(pos, ModFluids.cursedwater_block.getDefaultState());

				--this.getEntityItem().stackSize;

				if(this.getEntityItem().stackSize <= 0)
					this.setDead();

				if(!player.hasAchievement(AchievementHandler.achievementCursedWater))
					player.addStat(AchievementHandler.achievementCursedWater);

				return;
			}

		}

	}
}
