package com.mitologia.chin.bukkit;

import com.laytonsmith.abstraction.AbstractionObject;
import com.mitologia.chin.enums.MCMapCursorType;

public interface MCMapCursor extends AbstractionObject {

	String getCaption();

	byte getDirection();

	/**
	 * @deprecated
	 */
	@Deprecated
	byte getRawType();

	MCMapCursorType getType();

	byte getX();

	byte getY();

	boolean isVisible();

	void setCaption(String caption);

	void setDirection(byte direction);

	/**
	 * @deprecated
	 */
	@Deprecated
	void setRawType(byte type);

	void setType(MCMapCursorType type);

	void setVisible(boolean visible);

	void setX(byte x);

	void setY(byte y);

}
