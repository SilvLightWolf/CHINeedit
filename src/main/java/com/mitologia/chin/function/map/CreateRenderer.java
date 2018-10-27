package com.mitologia.chin.function.map;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.mitologia.chin.Mapper;
import com.mitologia.chin.ObjGenerator;
import com.mitologia.chin.abstraction.BukkitMCMapRenderer;
import com.mitologia.chin.bukkit.MCMapRenderer;

@api
public class CreateRenderer extends AbstractFunction {

	@Override
	public Class<? extends CREThrowable>[] thrown() {
		return new Class[0];
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
		MCMapRenderer mr;
		if(args.length == 0 ){
			mr = new BukkitMCMapRenderer();
		} else {
			mr = new BukkitMCMapRenderer(Boolean.getBoolean(args[0].val()));
		}
		return new CString(Mapper.createKey("renderer", mr), t);
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "create_renderer";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{ 0, 1 };
	}

	@Override
	public String docs() {
		return "void ([boolean]) create renderer";
	}

}
