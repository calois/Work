package com.nasdaqomx.test.selenium.base.page;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nasdaqomx.test.selenium.base.Project;
import com.nasdaqomx.test.selenium.base.TestException;
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
		return TestUtils.createPageObject(clazz, testManager);
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

	protected WebElement findElement(By by) {
		return getWebDriver().findElement(by);
	}

	protected List<WebElement> findElements(By by) {
		return getWebDriver().findElements(by);
	}

	protected WebElement getElementUntilPresent(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.presenceOfElementLocated(by));
	}

	protected WebElement getElementUntilVisible(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.visibilityOfElementLocated(by));
	}

	protected List<WebElement> getElementsUntilVisible(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	protected List<WebElement> getElementsUntilVisible(List<WebElement> elements) {
		return getWebDriveWait().until(
				ExpectedConditions.visibilityOfAllElements(elements));
	}

	protected boolean isDisplayedAfterWait(WebElement element) {
		return null != getWebDriveWait().until(
				ExpectedConditions.visibilityOf(element));
	}

	protected List<WebElement> getElementsUntilPresent(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

	protected boolean isTextContainedAfterWait(By by, String text) {
		return getWebDriveWait().until(
				ExpectedConditions.textToBePresentInElement(by, text));
	}

	protected boolean isTextContainedAfterWait(WebElement element, String text) {
		return getWebDriveWait().until(textToBePresentInElement(element, text));
	}

	protected boolean isValueContainedAfterWait(By by, String value) {
		return getWebDriveWait().until(
				ExpectedConditions.textToBePresentInElementValue(by, value));
	}

	protected boolean isValueContainedAfterWait(WebElement element, String value) {
		return getWebDriveWait().until(
				textToBePresentInElementValue(element, value));
	}

	protected boolean isDisappearedAfterWait(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.invisibilityOfElementLocated(by));
	}

	protected boolean isTextDisappearedAfterWait(By by, String text) {
		return getWebDriveWait().until(
				ExpectedConditions.invisibilityOfElementWithText(by, text));
	}

	protected WebElement getElementUntilClickable(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.elementToBeClickable(by));
	}

	protected boolean isPresent(By by) {
		try {
			return null != findElement(by);
		} catch (WebDriverException e) {
			return false;
		}
	}

	protected boolean isPresentAfterWait(By by) {
		try {
			return null != getElementUntilPresent(by);
		} catch (WebDriverException e) {
			return false;
		}
	}

	protected boolean isDisplayed(By by) {
		try {
			return findElement(by).isDisplayed();
		} catch (WebDriverException e) {
			return false;
		}
	}

	protected boolean isDisplayedAfterWait(By by) {
		try {
			return null != getElementsUntilVisible(by);
		} catch (WebDriverException e) {
			return false;
		}
	}

	protected boolean isEnabled(By by) {
		try {
			return findElement(by).isEnabled();
		} catch (WebDriverException e) {
			return false;
		}
	}

	protected boolean isSelected(By by) {
		try {
			return findElement(by).isSelected();
		} catch (WebDriverException e) {
			return false;
		}
	}

	protected boolean isSelectedAfterWait(WebElement element) {
		return getWebDriveWait().until(
				ExpectedConditions.elementToBeSelected(element));
	}

	protected boolean isSelectedAfterWait(By by) {
		return getWebDriveWait().until(
				ExpectedConditions.elementToBeSelected(by));
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
			fail(String
					.format("The URL should start with: ('%s'), but the actual result is:  ('%s')",
							expectUrl, actualUrl));
		}
	}

	protected void fail(String message) {
		testManager.takeScreenshot(getProject());
		throw new TestException(message);
	}

	private ExpectedCondition<Boolean> textToBePresentInElement(
			final WebElement element, final String text) {

		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					String elementText = element.getText();
					return elementText.contains(text);
				} catch (StaleElementReferenceException e) {
					return null;
				}
			}

			@Override
			public String toString() {
				return String.format(
						"text ('%s') to be present in element ('%s')", text,
						element);
			}
		};
	}

	private ExpectedCondition<Boolean> textToBePresentInElementValue(
			final WebElement element, final String text) {

		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					String elementText = element.getAttribute("value");
					if (elementText != null) {
						return elementText.contains(text);
					} else {
						return false;
					}
				} catch (StaleElementReferenceException e) {
					return null;
				}
			}

			@Override
			public String toString() {
				return String
						.format("text ('%s') to be the value of element element ('%s')",
								text, element);
			}
		};
	}
}
