package com.nasdaqomx.selenium.test.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chromeDriver;
	private DriverType driverType;
	private long implicitWait = 30;
	private long explicitWait = 10;
	private Map<TestApp, TestAppObject> appObj = new HashMap<>();

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

	public long getImplicitWait() {
		return implicitWait;
	}

	public void setImplicitWait(long implicitWait) {
		this.implicitWait = implicitWait;
	}

	public long getExplicitWait() {
		return explicitWait;
	}

	public void setExplicitWait(long explicitWait) {
		this.explicitWait = explicitWait;
	}

	public Map<TestApp, TestAppObject> getAppObj() {
		return appObj;
	}

	public void setAppObj(Map<TestApp, TestAppObject> appObj) {
		this.appObj = appObj;
	}
}
