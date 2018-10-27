package com.mitologia.chin;

import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CClosure;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.mitologia.chin.abstraction.BukkitMCMapCanvas;
import com.mitologia.chin.abstraction.BukkitMCMapView;
import com.mitologia.chin.bukkit.MCMapCanvas;
import com.mitologia.chin.bukkit.MCMapView;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class Renderer extends MapRenderer {

	private CClosure closure;

	public Renderer() {
		super();
	}

	public Renderer(boolean contextual) {
		super(contextual);
	}

	public void setClosure(CClosure c) {
		closure = c;
	}

	@Override
	public void render(MapView map, MapCanvas canvas, Player player) {

		String mvkey = Mapper.findKey("view", new BukkitMCMapView(map), true);
		String mckey = Mapper.findKey("canvas", new BukkitMCMapCanvas(canvas), true);
		Target t = Target.UNKNOWN;
		CArray mvarr = ObjGenerator.getMapView((MCMapView) Mapper.mapObj.get(mvkey), t);
		mvarr.set("key", mvkey);
		CArray mcarr = ObjGenerator.getMapCanvas((MCMapCanvas) Mapper.mapObj.get(mckey), t);
		mcarr.set("key", mckey);

		closure.execute(mvarr, mcarr, new CString(player.getName(), t));

	}

}
