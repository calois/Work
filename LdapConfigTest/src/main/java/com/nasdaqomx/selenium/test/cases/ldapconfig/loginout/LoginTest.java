package com.nasdaqomx.selenium.test.cases.ldapconfig.loginout;

import com.nasdaqomx.selenium.test.base.AbstractTest;
import com.nasdaqomx.selenium.test.base.anno.TestBefore;
import com.nasdaqomx.selenium.test.page.ldapconfig.LoginPage;

public class LoginTest extends AbstractTest {

	private LoginPage loginPage;

	@TestBefore
	public void before() {
		loginPage = createPageObject(LoginPage.class);
		assertEquals(getOutputData("loginUrl"), loginPage.getUrl());
	}

	public void testLoginSuccess() {
		verifyEquals(
				getOutputData("defaultUrl"),
				loginPage.loginAs(getInputData("username"),
						getInputData("password")).getUrl());
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
		verifyEquals(getOutputData("msg"), loginPage
				.submitLoginExpectingFailure().getInfoMsg());
	}
}
