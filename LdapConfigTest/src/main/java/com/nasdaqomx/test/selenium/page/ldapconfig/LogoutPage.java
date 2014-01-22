package com.nasdaqomx.test.selenium.page.ldapconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.base.page.AbstractPageObject;

public class LogoutPage extends AbstractPageObject {

	private static final String URL = "logout.view";
	// <a href="index.view">Click here to log in again.</a>
	private static final String LOGIN_LINK_LOCATOR = "log in again";
	private static final String MESSAGE_LOCATOR = "//div[@id='content']/p";

	private WebElement loginLink;

	public LogoutPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl(), false);
		try {
			loginLink = getElement(By.partialLinkText(LOGIN_LINK_LOCATOR));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public LoginPage clickLoginLink() {
		this.loginLink.click();
		return createPageObject(LoginPage.class);
	}

	public String getMessage() {
		return getElement(By.xpath(MESSAGE_LOCATOR)).getText();
	}
}
