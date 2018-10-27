package com.mitologia.chin.bukkit;

import com.laytonsmith.abstraction.AbstractionObject;

public interface MCMapCanvas extends AbstractionObject {

	void drawImage(int x, int y, MCAwtImage image);

	void drawText(int x, int y, MCMapFont font, String text);

	byte getBasePixel(int x, int y);

	MCMapCursorCollection getCursors();

	MCMapView getMapView();

	byte getPixel(int x, int y);

	void setCursors(MCMapCursorCollection cursors);

	void setPixel(int x, int y, byte color);

}
