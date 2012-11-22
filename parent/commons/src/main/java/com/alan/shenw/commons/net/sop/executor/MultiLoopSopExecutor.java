package com.alan.shenw.commons.net.sop.executor;

import java.util.Map;

import com.alan.shenw.commons.net.sop.SopConnector;

public class MultiLoopSopExecutor extends AbstractSopExecutor {

	public MultiLoopSopExecutor(SopConnector conn, String requestId, Map<String, String> request, String responseId) {
		super(conn, requestId, request, responseId);
	}

}
