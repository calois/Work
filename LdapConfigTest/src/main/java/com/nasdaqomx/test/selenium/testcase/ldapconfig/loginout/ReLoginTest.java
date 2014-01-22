package com.nasdaqomx.test.selenium.testcase.ldapconfig.loginout;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.anno.TestBefore;
import com.nasdaqomx.test.selenium.page.ldapconfig.LoginPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.LogoutPage;

public class ReLoginTest extends AbstractTest {

	private LogoutPage logoutPage;

	@TestBefore
	public void before() {
		logoutPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"))
				.logout();
	}

	public void testLoginLink() {
		logoutPage.loginAgain().loginAs(getInputData("username"),
				getInputData("password"));
	}
}
