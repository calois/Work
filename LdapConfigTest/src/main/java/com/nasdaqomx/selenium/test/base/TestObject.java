package com.nasdaqomx.selenium.test.base;

import org.openqa.selenium.WebDriver;

public class TestObject {
	private WebDriver webDriver;
	private long explicitWait;
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

	public long getExplicitWait() {
		return explicitWait;
	}

	public void setExplicitWait(long explicitWait) {
		this.explicitWait = explicitWait;
	}
}
