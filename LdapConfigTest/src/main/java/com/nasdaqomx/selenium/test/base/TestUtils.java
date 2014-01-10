package com.nasdaqomx.selenium.test.base;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.nasdaqomx.selenium.test.base.anno.TestAfter;
import com.nasdaqomx.selenium.test.base.anno.TestBefore;

public class TestUtils {
	private static final Log LOGGER = LogFactory.getLog(TestUtils.class);

	public static WebDriver getWebDriver(TestConfig testConfig) {
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

	public static String takeScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	public static Properties toProperties(String s) {
		Properties prop = new Properties();
		try (InputStream is = new ByteArrayInputStream(s.getBytes())) {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getStackTrace(Throwable e) {
		if (null == e) {
			return "";
		}
		StringWriter sw = new StringWriter();
		PrintWriter ps = new PrintWriter(sw);
		e.printStackTrace(ps);
		ps.close();
		try {
			sw.close();
		} catch (IOException ie) {
			LOGGER.info("Failed to close StringWriter during getStacktrace", ie);
		}
		return sw.toString();
	}

	public static boolean isEmpty(String s) {
		return null == s || s.length() == 0;
	}

	public static boolean isNumeric(String s) {
		return null != s && s.matches("^[0-9]+$");
	}

	public static boolean isEmailAddress(String s) {
		return null != s
				&& s.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}

	public static String localeToString(Locale l) {
		return l.getLanguage() + "_" + l.getCountry();
	}

	public static Locale stringToLocale(String s) {
		if (null == s) {
			return null;
		}
		StringTokenizer tempStringTokenizer = new StringTokenizer(s, "_");
		String l = "en", c = "";
		if (tempStringTokenizer.hasMoreTokens()) {
			l = (String) tempStringTokenizer.nextElement();
		}
		if (tempStringTokenizer.hasMoreTokens()) {
			c = (String) tempStringTokenizer.nextElement();
		}
		return new Locale(l, c);
	}

	public static String generateFilename(String userAgent, String fileName,
			String suffix) throws UnsupportedEncodingException {
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			String encodedFilename = URLEncoder.encode(fileName, "UTF-8");
			if (userAgent.indexOf("msie") != -1
					|| userAgent.indexOf("mozilla") != -1) {
				return "filename=\"" + encodedFilename + suffix + "\"";
			}
			if (userAgent.indexOf("opera") != -1) {
				return "filename*=UTF-8''" + encodedFilename + suffix;
			}
			if (userAgent.indexOf("safari") != -1
					|| userAgent.indexOf("applewebkit") != -1) {
				return "filename=\"" + fileName + suffix + "\"";
			}
		}
		return "filename=\"" + fileName + suffix + "\"";
	}

	public static String formatDateString(Date date) {
		return formatDateString(date, "dd/MM/yyyy");
	}

	public static String formatDateString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static Method getTestBeforeMethod(Class<? extends AbstractTest> clazz) {
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getAnnotation(TestBefore.class) != null) {
				return method;
			}
		}
		return null;
	}

	public static Method getTestAfterMethod(Class<? extends AbstractTest> clazz) {
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getAnnotation(TestAfter.class) != null) {
				return method;
			}
		}
		return null;
	}
}
