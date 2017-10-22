package com.iamshift.blocks.concrete;

import java.util.List;

import com.iamshift.References;
import com.iamshift.blocks.IMetaBlockName;
import com.iamshift.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ConcretePowder extends Block implements IMetaBlockName
{
	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumDyeColor.class);

	private static final BlockStaticLiquid water = Blocks.WATER;
	private static final BlockDynamicLiquid flowing_water = Blocks.FLOWING_WATER;

	public ConcretePowder(String name) 
	{
		super(Material.SAND);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setHardness(0.5F);
		setResistance(2.5F);
		setHarvestLevel("shovel", 0);
		setSoundType(SoundType.SAND);
		
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumDyeColor.WHITE));
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
	{
		for(EnumDyeColor color : EnumDyeColor.values())
			list.add(new ItemStack(itemIn, 1, color.getMetadata()));
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
		return ((EnumDyeColor)state.getValue(TYPE)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(TYPE, EnumDyeColor.byMetadata(meta));
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumDyeColor.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		Block blockIn = worldIn.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock();
		if ((blockIn.equals(water)) || (blockIn.equals(flowing_water))) 
			worldIn.setBlockState(pos, ModBlocks.concrete.getStateFromMeta(getMetaFromState(state)));

		blockIn = worldIn.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock();
		if ((blockIn.equals(water)) || (blockIn.equals(flowing_water))) 
			worldIn.setBlockState(pos, ModBlocks.concrete.getStateFromMeta(getMetaFromState(state)));

		blockIn = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock();
		if ((blockIn.equals(water)) || (blockIn.equals(flowing_water))) 
			worldIn.setBlockState(pos, ModBlocks.concrete.getStateFromMeta(getMetaFromState(state)));

		blockIn = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock();
		if ((blockIn.equals(water)) || (blockIn.equals(flowing_water))) 
			worldIn.setBlockState(pos, ModBlocks.concrete.getStateFromMeta(getMetaFromState(state)));
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
	{
		Block block = worldIn.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock();
		if ((block.equals(water)) || (block.equals(flowing_water))) 
			worldIn.setBlockState(pos, ModBlocks.concrete.getStateFromMeta(getMetaFromState(state)));

		block = worldIn.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock();
		if ((block.equals(water)) || (block.equals(flowing_water))) 
			worldIn.setBlockState(pos, ModBlocks.concrete.getStateFromMeta(getMetaFromState(state)));

		block = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock();
		if ((block.equals(water)) || (block.equals(flowing_water))) 
			worldIn.setBlockState(pos, ModBlocks.concrete.getStateFromMeta(getMetaFromState(state)));

		block = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock();
		if ((block.equals(water)) || (block.equals(flowing_water))) 
			worldIn.setBlockState(pos, ModBlocks.concrete.getStateFromMeta(getMetaFromState(state)));
	}
}
