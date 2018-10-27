package com.mitologia.chin;

import com.laytonsmith.core.constructs.*;
import com.mitologia.chin.abstraction.BukkitMCCharacterSprite;
import com.mitologia.chin.abstraction.BukkitMCMapCursor;
import com.mitologia.chin.abstraction.BukkitMCMapCursorCollection;
import com.mitologia.chin.bukkit.*;
import com.mitologia.chin.enums.MCMapCursorType;
import org.bukkit.map.MapView;

import java.util.HashMap;

public class ObjGenerator {

	public static MCMapCursor mapCursor(CArray array, Target t) {
		if (array.size() >= 3) {
			byte x = Byte.valueOf(array.get("x", t).val());
			byte y = Byte.valueOf(array.get("y", t).val());
			byte d = Byte.valueOf(array.get("direction", t).val());
			if (array.size() >= 4) {
				MCMapCursorType type = MCMapCursorType.valueOf(array.get("type", t).val().toUpperCase());
				if (array.size() >= 5) {
					boolean visible = Boolean.getBoolean(array.get("visible", t).val());
					if (array.size() == 6) {
						String caption = array.get("caption", t).val();
						return mapCursor(x, y, d, type, visible, caption);
					}
					return mapCursor(x, y, d, type, visible);
				}
				return mapCursor(x, y, d, type);
			}
			return mapCursor(x, y, d);
		}
		return null;
	}

	public static MCMapCursor mapCursor(byte x, byte y, byte direc) {
		return mapCursor(x, y, direc, MCMapCursorType.WHITE_POINTER);
	}

	public static MCMapCursor mapCursor(byte x, byte y, byte direc, MCMapCursorType type) {
		return mapCursor(x, y, direc, type, true);
	}

	public static MCMapCursor mapCursor(byte x, byte y, byte direc, MCMapCursorType type, boolean visible) {
		return new BukkitMCMapCursor(x, y, direc, type, visible);
	}

	public static MCMapCursor mapCursor(byte x, byte y, byte direc, MCMapCursorType type, boolean visible, String caption) {
		return new BukkitMCMapCursor(x, y, direc, type, visible, caption);
	}


	public static MCMapCursorCollection mapCursorCollection(CArray arr, Target t) {
		MCMapCursorCollection mcc = new BukkitMCMapCursorCollection();
		for (Construct c : arr.asList()) {
			if (c instanceof CArray)
				mcc.addCursor(mapCursor((CArray) c, t));
		}
		return mcc;
	}


	public static MCCharacterSprite charSprite(CArray arr, Target t) {
		int width = Integer.parseInt(arr.get("width", t).val());
		int heigth = Integer.parseInt(arr.get("height", t).val());
		MCCharacterSprite cs = new BukkitMCCharacterSprite(width, heigth, null);
		return cs;
	}


	public static CArray getImageArray(MCAwtImage ai, Target t) {

		CArray ret = new CArray(t);

		String newkey = Mapper.findKey("image", ai, true);
		ret.set("key", newkey);

		return ret;

	}

	public static CArray getImageArray(String key, Target t) {

		CArray ret = new CArray(t);

		MCAwtImage ai = (MCAwtImage) Mapper.mapObj.get(key);
		ret.set("key", key);

		return ret;
	}


	public static CArray getCharSpriteArray(MCCharacterSprite cs, Target t) {

		CArray ret = new CArray(t);

		String newkey = Mapper.findKey("charsprite", cs, true);
		ret.set("key", newkey);

		ret.set("height", new CInt(cs.getHeight(), t), t);
		ret.set("width", new CInt(cs.getWidth(), t), t);

		return ret;
	}

	public static CArray getCharSpriteArray(String key, Target t) {

		CArray ret = new CArray(t);
		MCCharacterSprite cs = (MCCharacterSprite) Mapper.mapObj.get(key);

		ret.set("height", new CInt(cs.getHeight(), t), t);
		ret.set("width", new CInt(cs.getWidth(), t), t);
		ret.set("key", key);

		return ret;
	}


