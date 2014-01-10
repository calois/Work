package com.nasdaqomx.selenium.test.base;

import java.io.Serializable;

public class TestAppObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String baseUrl;

	private TestApp app;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public TestApp getApp() {
		return app;
	}

	public void setApp(TestApp app) {
		this.app = app;
	}
}
