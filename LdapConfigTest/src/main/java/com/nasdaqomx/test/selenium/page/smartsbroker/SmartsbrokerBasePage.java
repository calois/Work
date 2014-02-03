package com.nasdaqomx.test.selenium.page.smartsbroker;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.base.page.AbstractPageObject;

public class SmartsbrokerBasePage extends AbstractPageObject {

	private static final By TITLE_LOCATOR = By.id("PageTitle");
	private static final By NAVIGATION_LOCATOR = By.id("Navigation");
	private static final By ACTIVE_LOCATOR = By.className("active");
	private static final By PROGRAM_START_LOCATOR = By.linkText("cheese");
	private static final By PASSWORD_LOCATOR = By.linkText("Password");
	private static final By REPORTS_LOCATOR = By.linkText("Reports");
	private static final By USERS_LOCATOR = By.linkText("Users");
	private static final By ALERTS_LOCATOR = By.linkText("Alerts");
	private static final By ADMIN_LOCATOR = By.linkText("Admin");
	private static final By lOGOUT_LOCATOR = By.linkText("Logout");

	private WebElement logout;
	private WebElement navigation;

	public SmartsbrokerBasePage(TestManager testManager) {
		super(testManager);
		try {
			navigation = findElement(NAVIGATION_LOCATOR);
			logout = navigation.findElement(lOGOUT_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public void toProgramStart() {
		navigation.findElement(PROGRAM_START_LOCATOR).click();
	}

	public void toPassword() {
		navigation.findElement(PASSWORD_LOCATOR).click();
	}

	public void toReports() {
		navigation.findElement(REPORTS_LOCATOR).click();
	}

	public void toUsers() {
		navigation.findElement(USERS_LOCATOR).click();
	}

	public void toAlerts() {
		navigation.findElement(ALERTS_LOCATOR).click();
	}

	public void toAdmin() {
		navigation.findElement(ADMIN_LOCATOR).click();
	}

	public LogoutPage toLogout() {
		this.logout.click();
		return createPageObject(LogoutPage.class);
	}

	public String getPageTitle() {
		return findElement(TITLE_LOCATOR).getText();
	}

	public String getActiveNavigation() {
		return navigation.findElement(ACTIVE_LOCATOR).getText();
	}
}
