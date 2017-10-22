package com.iamshift.blocks.colorabletorch;

import com.iamshift.blocks.ModBlocks;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BaseTorch extends BlockTorch
{
	public BaseTorch() 
	{
		super();
		setHardness(0.0F);
		setResistance(0.0F);
		setLightLevel(0.9375F);
		setSoundType(SoundType.WOOD);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(worldIn.isRemote)
			return false;
		
		if(heldItem == null)
			return false;

		if(heldItem.getItem() == null)
			return false;
		
		if(!(heldItem.getItem() instanceof ItemDye))
			return false;
		
		switch(heldItem.getMetadata())
		{
		case 0:
			worldIn.setBlockState(pos, ModBlocks.torch_black.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 1:
			worldIn.setBlockState(pos, ModBlocks.torch_red.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 2:
			worldIn.setBlockState(pos, ModBlocks.torch_green.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 3:
			worldIn.setBlockState(pos, ModBlocks.torch_brown.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 4:
			worldIn.setBlockState(pos, ModBlocks.torch_blue.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 5:
			worldIn.setBlockState(pos, ModBlocks.torch_purple.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 6:
			worldIn.setBlockState(pos, ModBlocks.torch_cyan.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 7:
			worldIn.setBlockState(pos, ModBlocks.torch_lightgray.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 8:
			worldIn.setBlockState(pos, ModBlocks.torch_gray.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 9:
			worldIn.setBlockState(pos, ModBlocks.torch_pink.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 10:
			worldIn.setBlockState(pos, ModBlocks.torch_lime.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 11:
			worldIn.setBlockState(pos, ModBlocks.torch_yellow.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 12:
			worldIn.setBlockState(pos, ModBlocks.torch_lightblue.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 13:
			worldIn.setBlockState(pos, ModBlocks.torch_magenta.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 14:
			worldIn.setBlockState(pos, ModBlocks.torch_orange.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		case 15:
			worldIn.setBlockState(pos, ModBlocks.torch_white.getDefaultState().withProperty(FACING, getDirection(state)));
			break;
		}
		
		return true;
	}
	
	private EnumFacing getDirection(IBlockState state)
	{
		int meta = this.getMetaFromState(state);
		
		EnumFacing face = EnumFacing.UP;
		
		switch (meta)
        {
            case 1:
            	face = EnumFacing.EAST;
            	break;
            case 2:
            	face = EnumFacing.WEST;
            	break;
            case 3:
            	face = EnumFacing.SOUTH;
            	break;
            case 4:
            	face = EnumFacing.NORTH;
            	break;
        }
		
		return face;
	}
}
