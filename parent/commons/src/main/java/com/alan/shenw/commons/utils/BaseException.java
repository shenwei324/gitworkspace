package com.alan.shenw.commons.utils;

public class BaseException extends Exception {

	private static final long serialVersionUID = 8466100894180359717L;

	public BaseException() {
		super();
	}

	public BaseException(Throwable cause, String message) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
	
	public BaseException(String message, String... args) {
		super(format(message, args));
	}

	public BaseException(Throwable cause, String message, String... args) {
		super(format(message, args), cause);
	}
	
	protected static String format(String message, String[] args) {
		if (args.length > 0 && message.indexOf("{}") >= 0) {
			StringBuilder builder = new StringBuilder();
			int index = 0;
			int pos = 0;
			int i = 0;
			while ((index = message.indexOf("{}", pos)) >= 0 && i < args.length) {
				builder.append(message, pos, index);
				builder.append(args[i]);
				pos = index + 2;
				i++;
			}
			if (pos < message.length() - 1) {
				builder.append(message, pos, message.length());
			}
			return builder.toString();
		} else {
			return message;
		}
	}
}
