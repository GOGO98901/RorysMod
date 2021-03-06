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
package me.roryclaasen.rorysmodcore;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class RorysModCore extends DummyModContainer {

	public RorysModCore() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.autogenerated = false;
		meta.modId = "rorysmodcore";
		meta.name = "Rory's Mod Core";
		meta.version = "2.0";
		meta.credits = "";
		meta.authorList = Arrays.asList("Rory Claasen");
		meta.description = "The Backbone to Rory's Mod";
		meta.url = "http://rorysmod.rtfd.io";
		meta.updateUrl = "";
		meta.screenshots = new String[0];
		meta.logoFile = "assets/rorysmod/textures/logo.png";
		meta.parent = "rorysmod";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Subscribe
	public void modConstruction(FMLConstructionEvent evt) {}

	@Subscribe
	public void preInit(FMLPreInitializationEvent evt) {}

	@Subscribe
	public void init(FMLInitializationEvent evt) {}

	@Subscribe
	public void postInit(FMLPostInitializationEvent evt) {}

}
