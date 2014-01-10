package com.nasdaqomx.selenium.test.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class TestManager {
	private TestConfig testConfig;
	private Map<Project, WebDriver> driverMap = new HashMap<>();

	public TestManager(TestConfig testConfig) {
		this.testConfig = testConfig;
	}

	public String getChromeDriver() {
		return testConfig.getChromeDriver();
	}

	public DriverType getDriverType() {
		return testConfig.getDriverType();
	}

	public long getImplicitWait() {
		return testConfig.getImplicitWait();
	}

	public long getExplicitWait() {
		return testConfig.getExplicitWait();
	}

	public String getBaseUrl(Project project) {
		return testConfig.getProjectConfigMap().get(project).getBaseUrl();
	}

	public WebDriver getWebDriver(Project project) {
		if (driverMap.containsKey(project)) {
			return driverMap.get(project);
		} else {
			WebDriver webDriver = TestUtils.getWebDriver(testConfig);
			driverMap.put(project, webDriver);
			return webDriver;
		}
	}

	public void close() {
		for (WebDriver d : driverMap.values()) {
			d.close();
		}
	}
}
