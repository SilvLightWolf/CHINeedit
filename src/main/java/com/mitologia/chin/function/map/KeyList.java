package com.mitologia.chin.function.map;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.mitologia.chin.Mapper;
import com.mitologia.chin.bukkit.*;

@api
public class KeyList extends AbstractFunction {

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
		CArray objs = CArray.GetAssociativeArray(t);
		for (String key : Mapper.mapObj.keySet()) {
			CArray push = CArray.GetAssociativeArray(t);
			push.set("key", key);
			if (Mapper.mapObj.get(key) instanceof MCAwtImage)
				push.set("type", "Image");
			else if (Mapper.mapObj.get(key) instanceof MCCharacterSprite)
				push.set("type", "CharSprite");
			else if (Mapper.mapObj.get(key) instanceof MCMapCanvas)
				push.set("type", "Canvas");
			else if (Mapper.mapObj.get(key) instanceof MCMapCursor)
				push.set("type", "Cursor");
			else if (Mapper.mapObj.get(key) instanceof MCMapCursorCollection)
				push.set("type", "CursorCollection");
			else if (Mapper.mapObj.get(key) instanceof MCMapFont)
				push.set("type", "Font");
			else if (Mapper.mapObj.get(key) instanceof MCMapRenderer)
				push.set("type", "Renderer");
			else if (Mapper.mapObj.get(key) instanceof MCMapView)
				push.set("type", "View");
			else
				push.set("type", "Unknown");
			objs.push(push, t);
		}
		return objs;
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "key_list";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{0};
	}

	@Override
	public String docs() {
		return "Array () get Keys";
	}
}
