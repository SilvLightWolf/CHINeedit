package com.mitologia.chin.function.map;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CClosure;
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
import com.mitologia.chin.bukkit.MCMapCanvas;
import com.mitologia.chin.bukkit.MCMapRenderer;

@api
public class SetRenderer extends AbstractFunction {

	@Override
	public Class<? extends CREThrowable>[] thrown() {
		return new Class[]{
				CRECastException.class
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

		String mrkey = args[0].val();
		if (mrkey.length() != 14)
			throw new CRECastException("Expecting key length 14 but checked " + mrkey.length(), t);
		if (!Mapper.mapObj.keySet().contains(mrkey))
			throw new CRENotFoundException("Cannot found value of " + mrkey, t);
		MCMapRenderer renderer = (MCMapRenderer) Mapper.mapObj.get(mrkey);

		if (args[1] instanceof CClosure) {
			renderer.setClosure((CClosure) args[1]);
			Mapper.mapObj.put(mrkey, renderer);
		} else {
			throw new CRECastException("Expecting Closure but recieved " + args[1].getCType().name(), t);
		}

		return CVoid.VOID;
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "set_renderer";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{2};
	}

	@Override
	public String docs() {
		return "void (MapRenderer, Closure) set Renderer code [Closure : MapView, MapCanvas, Player]";
	}

}
