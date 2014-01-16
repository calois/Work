package com.nasdaqomx.test.selenium.base.page;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nasdaqomx.test.selenium.base.Project;
import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.base.TestUtils;

public abstract class AbstractPageObject {
	private static final Log LOGGER = LogFactory
			.getLog(AbstractPageObject.class);

	private TestManager testManager;

	public AbstractPageObject(TestManager testManager) {
		this.testManager = testManager;
	}

	public Project getProject() {
		return TestUtils.getTestProject(this.getClass());
	}

	protected <T extends AbstractPageObject> T createPageObject(Class<T> clazz) {
		try {
			T o = clazz.getDeclaredConstructor(TestManager.class).newInstance(
					testManager);
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
		getWebDriver().get(testManager.getBaseUrl(getProject()) + url);
	}

	protected void load() {
		getWebDriver().get(testManager.getBaseUrl(getProject()));
	}

	protected WebDriver getWebDriver() {
		return testManager.getWebDriver(getProject());
	}

	protected WebDriverWait getWebDriveWait() {
		return new WebDriverWait(getWebDriver(), testManager.getExplicitWait());
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
