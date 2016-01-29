package net.roryclaasen.rorysmod.data;

import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.roryclaasen.rorysmod.item.ItemBase;
import net.roryclaasen.rorysmod.item.ItemDust;
import net.roryclaasen.rorysmod.item.ItemIngot;
import net.roryclaasen.rorysmod.item.ItemPlate;
import net.roryclaasen.rorysmod.item.ItemRifle;
import net.roryclaasen.rorysmod.item.ItemRifleUpgrade;
import net.roryclaasen.rorysmod.util.ItemRegistry;
import net.roryclaasen.rorysmod.util.RMLog;
import codechicken.nei.api.API;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems implements TypeGroup {

	public ItemRegistry registry = new ItemRegistry();

	public static Item steelIngot, steelDust, steelPlate;
	public static Item carbonIngot;
	public static Item rifle, laserBolt;
	public static Item rifleUpgrade, upgradePlate;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		steelIngot = new ItemIngot("ingotSteel");
		steelDust = new ItemDust("dustSteel");
		steelPlate = new ItemPlate("plateSteel");
		carbonIngot = new ItemIngot("ingotCarbon");
		rifle = new ItemRifle("rifle");
		laserBolt = new ItemBase("laser").setCreativeTab(null);
		rifleUpgrade = new ItemRifleUpgrade("rifleUpgrade");
		upgradePlate = new ItemPlate("plateUpgrade");
	}

	@Override
	public void register(FMLInitializationEvent event) {
		RMLog.info("Registering Items");

		GameRegistry.registerItem(steelIngot, steelIngot.getUnlocalizedName());
		GameRegistry.registerItem(steelDust, steelDust.getUnlocalizedName());
		GameRegistry.registerItem(steelPlate, steelPlate.getUnlocalizedName());
		GameRegistry.registerItem(carbonIngot, carbonIngot.getUnlocalizedName());
		GameRegistry.registerItem(rifle, rifle.getUnlocalizedName());
		GameRegistry.registerItem(laserBolt, laserBolt.getUnlocalizedName());
		GameRegistry.registerItem(rifleUpgrade, rifleUpgrade.getUnlocalizedName());
		GameRegistry.registerItem(upgradePlate, upgradePlate.getUnlocalizedName());

		OreDictionary.registerOre("ingotSteel", steelIngot);
		OreDictionary.registerOre("dustSteel", steelDust);
		OreDictionary.registerOre("plateSteel", steelPlate);

		if (Loader.isModLoaded("NotEnoughItems")) {
			API.hideItem(new ItemStack(laserBolt));
		}
	}

	@Override
	public void createRecipes() {
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(carbonIngot), IC2Items.getItem("carbonFiber"), IC2Items.getItem("carbonFiber"), IC2Items.getItem("carbonFiber"), "ingotIron"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(steelDust, 2), IC2Items.getItem("carbonFiber"), IC2Items.getItem("carbonFiber"), IC2Items.getItem("carbonFiber"), "dustIron"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(steelPlate), "ingotSteel", IC2Items.getItem("ForgeHammer")));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(upgradePlate, 2), new Object[]{"rir", "nsn", "rir", 'r', Items.redstone, 'i', "ingotIron", 'n', Items.gold_nugget, 's', "plateSteel"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rifleUpgrade), new Object[]{"iii", "iui", "iii", 'u', new ItemStack(upgradePlate), 'i', Blocks.iron_bars}));

		GameRegistry.addSmelting(new ItemStack(steelDust), new ItemStack(steelIngot), 0.1f);
		GameRegistry.addSmelting(new ItemStack(carbonIngot), new ItemStack(steelIngot), 0.1f);

		Recipes.metalformerRolling.addRecipe(new RecipeInputItemStack(new ItemStack(steelIngot)), null, new ItemStack(steelPlate));
		Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(steelPlate)), null, new ItemStack(steelDust));
	}
}
