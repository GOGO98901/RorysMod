/*
 * Copyright 2017 Rory Claasen
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
package me.roryclaasen.rorysmod.core.intergrations;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.roryclaasen.rorysmod.core.RorysGlobal;
import me.roryclaasen.rorysmod.core.RorysMod;
import me.roryclaasen.rorysmod.core.intergrations.nei.RoryShapedRecipeHandler;
import me.roryclaasen.rorysmod.core.intergrations.nei.RoryShapelessRecipeHandler;
import me.roryclaasen.rorysmod.util.RMLog;
import net.minecraft.item.ItemStack;

@SideOnly(Side.CLIENT)
public class NEIRorysModConfig implements IConfigureNEI {
	@Override
	public void loadConfig() {
		RMLog.info("Loading NEI for " + RorysGlobal.NAME);
		API.hideItem(new ItemStack(RorysMod.items.laserBolt));

		API.registerRecipeHandler(new RoryShapelessRecipeHandler());
		API.registerUsageHandler(new RoryShapelessRecipeHandler());

		API.registerRecipeHandler(new RoryShapedRecipeHandler());
		API.registerUsageHandler(new RoryShapedRecipeHandler());
	}

	@Override
	public String getName() {
		return RorysGlobal.NAME + " NEI Plugin";
	}

	@Override
	public String getVersion() {
		return RorysGlobal.VERSION;
	}
}
