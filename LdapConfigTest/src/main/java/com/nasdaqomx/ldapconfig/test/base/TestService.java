package com.nasdaqomx.ldapconfig.test.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class TestService {

	private Properties prop;

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public Properties getProp() {
		return this.prop;
	}

	public TestResult run(DriverType driverType, String automationKey,
			TestData testData) {
		WebDriver webDriver = TestUtils.getWebDriver(driverType, prop);
		TestResult result = new TestResult();
		if (null == webDriver) {
			result.setStatus(TestResultStatus.INVALID);
			result.setMessage("Invalid Web Browser");
			return result;
		}
		try {
			TestObject testObject = new TestObject();
			testObject.setWebDriver(webDriver);
			testObject.setBaseUrl(prop.getProperty("baseUrl"));
			testObject.setDefaultWait(Long.parseLong(prop
					.getProperty("defaultWait")));
			testObject.setPresentWait(Long.parseLong(prop
					.getProperty("presentWait")));
			String className = automationKey.substring(0,
					automationKey.lastIndexOf("."));
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
