package com.nasdaqomx.test.selenium.page.ldapconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.base.page.AbstractPageObject;

public class LoginPage extends AbstractPageObject {

	/*
	 * <div id="header"> <img id="productlogo"
	 * src="/ldapconfig/images/ProductLogo.png" alt="LDAP Config"> </div>
	 */

	private static final String URL = "login.jsp";
	private static final String USER_NAME_NAME = "j_username";
	private static final String PASSWORD_NAME = "j_password";
	private static final String LOGIN_XPATH = "//input[@value='Login']";
	private static final String INFO_MSG_ID = "infomessage";
	private final String FORGOTTEN_PASSWORD_PARTIALLINKTEXT = "Forgotten your password";

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
	public LoginPage(TestManager testManager) {
		super(testManager);
		if (!this.getCurrentUrl().contains(URL)) {
			load();
		}
		assertUrl(URL, this.getSimpleUrl());
		try {
			userName = getElement(By.name(USER_NAME_NAME));
			password = getElement(By.name(PASSWORD_NAME));
			loginButton = getElement(By.xpath(LOGIN_XPATH));
			forgottenPassword = getElement(By
					.partialLinkText(FORGOTTEN_PASSWORD_PARTIALLINKTEXT));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getInfoMsg() {
		return getElement(By.id(INFO_MSG_ID)).getText();
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

	public LdapconfigBasePage submitLogin() {
		this.loginButton.click();
		return createPageObject(LdapconfigBasePage.class);
	}

	public LoginPage submitLoginExpectingFailure() {
		this.loginButton.click();
		return createPageObject(LoginPage.class);
	}

	public void toForgottenPassword() {
		this.forgottenPassword.click();
	}

	public LdapconfigBasePage loginAs(String name, String pwd) {
		typeUserName(name);
		typePassword(pwd);
		return submitLogin();
	}
}
