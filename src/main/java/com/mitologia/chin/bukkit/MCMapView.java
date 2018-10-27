package com.mitologia.chin.bukkit;

import com.laytonsmith.abstraction.AbstractionObject;
import com.laytonsmith.abstraction.MCWorld;
import com.mitologia.chin.enums.MCMapViewScale;

import java.util.List;

public interface MCMapView extends AbstractionObject {

	void addRenderer(MCMapRenderer renderer);

	int getCenterX();

	int getCenterZ();

	/**
	 * @deprecated
	 */
	@Deprecated
	short getId();

	List<MCMapRenderer> getRenderers();

	MCMapViewScale getScale();

	MCWorld getWorld();

	boolean isUnlimitedTracking();

	boolean isVirtual();

	boolean removeRenderer(MCMapRenderer renderer);

	void setCenterX(int x);

	void setCenterZ(int z);

	void setScale(MCMapViewScale scale);

	void setUnlimitedTracking(boolean unlimited);

	void setWorld(MCWorld world);

}
