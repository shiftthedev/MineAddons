package com.iamshift.fluids.blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import com.iamshift.References;
import com.iamshift.fluids.ModFluids;
import com.iamshift.interfaces.IMobChanger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.HorseType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CursedWaterBlock extends BlockFluidClassic
{
	public CursedWaterBlock() 
	{
		super(ModFluids.cursedwater, Material.WATER);
		setRegistryName(References.MODID, "cursedwater");
		setUnlocalizedName("cursedwater");

		setHardness(100.0F);
		setLightOpacity(3);
		disableStats();

		GameRegistry.register(this);
	}

	@Override
	protected boolean canFlowInto(IBlockAccess world, BlockPos pos) 
	{
		if (!world.isAirBlock(pos)) return false;

		return true;
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos)
	{
		if (world.isAirBlock(pos))
		{
			return true;
		}

		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (block == this)
		{
			return false;
		}

		if (displacements.containsKey(block))
		{
			if (displacements.get(block))
			{
				if (state.getBlock() != Blocks.SNOW_LAYER) //Forge: Vanilla has a 'bug' where snowballs don't drop like every other block. So special case because ewww...
					block.dropBlockAsItem(world, pos, state, 0);
				return true;
			}
			return false;
		}

		Material material = state.getMaterial();
		if (material.blocksMovement() || material == Material.PORTAL)
		{
			return false;
		}

		Biome biome = world.getBiome(pos);
		if(material == Material.WATER)
		{
			if(block instanceof SacredWaterBlock)
			{
				world.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
				return false;
			}
			
			if(biome != Biomes.OCEAN && biome != Biomes.DEEP_OCEAN)
			{
				int level = block.getMetaFromState(state);
				world.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, level));
				return false;
			}
		}

		return false;

	}

	private boolean isFluid(@Nonnull IBlockState blockstate)
	{
		return blockstate.getMaterial().isLiquid() || blockstate.getBlock() instanceof IFluidBlock;
	}

	@Override
	@Nonnull
	public IBlockState getExtendedState(IBlockState oldState, IBlockAccess worldIn, BlockPos pos) 
	{
		IExtendedBlockState state = (IExtendedBlockState) oldState;
		state = state.withProperty(FLOW_DIRECTION, (float)getFlowDirection(worldIn, pos));
		IBlockState[][] upBlockState = new IBlockState[3][3];
		float[][] height = new float[3][3];
		float[][] corner = new float[2][2];

		upBlockState[1][1] = worldIn.getBlockState(pos.down(densityDir));
		height[1][1] = this.getFluidHeightForRender(worldIn, pos, upBlockState[1][1]);

		if(height[1][1] == 1)
		{
			for(int i = 0; i < 2; i++)
			{
				for(int j = 0; j < 2; j++)
				{
					corner[i][j] = 1;
				}
			}
		}
		else
		{
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					if (i != 1 || j != 1)
					{
						upBlockState[i][j] = worldIn.getBlockState(pos.add(i - 1, 0, j - 1).down(densityDir));
						height[i][j] = this.getFluidHeightForRender(worldIn, pos.add(i - 1, 0, j - 1), upBlockState[i][j]);
					}
				}
			}


			for (int i = 0; i < 2; i++)
			{
				for (int j = 0; j < 2; j++)
				{
					corner[i][j] = getFluidHeightAverage(height[i][j], height[i][j + 1], height[i + 1][j], height[i + 1][j + 1]);
				}
			}

			//check for downflow above corners
			boolean n =  isFluid(upBlockState[0][1]);
			boolean s =  isFluid(upBlockState[2][1]);
			boolean w =  isFluid(upBlockState[1][0]);
			boolean e =  isFluid(upBlockState[1][2]);
			boolean nw = isFluid(upBlockState[0][0]);
			boolean ne = isFluid(upBlockState[0][2]);
			boolean sw = isFluid(upBlockState[2][0]);
			boolean se = isFluid(upBlockState[2][2]);
			if (nw || n || w)
			{
				corner[0][0] = 1;
			}
			if (ne || n || e)
			{
				corner[0][1] = 1;
			}
			if (sw || s || w)
			{
				corner[1][0] = 1;
			}
			if (se || s || e)
			{
				corner[1][1] = 1;
			}
		}

		state = state.withProperty(LEVEL_CORNERS[0], corner[0][0]);
		state = state.withProperty(LEVEL_CORNERS[1], corner[0][1]);
		state = state.withProperty(LEVEL_CORNERS[2], corner[1][1]);
		state = state.withProperty(LEVEL_CORNERS[3], corner[1][0]);
		return state;
	}


	public float getFluidHeightForRender(IBlockAccess world, BlockPos pos, @Nonnull IBlockState up) 
	{
		IBlockState here = world.getBlockState(pos);
		if (here.getBlock() == this)
		{
			if (up.getMaterial().isLiquid() || up.getBlock() instanceof IFluidBlock)
			{
				return 1;
			}

			if (getMetaFromState(here) == getMaxRenderHeightMeta())
			{
				return 0.875F;
			}
		}
		if (here.getBlock() instanceof BlockLiquid)
		{
			return Math.min(1 - BlockLiquid.getLiquidHeightPercent(here.getValue(BlockLiquid.LEVEL)), 14f / 16);
		}
		return !here.getMaterial().isSolid() && up.getBlock() == this ? 1 : this.getQuantaPercentage(world, pos) * 0.875F;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if(world.isRemote)
			return;

		if(!(entity instanceof EntityLivingBase))
			return;

		if(entity.isDead)
			return;

		if (entity instanceof EntityPlayer)
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100));
			return;
		}

		if(((EntityLivingBase)entity).isPotionActive(ModFluids.potionMobChanger) && ((EntityLivingBase)entity).getActivePotionEffect(ModFluids.potionMobChanger).getDuration() <= 0)
			((EntityLivingBase)entity).removeActivePotionEffect(ModFluids.potionMobChanger);

		if(!(((EntityLivingBase)entity).isPotionActive(ModFluids.potionMobChanger)) && tryConvertMob(world, pos, state, entity))
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(ModFluids.potionMobChanger, 6000));
	}

	private boolean tryConvertMob(World world, BlockPos pos, IBlockState state, Entity entity) 
	{
		if(entity instanceof IMobChanger)
		{
			((IMobChanger) entity).cursedWaterEffect();
			return true;
		}
		
		if (entity instanceof EntitySkeleton) 
		{
			EntitySkeleton skeleton = (EntitySkeleton) entity;

			if (skeleton.getSkeletonType() == SkeletonType.NORMAL) 
			{
				skeleton.setSkeletonType(SkeletonType.WITHER);
				skeleton.setHealth(skeleton.getMaxHealth());

				return true;
			}
		}

		if (entity instanceof EntityCreeper) 
		{
			EntityCreeper creeper = (EntityCreeper) entity;

			if (!creeper.getPowered()) 
			{
				creeper.onStruckByLightning(null);
				creeper.setHealth(creeper.getMaxHealth());

				return true;
			}
		}

		if (entity instanceof EntitySpider && !(entity instanceof EntityCaveSpider)) 
		{
			EntitySpider spider = (EntitySpider) entity;
			spider.setDead();

			EntityCaveSpider caveSpider = new EntityCaveSpider(world);
			caveSpider.setLocationAndAngles(spider.posX, spider.posY, spider.posZ, spider.rotationYaw, spider.rotationPitch);
			caveSpider.renderYawOffset = spider.renderYawOffset;
			caveSpider.setHealth(caveSpider.getMaxHealth());

			world.spawnEntityInWorld(caveSpider);

			return true;
		}

		if (entity instanceof EntitySquid) 
		{
			EntitySquid squid = (EntitySquid) entity;
			squid.setDead();

			EntityGhast ghast = new EntityGhast(world);
			ghast.setLocationAndAngles(squid.posX, squid.posY, squid.posZ, squid.rotationYaw, squid.rotationPitch);
			ghast.renderYawOffset = squid.renderYawOffset;
			ghast.setHealth(ghast.getMaxHealth());

			world.spawnEntityInWorld(ghast);

			return true;
		}

		if (entity instanceof EntitySilverfish) 
		{
			EntitySilverfish silverfish = (EntitySilverfish) entity;
			silverfish.setDead();

			EntityEndermite endermite = new EntityEndermite(world);
			endermite.setLocationAndAngles(silverfish.posX, silverfish.posY, silverfish.posZ, silverfish.rotationYaw, silverfish.rotationPitch);
			endermite.renderYawOffset = silverfish.renderYawOffset;
			endermite.setHealth(endermite.getMaxHealth());

			world.spawnEntityInWorld(endermite);

			return true;
		}

		if (entity instanceof EntityVillager) 
		{
			EntityVillager villager = (EntityVillager) entity;
			villager.setDead();

			EntityWitch witch = new EntityWitch(world);
			witch.setLocationAndAngles(villager.posX, villager.posY, villager.posZ, villager.rotationYaw, villager.rotationPitch);
			witch.renderYawOffset = villager.renderYawOffset;
			witch.setHealth(witch.getMaxHealth());

			world.spawnEntityInWorld(witch);

			return true;
		}

		if (entity instanceof EntityGuardian) 
		{
			EntityGuardian guardian = (EntityGuardian) entity;

			if (!guardian.isElder()) 
			{
				guardian.setElder();
				guardian.setHealth(guardian.getMaxHealth());

				return true;
			}
		}

		if (entity instanceof EntityBat) 
		{
			EntityBat bat = (EntityBat) entity;
			bat.setDead();

			EntityBlaze blaze = new EntityBlaze(world);
			blaze.setLocationAndAngles(bat.posX, bat.posY, bat.posZ, bat.rotationYaw, bat.rotationPitch);
			blaze.renderYawOffset = bat.renderYawOffset;
			blaze.setHealth(blaze.getMaxHealth());

			world.spawnEntityInWorld(blaze);

			return true;
		}

		if (entity instanceof EntityHorse) 
		{
			EntityHorse horse = (EntityHorse) entity;

			if (horse.getType() != HorseType.ZOMBIE) 
			{
				horse.setType(HorseType.ZOMBIE);
				horse.setHealth(horse.getMaxHealth());

				return true;
			}
		}

		if (entity instanceof EntityRabbit) 
		{
			EntityRabbit rabbit = (EntityRabbit) entity;

			if (rabbit.getRabbitType() != 99) 
			{
				rabbit.setRabbitType(99);
				rabbit.setHealth(rabbit.getMaxHealth());

				return true;
			}
		}

		if (entity instanceof EntityZombie && !(entity instanceof EntityPigZombie)) 
		{
			EntityZombie zombie = (EntityZombie) entity;
			zombie.setDead();

			EntityPigZombie pigzombie = new EntityPigZombie(world);
			pigzombie.setLocationAndAngles(zombie.posX, zombie.posY, zombie.posZ, zombie.rotationYaw, zombie.rotationPitch);
			pigzombie.renderYawOffset = zombie.renderYawOffset;
			pigzombie.setHealth(pigzombie.getMaxHealth());

			world.spawnEntityInWorld(pigzombie);

			return true;
		}
		
		return false;
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) 
	{
		super.randomDisplayTick(state, world, pos, rand);
		if (rand.nextInt(20)==0)
		{           
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + rand.nextFloat(), pos.getY() + 1.0F, pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

	@SideOnly(Side.CLIENT)
	public void render()
	{
		ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(LEVEL).build());
	}
}
