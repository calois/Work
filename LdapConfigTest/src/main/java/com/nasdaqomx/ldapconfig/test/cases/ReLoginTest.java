package com.nasdaqomx.ldapconfig.test.cases;

import com.nasdaqomx.ldapconfig.test.base.AbstractTest;
import com.nasdaqomx.ldapconfig.test.base.anno.TestAfter;
import com.nasdaqomx.ldapconfig.test.base.anno.TestBefore;
import com.nasdaqomx.ldapconfig.test.page.LoginPage;
import com.nasdaqomx.ldapconfig.test.page.LogoutPage;

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
