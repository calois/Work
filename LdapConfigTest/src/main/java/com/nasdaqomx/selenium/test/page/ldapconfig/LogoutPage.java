package com.nasdaqomx.selenium.test.page.ldapconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.selenium.test.base.AbstractTest;
import com.nasdaqomx.selenium.test.base.TestManager;

public class LogoutPage extends LdapConfigBasePage {

	private static final String URL = "logout.view";
	// <a href="index.view">Click here to log in again.</a>
	private static final String LOGIN_LINK_LOCATOR = "log in again";
	private static final String MESSAGE_LOCATOR = "//div[@id='content']/p";

	private WebElement loginLink;

	public LogoutPage(TestManager testManager) {
		super(testManager);
		AbstractTest.assertEquals(getTestApp(), URL, this.getSimpleUrl());
		try {
			loginLink = getElement(By.partialLinkText(LOGIN_LINK_LOCATOR));
		} catch (NoSuchElementException e) {
			AbstractTest.fail(getTestApp(), e.getMessage());
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
