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

	public TestResult test(DriverType driverType, String testKey,
			TestData testData) {
		WebDriver webDriver = TestUtils.getWebDriver(driverType, prop);
		TestResult result = new TestResult();
		if (webDriver == null) {
			result.setStatus(TestResultStatus.INVALID);
			result.setMessage("Invalid driver parameter.");
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
			String testCase = testKey.substring(testKey.lastIndexOf(".") + 1);
			try {
				Class<?> clazz = Class.forName(className);
				AbstractTest test = (AbstractTest) clazz.newInstance();
				test.setTestData(testData);
				test.setTestObject(testObject);
				Method method = clazz.getDeclaredMethod(testCase);
				try {
					method.invoke(test);
				} catch (InvocationTargetException ie) {
					if (ie.getTargetException() instanceof TestException) {
						result.setStatus(TestResultStatus.FAIL);
						result.setMessage(TestUtils.getStackTrace(ie
								.getTargetException()));
						return result;
					} else {
						throw ie.getTargetException();
					}
				}
			} catch (Throwable e) {
				result.setStatus(TestResultStatus.INVALID);
				result.setMessage(TestUtils.getStackTrace(e));
				return result;
			}
			return result;
		} finally {
			webDriver.close();
		}
	}

}
