package com.alan.shenw.commons.utils;

import java.util.HashMap;
import java.util.Map;

public class MapKit {
	
	public static <T, U> Map<T, U> createMap(T[] keys, U[] values) {
		Map<T, U> map = new HashMap<T, U>();
		if (keys == null || values == null) {
			return map;
		} else if (keys.length != values.length) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
		return map;
	}
	
	public static <T> Map<T, T> createMap(T... ts) {
		Map<T, T> map = new HashMap<T, T>();
		int length = ts.length / 2 * 2;
		for (int i = 0; i < length; i+=2) {
			map.put(ts[i], ts[i+1]);
		}
		return map;
	}
}
