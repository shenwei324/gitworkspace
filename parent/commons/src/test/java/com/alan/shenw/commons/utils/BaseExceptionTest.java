package com.alan.shenw.commons.utils;

import org.junit.Test;

import com.alan.shenw.commons.test.TestKit;
import com.alan.shenw.commons.test.annotation.CheckMethodWithoutTest;

@CheckMethodWithoutTest(src = BaseException.class)
public class BaseExceptionTest extends TestKit {

	@Test
	public void testFormat() {
		String out = BaseException.format("{}-{}-{}", new String[]{"2012", "11", "07"});
		assertEquals("2012-11-07", out);
		
		out = BaseException.format("Server will shutdown", new String[]{"a", "b", "c", "d"});
		assertEquals("Server will shutdown", out);
		
		out = BaseException.format("Server shutdown {}:{}", new String[]{});
		assertEquals("Server shutdown {}:{}", out);
		
		out = BaseException.format("Server shutdown {}:{}", new String[]{"14"});
		assertEquals("Server shutdown 14:{}", out);
		
		out = BaseException.format("Server shutdown {}:\\{\\}", new String[]{"14", "30"});
		assertEquals("Server shutdown 14:\\{\\}", out);
	}
}
