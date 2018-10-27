package com.mitologia.chin.enums.bukkit;

import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.abstraction.enums.EnumConvertor;
import com.laytonsmith.annotations.abstractionenum;
import com.mitologia.chin.enums.MCMapCursorType;
import org.bukkit.map.MapCursor;

@abstractionenum(
		implementation = Implementation.Type.BUKKIT,
		forAbstractEnum = MCMapCursorType.class,
		forConcreteEnum = MapCursor.Type.class
)
public class BukkitMCMapCursorType extends EnumConvertor<MCMapCursorType, MapCursor.Type> {

	private static BukkitMCMapCursorType instance;

	public static BukkitMCMapCursorType getConvertor() {
		if (instance == null) {
			instance = new BukkitMCMapCursorType();
		}
		return instance;
	}
}
