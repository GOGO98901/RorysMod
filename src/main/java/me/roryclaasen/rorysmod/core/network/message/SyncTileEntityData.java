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
package me.roryclaasen.rorysmod.core.network.message;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import me.roryclaasen.rorysmod.core.network.AbstractMessage.AbstractServerMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class SyncTileEntityData extends AbstractServerMessage<SyncTileEntityData> {

	private NBTTagCompound nbtCompound;

	public SyncTileEntityData() {}

	public SyncTileEntityData(NBTTagCompound nbtCompound) {
		this.nbtCompound = nbtCompound;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		nbtCompound = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeNBTTagCompoundToBuffer(nbtCompound);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		int x = nbtCompound.getInteger("x");
		int y = nbtCompound.getInteger("y");
		int z = nbtCompound.getInteger("z");
		TileEntity entity = player.worldObj.getTileEntity(x, y,z);
		if (entity != null) {
			entity.readFromNBT(nbtCompound);
		} 
	}
}
