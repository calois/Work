package com.nasdaqomx.test.selenium.testcase.ldapconfig.loginout;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.anno.TestBefore;
import com.nasdaqomx.test.selenium.page.ldapconfig.LoginPage;

public class LoginTest extends AbstractTest {

	private LoginPage loginPage;

	@TestBefore
	public void before() {
		loginPage = createPageObject(LoginPage.class);
	}

	public void testLoginSuccess() {
		loginPage.typeUserName(getInputData("username"));
		loginPage.typePassword(getInputData("password"));
		loginPage.submitLogin();
	}

	public void testLoginFail() {
		String username = getInputData("username");
		String password = getInputData("password");
		if (null != username) {
			loginPage.typeUserName(username);
		}
		if (null != password) {
			loginPage.typePassword(password);
		}
		loginPage = loginPage.submitLoginExpectingFailure();
		verifyEquals(getOutputData("msg"), loginPage.getInfoMsg());
		loginPage.loginAs(getInputData("usernameAfter"),
				getInputData("passwordAfter"));
	}
}
