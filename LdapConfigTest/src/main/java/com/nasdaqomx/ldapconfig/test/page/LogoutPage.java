package com.nasdaqomx.ldapconfig.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.ldapconfig.test.base.AbstractTest;
import com.nasdaqomx.ldapconfig.test.base.TestObject;
import com.nasdaqomx.ldapconfig.test.base.page.AbstractPageObject;

public class LogoutPage extends AbstractPageObject {

	// <a href="index.view">Click here to log in again.</a>
	private final String LOGIN_LINK_LOCATOR = "log in again";
	private final String MESSAGE_LOCATOR = "//div[@id='content']/p";

	private WebElement loginLink;

	public LogoutPage(TestObject testObject) {
		super(testObject);
		try {
			loginLink = getBy(By.partialLinkText(LOGIN_LINK_LOCATOR));
		} catch (NoSuchElementException e) {
			AbstractTest.fail(e.getMessage());
		}
	}

	public LoginPage clickLoginLink() {
		this.loginLink.click();
		return createPageObject(LoginPage.class);
	}

	public String getMessage() {
		return getBy(By.xpath(MESSAGE_LOCATOR)).getText();
	}
}
