package com.nasdaqomx.selenium.test.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class TestManager {
	private TestObject testObject;
	private Map<TestApp, WebDriver> driverMap = new HashMap<>();

	public TestManager(TestObject testObject) {
		this.testObject = testObject;
	}

	public String getChromeDriver() {
		return testObject.getChromeDriver();
	}

	public DriverType getDriverType() {
		return testObject.getDriverType();
	}

	public long getImplicitWait() {
		return testObject.getImplicitWait();
	}

	public long getExplicitWait() {
		return testObject.getExplicitWait();
	}

	public String getBaseUrl(TestApp app) {
		return testObject.getAppObj().get(app).getBaseUrl();
	}

	public WebDriver getWebDriver(TestApp app) {
		if (driverMap.containsKey(app)) {
			return driverMap.get(app);
		} else {
			WebDriver webDriver = TestUtils.getWebDriver(
					testObject.getDriverType(), testObject.getChromeDriver(),
					testObject.getExplicitWait());
			driverMap.put(app, webDriver);
			return webDriver;
		}
	}

	public void close() {
		for (WebDriver d : driverMap.values()) {
			d.close();
		}
	}
}
