package com.iamshift.entities;

import javax.annotation.Nullable;

import com.iamshift.Config;
import com.iamshift.entities.ais.AITrueCreeperSwell;
import com.iamshift.interfaces.IMobChanger;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TrueCreeper extends EntityMob implements IMobChanger
{
	private static final DataParameter<Integer> STATE = EntityDataManager.<Integer>createKey(EntityCreeper.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IGNITED = EntityDataManager.<Boolean>createKey(EntityCreeper.class, DataSerializers.BOOLEAN);

	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 30;
	private int explosionRadius = 3;
	private int droppedSkulls;

	public TrueCreeper(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.8F, 2.46F);
	}

	@Override
	protected void initEntityAI() 
	{
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new AITrueCreeperSwell(this));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, PeaceCreeper.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
	}

	@Override
	public int getMaxFallHeight() 
	{
		return this.getAttackTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
	}

	@Override
	public void fall(float distance, float damageMultiplier) 
	{
		super.fall(distance, damageMultiplier);
		this.timeSinceIgnited = (int)((float)this.timeSinceIgnited + distance * 1.5F);

		if (this.timeSinceIgnited > this.fuseTime - 5)
		{
			this.timeSinceIgnited = this.fuseTime - 5;
		}
	}

	@Override
	protected void entityInit() 
	{
		super.entityInit();
		this.dataManager.register(STATE, Integer.valueOf(-1));
		this.dataManager.register(IGNITED, Boolean.valueOf(false));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) 
	{
		super.writeEntityToNBT(compound);

		compound.setShort("Fuse", (short)this.fuseTime);
		compound.setByte("ExplosionRadius", (byte)this.explosionRadius);
		compound.setBoolean("ignited", this.hasIgnited());
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);

		if (compound.hasKey("Fuse", 99))
		{
			this.fuseTime = compound.getShort("Fuse");
		}

		if (compound.hasKey("ExplosionRadius", 99))
		{
			this.explosionRadius = compound.getByte("ExplosionRadius");
		}

		if (compound.getBoolean("ignited"))
		{
			this.setIgnite(true);
		}
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt) 
	{
		return;
	}

	@Override
	public void onUpdate() 
	{
		if (this.isEntityAlive())
		{
			this.lastActiveTime = this.timeSinceIgnited;

			if (this.hasIgnited())
			{
				this.setCreeperState(1);
			}

			int i = this.getCreeperState();

			if (i > 0 && this.timeSinceIgnited == 0)
			{
				this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
			}

			this.timeSinceIgnited += i;

			if (this.timeSinceIgnited < 0)
			{
				this.timeSinceIgnited = 0;
			}

			if (this.timeSinceIgnited >= this.fuseTime)
			{
				this.timeSinceIgnited = this.fuseTime;
				this.explode();
			}
		}

		super.onUpdate();
	}

	@Override
	protected SoundEvent getHurtSound()
	{
		return SoundEvents.ENTITY_CREEPER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_CREEPER_DEATH;
	}

	@Override
	public void onDeath(DamageSource cause)
	{
		super.onDeath(cause);

		if (this.worldObj.getGameRules().getBoolean("doMobLoot"))
		{
			if (cause.getEntity() instanceof EntitySkeleton)
			{
				int i = Item.getIdFromItem(Items.RECORD_13);
				int j = Item.getIdFromItem(Items.RECORD_WAIT);
				int k = i + this.rand.nextInt(j - i + 1);
				this.dropItem(Item.getItemById(k), 1);
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public float getCreeperFlashIntensity(float par1)
	{
		return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * par1) / (float)(this.fuseTime - 2);
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable()
	{
		return LootTableList.ENTITIES_CREEPER;
	}

	public int getCreeperState()
	{
		return ((Integer)this.dataManager.get(STATE)).intValue();
	}

	public void setCreeperState(int state)
	{
		this.dataManager.set(STATE, Integer.valueOf(state));
	}


	private void explode()
	{
		if (!this.worldObj.isRemote)
		{
			boolean flag = this.worldObj.getGameRules().getBoolean("mobGriefing");
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, flag);

			this.timeSinceIgnited = 0;
			this.lastActiveTime = 0;
			this.setIgnite(false);
			this.setCreeperState(-1);
		}
	}

	public boolean hasIgnited()
	{
		return ((Boolean)this.dataManager.get(IGNITED)).booleanValue();
	}

	public void setIgnite(boolean ignite)
	{
		this.dataManager.set(IGNITED, Boolean.valueOf(ignite));
	}

	@Override
	public boolean getCanSpawnHere() 
	{
		if(this.worldObj.provider instanceof WorldProviderSurface)
			return super.getCanSpawnHere();

		return false;
	}

	//IMOBCHANGER
	@Override
	public void cursedWaterEffect() {}

	@Override
	public void sacredWaterEffect() 
	{
		if(Config.peacecreeper)
		{
			PeaceCreeper creeper = new PeaceCreeper(worldObj);
			creeper.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			creeper.renderYawOffset = this.renderYawOffset;
			creeper.setHealth(creeper.getMaxHealth());

			this.setDead();
			worldObj.spawnEntityInWorld(creeper);
		}
	} 

}
