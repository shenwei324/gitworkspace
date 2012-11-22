package com.alan.shenw.commons.utils;

import java.util.UUID;

public class SerialKit {

	public static String getSerialNumber() {
		char[] uuid = getUUID().toCharArray();
		StringBuilder builder = new StringBuilder();
		builder.append(uuid, 0, 8);
		builder.append(uuid, 9, 4);
		builder.append(uuid, 14, 4);
		builder.append(uuid, 19, 4);
		builder.append(uuid, 24, 12);
		return builder.toString();
	}
	
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
