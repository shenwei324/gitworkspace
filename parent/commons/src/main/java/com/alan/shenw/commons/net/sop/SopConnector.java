package com.alan.shenw.commons.net.sop;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.alan.shenw.commons.net.sop.executor.LoopSopExecutor;
import com.alan.shenw.commons.net.sop.executor.MultiLoopSopExecutor;
import com.alan.shenw.commons.net.sop.executor.SimpleSopExecutor;

public class SopConnector {

	/**
	 * 返回数据简单数据的SOP类型
	 */
	public static final int SOP_TYPE_SIMPLE_RESP = 0;
	/**
	 * 返回单个循环体数据的SOP类型
	 */
	public static final int SOP_TYPE_LOOP_RESP = 1;
	/**
	 * 返回多个循环体数据的SOP类型
	 */
	public static final int SOP_TYPE_MULTI_LOOP_RESP = 2;
	/**
	 * 需要返回的字段
	 */
	public static final String RESPONSE_FROM_FIELDS = "RESPONSE_FROM_FIELDS";
	/**
	 * 返回的MapFile名
	 */
	public static final String RESPONSE_FROM_ID = "RESPONSE_FROM_ID";
	/**
	 * 请求的值
	 */
	public static final String REQUEST_FROM_VALUE = "REQUEST_FROM_VALUE";
	/**
	 * 请求的MapFile名
	 */
	public static final String REQUEST_FROM_ID = "REQUEST_FROM_ID";

	private SopBean bean;
	private String hostname;
	private int port;
	private String mapFileHome;

	public SopConnector(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public String getMapFileHome() {
		return mapFileHome;
	}

	public void setMapFileHome(String mapFileHome) {
		this.mapFileHome = mapFileHome;
	}

	public Map<String, Object> exec(int sopType, String requestId, Map<String, String> requestMap, String responseId, List<String> responseFields) throws SopException {
		Callable<Map<String, Object>> task = call(sopType, requestId, requestMap, responseId, responseFields);
		try {
			return task.call();
		} catch (Exception e) {
			throw new SopException(e);
		}
	}

	public Callable<Map<String, Object>> call(int sopType, String requestId, Map<String, String> requestMap, String responseId, List<String> responseFields) {
		Callable<Map<String, Object>> call;
		switch (sopType) {
		case SOP_TYPE_SIMPLE_RESP:
			call = new SimpleSopExecutor(this, requestId, requestMap, responseId);
			break;
		case SOP_TYPE_LOOP_RESP:
			call = new LoopSopExecutor(this, requestId, requestMap, responseId);
			break;
		case SOP_TYPE_MULTI_LOOP_RESP:
			call = new MultiLoopSopExecutor(this, requestId, requestMap, responseId);
			break;
		default:
			call = null;
		}
		return call;
	}
}
