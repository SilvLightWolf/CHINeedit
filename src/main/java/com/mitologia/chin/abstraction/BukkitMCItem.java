package com.mitologia.chin.abstraction;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCEntity;
import com.mitologia.chin.bukkit.MCItem;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class BukkitMCItem extends BukkitMCEntity implements MCItem {

	Item item;

	public BukkitMCItem(Item i) {
		super(i);
		this.item = i;
	}

	@Override
	public MCItemStack getItemStack() {
		return new BukkitMCItemStack(item.getItemStack());
	}

	@Override
	public int getPickupDelay() {
		return item.getPickupDelay();
	}

	@Override
	public void setItemStack(MCItemStack mcItemStack) {
		item.setItemStack((ItemStack) mcItemStack.getHandle());
	}

	@Override
	public void setPickupDelay(int i) {
		item.setPickupDelay(i);
	}

}
