package com.iamshift.mineaddons.blocks.lavasponge;

import java.util.List;
import java.util.Queue;
import java.util.Random;

import com.google.common.collect.Lists;
import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.blocks.IMetaBlockName;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LavaSponge extends Block implements IMetaBlockName
{
	public static final PropertyEnum TYPE = PropertyEnum.create("type", LavaSpongeEnum.Type.class);

	public LavaSponge(String name) 
	{
		super(Material.SPONGE);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));

		setHardness(0.3F);
		setSoundType(SoundType.PLANT);
		
		setDefaultState(this.blockState.getBaseState().withProperty(TYPE, LavaSpongeEnum.Type.DRY));
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
	{
		for (LavaSpongeEnum.Type type : LavaSpongeEnum.Type.values()) 
		{
			list.add(new ItemStack(itemIn, 1, type.getID()));
		}
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
		return new BlockStateContainer(this, new IProperty[] {TYPE});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		LavaSpongeEnum.Type type = (LavaSpongeEnum.Type)state.getValue(TYPE);
		return type.getID();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		return getDefaultState().withProperty(TYPE, LavaSpongeEnum.Type.values()[meta]);
	}
	
	@Override
	public String getSpecialName(ItemStack stack) 
	{
		return LavaSpongeEnum.Type.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		this.tryAbsorb(worldIn, pos, state);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) 
	{
		this.tryAbsorb(worldIn, pos, state);
		super.neighborChanged(state, worldIn, pos, blockIn);
	}
	
	protected void tryAbsorb(World worldIn, BlockPos pos, IBlockState state) 
	{
		if (((LavaSpongeEnum.Type)state.getValue(TYPE) == LavaSpongeEnum.Type.DRY) && this.absorb(worldIn, pos))
		{
			worldIn.setBlockState(pos, state.withProperty(TYPE, LavaSpongeEnum.Type.WET), 2);
			worldIn.playEvent(2001, pos, Block.getIdFromBlock(Blocks.LAVA));
		}
	}

	private boolean absorb(World worldIn, BlockPos pos)
	{
		Queue<Tuple<BlockPos, Integer>> queue = Lists.<Tuple<BlockPos, Integer>>newLinkedList();
		List<BlockPos> list = Lists.<BlockPos>newArrayList();
		queue.add(new Tuple(pos, Integer.valueOf(0)));
		int i = 0;

		while (!((Queue)queue).isEmpty())
		{
			Tuple<BlockPos, Integer> tuple = (Tuple)queue.poll();
			BlockPos blockpos = (BlockPos)tuple.getFirst();
			int j = ((Integer)tuple.getSecond()).intValue();

			for (EnumFacing enumfacing : EnumFacing.values())
			{
				BlockPos blockpos1 = blockpos.offset(enumfacing);

				if (worldIn.getBlockState(blockpos1).getMaterial() == Material.LAVA)
				{
					worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 2);
					list.add(blockpos1);
					++i;

					if (j < 6)
					{
						queue.add(new Tuple(blockpos1, Integer.valueOf(j + 1)));
					}
				}
			}

			if (i > 64)
			{
				break;
			}
		}

		for (BlockPos blockpos2 : list)
		{
			worldIn.notifyNeighborsOfStateChange(blockpos2, Blocks.AIR);
		}

		return i > 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		if ((LavaSpongeEnum.Type)stateIn.getValue(TYPE) == LavaSpongeEnum.Type.WET)
		{
			EnumFacing enumfacing = EnumFacing.random(rand);

			if (enumfacing != EnumFacing.UP && !worldIn.getBlockState(pos.offset(enumfacing)).isFullyOpaque())
			{
				double d0 = (double)pos.getX();
				double d1 = (double)pos.getY();
				double d2 = (double)pos.getZ();

				if (enumfacing == EnumFacing.DOWN)
				{
					d1 = d1 - 0.05D;
					d0 += rand.nextDouble();
					d2 += rand.nextDouble();
				}
				else
				{
					d1 = d1 + rand.nextDouble() * 0.8D;

					if (enumfacing.getAxis() == EnumFacing.Axis.X)
					{
						d2 += rand.nextDouble();

						if (enumfacing == EnumFacing.EAST)
						{
							++d0;
						}
						else
						{
							d0 += 0.05D;
						}
					}
					else
					{
						d0 += rand.nextDouble();

						if (enumfacing == EnumFacing.SOUTH)
						{
							++d2;
						}
						else
						{
							d2 += 0.05D;
						}
					}
				}

				worldIn.spawnParticle(EnumParticleTypes.DRIP_LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}
}
