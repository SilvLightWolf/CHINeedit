package com.mitologia.chin.function.map;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
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
import com.mitologia.chin.bukkit.*;
import com.mitologia.chin.enums.MCMapCursorType;
import com.mitologia.chin.enums.MCMapViewScale;

@api
public class EditMapObject extends AbstractFunction {
	@Override
	public Class<? extends CREThrowable>[] thrown() {
		return new Class[]{
				CRENotFoundException.class,
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

		String key = args[0].val();
		if (!Mapper.mapObj.keySet().contains(key)) throw new CRENotFoundException("Unknown key : " + key, t);
		Object mapobj = Mapper.mapObj.get(key);

		String k = args[1].val();

		if (mapobj instanceof MCMapCursor) {
			MCMapCursor cursor = (MCMapCursor) mapobj;
			if (k.equalsIgnoreCase("caption")) {
				cursor.setCaption(args[2].val());
			} else if (k.equalsIgnoreCase("direction")) {
				cursor.setDirection(Byte.parseByte(args[2].val()));
			} else if (k.equalsIgnoreCase("x")) {
				cursor.setX(Byte.parseByte(args[2].val()));
			} else if (k.equalsIgnoreCase("y")) {
				cursor.setY(Byte.parseByte(args[2].val()));
			} else if (k.equalsIgnoreCase("type")) {
				cursor.setType(MCMapCursorType.valueOf(args[2].val().toUpperCase()));
			} else if (k.equalsIgnoreCase("visible")) {
				cursor.setVisible(Boolean.getBoolean(args[2].val()));
			}
			Mapper.mapObj.put(key, cursor);
		} else if (mapobj instanceof MCMapView) {
			MCMapView view = (MCMapView) mapobj;
			if (k.equalsIgnoreCase("X")) {
				view.setCenterX(Integer.parseInt(args[2].val()));
			} else if (k.equalsIgnoreCase("Z")) {
				view.setCenterZ(Integer.parseInt(args[2].val()));
			} else if (k.equalsIgnoreCase("scale")) {
				view.setScale(MCMapViewScale.valueOf(args[2].val().toUpperCase()));
			} else if (k.equalsIgnoreCase("unlimited_tracking")) {
				view.setUnlimitedTracking(Boolean.getBoolean(args[2].val()));
			} else if (k.equalsIgnoreCase("world")) {
				view.setWorld(Static.getWorld(args[2].val(), t));
			}
			Mapper.mapObj.put(key, view);
		} else if (mapobj instanceof MCMapRenderer) {
			MCMapRenderer renderer = (MCMapRenderer) mapobj;
			if( k.equalsIgnoreCase("initialize")) {
				String mvkey = args[2].val();
				if (mvkey.length() != 15)
					throw new CRECastException("Expecting key length is 15 but recieved " + mvkey.length(), t);
				if (!Mapper.mapObj.keySet().contains(mvkey))
					throw new CRENotFoundException("Unknown key : " + mvkey, t);
				renderer.initialize((MCMapView) Mapper.mapObj.get(mvkey));
			}
			Mapper.mapObj.put(key, renderer);
		}

		return CVoid.VOID;
	}

	@Override
	public Version since() {
		return new SimpleVersion(1, 0, 0);
	}

	@Override
	public String getName() {
		return "edit_map_object";
	}

	@Override
	public Integer[] numArgs() {
		return new Integer[]{3};
	}

	@Override
	public String docs() {
		return "boolean (TargetKey, Editkey, Editvalue) edit map object";
	}
}
