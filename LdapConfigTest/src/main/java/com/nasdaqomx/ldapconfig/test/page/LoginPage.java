package com.nasdaqomx.ldapconfig.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.ldapconfig.test.base.AbstractTest;
import com.nasdaqomx.ldapconfig.test.base.TestObject;
import com.nasdaqomx.ldapconfig.test.base.page.AbstractPageObject;

public class LoginPage extends AbstractPageObject {

	/*
	 * <div id="header"> <img id="productlogo"
	 * src="/ldapconfig/images/ProductLogo.png" alt="LDAP Config"> </div>
	 */

	// <input type="text" name="j_username">
	private final String USER_NAME_LOCATOR = "j_username";
	// <input type="password" name="j_password">
	private final String PASSWORD_LOCATOR = "j_password";
	// <input type="submit" value="Login">
	private final String LOGIN_LOCATOR = "//input[@type='submit']";
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
		load("/");
		try {
			userName = getBy(By.name(USER_NAME_LOCATOR));
			password = getBy(By.name(PASSWORD_LOCATOR));
			loginButton = getBy(By.xpath(LOGIN_LOCATOR));
			forgottenPassword = getBy(By
					.partialLinkText(FORGOTTEN_PASSWORD_LOCATOR));
		} catch (NoSuchElementException e) {
			AbstractTest.fail(e.getMessage());
		}
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
	
	public void clickLogin(){
		this.loginButton.click();
	}
	
	public void clickForgottenPassword(){
		this.forgottenPassword.click();
	}
	
	public void login(String name, String pwd){
		typeUserName(name);
		typePassword(pwd);
		clickLogin();
	}
}
