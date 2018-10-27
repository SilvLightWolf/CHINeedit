package com.mitologia.chin.function.map;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.mitologia.chin.Mapper;
import com.mitologia.chin.ObjGenerator;

@api
public class CreateCursorCollection extends AbstractFunction {

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends CREThrowable>[] thrown() {
		return new Class[]{CREException.class};
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
			return new CString(Mapper.createKey("cursorcollection", ObjGenerator.mapCursorCollection(arr, t)), t);
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
		return "create_cursor_collection";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{1};
	}

	@Override
	public String docs() {
		return "Key (array) create Cursor Collection";
	}
}
