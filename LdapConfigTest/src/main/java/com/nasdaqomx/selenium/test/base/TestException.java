package com.nasdaqomx.selenium.test.base;

public class TestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TestApp testApp;

	public TestApp getTestApp() {
		return testApp;
	}

	public TestException(TestApp testApp, String message) {
		super(message);
		this.testApp = testApp;
	}

	public TestException(TestApp testApp, String message, Throwable cause) {
		super(message, cause);
		this.testApp = testApp;
	}

	public TestException(TestApp testApp, Throwable cause) {
		super(cause);
		this.testApp = testApp;
	}
}
