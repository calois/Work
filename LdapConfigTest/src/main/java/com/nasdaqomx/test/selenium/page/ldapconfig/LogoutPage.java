package com.nasdaqomx.test.selenium.page.ldapconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.base.page.AbstractPageObject;

public class LogoutPage extends AbstractPageObject {

	private static final String URL = "logout.view";
	private static final By LOGIN_LINK_LOCATOR = By.partialLinkText("log in again");
	private static final By MESSAGE_LOCATOR = By.xpath("//div[@id='content']/p");

	private WebElement loginLink;

	public LogoutPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			loginLink = findElement(LOGIN_LINK_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public LoginPage toLogin() {
		this.loginLink.click();
		return createPageObject(LoginPage.class);
	}

	public String getMessage() {
		return findElement(MESSAGE_LOCATOR).getText();
	}
}
