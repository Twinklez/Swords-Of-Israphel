package com.twinklez;

import java.io.File;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.src.BaseMod;
import net.minecraft.src.MLProp;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Swords of Israphel", name = "Swords of Israphel", version = "0.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SwordsofIsraphel extends BaseMod
{
	static EnumToolMaterial HEAL = EnumHelper.addToolMaterial("HEAL", 2, 250, 0.5F, 1, 9); 
	static EnumToolMaterial LIGHT = EnumHelper.addToolMaterial("LIGHT", 3, 750, 1.0F, 2, 9); 
	static EnumToolMaterial FIRE = EnumHelper.addToolMaterial("FIRE", 3, 500, 1.0F, 3, 9); 
	static EnumToolMaterial FREEZE = EnumHelper.addToolMaterial("FREEZE", 3, 750, 1.0F, 2, 9); 
	static EnumToolMaterial GHAST = EnumHelper.addToolMaterial("GHAST", 3, 500, 1.0F, 6, 9); 
	static EnumToolMaterial PRUQA = EnumHelper.addToolMaterial("PRUQA", 3, 1000, 1.0F, 100, 9);
	
	public SOIEntityRegistry entityRegistry;

	public static Item freezeSword = (new SOIFreeze(10000, FREEZE).setUnlocalizedName("freezeSword"));
	public static Item lightningSword = (new SOILightning(10001, LIGHT).setUnlocalizedName("lightningSword"));
	public static Item healingSword = (new SOIHealing(10002, HEAL).setUnlocalizedName("healingSword"));
	public static Item flameSword = (new SOIFlame(10003, FIRE).setUnlocalizedName("flameSword"));
	public static Item ghastSword = (new SOIGhast(10004, GHAST).setUnlocalizedName("ghastSword"));
	public static Item pruqaSword = (new SOIPruqa(10005, PRUQA).setUnlocalizedName("pruqaSword"));

	@MLProp public static int idFreezeSword = 10000;
	@MLProp public static int idLightningSword = 10001;
	@MLProp public static int idHealingSword = 10002;
	@MLProp public static int idFlameSword = 10003;
	@MLProp public static int idGhastSword = 10004;
	@MLProp public static int idPruqaSword = 10005;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "Swords_of_Israphel" + ".cfg"));
		config.load();
		Property property = config.get(Configuration.CATEGORY_GENERAL, "com", "com");
		property.comment = "Swords of Israphel [SOI] Current Version: v0.2";
		property.comment = "You can edit the ID codes for Swords of Israphel (SOI)'s Items or Blocks";
		idFreezeSword = config.getItem("freezeSword", 10000).getInt();
		idLightningSword = config.getItem("lightningSword", 10001).getInt();
		idHealingSword = config.getItem("healingSword", 10002).getInt();
		idFlameSword = config.getItem("flameSword", 10003).getInt();
		idGhastSword = config.getItem("ghastSword", 10004).getInt();
		idPruqaSword = config.getItem("pruqaSword", 10005).getInt();
		config.save();	
	}
	
	@SidedProxy(clientSide = "com.twinklez.ClientProxySOI", serverSide = "com.twinklez.CommonProxySOI")
	public static CommonProxySOI proxy;
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderInfo();
		entityRegistry = new SOIEntityRegistry(this);
		LanguageRegistry.addName(freezeSword, "\u00a7bFreezing Sword");
		LanguageRegistry.addName(lightningSword, "\u00a7eLightning Sword");
		LanguageRegistry.addName(healingSword, "\u00a7dHealing Sword");
		LanguageRegistry.addName(flameSword, "\u00a74Flame Sword");
		LanguageRegistry.addName(ghastSword, "\u00a7cGhast Sword");
		LanguageRegistry.addName(pruqaSword, "\u00a78\u00a7kiiiii\u00a7l\u00a73Pruqa Sword\u00a78\u00a7kiiiii");	
		GameRegistry.addRecipe(new ItemStack(freezeSword, 1), new Object[] { " M ", "MXM", " Y ", 'M', Item.snowball, 'X', Block.blockSnow, 'Y', Item.stick });
		GameRegistry.addRecipe(new ItemStack(lightningSword, 1), new Object[] { " M ", "ZXZ", " Y ", 'M', Item.blazePowder, 'Z', Item.coal, 'X', Item.magmaCream, 'Y', Item.stick });
		GameRegistry.addRecipe(new ItemStack(healingSword, 1), new Object[] { " X ", "ZMZ", " Y ", 'X', Item.appleGold, 'Z', Item.melon, 'M', Item.goldNugget, 'Y', Item.stick });
		GameRegistry.addRecipe(new ItemStack(flameSword, 1), new Object[] { " X ", " M ", " Y ", 'X', Item.coal, 'M', Item.flintAndSteel, 'Y', Item.blazeRod });
		GameRegistry.addRecipe(new ItemStack(ghastSword, 1), new Object[] { " X ", " M ", " Y ", 'X', Item.ghastTear, 'M', Item.gunpowder, 'Y', Item.blazeRod });
		GameRegistry.addRecipe(new ItemStack(pruqaSword, 1), new Object[] { " X ", "ZAZ", " Y ", 'X', Item.diamond, 'Z', Block.obsidian, 'A', Item.ingotIron, 'Y', Item.stick });
	}
	
	public void addRenderer(Map map)
	{

	}
	
	public void load()
	{
		
	}
	
	public String getVersion()
	{
		return "1.5";
	}

}

/** Old 1.4.7 Stuff [Please Ignore]
 * 
 * ITEM RENDERING
 	freezeSword.iconIndex = ModLoader.addOverride("/gui/items.png", "/soi/items/freezeSword.png");
	lightningSword.iconIndex = ModLoader.addOverride("/gui/items.png", "/soi/items/lightningSword.png");	
	healingSword.iconIndex = ModLoader.addOverride("/gui/items.png", "/soi/items/healingSword.png");
	flameSword.iconIndex = ModLoader.addOverride("/gui/items.png", "/soi/items/flameSword.png");
	ghastSword.iconIndex = ModLoader.addOverride("/gui/items.png", "/soi/items/ghastSword.png");
	pruqaSword.iconIndex = ModLoader.addOverride("/gui/items.png", "/soi/items/pruqaSword.png");
	
	TEXTURE RENDERING HAS BEEN CHANGED IN MINECRAFT 1.5
	I AM GOING TO WAIT FOR FORGE TO TEACH ME LOL
	FOR NOW IM USE MY RECOMMENDATIONS :D AND IT WORKS TOO 
*/