	public static CArray getMapCanvas(MCMapCanvas mc, Target t) {

		CArray ret = new CArray(t);

		String newkey = Mapper.findKey("canvas", mc, true);
		ret.set("key", newkey);

		HashMap<String, Object> descs = new HashMap<>();

		CArray ccarr = getMapCursorCollection(mc.getCursors(), t);
		ret.set("cursorcollection", ccarr, t);
		descs.put(ccarr.get("key", t).val(), mc.getCursors());

		CArray mvarr = getMapView(mc.getMapView(), t);
		ret.set("view", mvarr, t);
		descs.put(mvarr.get("key", t).val(), mc.getMapView());

		return ret;
	}


	public static CArray getMapCanvas(String key, Target t) {

		CArray ret = new CArray(t);
		MCMapCanvas mc = (MCMapCanvas) Mapper.mapObj.get(key);

		String cckey = null;
		String mvkey = null;
		for(String desckey : Mapper.mapObj_desc.get(key).keySet()) {
			if (Mapper.mapObj_desc.get(key).get(desckey) instanceof MCMapCursorCollection) {
				cckey = desckey;
			}
			if (Mapper.mapObj_desc.get(key).get(desckey) instanceof MCMapView) {
				mvkey = desckey;
			}
		}

		ret.set("cursorcollection", getMapCursorCollection(cckey, t), t);
		ret.set("view", getMapView(mvkey, t), t);
		ret.set("key", key);

		return ret;
	}


	public static CArray getMapCursor(MCMapCursor mc, Target t) {

		CArray ret = new CArray(t);

		String newkey = Mapper.findKey("cursor", mc, true);
		ret.set("key", newkey);

		ret.set("X", new CInt(mc.getX(), t), t);
		ret.set("Y", new CInt(mc.getY(), t), t);
		ret.set("direction", new CInt(mc.getDirection(), t), t);
		ret.set("type", new CString(mc.getType().name(), t), t);
		ret.set("visible", CBoolean.get(mc.isVisible()), t);
		if (mc.getCaption() != null)
			ret.set("caption", mc.getCaption());

		return ret;
	}

	public static CArray getMapCursor(String key, Target t) {

		CArray ret = new CArray(t);
		MCMapCursor mc = (MCMapCursor) Mapper.mapObj.get(key);

		ret.set("key", key);
		ret.set("X", new CInt(mc.getX(), t), t);
		ret.set("Y", new CInt(mc.getY(), t), t);
		ret.set("direction", new CInt(mc.getDirection(), t), t);
		ret.set("type", new CString(mc.getType().name(), t), t);
		ret.set("visible", CBoolean.get(mc.isVisible()), t);
		if (mc.getCaption() != null)
			ret.set("caption", mc.getCaption());

		return ret;

	}


	public static CArray getMapCursorCollection(MCMapCursorCollection mcc, Target t) {
		CArray ret = new CArray(t);

		String newkey = Mapper.findKey("cursorcollection", mcc, true);
		ret.set("key", newkey);

		CArray cursors = new CArray(t);
		for (int i = 0; i < mcc.size(); i++) {
			cursors.push(getMapCursor(mcc.getCursor(i), t), t);
		}

		ret.set("cursors", cursors, t);

		return ret;
	}

	public static CArray getMapCursorCollection(String key, Target t) {

		CArray ret = new CArray(t);
		MCMapCursorCollection mcc = (MCMapCursorCollection) Mapper.mapObj.get(key);

		CArray cursors = new CArray(t);
		for (String desckey : Mapper.mapObj_desc.keySet()){
			cursors.push(getMapCursor(desckey, t), t);
		}

		ret.set("cursors", cursors, t);
		ret.set("key", key);

		return ret;

	}


	public static CArray getMapFont(MCMapFont mf, Target t) {
		return getMapFont(mf, t, "A");
	}

