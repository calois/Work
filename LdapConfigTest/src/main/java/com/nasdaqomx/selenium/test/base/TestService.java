package com.nasdaqomx.selenium.test.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;

public class TestService {

	private String BASE_PACKAGE = "com.nasdaqomx.selenium.test.cases.";
	private String chromeDriver;
	private String driverType;
	private String defaultWait;
	private String presentWait;
	private String baseUrl;

	public String getChromeDriver() {
		return chromeDriver;
	}

	public void setChromeDriver(String chromeDriver) {
		this.chromeDriver = chromeDriver;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public String getDefaultWait() {
		return defaultWait;
	}

	public void setDefaultWait(String defaultWait) {
		this.defaultWait = defaultWait;
	}

	public String getPresentWait() {
		return presentWait;
	}

	public void setPresentWait(String presentWait) {
		this.presentWait = presentWait;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public TestResult run(String automationKey, TestData testData) {
		WebDriver webDriver = TestUtils.getWebDriver(driverType, chromeDriver,
				defaultWait);
		TestResult result = new TestResult();
		if (null == webDriver) {
			result.setStatus(TestResultStatus.INVALID);
			result.setMessage("Invalid Web Browser");
			return result;
		}
		try {
			TestObject testObject = new TestObject();
			testObject.setWebDriver(webDriver);
			testObject.setBaseUrl(baseUrl);
			testObject.setDefaultWait(Long.parseLong(defaultWait));
			testObject.setPresentWait(Long.parseLong(presentWait));
			String className = BASE_PACKAGE.concat(automationKey.substring(0,
					automationKey.lastIndexOf(".")));
			String methodName = automationKey.substring(automationKey
					.lastIndexOf(".") + 1);
			try {
				Class<?> clazz = Class.forName(className);
				AbstractTest test = (AbstractTest) clazz.newInstance();
				test.setTestData(testData);
				test.setTestObject(testObject);
				Method precondition = TestUtils.getTestBeforeMethod(test
						.getClass());
				// run precondition first
				if (null != precondition) {
					try {
						precondition.invoke(test);
					} catch (InvocationTargetException ie) {
						if (ie.getTargetException() instanceof TestException) {
							result.setStatus(TestResultStatus.BLOCKED);
							result.setMessage(TestUtils.getStackTrace(ie
									.getTargetException()));
							result.setScreenshot(TestUtils
									.takeScreenshot(webDriver));
							return result;
						} else {
							throw ie.getTargetException();
						}
					}
				}
				// run test steps
				Method method = clazz.getDeclaredMethod(methodName);
				try {
					method.invoke(test);
					test.checkForVerificationErrors();
				} catch (InvocationTargetException ie) {
					throw ie.getTargetException();
				} finally {
					Method afterMethod = TestUtils.getTestAfterMethod(test
							.getClass());
					if (null != afterMethod) {
						test.clearVerificationErrors();
						afterMethod.invoke(test);
						test.checkForVerificationErrors();
					}
				}
			} catch (TestException e) {
				result.setStatus(TestResultStatus.FAILED);
				result.setMessage(TestUtils.getStackTrace(e));
				result.setScreenshot(TestUtils.takeScreenshot(webDriver));
				return result;
			} catch (Throwable e) {
				e.printStackTrace();
				result.setStatus(TestResultStatus.INVALID);
				result.setMessage(TestUtils.getStackTrace(e));
				return result;
			}
			result.setStatus(TestResultStatus.PASSED);
			return result;
		} finally {
			if (null != webDriver) {
				webDriver.close();
			}
		}
	}
}
