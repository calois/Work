package com.nasdaqomx.selenium.test.cases.ldapconfig.loginout;

import com.nasdaqomx.selenium.test.base.AbstractTest;
import com.nasdaqomx.selenium.test.base.anno.TestAfter;
import com.nasdaqomx.selenium.test.base.anno.TestBefore;
import com.nasdaqomx.selenium.test.page.ldapconfig.LoginPage;
import com.nasdaqomx.selenium.test.page.ldapconfig.LogoutPage;

public class ReLoginTest extends AbstractTest {

	private LogoutPage logoutPage;

	@TestBefore
	public void before() {
		logoutPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"))
				.clickLogout();
		assertEquals(getOutputData("logoutUrl"), logoutPage.getUrl());
	}

	@TestAfter
	public void after() {
	}

	public void testLoginLink() {
		LoginPage loginPage = logoutPage.clickLoginLink();
		verifyEquals(getOutputData("loginUrl"), loginPage.getUrl());
	}
}
