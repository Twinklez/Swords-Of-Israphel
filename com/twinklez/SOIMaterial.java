package com.twinklez;

public enum SOIMaterial
{
	HEAL(2, 250, 0.5F, 1, 9),
    LIGHT(3, 500, 1.0F, 2, 9),
    FIRE(3, 500, 1.0F, 3, 9),
    FREEZE(3, 750, 1.0F, 2, 9),
    GHAST(3, 500, 1.0F, 6, 9),
    PRUQA(3, 1000, 1.0F, 100, 9);

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiencyOnProperMaterial;
    private final int damageVsEntity;
    private final int enchantability;

    private SOIMaterial(int j, int k, float f, int l, int i1)
    {
        harvestLevel = j;
        maxUses = k;
        efficiencyOnProperMaterial = f;
        damageVsEntity = l;
        enchantability = i1;
    }

    public int getMaxUses()
    {
        return maxUses;
    }

    public float getEfficiencyOnProperMaterial()
    {
        return efficiencyOnProperMaterial;
    }

    public int getDamageVsEntity()
    {
        return damageVsEntity;
    }

    public int getHarvestLevel()
    {
        return harvestLevel;
    }

    public int getEnchantability()
    {
        return enchantability;
    }
}
