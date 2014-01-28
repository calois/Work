package com.nasdaqomx.test.selenium.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String BASE_PACKAGE = "com.nasdaqomx.test.selenium.testcase.";

	private TestResult createTestResult(TestResultStatus status,
			String message, TestManager testManager) {
		TestResult result = new TestResult();
		result.setStatus(status);
		result.setMessage(message);
		result.setScreenshotList(testManager.getScreenshotList());
		testManager.clearScreenshotList();
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
							e.printStackTrace();
							return createTestResult(TestResultStatus.BLOCKED,
									TestUtils.getStackTrace(e), testManager);
						} else {
							throw ie.getTargetException();
						}
					}
				}
				// run test steps
				try {
					Method method = clazz.getDeclaredMethod(methodName);
					method.invoke(testCase);
					testCase.checkForVerificationErrors();
				} catch (InvocationTargetException ie) {
					throw ie.getTargetException();
				}
			} catch (TestException e) {
				e.printStackTrace();
				return createTestResult(TestResultStatus.FAILED,
						TestUtils.getStackTrace(e), testManager);
			} catch (Throwable e) {
				e.printStackTrace();
				return createTestResult(TestResultStatus.INVALID,
						TestUtils.getStackTrace(e), testManager);
			} finally {
				Method afterMethod = TestUtils.getTestAfterMethod(testCase
						.getClass());
				if (null != afterMethod) {
					try {
						afterMethod.invoke(testCase);
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
			return createTestResult(TestResultStatus.PASSED, "", testManager);
		} finally {
			testManager.close();
		}
	}
}
