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
package me.roryclaasen.rorysmod.core;

import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class RorysGlobal {

	private RorysGlobal() {}

	public static final String MODID = "rorysmod";
	public static final String NAME = "Rory's Mod";
	public static final String VERSION = "@version@";

	public static final ArmorMaterial SOLDER_ARMOR = EnumHelper.addArmorMaterial("solder", 12, new int[] { 1, 4, 2, 1 }, 10);
}
