package com.mitologia.chin.abstraction;

import com.mitologia.chin.bukkit.*;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapFont;

import java.awt.*;

public class BukkitMCMapCanvas implements MCMapCanvas {

	private MapCanvas mc;

	public BukkitMCMapCanvas(MapCanvas mc) {
		this.mc = mc;
	}

	@Override
	public void drawImage(int x, int y, MCAwtImage image) {
		mc.drawImage(x, y, (Image) image.getHandle());
	}

	@Override
	public void drawText(int x, int y, MCMapFont font, String text) {
		mc.drawText(x, y, (MapFont) font.getHandle(), text);
	}

	@Override
	public byte getBasePixel(int x, int y) {
		return mc.getBasePixel(x, y);
	}

	@Override
	public BukkitMCMapCursorCollection getCursors() {
		return new BukkitMCMapCursorCollection(mc.getCursors());
	}

	@Override
	public MCMapView getMapView() {
		return new BukkitMCMapView(mc.getMapView());
	}

	@Override
	public byte getPixel(int x, int y) {
		return mc.getPixel(x, y);
	}

	@Override
	public void setCursors(MCMapCursorCollection cursors) {
		mc.setCursors((MapCursorCollection) cursors.getHandle());
	}

	@Override
	public void setPixel(int x, int y, byte color) {
		mc.setPixel(x, y, color);
	}

	@Override
	public Object getHandle() {
		return mc;
	}
}
