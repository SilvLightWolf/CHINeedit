package com.mitologia.chin.abstraction;

import com.mitologia.chin.bukkit.MCMapCursor;
import com.mitologia.chin.enums.MCMapCursorType;
import org.bukkit.map.MapCursor;

public class BukkitMCMapCursor implements MCMapCursor {

	private MapCursor mc;

	public BukkitMCMapCursor(MapCursor mc) {
		this.mc = mc;
	}

	public BukkitMCMapCursor(byte x, byte y, byte direction, MCMapCursorType type, boolean visible) {
		this.mc = new MapCursor(x, y, direction, MapCursor.Type.valueOf(type.name()), visible);
	}

	public BukkitMCMapCursor(byte x, byte y, byte direction, MCMapCursorType type, boolean visible, String caption) {
		this.mc = new MapCursor(x, y, direction, MapCursor.Type.valueOf(type.name()), visible, caption);
	}

	@Override
	public String getCaption() {
		return mc.getCaption();
	}

	@Override
	public byte getDirection() {
		return mc.getDirection();
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public byte getRawType() {
		return mc.getRawType();
	}

	@Override
	public MCMapCursorType getType() {
		return MCMapCursorType.valueOf(mc.getType().name());
	}

	@Override
	public byte getX() {
		return mc.getX();
	}

	@Override
	public byte getY() {
		return mc.getY();
	}

	@Override
	public boolean isVisible() {
		return mc.isVisible();
	}

	@Override
	public void setCaption(String caption) {
		mc.setCaption(caption);
	}

	@Override
	public void setDirection(byte direction) {
		mc.setDirection(direction);
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void setRawType(byte type) {
		mc.setRawType(type);
	}

	@Override
	public void setType(MCMapCursorType type) {
		mc.setType(MapCursor.Type.valueOf(type.name()));
	}

	@Override
	public void setVisible(boolean visible) {
		mc.setVisible(visible);
	}

	@Override
	public void setX(byte x) {
		mc.setX(x);
	}

	@Override
	public void setY(byte y) {
		mc.setY(y);
	}

	@Override
	public Object getHandle() {
		return mc;
	}
}
