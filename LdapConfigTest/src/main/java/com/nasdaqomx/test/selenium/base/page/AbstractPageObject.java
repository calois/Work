package com.nasdaqomx.test.selenium.base.page;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
	protected boolean isDisappeared(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.invisibilityOfElementLocated(by));
	}

	protected boolean isPresent(final By by) {
		try {
			return getWebDriveWait().until(visibilityOfElementLocated(by));
		} catch (TimeoutException e) {
			return false;
		}
	}

	private ExpectedCondition<Boolean> visibilityOfElementLocated(
			final By locator) {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return findElement(locator, driver).isDisplayed();
				} catch (NoSuchElementException e) {
					return false;
				} catch (StaleElementReferenceException e) {
					return false;
				}
			}

			public String toString() {
				return "element is visible: " + locator;
			}
		};
	}

	private WebElement findElement(By by, WebDriver driver) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			throw e;
		} catch (WebDriverException e) {
			LOGGER.warn(String.format(
					"WebDriverException thrown by findElement(%s)", by), e);
			throw e;
		}
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

	protected void assertUrl(String expectUrl, String actualUrl) {
		if (!actualUrl.startsWith(expectUrl)) {
			fail("Expected URL Starts with: \"" + expectUrl
					+ "\" but actual: \"" + actualUrl);
		}
	}

	protected void fail(String message) {
		throw new RuntimeException(message);
	}
}
