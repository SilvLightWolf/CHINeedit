package com.mitologia.chin.abstraction;

import com.mitologia.chin.bukkit.MCCharacterSprite;
import com.mitologia.chin.bukkit.MCMapFont;
import org.bukkit.map.MapFont;

public class BukkitMCMapFont implements MCMapFont {

	private MapFont mf;

	public BukkitMCMapFont(MapFont mf) {
		this.mf = mf;
	}

	@Override
	public MCCharacterSprite getChar(char ch) {
		return new BukkitMCCharacterSprite(mf.getChar(ch));
	}

	@Override
	public int getHeight() {
		return mf.getHeight();
	}

	@Override
	public int getWidth(String text) {
		return mf.getWidth(text);
	}

	@Override
	public boolean isValid(String text) {
		return mf.isValid(text);
	}

	@Override
	public void setChar(char ch, MCCharacterSprite sprite) {
		mf.setChar(ch, (MapFont.CharacterSprite) sprite.getHandle());
	}

	@Override
	public Object getHandle() {
		return mf;
	}
}
