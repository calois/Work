package com.nasdaqomx.test.selenium.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	private String chromeDriver;
	private DriverType driverType;
	private long implicitWait = 30;
	private long explicitWait = 10;
	private Map<Project, ProjectConfig> projectConfigMap = new HashMap<>();

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

	public Map<Project, ProjectConfig> getProjectConfigMap() {
		return projectConfigMap;
	}

	public void setProjectConfigMap(Map<Project, ProjectConfig> projectConfigMap) {
		this.projectConfigMap = projectConfigMap;
	}
}
