package com.nasdaqomx.ldapconfig.test.base;

import java.util.Properties;

public class TestData {
	private Properties inputData;
	private Properties outputData;

	public TestData(Properties inputData, Properties outputData) {
		this.inputData = inputData;
		this.outputData = outputData;
	}

	public String getInputData(String key) {
		return inputData.getProperty(key);
	}

	public String getOutputData(String key) {
		return outputData.getProperty(key);
	}
}
