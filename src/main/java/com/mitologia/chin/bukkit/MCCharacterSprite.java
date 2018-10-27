package com.mitologia.chin.bukkit;

import com.laytonsmith.abstraction.AbstractionObject;

public interface MCCharacterSprite extends AbstractionObject {

	boolean get(int row, int col);

	int getHeight();

	int getWidth();

}
