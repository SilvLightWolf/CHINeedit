package com.mitologia.chin.bukkit;

import com.laytonsmith.abstraction.AbstractionObject;

public interface MCMapFont extends AbstractionObject {

	MCCharacterSprite getChar(char ch);

	int getHeight();

	int getWidth(String text);

	boolean isValid(String text);

	void setChar(char ch, MCCharacterSprite sprite);

}
