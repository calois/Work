package com.nasdaqomx.selenium.test.base.job;

import com.nasdaqomx.selenium.test.base.TestConfig;
import com.nasdaqomx.selenium.test.base.TestData;

class TestJob {
	private String id;
	private TestConfig testConfig;
	private String automationKey;
	private TestData testData;
	private TestJobInnerCallback callback;

	public TestJobInnerCallback getCallback() {
		return callback;
	}

	public void setCallback(TestJobInnerCallback callback) {
		this.callback = callback;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TestConfig getTestConfig() {
		return testConfig;
	}

	public void setTestConfig(TestConfig testConfig) {
		this.testConfig = testConfig;
	}

	public String getAutomationKey() {
		return automationKey;
	}

	public void setAutomationKey(String automationKey) {
		this.automationKey = automationKey;
	}

	public TestData getTestData() {
		return testData;
	}

	public void setTestData(TestData testData) {
		this.testData = testData;
	}
}
