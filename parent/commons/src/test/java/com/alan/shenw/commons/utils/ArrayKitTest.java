package com.alan.shenw.commons.utils;

import org.junit.Test;

import com.alan.shenw.commons.test.TestKit;
import com.alan.shenw.commons.test.annotation.CheckMethodWithoutTest;

@CheckMethodWithoutTest(src = ArrayKit.class)
public class ArrayKitTest extends TestKit {

	@Test
	public void testAddToFirst() {
		String[] array = {"c", "d"};
		array = ArrayKit.addToFirst(array, "a", "b");
		assertEquals(4, array.length);
		assertEquals("a", array[0]);
		assertEquals("b", array[1]);
		assertEquals("c", array[2]);
		assertEquals("d", array[3]);
	}
	
	@Test
	public void testNewArrayInstance() {
		String[] array = ArrayKit.newArrayInstance(String.class, "a", "b", "c");
		assertEquals(3, array.length);
		assertEquals("a", array[0]);
		assertEquals("b", array[1]);
		assertEquals("c", array[2]);
	}
	
	@Test
	public void testNewArrayInstanceWithLength() {
		String[] array = ArrayKit.newArrayInstance(String.class, 5);
		assertEquals(5, array.length);
		for (int i = 0; i < 5; i++) {
			assertNull(array[i]);
		}
		
		array = ArrayKit.newArrayInstance(String.class, 5, "a", "b", "c");
		assertEquals(5, array.length);
		assertEquals("a", array[0]);
		assertEquals("b", array[1]);
		assertEquals("c", array[2]);
		assertNull(array[3]);
		assertNull(array[4]);
	}
	
	@Test
	public void testCopyWithLength() {
		String[] src = {"a", "b", "c"};
		String[] dest = new String[4];
		dest[0] = "c";
		dest[1] = "d";
		ArrayKit.copy(src, 0, dest, 2, 2);
		assertEquals("c", dest[0]);
		assertEquals("d", dest[1]);
		assertEquals("a", dest[2]);
		assertEquals("b", dest[3]);
	}
	
	@Test
	public void testCopy() {
		String[] src = {"a", "b", "c"};
		String[] dest = new String[4];
		
		ArrayKit.copy(src, 1, dest, 0);
		assertEquals("b", dest[0]);
		assertEquals("c", dest[1]);
		assertNull(dest[2]);
		assertNull(dest[3]);
	}
}
