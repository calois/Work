package com.nasdaqomx.selenium.test.base;

public class TestConfig {
	private String chromeDriver;
	private DriverType driverType;
	private Long implicitWait;
	private Long explicitWait;
	private String baseUrl;

	public String getChromeDriver() {
		return chromeDriver;
	}

	public void setChromeDriver(String chromeDriver) {
		this.chromeDriver = chromeDriver;
	}

	public DriverType getDriverType() {
		return driverType;
	}

	public void setDriverType(DriverType driverType) {
		this.driverType = driverType;
	}

	public Long getImplicitWait() {
		return implicitWait;
	}

	public void setImplicitWait(Long implicitWait) {
		this.implicitWait = implicitWait;
	}

	public Long getExplicitWait() {
		return explicitWait;
	}

	public void setExplicitWait(Long explicitWait) {
		this.explicitWait = explicitWait;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}