	public static CArray getMapFont(MCMapFont mf, Target t, String chara) {

		CArray ret = new CArray(t);

		String newkey = Mapper.findKey("font", mf, true);
		ret.set("key", newkey);

		ret.set("char", getCharSpriteArray(mf.getChar(chara.charAt(0)), t), t);
		ret.set("height", new CInt(mf.getHeight(), t), t);
		ret.set("width", new CInt(mf.getWidth(chara), t), t);
		ret.set("valid", CBoolean.get(mf.isValid(chara)), t);

		return ret;

	}

	public static CArray getMapFont(String key, Target t){
		return getMapFont(key, t, "A");
	}

	public static CArray getMapFont(String key, Target t, String chara) {

		CArray ret = new CArray(t);
		MCMapFont mf = (MCMapFont) Mapper.mapObj.get(key);

		ret.set("char", getCharSpriteArray(mf.getChar(chara.charAt(0)), t), t);
		ret.set("height", new CInt(mf.getHeight(), t), t);
		ret.set("width", new CInt(mf.getWidth(chara), t), t);
		ret.set("valid", CBoolean.get(mf.isValid(chara)), t);
		ret.set("key", key);

		return ret;

	}


	public static CArray getMapRenderer(MCMapRenderer mr, Target t) {

		CArray ret = new CArray(t);

		String newkey = Mapper.findKey("renderer", mr, true);
		ret.set("key", newkey);

		ret.set("contextual", CBoolean.get(mr.isContextual()), t);

		return ret;

	}

	public static CArray getMapRenderer(String key, Target t) {

		CArray ret = new CArray(t);
		MCMapRenderer mr = (MCMapRenderer) Mapper.mapObj.get(key);

		ret.set("contextual", CBoolean.get(mr.isContextual()), t);
		ret.set("key", key);

		return ret;

	}

	public static CArray getMapView(MCMapView mv, Target t) {

		CArray ret = new CArray(t);

		String newkey = Mapper.findKey("view", mv, true);
		ret.set("key", newkey);

		CArray renderers = new CArray(t);
		HashMap<String, Object> descs = new HashMap<>();
		for (MCMapRenderer mr : mv.getRenderers()) {
			CArray arr = getMapRenderer(mr, t);
			renderers.push(arr, t);
			descs.put(arr.get("key", t).val(), mr);
		}
		Mapper.mapObj_desc.put(newkey, descs);

		ret.set("renderers", renderers, t);
		ret.set("X", new CInt(mv.getCenterX(), t), t);
		ret.set("Z", new CInt(mv.getCenterZ(), t), t);
		ret.set("scale", new CString(mv.getScale().name(), t), t);
		ret.set("unlimited_tracking", CBoolean.get(mv.isUnlimitedTracking()), t);
		ret.set("is_virtual", CBoolean.get(mv.isVirtual()), t);
		if (mv.getWorld() == null) {
			ret.set("world", "Unknown");
		} else {
			ret.set("world", mv.getWorld().getName());
		}

		return ret;

	}

	public static CArray getMapView(String key, Target t) {

		CArray ret = new CArray(t);
		MCMapView mv = (MCMapView) Mapper.mapObj.get(key);

		CArray renderers = new CArray(t);
		for (String desckey : Mapper.mapObj_desc.get(key).keySet()){
			renderers.push(getMapRenderer(desckey, t), t);
		}

		System.out.println("renderers : " + ((MapView) mv.getHandle()).getRenderers().size());

		ret.set("renderers", renderers, t);
		ret.set("X", new CInt(mv.getCenterX(), t), t);
		ret.set("Z", new CInt(mv.getCenterZ(), t), t);
		ret.set("scale", new CString(mv.getScale().name(), t), t);
		ret.set("unlimited_tracking", CBoolean.get(mv.isUnlimitedTracking()), t);
		ret.set("is_virtual", CBoolean.get(mv.isVirtual()), t);
		if (mv.getWorld() == null) {
			ret.set("world", "Unknown");
		} else {
			ret.set("world", mv.getWorld().getName());
		}
		ret.set("key", key);

		return ret;

	}

}
