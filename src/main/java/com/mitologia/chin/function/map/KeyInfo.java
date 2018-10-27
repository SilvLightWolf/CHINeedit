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
public class KeyInfo extends AbstractFunction {

	public CArray getInfo(String key, Target t, String sv) {
		CArray ret = CArray.GetAssociativeArray(t);
		switch (key.length()) {
			case 8:
				ret = ObjGenerator.getImageArray(key, t);
				break;
			case 9:
				ret = ObjGenerator.getCharSpriteArray(key, t);
				break;
			case 10:
				ret = ObjGenerator.getMapCanvas(key, t);
				break;
			case 11:
				ret = ObjGenerator.getMapCursor(key, t);
				break;
			case 12:
				ret = ObjGenerator.getMapCursorCollection(key, t);
				break;
			case 13:
				if (sv == null) {
					ret = ObjGenerator.getMapFont( key, t);
				} else {
					ret = ObjGenerator.getMapFont(key, t, sv);
				}
				break;
			case 14:
				ret = ObjGenerator.getMapRenderer(key, t);
				break;
			case 15:
				ret = ObjGenerator.getMapView(key, t);
				break;
		}
		ret.set("key", key);
		return ret;
	}

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
		if (Mapper.mapObj.keySet().contains(args[0].val())) {
			return (args.length == 2) ? getInfo(args[0].val(), t, args[1].val()) : getInfo(args[0].val(), t, null);
		} else {
			throw new CRECastException("Unknown Key : " + args[0].val(), t);
		}
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "key_info";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{1, 2};
	}

	@Override
	public String docs() {
		return "array (key[, fontString]) Get key info";
	}
}
