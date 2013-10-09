package com.omkbron.sendemail.util;

public class SendMailException extends RuntimeException {

	private static final long serialVersionUID = 9201823389257345952L;

	public SendMailException() {
		super();
	}

	public SendMailException(String message, Throwable cause) {
		super(message, cause);
	}

	public SendMailException(String message) {
		super(message);
	}

	public SendMailException(Throwable cause) {
		super(cause);
	}

}
