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
		loginPage.typeUserName(getInputData("username"));
		loginPage.typePassword(getInputData("password"));
		LoginPage loginAfterFailurePage = loginPage
				.submitLoginExpectingFailure();
		verifyEquals(getOutputData("msg"), loginAfterFailurePage.getInfoMsg());
		loginAfterFailurePage.loginAs(getInputData("usernameAfter"),
				getInputData("passwordAfter"));
	}
}
