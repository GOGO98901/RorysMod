package net.roryclaasen.rorysmod.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBaseEnergyContainer extends ItemBase implements IEnergyContainerItem {

	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	protected int tier = 1;

	public ItemBaseEnergyContainer(String unlocalizedName, int capacity, int maxReceive, int maxExtract) {
		super(unlocalizedName);
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}

	public ItemBaseEnergyContainer(String unlocalizedName, int capacity, int maxTransfer) {
		this(unlocalizedName, capacity, maxTransfer, maxTransfer);
	}

	public ItemBaseEnergyContainer(String unlocalizedName, int capacity) {
		this(unlocalizedName, capacity, capacity, capacity);
	}

	public ItemBaseEnergyContainer setCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public ItemBaseEnergyContainer setTier(int tier) {
		this.tier = tier;
		return this;
	}

	public int getTier() {
		return this.tier;
	}

	public void setMaxTransfer(int maxTransfer) {
		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive) {
		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract) {
		this.maxExtract = maxExtract;
	}

	public boolean use(ItemStack container, int maxExtract, boolean simulate) {
		return extractEnergy(container, maxExtract, simulate) != 0;
	}

	public boolean use(ItemStack container, boolean sitmulate) {
		return use(container, this.maxExtract, sitmulate);
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		if (container.stackTagCompound == null) container.stackTagCompound = new NBTTagCompound();
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyRecived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
		if (!simulate) {
			energy += energyRecived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyRecived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) return 0;
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
		if (!simulate) {
			energy -= energyExtracted;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) return 0;
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return capacity;
	}

	public void updateItemDamage(ItemStack itemtack) {
		if (itemtack.stackTagCompound.hasKey("Energy")) {
			int energy = itemtack.stackTagCompound.getInteger("Energy");
			int percentage = (int) (((double) energy / (double) this.capacity) * (double) 100);
			itemtack.setItemDamage(getMaxDamage() - percentage);
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		if (stack.stackTagCompound.hasKey("Energy")) {
			int energy = stack.stackTagCompound.getInteger("Energy");
			tooltip.add(energy + "/" + this.capacity + "RF");
		}
	}
}
