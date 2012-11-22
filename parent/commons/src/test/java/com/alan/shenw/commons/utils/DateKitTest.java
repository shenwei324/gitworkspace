package com.alan.shenw.commons.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.alan.shenw.commons.test.TestKit;
import com.alan.shenw.commons.test.annotation.CheckMethodWithoutTest;

@CheckMethodWithoutTest(src = DateKit.class)
public class DateKitTest extends TestKit {

	@Test
	public void testGet() {
		Date d = DateKit.get(2011, 5, 10);
		Calendar cale = Calendar.getInstance();
		cale.setTime(d);
		assertEquals(2011, cale.get(Calendar.YEAR));
		assertEquals(4, cale.get(Calendar.MONTH));
		assertEquals(10, cale.get(Calendar.DAY_OF_MONTH));
		assertEquals(0, cale.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, cale.get(Calendar.MINUTE));
		assertEquals(0, cale.get(Calendar.SECOND));
		assertEquals(0, cale.get(Calendar.MILLISECOND));
	}
	
	@Test
	public void testParse() {
		Date result = DateKit.parse("20120101", "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMdd");
		assertEquals(DateKit.get(2012, 1, 1), result);
		
		result = DateKit.parse("2012_01_01", "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMdd");
		assertNull(result);
		
		result = DateKit.parse("2012_01_01");
		assertNull(result);
		
		result = DateKit.parse(null, "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMdd");
		assertNull(result);
	}
	
	@Test
	public void testReformat() throws ParseException {
		String result = DateKit.reformat("05/30/2012", "MM/dd/yyyy", "yyyyMMdd");
		assertEquals("20120530", result);
	}
}
