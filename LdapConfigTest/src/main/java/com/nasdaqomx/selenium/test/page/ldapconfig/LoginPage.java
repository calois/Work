package com.nasdaqomx.selenium.test.page.ldapconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.selenium.test.base.TestObject;
import com.nasdaqomx.selenium.test.base.page.AbstractPageObject;
import com.nasdaqomx.selenium.test.page.ldapconfig.clients.ListClients;

public class LoginPage extends AbstractPageObject {

	/*
	 * <div id="header"> <img id="productlogo"
	 * src="/ldapconfig/images/ProductLogo.png" alt="LDAP Config"> </div>
	 */

	private static final String URL = "login.jsp";
	// <input type="text" name="j_username">
	private static final String USER_NAME_LOCATOR = "j_username";
	// <input type="password" name="j_password">
	private static final String PASSWORD_LOCATOR = "j_password";
	// <input type="submit" value="Login">
	private static final String LOGIN_LOCATOR = "//input[@type='submit']";
	private static final String INFO_MSG_LOCATOR = "infomessage";
	// <a href="forgottenPassword.view">Forgotten your password?</a>
	private final String FORGOTTEN_PASSWORD_LOCATOR = "Forgotten your password";

	private WebElement userName;
	private WebElement password;
	private WebElement loginButton;
	private WebElement forgottenPassword;

	/*
	 * <div id="LdapFooter"> <div id="ProductInfo"> <ul>
	 * <li><span>Email:</span><a
	 * href="mailto:smbc-support@nasdaqomx.com">smbc-support
	 * 
	 * @nasdaqomx.com</a></li> <li><span>Phone:</span>+61 2 8083 9000</li> <li>©
	 * 2013, The <a href="http://www.nasdaqomx.com" target="_blank">NASDAQ
	 * OMX</a> Group, Inc. All Rights Reserved.</li> </ul> </div> </div>
	 */
	public LoginPage(TestObject testObject) {
		super(testObject);
		if (!this.getCurrentUrl().contains(URL)) {
			load();
		}
		if (!this.getSimpleUrl().startsWith(URL)) {
			fail("Expected URL Starts with: \"" + URL + "\" but actual: \""
					+ this.getSimpleUrl());
		}
		try {
			userName = getElement(By.name(USER_NAME_LOCATOR));
			password = getElement(By.name(PASSWORD_LOCATOR));
			loginButton = getElement(By.xpath(LOGIN_LOCATOR));
			forgottenPassword = getElement(By
					.partialLinkText(FORGOTTEN_PASSWORD_LOCATOR));

		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getInfoMsg() {
		return getElement(By.id(INFO_MSG_LOCATOR)).getText();
	}

	public void typeUserName(String name) {
		this.userName.sendKeys(name);
	}

	public String getUserName() {
		return this.userName.getText();
	}

	public void typePassword(String password) {
		this.password.sendKeys(password);
	}

	public String getPassword() {
		return this.password.getText();
	}

	public ListClients submitLogin() {
		this.loginButton.click();
		return createPageObject(ListClients.class);
	}

	public LoginPage submitLoginExpectingFailure() {
		this.loginButton.click();
		return createPageObject(LoginPage.class);
	}

	public void clickForgottenPassword() {
		this.forgottenPassword.click();
	}

	public ListClients loginAs(String name, String pwd) {
		typeUserName(name);
		typePassword(pwd);
		submitLogin();
		return createPageObject(ListClients.class);
	}
}
