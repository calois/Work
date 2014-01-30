package com.nasdaqomx.test.selenium.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestManager {

	private TestConfig testConfig;
	private List<String> screenShotList = new ArrayList<>();
	private Map<Project, WebDriver> driverMap = new HashMap<>();

	public TestManager(TestConfig testConfig) {
		this.testConfig = testConfig;
	}

	public List<String> getScreenshotList() {
		return this.screenShotList;
	}

	public void clearScreenshotList() {
		this.screenShotList = new ArrayList<>();
	}

	public void takeScreenshot(Project project) {
		this.screenShotList.add(takeScreenshot(driverMap.get(project)));
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
			WebDriver webDriver = getWebDriver();
			driverMap.put(project, webDriver);
			return webDriver;
		}
	}

	private WebDriver getWebDriver() {
		DesiredCapabilities capabilities;
		WebDriver driver;
		switch (testConfig.getDriverType()) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver",
					testConfig.getChromeDriver());
			capabilities = DesiredCapabilities.chrome();
			driver = new ChromeDriver(capabilities);
			driver.manage()
					.timeouts()
					.implicitlyWait(testConfig.getImplicitWait(),
							TimeUnit.SECONDS);
			return driver;
		case FIREFOX:
			capabilities = DesiredCapabilities.firefox();
			driver = new FirefoxDriver(capabilities);
			driver.manage()
					.timeouts()
					.implicitlyWait(testConfig.getImplicitWait(),
							TimeUnit.SECONDS);
			return driver;
		default:
			return null;
		}
	}

	private String takeScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	public void close() {
		for (WebDriver d : driverMap.values()) {
			d.close();
			d.quit();
		}
	}
}
