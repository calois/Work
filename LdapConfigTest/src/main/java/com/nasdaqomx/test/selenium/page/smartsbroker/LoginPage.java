package com.nasdaqomx.test.selenium.page.smartsbroker;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.base.page.AbstractPageObject;

public class LoginPage extends AbstractPageObject {

	private static final String URL = "login.jsp";
	private static final By USER_NAME_LOCATOR = By.name("j_username");
	private static final By PASSWORD_LOCATOR = By.name("j_password");
	private static final By LOGIN_LOCATOR = By.xpath("//input[@value='Login']");
	private static final By INFO_MSG_LOCATOR = By.id("infomessage");
	private final By FORGOTTEN_PASSWORD_LOCATOR = By
			.partialLinkText("Forgotten your password");

	private WebElement userName;
	private WebElement password;
	private WebElement loginButton;
	private WebElement forgottenPassword;

	public LoginPage(TestManager testManager) {
		super(testManager);
		if (!this.getCurrentUrl().contains(URL)) {
			load();
		}
		assertUrl(URL, this.getSimpleUrl());
		try {
			userName = findElement(USER_NAME_LOCATOR);
			password = findElement(PASSWORD_LOCATOR);
			loginButton = findElement(LOGIN_LOCATOR);
			forgottenPassword = findElement(FORGOTTEN_PASSWORD_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getInfoMsg() {
		return findElement(INFO_MSG_LOCATOR).getText();
	}

	public void typeUserName(String value) {
		if (null != value) {
			this.userName.sendKeys(value);
		}
	}

	public String getUserName() {
		return this.userName.getText();
	}

	public void typePassword(String value) {
		if (null != value) {
			this.password.sendKeys(value);
		}
	}

	public String getPassword() {
		return this.password.getText();
	}

	public SmartsbrokerBasePage submitLogin() {
		this.loginButton.click();
		return createPageObject(SmartsbrokerBasePage.class);
	}

	public LoginPage submitLoginExpectingFailure() {
		this.loginButton.click();
		return createPageObject(LoginPage.class);
	}

	public void toForgottenPassword() {
		this.forgottenPassword.click();
	}

	public SmartsbrokerBasePage loginAs(String name, String pwd) {
		typeUserName(name);
		typePassword(pwd);
		return submitLogin();
	}
}
