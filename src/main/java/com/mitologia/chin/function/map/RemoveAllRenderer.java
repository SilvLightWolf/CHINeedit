package com.mitologia.chin.function.map;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CRENotFoundException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.mitologia.chin.Mapper;
import com.mitologia.chin.bukkit.MCMapRenderer;
import com.mitologia.chin.bukkit.MCMapView;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.HashMap;

@api
public class RemoveAllRenderer extends AbstractFunction {

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends CREThrowable>[] thrown() {
		return new Class[]{
				CRECastException.class,
				CRENotFoundException.class
		};
	}

	@Override
	public boolean isRestricted() {
		return false;
	}

	@Override
	public Boolean runAsync() {
		return null;
	}

	@Override
	public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

		String viewkey = args[0].val();
		MCMapView mv;
		if (viewkey.length() != 15)
			throw new CRECastException("Expecting key length is 15 but checked " + viewkey.length(), t);
		if (!Mapper.mapObj.keySet().contains(viewkey))
			throw new CRENotFoundException("Cannot found value of " + viewkey, t);
		mv = (MCMapView) Mapper.mapObj.get(viewkey);

		for(MapRenderer mr : ((MapView)mv.getHandle()).getRenderers()) {
			((MapView)mv.getHandle()).removeRenderer(mr);
		}

		Mapper.mapObj.put(viewkey, mv);
		HashMap<String, Object> descs = new HashMap<>();
		Mapper.mapObj_desc.put(viewkey, descs);

		return CVoid.VOID;
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "remove_all_renderer";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{1};
	}

	@Override
	public String docs() {
		return "void (MapView) remove All MapRenderer from MapView";
	}
}
