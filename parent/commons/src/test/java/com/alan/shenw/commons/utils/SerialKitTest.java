package com.alan.shenw.commons.utils;

import org.junit.Test;

import com.alan.shenw.commons.test.TestKit;
import com.alan.shenw.commons.test.annotation.CheckMethodWithoutTest;

@CheckMethodWithoutTest(src = SerialKit.class)
public class SerialKitTest extends TestKit {

	@Test
	public void testGetUUID() {
		String uuid = SerialKit.getUUID();
		assertEquals(36, uuid.length());
		assertNotSame(uuid, SerialKit.getUUID());
	}
	
	@Test
	public void testGetSerialNumber() {
		String sn = SerialKit.getSerialNumber();
		assertEquals(32, sn.length());
		assertTrue(sn.indexOf('-') < 0);
	}
}
