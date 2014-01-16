package com.nasdaqomx.test.selenium.base;

import java.util.regex.Pattern;

import com.nasdaqomx.test.selenium.base.page.AbstractPageObject;

public abstract class AbstractTest {

	private TestManager testManager;
	private TestData testData;

	private StringBuilder verificationErrors = new StringBuilder();

	public Project getProject() {
		return TestUtils.getTestProject(this.getClass());
	}

	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}

	protected <T extends AbstractPageObject> T createPageObject(Class<T> clazz) {
		try {
			T o = clazz.getDeclaredConstructor(TestManager.class).newInstance(
					testManager);
			return o;
		} catch (Exception e) {
			throw new TestException(TestUtils.getTestProject(clazz),
					"Fail to create page object: " + clazz.getSimpleName(), e);
		}
	}

	/** Like assertTrue, but fails at the end of the test (during tearDown) */
	public void verifyTrue(boolean b) {
		try {
			assertTrue(b);
		} catch (TestException e) {
			verificationErrors.append(TestUtils.getStackTrace(e));
		}
	}

	public void verifyTrue(String msg, boolean b) {
		try {
			assertTrue(msg, b);
		} catch (TestException e) {
			verificationErrors.append(TestUtils.getStackTrace(e));
		}
	}

	/** Like assertFalse, but fails at the end of the test (during tearDown) */
	public void verifyFalse(boolean b) {
		try {
			assertFalse(b);
		} catch (TestException e) {
			verificationErrors.append(TestUtils.getStackTrace(e));
		}
	}

	/** Like assertEquals, but fails at the end of the test (during tearDown) */
	public void verifyEquals(Object expected, Object actual) {
		try {
			assertEquals(expected, actual);
		} catch (TestException e) {
			verificationErrors.append(TestUtils.getStackTrace(e));
		}
	}

	/** Like assertEquals, but fails at the end of the test (during tearDown) */
	public void verifyEquals(boolean expected, boolean actual) {
		try {
			assertEquals(Boolean.valueOf(expected), Boolean.valueOf(actual));
		} catch (TestException e) {
			verificationErrors.append(TestUtils.getStackTrace(e));
		}
	}

	public void assertEquals(Object expected, Object actual) {
		assertEquals(this.getProject(), expected, actual);
	}

	/** Like JUnit's Assert.assertEquals, but knows how to compare string arrays */
	public static void assertEquals(Project testApp, Object expected,
			Object actual) {
		if (expected == null) {
			assertTrue(testApp, "Expected: \"" + expected + "\" but actual: \""
					+ actual + "\"", actual == null);
		} else if (expected instanceof String && actual instanceof String) {
			assertEquals(testApp, (String) expected, (String) actual);
		} else if (expected instanceof String && actual instanceof String[]) {
			assertEquals(testApp, (String) expected, (String[]) actual);
		} else if (expected instanceof String && actual instanceof Number) {
			assertEquals(testApp, (String) expected, actual.toString());
		} else if (expected instanceof Number && actual instanceof String) {
			assertEquals(testApp, expected.toString(), (String) actual);
		} else if (expected instanceof String[] && actual instanceof String[]) {
			assertEquals(testApp, (String[]) expected, (String[]) actual);
		} else {
			assertTrue(testApp, "Expected: \"" + expected + "\" but actual: \""
					+ actual + "\" ", expected.equals(actual));
		}
	}

	public static String join(String[] sa, char c) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < sa.length; j++) {
			sb.append(sa[j]);
			if (j < sa.length - 1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * Like JUnit's Assert.assertEquals, but handles "regexp:" strings like HTML
	 * Selenese
	 */
	public void assertEquals(String expected, String actual) {
		assertEquals(getProject(), expected, actual);
	}

	public static void assertEquals(Project testApp, String expected,
			String actual) {
		assertTrue(testApp, "Expected: \"" + expected + "\" but actual: \""
				+ actual + "\"", seleniumEquals(expected, actual));
	}

	/**
	 * Like JUnit's Assert.assertEquals, but joins the string array with commas,
	 * and handles "regexp:" strings like HTML Selenese
	 */
	public void assertEquals(String expected, String[] actual) {
		assertEquals(getProject(), expected, actual);
	}

	public static void assertEquals(Project testApp, String expected,
			String[] actual) {
		assertEquals(testApp, expected, join(actual, ','));
	}

	public void assertEquals(String[] expected, String[] actual) {
		assertEquals(getProject(), expected, actual);
	}

	public static void assertEquals(Project testApp, String[] expected,
			String[] actual) {
		assertEquals(testApp, join(expected, ','), join(actual, ','));
	}

	/**
	 * Asserts that two string arrays have identical string contents (fails at
	 * the end of the test, during tearDown)
	 */
	public void verifyEquals(String[] expected, String[] actual) {
		String comparisonDumpIfNotEqual = verifyEqualsAndReturnComparisonDumpIfNot(
				expected, actual);
		if (comparisonDumpIfNotEqual != null) {
			verificationErrors.append(comparisonDumpIfNotEqual);
		}
	}

	/** Like assertNotEquals, but fails at the end of the test (during tearDown) */
	public void verifyNotEquals(Object expected, Object actual) {
		try {
			assertNotEquals(expected, actual);
		} catch (TestException e) {
			verificationErrors.append(TestUtils.getStackTrace(e));
		}
	}

	/** Like assertNotEquals, but fails at the end of the test (during tearDown) */
	public void verifyNotEquals(boolean expected, boolean actual) {
		try {
			assertNotEquals(Boolean.valueOf(expected), Boolean.valueOf(actual));
		} catch (TestException e) {
			verificationErrors.append(TestUtils.getStackTrace(e));
		}
	}

	/** Asserts that two objects are not the same (compares using .equals()) */
	public void assertNotEquals(Object expected, Object actual) {
		assertNotEquals(getProject(), expected, actual);
	}

	public static void assertNotEquals(Project testApp, Object expected,
			Object actual) {
		if (expected == null) {
			assertFalse(testApp, "did not expect null to be null",
					actual == null);
		} else if (expected.equals(actual)) {
			fail(testApp, "did not expect (" + actual + ") to be equal to ("
					+ expected + ")");
		}
	}

	public static void fail(Project app, String message) {
		throw new TestException(app, message);
	}

	public void fail(String message) {
		fail(getProject(), message);
	}

	public void assertTrue(String message, boolean condition) {
		assertTrue(getProject(), message, condition);
	}

	public static void assertTrue(Project testApp, String message,
			boolean condition) {
		if (!condition)
			fail(testApp, message);
	}

	public void assertTrue(boolean condition) {
		assertTrue(getProject(), condition);
	}

	public static void assertTrue(Project testApp, boolean condition) {
		assertTrue(testApp, null, condition);
	}

	public void assertFalse(String message, boolean condition) {
		assertFalse(getProject(), message, condition);
	}

	public static void assertFalse(Project app, String message,
			boolean condition) {
		assertTrue(app, message, !condition);
	}

	public void assertFalse(boolean condition) {
		assertFalse(getProject(), condition);
	}

	public static void assertFalse(Project testApp, boolean condition) {
		assertTrue(testApp, null, !condition);
	}

	/** Asserts that two booleans are not the same */
	public void assertNotEquals(boolean expected, boolean actual) {
		assertNotEquals(Boolean.valueOf(expected), Boolean.valueOf(actual));
	}

	public static void assertNotEquals(Project testApp, boolean expected,
			boolean actual) {
		assertNotEquals(testApp, Boolean.valueOf(expected),
				Boolean.valueOf(actual));
	}

	/** Sleeps for the specified number of milliseconds */
	public void pause(int millisecs) {
		try {
			Thread.sleep(millisecs);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Asserts that there were no verification errors during the current test,
	 * failing immediately if any are found
	 */
	public void checkForVerificationErrors() {
		String verificationErrorString = verificationErrors.toString();
		clearVerificationErrors();
		if (!"".equals(verificationErrorString)) {
			fail(this.getProject(), verificationErrorString);
		}
	}

	/** Clears out the list of verification errors */
	public void clearVerificationErrors() {
		verificationErrors = new StringBuilder();
	}

	/**
	 * Compares two strings, but handles "regexp:" strings like HTML Selenese
	 * 
	 * @param expectedPattern
	 * @param actual
	 * @return true if actual matches the expectedPattern, or false otherwise
	 */
	public static boolean seleniumEquals(String expectedPattern, String actual) {
		if (expectedPattern == null || actual == null) {
			return expectedPattern == null && actual == null;
		}
		if (actual.startsWith("regexp:") || actual.startsWith("regex:")
				|| actual.startsWith("regexpi:")
				|| actual.startsWith("regexi:")) {
			// swap 'em
			String tmp = actual;
			actual = expectedPattern;
			expectedPattern = tmp;
		}
		Boolean b;
		b = handleRegex("regexp:", expectedPattern, actual, 0);
		if (b != null) {
			return b.booleanValue();
		}
		b = handleRegex("regex:", expectedPattern, actual, 0);
		if (b != null) {
			return b.booleanValue();
		}
		b = handleRegex("regexpi:", expectedPattern, actual,
				Pattern.CASE_INSENSITIVE);
		if (b != null) {
			return b.booleanValue();
		}
		b = handleRegex("regexi:", expectedPattern, actual,
				Pattern.CASE_INSENSITIVE);
		if (b != null) {
			return b.booleanValue();
		}

		if (expectedPattern.startsWith("exact:")) {
			String expectedExact = expectedPattern.replaceFirst("exact:", "");
			if (!expectedExact.equals(actual)) {
				System.out.println("expected " + actual + " to match "
						+ expectedPattern);
				return false;
			}
			return true;
		}

		String expectedGlob = expectedPattern.replaceFirst("glob:", "");
		expectedGlob = expectedGlob.replaceAll(
				"([\\]\\[\\\\{\\}$\\(\\)\\|\\^\\+.])", "\\\\$1");

		expectedGlob = expectedGlob.replaceAll("\\*", ".*");
		expectedGlob = expectedGlob.replaceAll("\\?", ".");
		if (!Pattern.compile(expectedGlob, Pattern.DOTALL).matcher(actual)
				.matches()) {
			return false;
		}
		return true;
	}

	private static Boolean handleRegex(String prefix, String expectedPattern,
			String actual, int flags) {
		if (expectedPattern.startsWith(prefix)) {
			String expectedRegEx = expectedPattern.replaceFirst(prefix, ".*")
					+ ".*";
			Pattern p = Pattern.compile(expectedRegEx, flags);
			if (!p.matcher(actual).matches()) {
				System.out.println("expected " + actual + " to match regexp "
						+ expectedPattern);
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}
		return null;
	}

	/**
	 * Compares two objects, but handles "regexp:" strings like HTML Selenese
	 * 
	 * @see #seleniumEquals(String, String)
	 * @return true if actual matches the expectedPattern, or false otherwise
	 */
	public static boolean seleniumEquals(Object expected, Object actual) {
		if (expected == null) {
			return actual == null;
		}
		if (expected instanceof String && actual instanceof String) {
			return seleniumEquals((String) expected, (String) actual);
		}
		return expected.equals(actual);
	}

	private static String verifyEqualsAndReturnComparisonDumpIfNot(
			String[] expected, String[] actual) {
		boolean misMatch = false;
		if (expected.length != actual.length) {
			misMatch = true;
		}
		for (int j = 0; j < expected.length; j++) {
			if (!seleniumEquals(expected[j], actual[j])) {
				misMatch = true;
				break;
			}
		}
		if (misMatch) {
			return "Expected: " + stringArrayToString(expected)
					+ " but actual: " + stringArrayToString(actual);
		}
		return null;
	}

	private static String stringArrayToString(String[] sa) {
		StringBuilder sb = new StringBuilder("{");
		for (int j = 0; j < sa.length; j++) {
			sb.append(" ").append("\"").append(sa[j]).append("\"");
		}
		sb.append(" }");
		return sb.toString();
	}

	protected String getInputData(String key) {
		return testData.getInputData(key);
	}

	protected String getOutputData(String key) {
		return testData.getOutputData(key);
	}

	public void setTestData(TestData testData) {
		this.testData = testData;
	}
}