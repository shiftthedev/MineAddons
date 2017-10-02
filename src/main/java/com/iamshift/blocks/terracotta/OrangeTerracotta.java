package com.iamshift.blocks.terracotta;

import java.util.Random;

import com.iamshift.References;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class OrangeTerracotta extends Block
{
  public static final PropertyDirection FACING = BlockHorizontal.FACING;
  
  public OrangeTerracotta(String name)
  {
    super(Material.ROCK);
    setUnlocalizedName(name);
    setRegistryName(new ResourceLocation(References.MODID, name));
    
    setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    
    setHardness(1.4F);
    setResistance(7.0F);
    setHarvestLevel("pickaxe", 0);
    setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
  }
  

  public Item getItemDropped(IBlockState state, Random rand, int fortune)
  {
    return Item.getItemFromBlock(this);
  }
  

  public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
  {
    return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
  }
  

  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
  {
    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
  }
  

  public IBlockState getStateFromMeta(int meta)
  {
    EnumFacing face = EnumFacing.getFront(meta);
    if (face.getAxis() == EnumFacing.Axis.Y) {
      face = EnumFacing.NORTH;
    }
    return getDefaultState().withProperty(FACING, face);
  }
  

  public int getMetaFromState(IBlockState state)
  {
    return ((EnumFacing)state.getValue(FACING)).getIndex();
  }
  

  public IBlockState withRotation(IBlockState state, Rotation rot)
  {
    return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
  }
  

  public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
  {
    return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
  }
  

  protected BlockStateContainer createBlockState()
  {
    return new BlockStateContainer(this, new IProperty[] { FACING });
  }
}
