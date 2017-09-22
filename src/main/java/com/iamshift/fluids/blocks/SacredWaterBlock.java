package com.iamshift.fluids.blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import com.iamshift.References;
import com.iamshift.fluids.ModFluids;

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

public class SacredWaterBlock extends BlockFluidClassic
{
	public SacredWaterBlock() 
	{
		super(ModFluids.sacredwater, Material.WATER);
		setRegistryName(References.MODID, "sacredwater");
		setUnlocalizedName("sacredwater");

		setHardness(100.0F);
		setLightOpacity(3);
		disableStats();

		setDensity(1);

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
			if(block instanceof CursedWaterBlock)
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

		if(entity instanceof EntityPlayer)
		{
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100));
			return;
		}

		if(((EntityLivingBase)entity).isPotionActive(ModFluids.potionMobChanger) && ((EntityLivingBase)entity).getActivePotionEffect(ModFluids.potionMobChanger).getDuration() <= 0)
			((EntityLivingBase)entity).removeActivePotionEffect(ModFluids.potionMobChanger);

		if(!(((EntityLivingBase)entity).isPotionActive(ModFluids.potionMobChanger)) && tryConvertMob(world, pos, state, entity))
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(ModFluids.potionMobChanger, 6000));
	}

	private boolean tryConvertMob(World world, BlockPos pos, IBlockState state, Entity entity) 
	{
		if(entity instanceof EntitySkeleton)
		{
			EntitySkeleton skeleton = (EntitySkeleton) entity;

			if (skeleton.getSkeletonType() == SkeletonType.WITHER) 
			{
				skeleton.setSkeletonType(SkeletonType.NORMAL);
				skeleton.setHealth(skeleton.getMaxHealth());

				return true;
			}
		}

		if (entity instanceof EntityCreeper) 
		{
			EntityCreeper creeper = (EntityCreeper) entity;

			if (creeper.getPowered()) 
			{
				creeper.setDead();

				EntityCreeper newCreeper = new EntityCreeper(world);
				newCreeper.setLocationAndAngles(creeper.posX, creeper.posY, creeper.posZ, creeper.rotationYaw, creeper.rotationPitch);
				newCreeper.renderYawOffset = creeper.renderYawOffset;
				newCreeper.setHealth(newCreeper.getMaxHealth());

				world.spawnEntityInWorld(newCreeper);

				return true;
			}
		}

		if (entity instanceof EntityCaveSpider) 
		{
			EntityCaveSpider cavespider = (EntityCaveSpider) entity;
			cavespider.setDead();

			EntitySpider spider = new EntitySpider(world);
			spider.setLocationAndAngles(cavespider.posX, cavespider.posY, cavespider.posZ, cavespider.rotationYaw, cavespider.rotationPitch);
			spider.renderYawOffset = cavespider.renderYawOffset;
			spider.setHealth(spider.getMaxHealth());

			world.spawnEntityInWorld(spider);

			return true;
		}

		if(entity instanceof EntityGhast)
		{
			EntityGhast ghast = (EntityGhast) entity;
			ghast.setDead();

			EntitySquid squid = new EntitySquid(world);
			squid.setLocationAndAngles(ghast.posX, ghast.posY, ghast.posZ, ghast.rotationYaw, ghast.rotationPitch);
			squid.renderYawOffset = ghast.renderYawOffset;
			squid.setHealth(squid.getMaxHealth());

			world.spawnEntityInWorld(squid);

			return true;
		}

		if(entity instanceof EntityEndermite)
		{
			EntityEndermite endermite = (EntityEndermite) entity;
			endermite.setDead();

			EntitySilverfish silverfish = new EntitySilverfish(world);
			silverfish.setLocationAndAngles(endermite.posX, endermite.posY, endermite.posZ, endermite.rotationYaw, endermite.rotationPitch);
			silverfish.renderYawOffset = endermite.renderYawOffset;
			silverfish.setHealth(silverfish.getMaxHealth());

			world.spawnEntityInWorld(silverfish);

			return true;
		}

		if(entity instanceof EntityWitch)
		{
			EntityWitch witch = (EntityWitch) entity;
			witch.setDead();

			EntityVillager villager = new EntityVillager(world);
			villager.setLocationAndAngles(witch.posX, witch.posY, witch.posZ, witch.rotationYaw, witch.rotationPitch);
			villager.renderYawOffset = witch.renderYawOffset;
			villager.setHealth(villager.getMaxHealth());

			world.spawnEntityInWorld(villager);

			return true;
		}

		if (entity instanceof EntityGuardian) 
		{
			EntityGuardian guardian = (EntityGuardian) entity;

			if (guardian.isElder()) 
			{
				guardian.setElder(false);
				guardian.setHealth(guardian.getMaxHealth());

				return true;
			}
		}

		if(entity instanceof EntityBlaze)
		{
			EntityBlaze blaze = (EntityBlaze) entity;
			blaze.setDead();

			EntityBat bat = new EntityBat(world);
			bat.setLocationAndAngles(blaze.posX, blaze.posY, blaze.posZ, blaze.rotationYaw, blaze.rotationPitch);
			bat.renderYawOffset = blaze.renderYawOffset;
			bat.setHealth(bat.getMaxHealth());

			world.spawnEntityInWorld(bat);

			return true;
		}

		if (entity instanceof EntityHorse) 
		{
			EntityHorse horse = (EntityHorse) entity;

			if (horse.getType() == HorseType.ZOMBIE) 
			{
				int rand = new Random().nextInt(3);

				horse.setType(HorseType.values()[rand]);
				horse.setHealth(horse.getMaxHealth());

				return true;
			}
		}

		if (entity instanceof EntityRabbit) 
		{
			EntityRabbit rabbit = (EntityRabbit) entity;

			if (rabbit.getRabbitType() == 99) 
			{
				int rand = new Random().nextInt(6);

				rabbit.setRabbitType(rand);
				rabbit.setHealth(rabbit.getMaxHealth());

				return true;
			}
		}

		if(entity instanceof EntityPigZombie)
		{
			EntityPigZombie pigzombie = (EntityPigZombie) entity;
			pigzombie.setDead();

			EntityZombie zombie = new EntityZombie(world);
			zombie.setLocationAndAngles(pigzombie.posX, pigzombie.posY, pigzombie.posZ, pigzombie.rotationYaw, pigzombie.rotationPitch);
			zombie.renderYawOffset = pigzombie.renderYawOffset;
			zombie.setHealth(zombie.getMaxHealth());

			world.spawnEntityInWorld(zombie);

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
			world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + rand.nextFloat(), pos.getY() + 1.0F, pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

	@SideOnly(Side.CLIENT)
	public void render()
	{
		ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(LEVEL).build());
	}
}
