package com.alan.shenw.commons.utils;

import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class ArrayKit {
	
	public static <T> T[] addToFirst(T[] src, T... items) {
		Class<T> claxx;
		if (src != null) {
			claxx = (Class<T>) src.getClass().getComponentType();
		} else if (items != null) {
			claxx = (Class<T>) items.getClass().getComponentType();
		} else {
			throw new IllegalArgumentException("Arguments src and items all null.");
		}
		
		T[] array = newArrayInstance(claxx, src, items);
		int position = copy(items, 0, array, 0);
		copy(src, 0, array, position);
		
		return array;
	}
	
	public static void copy(Object[] src, int srcPos, Object[] dest, int destPos, int length) {
		System.arraycopy(src, srcPos, dest, destPos, length);
	}
	
	public static int copy(Object[] src, int srcPos, Object[] dest, int destPos) {
		int length = 0;
		if (src != null) {
			length = src.length - srcPos;
			if (dest.length - destPos < length) {
				throw new IllegalArgumentException("Can not copy all the src array.");
			}
			System.arraycopy(src, srcPos, dest, destPos, length);
		}
		return length;
	}

	private static <T> T[] newArrayInstance(Class<T> claxx, T[] src, T[] items) {
		int length = src != null ? src.length : 0;
		length += items != null ? items.length : 0;
		return newArrayInstance(claxx, length);
	}
	
	public static <T> T[] newArrayInstance(Class<T> claxx, int length, T... ts) {
		T[] array = (T[]) Array.newInstance(claxx, length);
		for (int i = 0; i < ts.length; i++) {
			array[i] = ts[i];
		}
		return array;
	}
	
	public static <T> T[] newArrayInstance(Class<T> claxx, T... ts) {
		return newArrayInstance(claxx, ts.length, ts);
	}
}
