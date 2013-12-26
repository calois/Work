package com.nasdaqomx.ldapconfig.test.base.page;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nasdaqomx.ldapconfig.test.base.TestException;
import com.nasdaqomx.ldapconfig.test.base.TestObject;

public abstract class AbstractPageObject {
	private static final Log LOGGER = LogFactory
			.getLog(AbstractPageObject.class);

	private TestObject testObject;

	public AbstractPageObject(TestObject testObject) {
		this.testObject = testObject;
	}

	protected <T extends AbstractPageObject> T createPageObject(Class<T> clazz) {
		try {
			T o = clazz.getDeclaredConstructor(TestObject.class).newInstance(
					testObject);
			return o;
		} catch (Exception e) {
			throw new TestException("Fail to create page object", e);
		}
	}

	protected String getCurrentUrl() {
		return getDriver().getCurrentUrl();
	}

	protected String getTitle() {
		return getDriver().getTitle();
	}

	protected void load(String url) {
		getDriver().get(testObject.getBaseUrl() + url);
	}

	protected WebDriver getDriver() {
		return testObject.getWebDriver();
	}

	protected WebElement getBy(By by) {
		return getDriver().findElement(by);
	}

	protected List<WebElement> getListBy(By by) {
		return getDriver().findElements(by);
	}

	protected boolean isPresentBy(By by) {
		try {
			getDriver()
					.manage()
					.timeouts()
					.implicitlyWait(testObject.getPresentWait(),
							TimeUnit.SECONDS);
			return !getDriver().findElements(by).isEmpty();
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			getDriver()
					.manage()
					.timeouts()
					.implicitlyWait(testObject.getDefaultWait(),
							TimeUnit.SECONDS);
		}
	}

	protected void doubleClickBy(final By by) {
		(new WebDriverWait(getDriver(), 10))
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						try {
							d.findElement(by).click();
							return true;
						} catch (Throwable e) {
							return false;
						}
					}
				});
		new Actions(getDriver()).doubleClick(getBy(by)).build().perform();
	}

	protected void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
	}
}
