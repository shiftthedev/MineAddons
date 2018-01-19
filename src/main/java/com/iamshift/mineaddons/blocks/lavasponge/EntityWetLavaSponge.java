package com.iamshift.mineaddons.blocks.lavasponge;

import java.util.List;

import com.iamshift.mineaddons.blocks.ModBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityWetLavaSponge extends EntityItem
{
	public EntityWetLavaSponge(World worldIn)
	{
		super(worldIn);
	}

	public EntityWetLavaSponge(World worldIn, double x, double y, double z, ItemStack stack) 
	{
		super(worldIn, x, y, z, stack);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(this.worldObj.isRemote)
			return;

		if(this.isDead)
			return;

		Item item = this.getEntityItem().getItem();

		int x = MathHelper.floor_double(this.posX);
		int y = MathHelper.floor_double((this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0D);
		int z = MathHelper.floor_double(this.posZ);

		BlockPos pos = new BlockPos(x, y, z);

		IBlockState state = this.worldObj.getBlockState(pos);
		Material mat = state.getMaterial();
		final ItemStack stack = this.getEntityItem();

		if(mat.isLiquid() && mat == Material.WATER && state.getBlock().getMetaFromState(state) == 0)
		{
			this.worldObj.setBlockToAir(pos);
			InventoryHelper.spawnItemStack(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Item.getItemFromBlock(ModBlocks.lavasponge), 1, 0));
			--stack.stackSize;
			return;
		}
		
		if(stack.stackSize <= 0)
			this.setDead();
	}
}