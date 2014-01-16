package com.nasdaqomx.test.selenium.base.job;

import com.nasdaqomx.test.selenium.base.TestConfig;
import com.nasdaqomx.test.selenium.base.TestData;
import com.nasdaqomx.test.selenium.base.TestResult;

class TestJob {
	private String id;
	private TestConfig testConfig;
	private String automationKey;
	private TestData testData;
	private TestJobInnerCallback callback;
	private TestResult result;

	public TestResult getResult() {
		return result;
	}

	public void setResult(TestResult result) {
		this.result = result;
	}

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
