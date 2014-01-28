package com.nasdaqomx.test.selenium.base;

import java.util.List;

public class TestResult {

	private TestResultStatus status;
	private List<String> screenshotList;
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

	public List<String> getScreenshotList() {
		return screenshotList;
	}

	public void setScreenshotList(List<String> screenshotList) {
		this.screenshotList = screenshotList;
	}
}
