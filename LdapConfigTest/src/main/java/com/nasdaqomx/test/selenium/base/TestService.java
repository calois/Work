package com.nasdaqomx.test.selenium.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String BASE_PACKAGE = "com.nasdaqomx.test.selenium.testcase.";

	public TestResult run(TestConfig testConfig, String automationKey,
			TestData testData) {
		TestResult result = new TestResult();
		TestManager testManager = new TestManager(testConfig);
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
											.getProject())));
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
					if (ie.getTargetException() instanceof TestException) {
						TestException e = (TestException) ie
								.getTargetException();
						result.setStatus(TestResultStatus.FAILED);
						result.setMessage(TestUtils.getStackTrace(e));
						result.setScreenshot(TestUtils
								.takeScreenshot(testManager.getWebDriver(e
										.getProject())));
						return result;
					} else {
						throw ie.getTargetException();
					}
				} finally {
					Method afterMethod = TestUtils.getTestAfterMethod(test
							.getClass());
					if (null != afterMethod) {
						test.clearVerificationErrors();
						afterMethod.invoke(test);
						test.checkForVerificationErrors();
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
				result.setStatus(TestResultStatus.INVALID);
				result.setMessage(TestUtils.getStackTrace(e));
				return result;
			}
			result.setStatus(TestResultStatus.PASSED);
			result.setMessage("");
			return result;
		} finally {
			testManager.close();
		}
	}
}
