package com.mitologia.chin.bukkit;

import com.laytonsmith.abstraction.AbstractionObject;

public interface MCMapCursorCollection extends AbstractionObject {

	MCMapCursor addCursor(int x, int y, byte direction);

	/**
	 * @deprecated
	 */
	@Deprecated
	MCMapCursor addCursor(int x, int y, byte direction, byte type);

	/**
	 * @deprecated
	 */
	@Deprecated
	MCMapCursor addCursor(int x, int y, byte direction, byte type, boolean visible);

	/**
	 * @deprecated
	 */
	@Deprecated
	MCMapCursor addCursor(int x, int y, byte direction, byte type, boolean visible, String caption);

	MCMapCursor addCursor(MCMapCursor cursor);

	MCMapCursor getCursor(int index);

	boolean removeCursor(MCMapCursor cursor);

	int size();

}
