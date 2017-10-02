package com.iamshift.blocks.concrete;

import java.util.List;

import com.iamshift.References;
import com.iamshift.blocks.IMetaBlockName;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class Concrete extends Block implements IMetaBlockName
{
	public static final PropertyEnum TYPE = PropertyEnum.create("type", ConcreteEnum.Type.class);

	public Concrete(String name)
	{
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setHardness(1.8F);
		setResistance(9.0F);
		setHarvestLevel("pickaxe", 0);

		setDefaultState(blockState.getBaseState().withProperty(TYPE, ConcreteEnum.Type.WHITE));
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
	{
		for (ConcreteEnum.Type type : ConcreteEnum.Type.values()) 
			list.add(new ItemStack(itemIn, 1, type.getID()));

	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return getMetaFromState(state);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		ConcreteEnum.Type type = (ConcreteEnum.Type)state.getValue(TYPE);
		return type.getID();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(TYPE, ConcreteEnum.Type.values()[meta]);
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return ConcreteEnum.Type.values()[stack.getItemDamage()].getName();
	}
}
