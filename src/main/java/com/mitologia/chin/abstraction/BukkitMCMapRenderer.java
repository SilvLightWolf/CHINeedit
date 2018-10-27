package com.mitologia.chin.abstraction;

import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.core.constructs.CClosure;
import com.mitologia.chin.Renderer;
import com.mitologia.chin.bukkit.MCMapCanvas;
import com.mitologia.chin.bukkit.MCMapRenderer;
import com.mitologia.chin.bukkit.MCMapView;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapView;

public class BukkitMCMapRenderer implements MCMapRenderer {

	private Renderer mr;

	public BukkitMCMapRenderer(boolean contextual) {
		this.mr = new Renderer(contextual);
	}

	public BukkitMCMapRenderer(Renderer mr) {
		this.mr = mr;
	}

	public BukkitMCMapRenderer() {
		this.mr = new Renderer();
	}

	@Override
	public void initialize(MCMapView map) {
		mr.initialize((MapView) map.getHandle());
	}

	@Override
	public void setClosure(CClosure c) {
		mr.setClosure(c);
	}

	@Override
	public boolean isContextual() {
		return mr.isContextual();
	}

	@Override
	public void render(MCMapView map, MCMapCanvas canvas, MCPlayer player) {
		mr.render((MapView) map.getHandle(), (MapCanvas) canvas.getHandle(), (Player) player.getHandle());
	}

	@Override
	public Object getHandle() {
		return mr;
	}
}
