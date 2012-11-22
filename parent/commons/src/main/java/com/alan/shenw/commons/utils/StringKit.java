package com.alan.shenw.commons.utils;



public class StringKit {

	public static void mix(String express, String... args) {
		String[] fields = express.split("\\{\\}");
		if (fields.length - args.length > 1) {
			throw new IllegalArgumentException("Argument is not enough. ");
		}
		
		StringBuilder buffer = new StringBuilder();
		buffer.append(fields[0]);
		
	}
}
