package com.nasdaqomx.ldapconfig.test.base;

import java.util.Properties;

public class TestData {
	private Properties data;
	private Properties expect;

	public TestData(Properties data, Properties expect) {
		this.data = data;
		this.expect = expect;
	}

	public String getData(String key) {
		return data.getProperty(key);
	}

	public String getExpect(String key) {
		return expect.getProperty(key);
	}
}
