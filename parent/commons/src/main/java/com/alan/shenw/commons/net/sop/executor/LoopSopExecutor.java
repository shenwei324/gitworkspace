package com.alan.shenw.commons.net.sop.executor;

import java.util.Map;

import com.alan.shenw.commons.net.sop.SopConnector;

public class LoopSopExecutor extends AbstractSopExecutor {

	public LoopSopExecutor(SopConnector conn, String requestId, Map<String, String> request, String responseId) {
		super(conn, requestId, request, responseId);
	}
}
