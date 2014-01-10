package com.nasdaqomx.selenium.test.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String BASE_PACKAGE = "com.nasdaqomx.selenium.test.cases.";

	private String chromeDriver;

	public void setChromeDriver(String chromeDriver) {
		this.chromeDriver = chromeDriver;
	}

	public TestResult run(TestObject testObject, String automationKey,
			TestData testData) {
		TestResult result = new TestResult();
		TestManager testManager = new TestManager(testObject);
		if (TestUtils.isEmpty(testObject.getChromeDriver())) {
			testObject.setChromeDriver(this.chromeDriver);
		}
		try {
			String className = BASE_PACKAGE.concat(automationKey.substring(0,
					automationKey.lastIndexOf(".")));
			String methodName = automationKey.substring(automationKey
					.lastIndexOf(".") + 1);
			try {
				Class<?> clazz = Class.forName(className);
				AbstractTest test = (AbstractTest) clazz.newInstance();
				test.setTestData(testData);
				test.setTestManager(testManager);
				Method precondition = TestUtils.getTestBeforeMethod(test
						.getClass());
				// run precondition first
				if (null != precondition) {
					try {
						precondition.invoke(test);
					} catch (InvocationTargetException ie) {
						if (ie.getTargetException() instanceof TestException) {
							TestException e = (TestException) ie
									.getTargetException();
							result.setStatus(TestResultStatus.BLOCKED);
							result.setMessage(TestUtils.getStackTrace(e));
							result.setScreenshot(TestUtils
									.takeScreenshot(testManager.getWebDriver(e
											.getTestApp())));
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
				result.setScreenshot(TestUtils.takeScreenshot(testManager
						.getWebDriver(e.getTestApp())));
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
			testManager.close();
		}
	}
}
