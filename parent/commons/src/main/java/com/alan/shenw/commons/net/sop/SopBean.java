package com.alan.shenw.commons.net.sop;

import java.util.HashMap;



public class SopBean {

	private HashMap<String, String> pool;
	
	public SopBean() {
		pool = new HashMap<String, String>();
	}
	
	/**
	 * FLD:SCTUMC 50 S 0 0 0 0 NULL 0
	 * @return
	 */
	public int split(String line) {
		int startIndex = line.indexOf(":");
		int endIndex = line.indexOf(" ");
		String length = line.substring(startIndex, endIndex);
		return Integer.valueOf(length);
	}

	public void put(String id, String key, String value) {
		pool.put(getPoolKey(id, key), value);
	}
	
	public String get(String id, String key) {
		return pool.get(getPoolKey(id, key));
	}
	
	private String getPoolKey(String id, String key) {
		if (id != null) {
			key = id + '.' + key;
		}
		return key;
	}
}
