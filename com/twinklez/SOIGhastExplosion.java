package com.twinklez;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SOIGhastExplosion extends EntityThrowable
{
	
    public SOIGhastExplosion(World par1World)
    {
        super(par1World);
    }

    public SOIGhastExplosion(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public SOIGhastExplosion(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    /**
     * Called when the throwable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
    	worldObj.spawnParticle("angryVillager", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
    	
        for (int i = 0; i < 8; i++)
        {
        	worldObj.createExplosion(this, posX, posY, posZ, 1.5F, true);
        }

        if (!worldObj.isRemote)
        {
            setDead();
        }
    }
}
