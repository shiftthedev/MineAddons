package com.iamshift.mineaddons.blocks.lavasponge;

import com.iamshift.mineaddons.blocks.IMetaBlockName;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLavaSponge extends ItemBlock
{
	private String thrower;
	
	public ItemLavaSponge(Block block) 
	{
		super(block);
		setHasSubtypes(true);
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) 
	{
		if(stack.getItemDamage() == 1)
			return true;
		
		return false;
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack)
	{
		EntityWetLavaSponge item = new EntityWetLavaSponge(world, location.posX, location.posY, location.posZ, itemstack);
		
		item.motionX = location.motionX;
		item.motionY = location.motionY;
		item.motionZ = location.motionZ;
		
		item.setPickupDelay(40);
		item.setThrower(thrower);
		
		thrower = null;
		
		return item;
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
	{
		this.thrower = player.getName();
		return true;
	}

	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "_" + ((IMetaBlockName)this.block).getSpecialName(stack);
	}
}
