package com.nasdaqomx.selenium.test.cases.ldapconfig.loginout;

import com.nasdaqomx.selenium.test.base.anno.TestBefore;
import com.nasdaqomx.selenium.test.cases.ldapconfig.LdapConfigBaseTest;
import com.nasdaqomx.selenium.test.page.ldapconfig.LoginPage;

public class LoginTest extends LdapConfigBaseTest {

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
