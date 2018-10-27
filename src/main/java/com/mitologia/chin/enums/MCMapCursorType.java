package com.mitologia.chin.enums;

public enum MCMapCursorType {

	WHITE_POINTER,
	GREEN_POINTER,
	RED_POINTER,
	BLUE_POINTER,
	WHITE_CROSS,
	RED_MARKER,
	WHITE_CIRCLE,
	SMALL_WHITE_CIRCLE,
	MANSION,
	TEMPLE,
	BANNER_WHITE,
	BANNER_ORANGE,
	BANNER_MAGENTA,
	BANNER_LIGHT_BLUE,
	BANNER_YELLOW,
	BANNER_LIME,
	BANNER_PINK,
	BANNER_GRAY,
	BANNER_LIGHT_GRAY,
	BANNER_CYAN,
	BANNER_PURPLE,
	BANNER_BLUE,
	BANNER_BROWN,
	BANNER_GREEN,
	BANNER_RED,
	BANNER_BLACK,
	RED_X;

	private byte value;

	/**
	 * @return the value
	 * @deprecated Magic value
	 */
	@Deprecated
	public byte getValue() {
		return value;
	}

	/**
	 * @param value the value
	 * @return the matching type
	 * @deprecated Magic value
	 */
	@Deprecated
	public static MCMapCursorType byValue(byte value) {
		for (MCMapCursorType t : values()) {
			if (t.value == value) return t;
		}
		return null;
	}
}
