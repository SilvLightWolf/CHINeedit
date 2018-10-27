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

@api
public class CreateCursor extends AbstractFunction {

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends CREThrowable>[] thrown() {
		return new Class[]{CRECastException.class};
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
		if (args[0] instanceof CArray) {
			CArray arr = (CArray) args[0];
			return new CString(Mapper.createKey("cursor", ObjGenerator.mapCursor(arr, t)), t);
		} else {
			throw new CRECastException("Expecting Array, but recieved " + args[0].val(), t);
		}
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "create_cursor";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{1};
	}

	@Override
	public String docs() {
		return "Key (array) create Cursor"
				+ "Array : x, y, direction(, cursortype(, visible(, caption)))"
				+ "(byte, byte, byte, String, boolean, String)";
	}
}
