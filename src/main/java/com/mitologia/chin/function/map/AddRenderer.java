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

import java.util.HashMap;

@api
public class AddRenderer extends AbstractFunction {

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
		String rendererkey = args[1].val();
		MCMapView mv;
		MCMapRenderer mr;
		if (viewkey.length() != 15)
			throw new CRECastException("Expecting key length is 15 but checked " + viewkey.length(), t);
		if (rendererkey.length() != 14)
			throw new CRECastException("Expecting key length is 14 but checked " + rendererkey.length(), t);
		if (!Mapper.mapObj.keySet().contains(viewkey))
			throw new CRENotFoundException("Cannot found value of " + viewkey, t);
		if (!Mapper.mapObj.keySet().contains(rendererkey))
			throw new CRENotFoundException("Cannot found value of " + rendererkey, t);
		mv = (MCMapView) Mapper.mapObj.get(viewkey);
		mr = (MCMapRenderer) Mapper.mapObj.get(rendererkey);
		mv.addRenderer(mr);
		mr.initialize(mv);

		Mapper.mapObj.put(viewkey, mv);
		HashMap<String, Object> descs = Mapper.mapObj_desc.get(viewkey);
		descs.put(rendererkey, mr);
		Mapper.mapObj_desc.put(viewkey, descs);

		return CVoid.VOID;
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "add_renderer";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{2};
	}

	@Override
	public String docs() {
		return "Void (MapView, MapRenderer) add MapRenderer to MapView";
	}
}
