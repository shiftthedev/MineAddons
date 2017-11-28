package com.iamshift.mineaddons.entities;

import com.iamshift.mineaddons.utils.LootManager;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class NoAiShulker extends EntityShulker
{

	public NoAiShulker(World worldIn) 
	{
		super(worldIn);
	}

	@Override
	protected void initEntityAI() 
	{
		this.tasks.addTask(1, new NoAiShulker.AIPeek());
        this.tasks.addTask(2, new EntityAILookIdle(this));
	}
	
	@Override
	protected void applyEntityAttributes() 
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1D);
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return null;
	}
	
	@Override
	protected SoundEvent getDeathSound() 
	{
		return null;
	}
	
	@Override
	protected SoundEvent getHurtSound() 
	{
		return null;
	}
	
	@Override
	protected float getSoundVolume() 
	{
		return 0F;
	}
	
	@Override
	protected ResourceLocation getLootTable() 
	{
		return LootManager.NOAISHULKER;
	}
	
	@Override
	protected boolean tryTeleportToNewPosition() 
	{
		return false;
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) 
	{
		return super.isEntityInvulnerable(source) || !((source.getEntity() instanceof EntityPlayer) && !(source.getEntity() instanceof FakePlayer));
	}
	
	class AIPeek extends EntityAIBase
    {
        private int peekTime;

        private AIPeek()
        {
        }

        public boolean shouldExecute()
        {
            return NoAiShulker.this.rand.nextInt(40) == 0;
        }
        
        public boolean continueExecuting()
        {
            return this.peekTime > 0;
        }

        public void startExecuting()
        {
            this.peekTime = 20 * (1 + NoAiShulker.this.rand.nextInt(3));
            NoAiShulker.this.updateArmorModifier(30);
        }

        public void resetTask()
        {
        	NoAiShulker.this.updateArmorModifier(0);
        }

        public void updateTask()
        {
            --this.peekTime;
        }
    }
}
