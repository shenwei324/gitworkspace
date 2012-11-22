package com.alan.shenw.commons.net.sop.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

import com.alan.shenw.commons.net.sop.SopBean;
import com.alan.shenw.commons.net.sop.SopConnector;
import com.alan.shenw.commons.net.sop.SopException;

public abstract class AbstractSopExecutor implements Callable<Map<String, Object>> {

	public static final String SOP_HEAD = "SOPHEAD";
	public static final String SOP_BODY = "SOPBODY";
	
	private SopConnector conn;
	private String requestId;
	private String responseId;
	private Map<String, String> request;

	public AbstractSopExecutor(SopConnector conn, String requestId, Map<String, String> request, String responseId) {
		this.conn = conn;
		this.requestId = requestId;
		this.responseId = responseId;
		this.request = request;
	}

	@Override
	public Map<String, Object> call() throws Exception {
		SopBean bean = new SopBean();
		createSopRequest(bean);
		createSopResponse(bean);
		return null;
	}

	protected SopBean createSopRequest(SopBean bean) throws IOException, SopException {
		createSopHeader(bean);
		createSopBody(bean);
		return bean;
	}
	
	protected void createSopResponse(SopBean bean) {
		// TODO Auto-generated method stub
	}

	protected void createSopHeader(SopBean bean) throws IOException, SopException {
		// TODO
		File mapFile = new File(conn.getMapFileHome(), requestId + ".sop");
		BufferedReader reader = new BufferedReader(new FileReader(mapFile));
		String line = reader.readLine();
		if (StringUtils.isEmpty(line) || (!SOP_HEAD.equals(line) && !SOP_BODY.equals(line))) {
			throw new SopException("Can not parse this map file [");
		} else {
			
		}
	}

	protected void createSopBody(SopBean bean) {
		// TODO
		Iterator<Entry<String, String>> iter = request.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			bean.put(requestId, entry.getKey(), entry.getValue());
		}
	}

	protected int getSopFileType(String requestId) {
		// TODO
		return -1;
	}
}
