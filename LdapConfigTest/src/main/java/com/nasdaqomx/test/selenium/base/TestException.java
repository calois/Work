package com.nasdaqomx.test.selenium.base;

public class TestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TestException(String message) {
		super(message);
	}

	public TestException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestException(Throwable cause) {
		super(cause);
	}

}
