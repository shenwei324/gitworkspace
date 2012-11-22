package com.alan.shenw.commons.utils;

import java.util.Map;

import org.junit.Test;

import com.alan.shenw.commons.test.TestKit;
import com.alan.shenw.commons.test.annotation.CheckMethodWithoutTest;

@CheckMethodWithoutTest(src = MapKit.class)
public class MapKitTest extends TestKit {
	
	@Test
	public void testCreateMapByObjectArray() {
		Map<String, String> map = MapKit.createMap("a", "apple", "b", "banana");
		assertEquals(2, map.size());
		assertEquals("apple", map.get("a"));
		assertEquals("banana", map.get("b"));
	}

	@Test
	public void testCreateMapByTwoObjectArray() {
		String[] keys = { "a", "b" };
		Integer[] values = { 97, 98 };
		Map<String, Integer> map = MapKit.createMap(keys, values);
		assertEquals(2, map.size());
		assertEquals(97, (int)map.get("a"));
		assertEquals(98, (int)map.get("b"));
	}
}
