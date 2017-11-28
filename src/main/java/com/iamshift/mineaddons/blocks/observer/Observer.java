package com.iamshift.mineaddons.blocks.observer;

import java.util.Random;

import com.iamshift.mineaddons.References;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Observer extends BlockDirectional
{
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	
	public Observer(String name) 
	{
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setHardness(3.0F);
		setResistance(9.0F);
		setHarvestLevel("pickaxe", 0);

		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH).withProperty(POWERED, Boolean.valueOf(false)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {FACING, POWERED});
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) 
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) 
	{
		if(((Boolean)state.getValue(POWERED)).booleanValue())
			worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(false)), 2);
		else
		{
			worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(true)), 2);
			worldIn.scheduleUpdate(pos, this, 2);
		}
		
		this.updateNeighborsInFront(worldIn, pos, state);
	}
	
	private void startSignal(IBlockState state, World worldIn, BlockPos pos)
	{
		if(!((Boolean)state.getValue(POWERED)).booleanValue())
		{
			if(!worldIn.isUpdateScheduled(pos, this))
				worldIn.scheduleUpdate(pos, this, 2);
		}
	}

	protected void updateNeighborsInFront(World worldIn, BlockPos pos, IBlockState state)
    {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        BlockPos blockpos = pos.offset(enumfacing.getOpposite());
        worldIn.notifyBlockOfStateChange(blockpos, this);
        worldIn.notifyNeighborsOfStateExcept(blockpos, this, enumfacing);
    }
	
	@Override
	public boolean canProvidePower(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) 
	{
		return blockState.getWeakPower(blockAccess, pos, side);
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) 
	{
		return ((Boolean)blockState.getValue(POWERED)).booleanValue() && blockState.getValue(FACING) == side ? 15 : 0;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		if (!worldIn.isRemote)
        {
            if (((Boolean)state.getValue(POWERED)).booleanValue())
            {
                this.updateTick(worldIn, pos, state, worldIn.rand);
            }

            this.startSignal(state, worldIn, pos);
        }
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) 
	{
		if (((Boolean)state.getValue(POWERED)).booleanValue() && worldIn.isUpdateScheduled(pos, this))
        {
            this.updateNeighborsInFront(worldIn, pos, state.withProperty(POWERED, Boolean.valueOf(false)));
        }
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack) 
	{
		return this.getDefaultState().withProperty(FACING, getFacingFromEntity(pos, placer).getOpposite());
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(POWERED)).booleanValue())
        {
            i |= 8;
        }

        return i;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
	}
	
	public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase placer)
	{
		if (Math.abs(placer.posX - (double)((float)pos.getX() + 0.5F)) < 2.0D && Math.abs(placer.posZ - (double)((float)pos.getZ() + 0.5F)) < 2.0D)
        {
            double d0 = placer.posY + (double)placer.getEyeHeight();

            if (d0 - (double)pos.getY() > 2.0D)
            {
                return EnumFacing.UP;
            }

            if ((double)pos.getY() - d0 > 0.0D)
            {
                return EnumFacing.DOWN;
            }
        }
		
		return placer.getHorizontalFacing().getOpposite();
	}
	
	@SubscribeEvent
	public void onBlockUpdate(BlockEvent.NeighborNotifyEvent event)
	{
		for(EnumFacing face : EnumFacing.VALUES)
		{
			BlockPos pos = event.getPos().offset(face);
			IBlockState state = event.getWorld().getBlockState(pos);
			
			if(state.getBlock() instanceof Observer)
			{
				if(state.getValue(FACING) == face.getOpposite())
					this.startSignal(state, event.getWorld(), pos);
			}
		}
	}
}
