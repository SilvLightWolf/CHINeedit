package com.mitologia.chin;

import com.mitologia.chin.bukkit.*;
import com.mitologia.chin.lib.RandomString;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Mapper {

	public static HashMap<String, Object> mapObj = new HashMap<>();

	public static HashMap<String, HashMap<String, Object>> mapObj_desc = new HashMap<>();

	static void reset() {
		mapObj = new HashMap<>();
		mapObj_desc = new HashMap<>();
	}

	public static String createKey(String type, Object obj) {

		String random = null;

		switch (type.toLowerCase()) {
			case "image":
				random = new RandomString(8, ThreadLocalRandom.current()).nextString();
				break;
			case "charsprite":
				random = new RandomString(9, ThreadLocalRandom.current()).nextString();
				break;
			case "canvas":
				random = new RandomString(10, ThreadLocalRandom.current()).nextString();
				break;
			case "cursor":
				random = new RandomString(11, ThreadLocalRandom.current()).nextString();
				break;
			case "cursorcollection":
				random = new RandomString(12, ThreadLocalRandom.current()).nextString();
				break;
			case "font":
				random = new RandomString(13, ThreadLocalRandom.current()).nextString();
				break;
			case "renderer":
				random = new RandomString(14, ThreadLocalRandom.current()).nextString();
				break;
			case "view":
				random = new RandomString(15, ThreadLocalRandom.current()).nextString();
				break;
		}

		return createKey(type, obj, random, null);
	}

	public static String createKey(String type, Object obj, String key, String parant){

		if (key != null) {
			if(parant == null) {
				mapObj.put(key, obj);
			} else {
				HashMap<String, Object> map;
				if(mapObj_desc.keySet().contains(parant)) {
					map = mapObj_desc.get(parant);
					map.put(key, obj);
				} else {
					map = new HashMap<>();
					map.put(key, obj);
				}
			}
		}

		return key;
	}

	public static int getLength(String type) {
		switch (type.toLowerCase()) {
			case "image":
				return 8;
			case "charsprite":
				return 9;
			case "canvas":
				return 10;
			case "cursor":
				return 11;
			case "cursorcollection":
				return 12;
			case "font":
				return 13;
			case "renderer":
				return 14;
			case "view":
				return 15;
			default:
				return 0;
		}
	}

	public static String getType(int length) {
		switch (length) {
			case 8:
				return "image";
			case 9:
				return "charsprite";
			case 10:
				return "canvas";
			case 11:
				return "cursor";
			case 12:
				return "cursorcollection";
			case 13:
				return "font";
			case 14:
				return "renderer";
			case 15:
				return "view";
			default:
				return null;
		}

	}

	public static String findKey(Object obj) {
		if (obj instanceof MCAwtImage)
			return findKey("image", obj);
		else if (obj instanceof MCCharacterSprite)
			return findKey("charsprite", obj);
		else if (obj instanceof MCMapCanvas)
			return findKey("canvas", obj);
		else if (obj instanceof MCMapCursor)
			return findKey("cursor", obj);
		else if (obj instanceof MCMapCursorCollection)
			return findKey("cursorcollection", obj);
		else if (obj instanceof MCMapFont)
			return findKey("font", obj);
		else if (obj instanceof MCMapRenderer)
			return findKey("renderer", obj);
		else if (obj instanceof MCMapView)
			return findKey("view", obj);
		else
			return null;
	}

	public static String findKey(String type, Object obj) {
		return findKey(type, obj, false);
	}

	public static String findKey(String type, Object obj, boolean generated) {
		for (String key : mapObj.keySet()) {
			if (key.length() == getLength(type)) {
				if (mapObj.get(key).equals(obj))
					return key;
			}
		}
		return generated ? createKey(type, obj) : null;
	}

}
