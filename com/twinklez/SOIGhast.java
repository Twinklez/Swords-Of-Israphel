package com.twinklez;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class SOIGhast extends ItemSword
{
	public EntityThrowable throwable;
    private int weaponDamage;
    private final EnumToolMaterial toolMaterial;

	public SOIGhast(int id, EnumToolMaterial material)
	{
        super(id, material);
        toolMaterial = material;
        maxStackSize = 1;
        setMaxDamage(material.getMaxUses());
        weaponDamage = 4 + material.getDamageVsEntity();
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        return block.blockID != Block.stone.blockID ? 1.5F : 5F;
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	par3List.add("\u00a76Rank: GODLY [LVL-3]");
    	par3List.add("\u00a72500 Uses");
    	par3List.add("\u00a77On Right Click: Sends a gravity affected invisible Ghast fireball.");
    	par3List.add("\u00a74Beware! Do not fire at feet! Use with caution!");
    }

    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        itemstack.damageItem(1, entityliving);
        return true;
    }
    
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        world.playSoundAtEntity(entityplayer, "mob.ghast.fireball4", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 1.0F));
        
        double var11 = 1;
        double var13 = 0;
        double var15 = 1;
        
        itemstack.damageItem(1, entityplayer);
        
        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new SOIGhastExplosion(world, entityplayer));
        }
        
        return itemstack;
    }


    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity entity)
    {
        return weaponDamage;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 0x11940;
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block block)
    {
    	return false;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return toolMaterial.getEnchantability();
    }
    
    public String getTextureFile()
    {
            return "/soi/gui/items.png";
    }
}