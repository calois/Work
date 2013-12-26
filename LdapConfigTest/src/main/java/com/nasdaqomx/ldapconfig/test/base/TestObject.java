package com.nasdaqomx.ldapconfig.test.base;

import org.openqa.selenium.WebDriver;

public class TestObject {
	private WebDriver webDriver;
	private long presentWait;
	private long defaultWait;
	private String baseUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public long getPresentWait() {
		return presentWait;
	}

	public void setPresentWait(long presentWait) {
		this.presentWait = presentWait;
	}

	public long getDefaultWait() {
		return defaultWait;
	}

	public void setDefaultWait(long defaultWait) {
		this.defaultWait = defaultWait;
	}
}
