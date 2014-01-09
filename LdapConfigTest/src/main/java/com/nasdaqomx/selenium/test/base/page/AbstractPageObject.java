package com.nasdaqomx.selenium.test.base.page;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nasdaqomx.selenium.test.base.TestObject;

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
			throw new RuntimeException("Fail to create page object: "
					+ clazz.getSimpleName(), e);
		}
	}
	
	protected String getCurrentUrl() {
		return getWebDriver().getCurrentUrl();
	}

	protected String getSimpleUrl() {
		return getCurrentUrl().substring(getCurrentUrl().lastIndexOf("/") + 1);
	}

	protected String getTitle() {
		return getWebDriver().getTitle();
	}

	protected void load(String url) {
		getWebDriver().get(testObject.getBaseUrl() + url);
	}

	protected void load() {
		getWebDriver().get(testObject.getBaseUrl());
	}

	protected WebDriver getWebDriver() {
		return testObject.getWebDriver();
	}

	protected WebDriverWait getWebDriveWait() {
		return new WebDriverWait(getWebDriver(), testObject.getExplicitWait());
	}

	protected WebElement getElement(By by) {
		return getWebDriver().findElement(by);
	}

	protected List<WebElement> getElements(By by) {
		return getWebDriver().findElements(by);
	}

	protected WebElement getElementUntilClickable(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.elementToBeClickable(by));
	}

	protected WebElement getElementUntilVisible(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.visibilityOfElementLocated(by));
	}

	protected WebElement getElementUntilPresent(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.presenceOfElementLocated(by));
	}

	protected List<WebElement> getElementsUntilVisible(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	protected List<WebElement> getElementsUntilPresent(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

	protected boolean isPresent(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.invisibilityOfElementLocated(by));
	}

	protected void doubleClick(final By by) {
		new Actions(getWebDriver()).doubleClick(getElementUntilClickable(by))
				.build().perform();
	}

	protected void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
	}

	protected void fail(String message) {
		throw new RuntimeException(message);
	}
}
