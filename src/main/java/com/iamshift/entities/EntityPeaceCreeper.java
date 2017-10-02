package com.iamshift.entities;

import javax.annotation.Nullable;

import com.iamshift.achievements.AchievementHandler;
import com.iamshift.interfaces.IMobChanger;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPeaceCreeper extends EntityTameable implements IMobChanger
{
	private static final DataParameter<Integer> COLLAR_COLOR = EntityDataManager.<Integer>createKey(EntityWolf.class, DataSerializers.VARINT);

	public EntityPeaceCreeper(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.35F, 0.8F);
		this.setTamed(false);
		this.setSitting(false);
	}

	@Override
	protected void initEntityAI() 
	{
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAIFollowOwner(this, 1.0D, 5.0F, 2.0F));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() 
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);

		if (this.isTamed())
		{
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		}
	}
	
	@Override
	public float getEyeHeight() 
	{
		return 0.7F;
	}

	@Override
	public void setAttackTarget(EntityLivingBase entitylivingbaseIn) {}

	@Override
	protected void entityInit() 
	{
		super.entityInit();
		this.dataManager.register(COLLAR_COLOR, Integer.valueOf(EnumDyeColor.WHITE.getDyeDamage()));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) 
	{
		super.writeEntityToNBT(compound);
		compound.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		super.readEntityFromNBT(compound);
		if (compound.hasKey("CollarColor", 99))
		{
			this.setCollarColor(EnumDyeColor.byDyeDamage(compound.getByte("CollarColor")));
		}
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
	protected float getSoundVolume() 
	{
		return 0.4F;
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() 
	{
		return null;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			Entity entity = source.getEntity();

			if (this.aiSit != null)
			{
				this.aiSit.setSitting(false);
			}

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
			{
				amount = (amount + 1.0F) / 2.0F;
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		return false;
	}

	@Override
	public void setTamed(boolean tamed) 
	{
		super.setTamed(tamed);

		if (tamed)
		{
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) 
	{
		if(this.isTamed())
		{
			if(stack != null)
			{
				if(stack.getItem() == Items.GUNPOWDER && this.getHealth() < 20.0F)
				{
					Item gunpowder = stack.getItem();

					if(!player.capabilities.isCreativeMode)
						--stack.stackSize;

					this.heal(1F);
					return true;
				}
				else if(stack.getItem() == Items.DYE)
				{
					EnumDyeColor color = EnumDyeColor.byDyeDamage(stack.getMetadata());

					if(color != this.getCollarColor())
					{
						this.setCollarColor(color);

						if(!player.capabilities.isCreativeMode)
							--stack.stackSize;

						return true;
					}
				}
			}

			if(this.isOwner(player) && !this.worldObj.isRemote)
			{
				this.aiSit.setSitting(!this.isSitting());
				this.navigator.clearPathEntity();
			}
		}
		else if(stack != null && stack.getItem() == Items.GUNPOWDER)
		{
			if(!player.capabilities.isCreativeMode)
				--stack.stackSize;

			if(!this.worldObj.isRemote)
			{
				if(this.rand.nextInt(3) == 0)
				{
					this.setTamed(true);
					this.navigator.clearPathEntity();
					this.aiSit.setSitting(true);
					this.setHealth(20.0F);
					this.setOwnerId(player.getUniqueID());
					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte)7);
					
					//ACHIEVEMENT
					if(!player.hasAchievement(AchievementHandler.achievementTamePeaceCreeper))
						player.addStat(AchievementHandler.achievementTamePeaceCreeper);
				}
				else
				{
					this.playTameEffect(false);
					this.worldObj.setEntityState(this, (byte)6);
				}
			}
		}

		return super.processInteract(player, hand, stack);
	}

	public EnumDyeColor getCollarColor()
	{
		return EnumDyeColor.byDyeDamage(((Integer)this.dataManager.get(COLLAR_COLOR)).intValue() & 15);
	}

	public void setCollarColor(EnumDyeColor collarcolor)
	{
		this.dataManager.set(COLLAR_COLOR, Integer.valueOf(collarcolor.getDyeDamage()));
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) 
	{
		return null;
	}

	@Override
	public boolean canMateWith(EntityAnimal otherAnimal) 
	{
		return false;
	}

	// IMOBCHANGER
	@Override
	public void cursedWaterEffect() 
	{
		if(!this.isTamed())
		{
			EntityTrueCreeper creeper = new EntityTrueCreeper(worldObj);
			creeper.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			creeper.renderYawOffset = this.renderYawOffset;
			creeper.setHealth(creeper.getMaxHealth());

			this.setDead();
			worldObj.spawnEntityInWorld(creeper);
		}
	}

	@Override
	public void sacredWaterEffect() {}

}
