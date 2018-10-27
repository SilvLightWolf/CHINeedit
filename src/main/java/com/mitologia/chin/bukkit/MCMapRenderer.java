package com.mitologia.chin.bukkit;

import com.laytonsmith.abstraction.AbstractionObject;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.core.constructs.CClosure;

public interface MCMapRenderer extends AbstractionObject {

	void initialize(MCMapView map);

	void setClosure(CClosure c);

	boolean isContextual();

	abstract void render(MCMapView map, MCMapCanvas canvas, MCPlayer player);

}
