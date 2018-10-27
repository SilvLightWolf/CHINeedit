package com.mitologia.chin.enums.bukkit;

import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.abstraction.enums.EnumConvertor;
import com.laytonsmith.annotations.abstractionenum;
import com.mitologia.chin.enums.MCMapViewScale;
import org.bukkit.map.MapView;

@abstractionenum(
		implementation = Implementation.Type.BUKKIT,
		forAbstractEnum = MCMapViewScale.class,
		forConcreteEnum = MapView.Scale.class
)
public class BukkitMCMapViewScale extends EnumConvertor<MCMapViewScale, MapView.Scale> {

	private static BukkitMCMapViewScale instance;

	public static BukkitMCMapViewScale getConvertor() {
		if (instance == null) {
			instance = new BukkitMCMapViewScale();
		}
		return instance;
	}
}
