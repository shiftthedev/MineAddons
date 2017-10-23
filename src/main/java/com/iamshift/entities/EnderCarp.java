package com.iamshift.entities;

import java.util.Random;

import com.iamshift.utils.LootManager;
import com.iamshift.utils.SoundManager;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EnderCarp extends EntityFlying
{
	private static final DataParameter<Byte> STATUS = EntityDataManager.<Byte>createKey(EnderCarp.class, DataSerializers.BYTE);
	private static final DataParameter<Integer> CARP_SIZE = EntityDataManager.<Integer>createKey(EnderCarp.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> CARP_TEXTURE = EntityDataManager.<Integer>createKey(EnderCarp.class, DataSerializers.VARINT);
	private float clientSideTailAnimation;
	private float clientSideTailAnimationO;
	private float clientSideTailAnimationSpeed;

	public EnderCarp(World worldIn) 
	{
		super(worldIn);
		this.experienceValue = 5;
		this.moveHelper = new EnderCarp.CarpMoveHelper(this);
		this.clientSideTailAnimation = this.rand.nextFloat();
		this.clientSideTailAnimationO = this.clientSideTailAnimation;
	}

	@Override
	protected void initEntityAI() 
	{
		this.tasks.addTask(1, new EnderCarp.AIRandomFly(this));
		this.tasks.addTask(2, new EnderCarp.AILookAround(this));
	}

	@Override
	protected void entityInit() 
	{
		super.entityInit();
		this.dataManager.register(STATUS, Byte.valueOf((byte)0));
		this.dataManager.register(CARP_SIZE, Integer.valueOf(1));
		this.dataManager.register(CARP_TEXTURE, Integer.valueOf(0));
	}

	protected void setCarpSize(int size)
	{
		this.dataManager.set(CARP_SIZE, Integer.valueOf(size));
		this.setSize(0.5F * (float)size, 0.5F * ((float)size / 1.5F));
		this.setPosition(this.posX, this.posY, this.posZ);
	}

	public int getCarpSize()
	{
		return ((Integer)this.dataManager.get(CARP_SIZE)).intValue();
	}

	public int getTexture()
	{
		return ((Integer)this.dataManager.get(CARP_SIZE)).intValue();
	}

	protected void setTexture(int size)
	{
		this.dataManager.set(CARP_TEXTURE, Integer.valueOf(size));
	}

	private boolean isSyncedFlagSet(int flagId)
	{
		return (((Byte)this.dataManager.get(STATUS)).byteValue() & flagId) != 0;
	}

	private void setSyncedFlag(int flagId, boolean state)
	{
		byte b0 = ((Byte)this.dataManager.get(STATUS)).byteValue();

		if (state)
		{
			this.dataManager.set(STATUS, Byte.valueOf((byte)(b0 | flagId)));
		}
		else
		{
			this.dataManager.set(STATUS, Byte.valueOf((byte)(b0 & ~flagId)));
		}
	}

	public boolean isMoving()
	{
		return this.isSyncedFlagSet(2);
	}

	void setMoving(boolean moving)
	{
		this.setSyncedFlag(2, moving);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) 
	{
		super.writeEntityToNBT(compound);

		compound.setInteger("Size", this.getCarpSize() - 1);
		compound.setInteger("Texture", this.getTexture());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		super.readEntityFromNBT(compound);

		int i = compound.getInteger("Size");
		if(i < 0) i = 0;
		this.setCarpSize(i + 1);

		int j = compound.getInteger("Texture");
		if(j < 0) j = 0;
		this.setTexture(j);
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) 
	{
		if(CARP_SIZE.equals(key))
		{
			int i = this.getCarpSize();
			this.setSize(0.5F * (float)i, 0.5F * ((float)i / 1.5F));
			this.rotationYaw = this.rotationYawHead;
			this.renderYawOffset = this.rotationYawHead;

			if (this.isInWater() && this.rand.nextInt(20) == 0)
			{
				this.resetHeight();
			}
		}

		super.notifyDataManagerChange(key);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) 
	{
		this.setCarpSize(this.rand.nextInt(4) + 1);
		this.setTexture(this.rand.nextInt(8));

		return livingdata;
	}

	@Override
	protected void applyEntityAttributes() 
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
	}

	@Override
	public void onLivingUpdate() 
	{
		if(this.worldObj.isRemote)
		{
			if(this.rand.nextInt(10) == 0)
			{
				for (int i = 0; i < 4; ++i)
					this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, 
							this.posX + (this.rand.nextDouble() - 0.5D) * ((double)this.width * 1.5D), 
							this.posY + this.rand.nextDouble() * ((double)this.height * 1.5D) - 0.25D, 
							this.posZ + (this.rand.nextDouble() - 0.5D) * ((double)this.width * 1.5D),
							(this.rand.nextDouble() - 0.5D) * 2.0D, 
							-this.rand.nextDouble(), 
							(this.rand.nextDouble() - 0.5D) * 2.0D, 
							new int[0]);
			}

			this.clientSideTailAnimationO = this.clientSideTailAnimation;

			if(this.isMoving())
			{
				if(this.clientSideTailAnimationSpeed < 0.5F)
					this.clientSideTailAnimationSpeed = 4F;
				else
					this.clientSideTailAnimationSpeed += (.5F - this.clientSideTailAnimationSpeed) * .1F;
			}
			else
				this.clientSideTailAnimationSpeed += (0.125F - this.clientSideTailAnimationSpeed) * .2F;

			this.clientSideTailAnimation += this.clientSideTailAnimationSpeed;
		}

		super.onLivingUpdate();
	}

	@Override
	public float getEyeHeight() 
	{
		return this.height * 0.5F;
	}

	@Override
	protected SoundEvent getAmbientSound() 
	{
		switch (getCarpSize()) 
		{
		case 1:
			return SoundManager.carp_ambient1;
		case 2:
			return SoundManager.carp_ambient2;
		case 3:
			return SoundManager.carp_ambient3;
		case 4:
			return SoundManager.carp_ambient4;
		}

		return SoundManager.carp_ambient4;
	}

	@Override
	protected SoundEvent getHurtSound() 
	{
		return SoundManager.carp_hurt;
	}

	@Override
	protected float getSoundVolume() 
	{
		return 0.3F;
	}

	@Override
	protected ResourceLocation getLootTable() 
	{
		return LootManager.ENDERCARP;
	}

	@Override
	public boolean getCanSpawnHere() 
	{
		if(this.worldObj.provider.getDimensionType() == DimensionType.THE_END && this.rand.nextInt(5) == 0)
		{
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
			int k = MathHelper.floor_double(this.posZ);
			BlockPos blockpos = new BlockPos(i, j, k);

			return this.worldObj.getBlockState(blockpos.down()).getBlock() == Blocks.END_STONE;
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public float getTailAnimation(float p_175471_1_)
	{
		return this.clientSideTailAnimationO + (this.clientSideTailAnimation - this.clientSideTailAnimationO) * p_175471_1_;
	}

	@Override
	public int getMaxSpawnedInChunk() 
	{
		return 2;
	}

	static class AILookAround extends EntityAIBase
	{
		private final EnderCarp parentEntity;

		public AILookAround(EnderCarp carp) 
		{
			this.parentEntity = carp;
			this.setMutexBits(2);
		}

		@Override
		public boolean shouldExecute() 
		{
			return true;
		}

		@Override
		public void updateTask()
		{
			if (this.parentEntity.getAttackTarget() == null)
			{
				this.parentEntity.rotationYaw = -((float)MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float)Math.PI);
				this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
			}
			else
			{
				EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
				double d0 = 64.0D;

				if (entitylivingbase.getDistanceSqToEntity(this.parentEntity) < 4096.0D)
				{
					double d1 = entitylivingbase.posX - this.parentEntity.posX;
					double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
					this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
					this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
				}
			}
		}
	}

	static class AIRandomFly extends EntityAIBase
	{
		private final EnderCarp parentEntity;

		public AIRandomFly(EnderCarp carp)
		{
			this.parentEntity = carp;
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute()
		{
			EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();

			if (!entitymovehelper.isUpdating())
			{
				return true;
			}
			else
			{
				double d0 = entitymovehelper.getX() - this.parentEntity.posX;
				double d1 = entitymovehelper.getY() - this.parentEntity.posY;
				double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
		}

		@Override
		public boolean continueExecuting()
		{
			return false;
		}

		@Override
		public void startExecuting()
		{
			Random random = this.parentEntity.getRNG();
			double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, (2.0D / parentEntity.getCarpSize()));
		}
	}

	static class CarpMoveHelper extends EntityMoveHelper
	{
		private final EnderCarp parentEntity;
		private int courseChangeCooldown;

		public CarpMoveHelper(EnderCarp carp)
		{
			super(carp);
			this.parentEntity = carp;
		}

		@Override
		public void onUpdateMoveHelper()
		{
			if (this.action == EntityMoveHelper.Action.MOVE_TO)
			{
				double d0 = this.posX - this.parentEntity.posX;
				double d1 = this.posY - this.parentEntity.posY;
				double d2 = this.posZ - this.parentEntity.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;

				if (this.courseChangeCooldown-- <= 0)
				{
					this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
					d3 = (double)MathHelper.sqrt_double(d3);

					if (this.isNotColliding(this.posX, this.posY, this.posZ, d3))
					{
						this.parentEntity.motionX += d0 / d3 * 0.1D;
						this.parentEntity.motionY += d1 / d3 * 0.1D;
						this.parentEntity.motionZ += d2 / d3 * 0.1D;
						this.parentEntity.setMoving(true);
					}
					else
					{
						this.action = EntityMoveHelper.Action.WAIT;
						this.parentEntity.setMoving(false);
					}
				}
			}
		}

		private boolean isNotColliding(double x, double y, double z, double p_179926_7_)
		{
			double d0 = (x - this.parentEntity.posX) / p_179926_7_;
			double d1 = (y - this.parentEntity.posY) / p_179926_7_;
			double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
			AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

			for (int i = 1; (double)i < p_179926_7_; ++i)
			{
				axisalignedbb = axisalignedbb.offset(d0, d1, d2);

				if (!this.parentEntity.worldObj.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty())
				{
					return false;
				}
			}

			return true;
		}
	}
}
