package com.nasdaqomx.selenium.test.base;


public class TestResult {

	private TestResultStatus status;
	private String screenshot;
	private String message;

	@Override
	public String toString() {
		return "TestResult [status=" + status + ", message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TestResultStatus getStatus() {
		return status;
	}

	public void setStatus(TestResultStatus status) {
		this.status = status;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}
}
