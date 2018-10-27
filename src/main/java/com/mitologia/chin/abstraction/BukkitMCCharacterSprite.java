package com.mitologia.chin.abstraction;

import com.mitologia.chin.bukkit.MCCharacterSprite;
import org.bukkit.map.MapFont;

public class BukkitMCCharacterSprite implements MCCharacterSprite {

	private MapFont.CharacterSprite cs;

	public BukkitMCCharacterSprite(MapFont.CharacterSprite cs) {
		this.cs = cs;
	}

	public BukkitMCCharacterSprite(int width, int height, boolean[] data) {
		this.cs = new MapFont.CharacterSprite(width, height, data);
	}

	@Override
	public boolean get(int row, int col) {
		return cs.get(row, col);
	}

	@Override
	public int getHeight() {
		return cs.getHeight();
	}

	@Override
	public int getWidth() {
		return cs.getWidth();
	}

	@Override
	public Object getHandle() {
		return cs;
	}
}
