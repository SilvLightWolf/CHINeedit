package com.mitologia.chin.abstraction;

import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.abstraction.bukkit.BukkitMCWorld;
import com.mitologia.chin.bukkit.MCMapRenderer;
import com.mitologia.chin.bukkit.MCMapView;
import com.mitologia.chin.enums.MCMapViewScale;
import org.bukkit.World;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.ArrayList;
import java.util.List;

public class BukkitMCMapView implements MCMapView {

	private MapView mv;

	public BukkitMCMapView(MapView mv) {
		this.mv = mv;
	}

	@Override
	public void addRenderer(MCMapRenderer renderer) {
		mv.addRenderer((MapRenderer) renderer.getHandle());
	}

	@Override
	public int getCenterX() {
		return mv.getCenterX();
	}

	@Override
	public int getCenterZ() {
		return mv.getCenterZ();
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public short getId() {
		return mv.getId();
	}

	@Override
	public List<MCMapRenderer> getRenderers() {
		List<MCMapRenderer> list = new ArrayList<>();
		for (MapRenderer mr : mv.getRenderers()) {
			list.add(new BukkitMCMapRenderer(mr.isContextual()));
		}
		return list;
	}

	@Override
	public MCMapViewScale getScale() {
		return MCMapViewScale.valueOf(mv.getScale().name());
	}

	@Override
	public MCWorld getWorld() {
		if(mv.getWorld() == null) {
			return null;
		}
		return new BukkitMCWorld(mv.getWorld());
	}

	@Override
	public boolean isUnlimitedTracking() {
		return mv.isUnlimitedTracking();
	}

	@Override
	public boolean isVirtual() {
		return mv.isVirtual();
	}

	@Override
	public boolean removeRenderer(MCMapRenderer renderer) {
		return mv.removeRenderer((MapRenderer) renderer.getHandle());
	}

	@Override
	public void setCenterX(int x) {
		mv.setCenterX(x);
	}

	@Override
	public void setCenterZ(int z) {
		mv.setCenterZ(z);
	}

	@Override
	public void setScale(MCMapViewScale scale) {
		mv.setScale(MapView.Scale.valueOf(scale.name()));
	}

	@Override
	public void setUnlimitedTracking(boolean unlimited) {
		mv.setUnlimitedTracking(unlimited);
	}

	@Override
	public void setWorld(MCWorld world) {
		mv.setWorld((World) world.getHandle());
	}

	@Override
	public Object getHandle() {
		return mv;
	}
}
