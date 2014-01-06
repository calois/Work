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

	public TestResult test(DriverType driverType, String testKey,
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
			String className = testKey.substring(0, testKey.lastIndexOf("."));
			String methodName = testKey.substring(testKey.lastIndexOf(".") + 1);
			try {
				Class<?> clazz = Class.forName(className);
				AbstractTest test = (AbstractTest) clazz.newInstance();
				test.setTestData(testData);
				test.setTestObject(testObject);

				Method beforeMethod = TestUtils.getTestBeforeMethod(test
						.getClass());
				if (null != beforeMethod) {
					try {
						beforeMethod.invoke(test);
						test.checkForVerificationErrors();
						test.clearVerificationErrors();
					} catch (Exception e) {
						result.setStatus(TestResultStatus.BLOCKED);
						result.setMessage(TestUtils.getStackTrace(e));
						return result;
					}
				}

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
						try {
							test.clearVerificationErrors();
							afterMethod.invoke(test);
							test.checkForVerificationErrors();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (TestException e) {
				//Try to capture the screen here.
				result.setStatus(TestResultStatus.FAILED);
				result.setMessage(TestUtils.getStackTrace(e));
				return result;
			} catch (Throwable e) {
				e.printStackTrace();
				result.setStatus(TestResultStatus.INVALID);
				result.setMessage(TestUtils.getStackTrace(e));
				return result;
			}
			//No exception means passed
			result.setStatus(TestResultStatus.PASSED);
			return result;
		} finally {
			webDriver.close();
		}
	}
}
