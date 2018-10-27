package com.mitologia.chin.abstraction;

import com.mitologia.chin.bukkit.MCMapCursor;
import com.mitologia.chin.bukkit.MCMapCursorCollection;
import org.bukkit.map.MapCursor;
import org.bukkit.map.MapCursorCollection;

public class BukkitMCMapCursorCollection implements MCMapCursorCollection {

	private MapCursorCollection mcc;

	public BukkitMCMapCursorCollection(MapCursorCollection mcc) {
		this.mcc = mcc;
	}

	public BukkitMCMapCursorCollection() {
		this.mcc = new MapCursorCollection();
	}

	@Override
	public MCMapCursor addCursor(int x, int y, byte direction) {
		return new BukkitMCMapCursor(mcc.addCursor(x, y, direction));
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public MCMapCursor addCursor(int x, int y, byte direction, byte type) {
		return new BukkitMCMapCursor(mcc.addCursor(x, y, direction, type));
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public MCMapCursor addCursor(int x, int y, byte direction, byte type, boolean visible) {
		return new BukkitMCMapCursor(mcc.addCursor(x, y, direction, type, visible));
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public MCMapCursor addCursor(int x, int y, byte direction, byte type, boolean visible, String caption) {
		return new BukkitMCMapCursor(mcc.addCursor(x, y, direction, type, visible, caption));
	}

	@Override
	public MCMapCursor addCursor(MCMapCursor cursor) {
		return new BukkitMCMapCursor(mcc.addCursor((MapCursor) cursor.getHandle()));
	}

	@Override
	public MCMapCursor getCursor(int index) {
		return new BukkitMCMapCursor(mcc.getCursor(index));
	}

	@Override
	public boolean removeCursor(MCMapCursor cursor) {
		return mcc.removeCursor((MapCursor) cursor.getHandle());
	}

	@Override
	public int size() {
		return mcc.size();
	}

	@Override
	public Object getHandle() {
		return mcc;
	}
}
