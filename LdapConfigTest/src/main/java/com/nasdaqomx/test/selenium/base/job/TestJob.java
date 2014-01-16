package com.nasdaqomx.test.selenium.base.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nasdaqomx.test.selenium.base.TestConfig;
import com.nasdaqomx.test.selenium.base.TestData;
import com.nasdaqomx.test.selenium.base.TestResult;

public class TestJob {
	private String id;
	private TestConfig testConfig;
	private String automationKey;
	private TestStatus status;

	@JsonIgnore
	private TestData testData;
	@JsonIgnore
	private TestJobInnerCallback callback;
	private TestResult result;

	public TestStatus getStatus() {
		return status;
	}

	public void setStatus(TestStatus status) {
		this.status = status;
	}

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
