package com.mitologia.chin.enums;

import org.bukkit.map.MapView;

public enum MCMapViewScale {

	CLOSEST,
	CLOSE,
	NORMAL,
	FAR,
	FARTHEST;

	private byte value;

	/**
	 * @deprecated
	 */
	@Deprecated
	public static MCMapViewScale valueOf(byte value) {
		switch (value) {
			case 0:
				return CLOSEST;
			case 1:
				return CLOSE;
			case 2:
				return NORMAL;
			case 3:
				return FAR;
			case 4:
				return FARTHEST;
			default:
				return null;
		}
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public byte getValue() {
		return this.value;
	}

}
