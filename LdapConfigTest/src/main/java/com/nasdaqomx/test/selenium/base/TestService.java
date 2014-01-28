package com.nasdaqomx.test.selenium.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String BASE_PACKAGE = "com.nasdaqomx.test.selenium.testcase.";

	private TestResult createTestResult(TestResultStatus status, String message,
			AbstractTest testCase) {
		TestResult result = new TestResult();
		result.setStatus(status);
		result.setMessage(message);
		if (null != testCase) {
			result.setScreenshotList(testCase.getScreenshotList());
			testCase.clearScreenshotList();
			testCase.clearVerificationErrors();
		}
		return result;
	}

	public TestResult run(TestConfig testConfig, String automationKey,
			TestData testData) {
		AbstractTest testCase = null;
		TestManager testManager = new TestManager(testConfig);
		try {
			String className = BASE_PACKAGE.concat(automationKey.substring(0,
					automationKey.lastIndexOf(".")));
			String methodName = automationKey.substring(automationKey
					.lastIndexOf(".") + 1);
			try {
				Class<?> clazz = Class.forName(className);
				testCase = (AbstractTest) clazz.newInstance();
				testCase.setTestData(testData);
				testCase.setTestManager(testManager);
				Method precondition = TestUtils.getTestBeforeMethod(testCase
						.getClass());
				// run precondition first
				if (null != precondition) {
					try {
						precondition.invoke(testCase);
					} catch (InvocationTargetException ie) {
						if (ie.getTargetException() instanceof TestException) {
							TestException e = (TestException) ie
									.getTargetException();
							return createTestResult(TestResultStatus.BLOCKED,
									TestUtils.getStackTrace(e), testCase);
						} else {
							throw ie.getTargetException();
						}
					}
				}
				// run test steps
				Method method = clazz.getDeclaredMethod(methodName);
				try {
					method.invoke(testCase);
					testCase.checkForVerificationErrors();
				} catch (InvocationTargetException ie) {
					throw ie.getTargetException();
				} finally {
					Method afterMethod = TestUtils.getTestAfterMethod(testCase
							.getClass());
					if (null != afterMethod) {
						afterMethod.invoke(testCase);
					}
				}
			} catch (TestException e) {
				return createTestResult(TestResultStatus.FAILED,
						TestUtils.getStackTrace(e), testCase);
			} catch (Throwable e) {
				e.printStackTrace();
				return createTestResult(TestResultStatus.INVALID,
						TestUtils.getStackTrace(e), testCase);
			}
			return createTestResult(TestResultStatus.PASSED, "", testCase);
		} finally {
			testManager.close();
		}
	}
}
