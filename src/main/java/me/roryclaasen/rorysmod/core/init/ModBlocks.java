/*
 * Copyright 2016-2017 Rory Claasen
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.roryclaasen.rorysmod.core.init;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ic2.api.item.IC2Items;
import me.roryclaasen.rorysmod.block.BlockBaseMeta;
import me.roryclaasen.rorysmod.block.BlockBlueprint;
import me.roryclaasen.rorysmod.block.BlockIngot;
import me.roryclaasen.rorysmod.block.BlockMachineRenamer;
import me.roryclaasen.rorysmod.block.BlockPoweredChest;
import me.roryclaasen.rorysmod.block.BlockRifleTable;
import me.roryclaasen.rorysmod.block.BlockTestingWall;
import me.roryclaasen.rorysmod.block.base.MultiBlockHandler;
import me.roryclaasen.rorysmod.core.RorysMod;
import me.roryclaasen.rorysmod.core.register.Register;
import me.roryclaasen.rorysmod.item.block.ItemMachineRenamer;
import me.roryclaasen.rorysmod.item.block.ItemPoweredChest;
import me.roryclaasen.rorysmod.util.RMLog;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModBlocks implements ModInterface {

	public Block testingWall;
	public Block upgradeTable;
	public Block steelBlock;
	public Block bluePrint;

	public Block poweredChest;
	public Block renamer;
	public Block solderBlock;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		testingWall = new BlockTestingWall(Material.iron, "blockTest");
		upgradeTable = new BlockRifleTable(Material.anvil, "tableUpgrade");
		steelBlock = new BlockIngot(Material.iron, "blockSteel");
		bluePrint = new BlockBlueprint(Material.iron, "blockBluePrint");

		poweredChest = new BlockPoweredChest(Material.wood, "blockChestPowered");

		renamer = new BlockMachineRenamer(Material.iron, "machineRenamer");
		solderBlock = new BlockIngot(Material.iron, "blockSolder");
	}

	public void register(FMLPreInitializationEvent event) {
		RMLog.info("Registering Blocks");
		Register.registerBlock(testingWall, MultiBlockHandler.class);
		Register.registerBlock(upgradeTable);
		Register.registerBlock(steelBlock);

		Register.registerBlock(bluePrint, MultiBlockHandler.class);
		Register.registerBlock(poweredChest, ItemPoweredChest.class);

		Register.registerBlock(renamer, ItemMachineRenamer.class);
		Register.registerBlock(solderBlock);

		Register.registerDictionary("blockSteel", steelBlock);
		for (int i = 0; i < ((BlockBaseMeta) testingWall).getMetaSize(); i++) {
			Register.registerDictionary("testingWall", new ItemStack(testingWall, 1, i));
		}
		for (int i = 0; i < ((BlockBaseMeta) bluePrint).getMetaSize(); i++) {
			Register.registerDictionary("bluePrint", new ItemStack(bluePrint, 1, i));
		}
		Register.registerDictionary("blockSolder", solderBlock);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		Register.addShapedRecipie(new ItemStack(steelBlock), new Object[] { "sss", "sss", "sss", 's', "ingotSteel" });
		for (int id = 0; id < ((BlockBaseMeta) testingWall).getMetaSize(); id++) {
			ItemStack dye = new ItemStack(Items.dye, 1, 15 - id);
			ItemStack currentBlock = new ItemStack(testingWall, 1, id);
			Register.addShapedRecipie(currentBlock, new Object[] { " i ", "idi", " i ", 'i', "ingotSteel", 'd', dye });
			Register.addShapelessRecipie(currentBlock, new Object[] { "testingWall", dye, dye, dye });
		}
		Register.addShapedRecipie(new ItemStack(upgradeTable), new Object[] { "sss", "ici", "iti", 's', "plateSteel", 'i', "ingotIron", 'c', "cpu", 't', Blocks.crafting_table });
		Register.addShapedRecipie(new ItemStack(bluePrint, 1, 0), new Object[] { " l ", "lbl", " l ", 'b', "testingWall", 'l', new ItemStack(Items.dye, 1, 4) });
		Register.addShapedRecipie(new ItemStack(bluePrint, 1, 1), new Object[] { " d ", " b ", "   ", 'b', "bluePrint", 'd', new ItemStack(Items.dye, 1, 15) });
		Register.addShapedRecipie(new ItemStack(bluePrint, 1, 2), new Object[] { "  d", " b ", "   ", 'b', "bluePrint", 'd', new ItemStack(Items.dye, 1, 15) });
		Register.addShapedRecipie(new ItemStack(bluePrint, 1, 3), new Object[] { "   ", " bd", "   ", 'b', "bluePrint", 'd', new ItemStack(Items.dye, 1, 15) });
		Register.addShapedRecipie(new ItemStack(bluePrint, 1, 4), new Object[] { "   ", " b ", "  d", 'b', "bluePrint", 'd', new ItemStack(Items.dye, 1, 15) });
		Register.addShapedRecipie(new ItemStack(bluePrint, 1, 0), new Object[] { "d  ", " b ", "   ", 'b', "bluePrint", 'd', new ItemStack(Items.dye, 1, 15) });

		Register.addShapelessRecipie(new ItemStack(poweredChest), new Object[] { Blocks.chest, Items.redstone, Blocks.tripwire_hook });

		Register.addShapedRecipie(new ItemStack(renamer), new Object[] { " n ", "gmg", "csc", 'n', Items.name_tag, 'g', "blockGlass", 'm', IC2Items.getItem("machine"), 'c', IC2Items.getItem("coil"), 's', "plateSteel" });
		Register.addShapedRecipie(new ItemStack(solderBlock), new Object[] { "sss", "sss", "sss", 's', "ingotSolder" });
	}

	@Override
	public void postinit(FMLPostInitializationEvent event) {
		Register.addPulverizerRecipe(2400, new ItemStack(steelBlock), new ItemStack(RorysMod.items.steelDust, 9));
		Register.addPulverizerRecipe(2400, new ItemStack(solderBlock), new ItemStack(RorysMod.items.solderDust, 9));
		if (Loader.isModLoaded("ThermalExpansion")) {
			Register.addShapedRecipie(new ItemStack(renamer), new Object[] { " n ", "gmg", "csc", 'n', Items.name_tag, 'g', "blockGlass", 'm', "thermalexpansion:machineFrame", 'c', "gearCopper", 's', "plateSteel" });
		}
	}
}
