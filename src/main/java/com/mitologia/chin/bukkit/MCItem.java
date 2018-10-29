package com.mitologia.chin.bukkit;

import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.MCItemStack;

public interface MCItem extends MCEntity {

	MCItemStack getItemStack();

	int getPickupDelay();

	void setItemStack(MCItemStack item);

	void setPickupDelay(int delay);

}
