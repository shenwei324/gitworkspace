package com.alan.shenw.commons.net.sop;

public class SopException extends Exception {

	private static final long serialVersionUID = -8875864907566691415L;

	public SopException(String msg) {
		super(msg);
	}
	
	public SopException(Throwable e) {
		super(e);
	}

}
